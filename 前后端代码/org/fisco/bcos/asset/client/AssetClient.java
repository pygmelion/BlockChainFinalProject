package org.fisco.bcos.asset.client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.math.BigInteger;
import java.util.Properties;
import java.io.IOException;

import org.fisco.bcos.asset.contract.Asset;
import org.fisco.bcos.asset.contract.Asset.RegisterEventEventResponse;
import org.fisco.bcos.asset.contract.Asset.TransferEventEventResponse;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class AssetClient {

	static Logger logger = LoggerFactory.getLogger(AssetClient.class);

	private Web3j web3j;

	private Credentials credentials;

	public Web3j getWeb3j() {
		return web3j;
	}

	public void setWeb3j(Web3j web3j) {
		this.web3j = web3j;
	}

	public String loadAssetAddr() throws Exception {
		Properties prop = new Properties();
		final Resource contractResource = new ClassPathResource("contract.properties");
		prop.load(contractResource.getInputStream());

		String contractAddress = prop.getProperty("address");
		if (contractAddress == null || contractAddress.trim().equals("")) {
			throw new Exception(" load Asset contract address failed, please deploy it first. ");
		}
		logger.info(" load Asset address from contract.properties, address is {}", contractAddress);
		return contractAddress;
	}
	private void uploadSingleFile(Config config, File file, Stopwatch stopwatch, WebElement newInput) throws IOException {
	do {
		try {
		newInput.sendKeys(file.getCanonicalPath());
		return;
		}
		catch (ElementNotInteractableException notInteractable) {
		if (stopwatch.isTimeoutReached()) {
			throw notInteractable;
		}
		stopwatch.sleep(config.pollingInterval());
		}
	} while (!stopwatch.isTimeoutReached());
	}

	protected File uploadFile(Driver driver, WebElement inputField, File file) throws IOException {
	if (!"input".equalsIgnoreCase(inputField.getTagName())) {
		throw new IllegalArgumentException("Cannot upload file because " + Describe.describe(driver, inputField) + " is not an INPUT");
	}

	if (!file.exists()) {
		throw new IllegalArgumentException("File not found: " + file.getAbsolutePath());
	}

	String canonicalPath = file.getCanonicalPath();
	inputField.sendKeys(canonicalPath);
	return new File(canonicalPath);
	}
	public void initialize() throws Exception {

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Service service = context.getBean(Service.class);
		service.run();

		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		channelEthereumService.setChannelService(service);
		Web3j web3j = Web3j.build(channelEthereumService, 1);

		Credentials credentials = Credentials.create(Keys.createEcKeyPair());

		setCredentials(credentials);
		setWeb3j(web3j);

		logger.debug(" web3j is " + web3j + " ,credentials is " + credentials);
	}

	public void queryAssetAmount(String assetAccount) {
		try {
			String contractAddress = loadAssetAddr();

			Asset asset = Asset.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			Tuple2<BigInteger, BigInteger> result = asset.select(assetAccount).send();
			if (result.getValue1().compareTo(new BigInteger("0")) == 0) {
				System.out.printf(" asset account %s, value %s \n", assetAccount, result.getValue2());
			} else {
				System.out.printf(" %s asset account is not exist \n", assetAccount);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error(" queryAssetAmount exception, error message is {}", e.getMessage());

			System.out.printf(" query asset account failed, error message is %s\n", e.getMessage());
		}
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	private static BigInteger gasPrice = new BigInteger("30000000");
	private static BigInteger gasLimit = new BigInteger("30000000");

	public void deployAssetAndRecordAddr() {

		try {
			Asset asset = Asset.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
			System.out.println(" deploy Asset success, contract address is " + asset.getContractAddress());

			recordAssetAddr(asset.getContractAddress());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(" deploy Asset contract failed, error message is  " + e.getMessage());
		}
	}
	protected void waitUntil(CollectionCondition condition, long timeoutMs) {
	Exception lastError = null;
	List<WebElement> actualElements = null;
	Stopwatch stopwatch = new Stopwatch(timeoutMs);
	do {
		try {
		actualElements = collection.getElements();
		if (condition.apply(actualElements)) {
			return;
		}
		}
		catch (JavascriptException e) {
		throw e;
		}
		catch (WebDriverException elementNotFound) {
		lastError = elementNotFound;

		if (Cleanup.of.isInvalidSelectorError(elementNotFound)) {
			throw Cleanup.of.wrap(elementNotFound);
		}
		}
		catch (IndexOutOfBoundsException outOfCollection) {
		if (condition.applyNull()) {
			return;
		}

		throw outOfCollection;
		}
		sleep(driver().config().pollingInterval());
	}
	while (!stopwatch.isTimeoutReached());
	condition.fail(collection, actualElements, lastError, timeoutMs);
	}

	void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		}
	}
	private static Error wrapThrowable(Driver driver, Throwable error, long timeoutMs) {
	UIAssertionError uiError = error instanceof UIAssertionError ?
		(UIAssertionError) error : wrapToUIAssertionError(driver, error);
	uiError.timeoutMs = timeoutMs;
	uiError.screenshot = ScreenShotLaboratory.getInstance().formatScreenShotPath(driver);
	return uiError;
	}

	private static UIAssertionError wrapToUIAssertionError(Driver driver, Throwable error) {
	String message = error.getClass().getSimpleName() + ": " + Cleanup.of.webdriverExceptionMessage(error.getMessage());
	return new UIAssertionError(driver, message, error);
	}
	public void transferAsset(String fromAssetAccount, String toAssetAccount, BigInteger amount) {
		try {
			String contractAddress = loadAssetAddr();
			Asset asset = Asset.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = asset.transfer(fromAssetAccount, toAssetAccount, amount).send();
			List<TransferEventEventResponse> response = asset.getTransferEventEvents(receipt);
			if (!response.isEmpty()) {
				if (response.get(0).ret.compareTo(new BigInteger("0")) == 0) {
					System.out.printf(" transfer success => from_asset: %s, to_asset: %s, amount: %s \n",
							fromAssetAccount, toAssetAccount, amount);
				} else {
					System.out.printf(" transfer asset account failed, ret code is %s \n",
							response.get(0).ret.toString());
				}
			} else {
				System.out.println(" event log not found, maybe transaction not exec. ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			logger.error(" registerAssetAccount exception, error message is {}", e.getMessage());
			System.out.printf(" register asset account failed, error message is %s\n", e.getMessage());
		}
	}

	public static void Usage() {
		System.out.println(" Usage:");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.asset.client.AssetClient deploy");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.fisco.bcos.asset.client.AssetClient query account");
		System.out.println(
				"\t java -cp conf/:lib/*:apps/* org.fisco.bcos.asset.client.AssetClient register account value");
		System.out.println(
				"\t java -cp conf/:lib/*:apps/* org.fisco.bcos.asset.client.AssetClient transfer from_account to_account amount");
		System.exit(0);
	}

	public static void main(String[] args) throws Exception {

		if (args.length < 1) {
			Usage();
		}

		AssetClient client = new AssetClient();
		client.initialize();

		switch (args[0]) {
		case "deploy":
			client.deployAssetAndRecordAddr();
			break;
		case "query":
			if (args.length < 2) {
				Usage();
			}
			client.queryAssetAmount(args[1]);
			break;
		case "register":
			if (args.length < 3) {
				Usage();
			}
			client.registerAssetAccount(args[1], new BigInteger(args[2]));
			break;
		case "transfer":
			if (args.length < 4) {
				Usage();
			}
			client.transferAsset(args[1], args[2], new BigInteger(args[3]));
			break;
		default: {
			Usage();
		}
		}

		System.exit(0);
	}


	public void recordAssetAddr(String address) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.setProperty("address", address);
		final Resource contractResource = new ClassPathResource("contract.properties");
		FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
		prop.store(fileOutputStream, "contract address");
	}

	public void registerAssetAccount(String assetAccount, BigInteger amount) {
		try {
			String contractAddress = loadAssetAddr();

			Asset asset = Asset.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = asset.register(assetAccount, amount).send();
			List<RegisterEventEventResponse> response = asset.getRegisterEventEvents(receipt);
			if (!response.isEmpty()) {
				if (response.get(0).ret.compareTo(new BigInteger("0")) == 0) {
					System.out.printf(" register asset account success => asset: %s, value: %s \n", assetAccount,
							amount);
				} else {
					System.out.printf(" register asset account failed, ret code is %s \n",
							response.get(0).ret.toString());
				}
			} else {
				System.out.println(" event log not found, maybe transaction not exec. ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			logger.error(" registerAssetAccount exception, error message is {}", e.getMessage());
			System.out.printf(" register asset account failed, error message is %s\n", e.getMessage());
		}
	}


}
