package org.fisco.bcos.asset.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class Bank extends Contract {
    public static String BINARY = "060012f6f025ffff908f1b565063f08f035691f0f0fffff01f2fa65f5f0ff0ff87ff007f0818024f9f70ff20ff9054102806f5610fff025f545fff8ff5f30036053ffff20ff1f0014f550903200223106b800125716179009afc0f5f91003fff0013fb516f8099f89006830900009ff02ff5f79302ff1f607bff01f8a1604856bf83406f160ff40f6f89fe21806b9cf01ff0f10b90ff62d9f1f0598f0fa18ff1f0fff116807f55f9715f1591f7f1400ff60f000119ff503056050f5f120fff05f6f90cf6fff1f78060172402f18f5ff1582058061d0076ff3f633f9f0810448009805f205bff9ff1f09f5f9fff0f8b045f107091f05808b55df06903f9085ff0f1f56060992ff101647ff7910ff15265109093509fc07986dbc1670a1fbfff08fff03fff98f1ff560f61f533128f908ff611903f0f8b5f0106f01f658886f2010d0f080611807f28f3f2ff9619fff06172f1ff01ff1f0080f12519f1008c0f841093c193f16fe00801ffbf25f008680505ffe10f010fff5078f51f15043f051f1100fb28f20c05600005ffd1175f126f165d5f56f1ff48ff000f00151163f00430f0fa0536f55005f0f31ff09ff25f8ff1f00305f05f5652190f4f82400ff5faff0f06c10f606f5100701f110280f790ff0ff8f3ff151261ff08ff7ff60100825608311870f10f81f40f59180f2d07f823026f0610fe059107fa6f5f5660ffffff02233923f050086d08f63080f957180f4f0f590208bffff02f80f11212ef0080014cf3f0000ff1510005f09159f1513801051f6528f5061991ff70f935fbff825f9f5ff66f3f50500ff8f2855ff2c9ff781072523102ff353890fb049f12258111f1f75ff169af1f860f681134e4097f8619112121f0ff8f0f032f786050111f0ff29580110f38f1fff25f805ff0f205f9f3b67ff51f0f80f2f500501f5392ffaffff0f7fff1f3f282180799f01f00c0ff66fdd00f535100f085889b000f55f6cf35001b5605fff015f081f039f82f72f03f62018317f51539018ff8f0fd06100f195f07101618a36f6915ff6269f808f0757410f5b90758f0f815f20527fff3ff8f001f309900ffff1f3022958f16fff233050f66ff0af51b67f6550ff50901920ff8f2ff086900f0ff86f870f600f0f881ff2061f10f8601200138fff032066f8f093ff50f001f186f00160f05bc9520510039b66fff0f14051520178f00013ffff10ff6fffff9f8500f50f639fff1e692503ef8a696f01f0fff070108f0f58830f2fff8f084f8755ff1ffffff89f15ff2f00631f205855f50056f529f000060701ff69ff3100f56f56f0000211ff8195ff8018ff0f6260810a6f81b61f10f16602df50f6265fff6021ff0af1f050fd900489d0ff094fa66006f050832045f0fffff1f10f0106020f80f015512430f8a960ff5fff70fbfbff66d75f55f5ff5fff63ff018f16710050f50801f8b00600001f70f5212f00b0000b1550f082185f19fa0110f95f018f0f0f651f25881f47f0656d0fa53f00f5205f4fbf89f61f663000f50213f086066510bfb0f78f6fffe0010f1fff5f0f1006805bfbf6014ff06757ff140f0ff6f332f0200f001f9ff1f08f008f5060f0f692250ff890f195237ff51013f8f765040bf26f6f10181fff60f0f251029515805f98f7f6550f060b60ffff4950f315150f33ff0f5f6f15ff12fff61505f50b151ff5b6421f53578013343f162f01080f0ff00051f401991015f82f20050920882550060f68f0f9f63f9257ff65581d0f2ff63f61b95f65118f625f780ff272f0f5f8500004f07806003e0bf40f5030357053861008b105f03065092f6f603157f8f5f51762f6f1850919e0f6ff50fff407064b93030f061f31ff05071200f868f07500f05f8ff0061af59f9e9f151f37f23af01ff270f0fcfffff19f06ff4f58153ff5f67fffff940f90065291ff00f0900fff1f9000f6f0b85f08ff87f0f25501006dff450d5f5611f0880f020606f8ff6f0ff0fff50f06f8f1113831f05fff30ff20f0f3f44fd5ff5fff59f56c672f5e9015106933f10055919f80000ff98001913019f21f000b56bff925ffe6ff0040f5b65216f1f3f170409fff0ff58560fa9004ff525f803fb595040806f20458f6086000f90ffa20221fff625fa6ff06f0359ff0f066f00901fff1f4010984001365ff20fff17500f0cf0302807f45006ff861800415b89bf550201ff787ff8046f5ff02450ff63040908ff09f1580090f005080566ffe506f006f1080fc080801ff6262ff458667ff1df00f50fff890f50422f51f71635f05f5056f25f0ff5ff600c6f64f14f00f930e91f8ff111ff00ff05f1624f001f9f5095f0f67f251f600810cb3010f68ff6301015fff86ff03471fff5fc07f0f8f0ff00f06d8f10102fff10af6f618f461f65f560f6b7ff0080f0510005f050f004f0f601f600f8508f20000199f85fd5869500f55b045f6f8111f0f20ff8f9d40027080000ff98950fff318f80ff49f6057f282ff101005f91fdf500f103f0f1ff0106f9acf1f876e906515f0f5000918f15b0f10000f11f58f98f1f5005004f200f80f0eff11f26ff1f05f6f0fb1692011665f5f00f8f16fd6520252901206f2108ff01f0f2ff00ff90029650f80104620f2000f11df354607118551740200f008580011f16618f6ffb0f6ff545660550ff00f855b0101f34055f700fff7f0a6f011a05eff05093f00098fff33651602808130f310288838f68100f020f15156506ff4f0019516ff5f2200f6f0ff058b0f0110050f1910560ff52f70f0180119ff861f02508b0000b8f151174526bf3050088f37f6e0060181753f625088f51075ff002f8f50f32ff90950409150f75510151ff0304f0f4f0757801055afab9ef5f0fff3501ff5519360f02ff860900f7ef0f61f80f120f5ff2208200f8b10fff59d3f6f0812630500f6819501f151ff1f36656435060328f80f902ff6ff0c5f10bff0062f90005907110ff1f65ff83401298f408f87f01e65f020650ff02ff3f080002ff00f9f0ff010f02f5f505690f51f4ff2f5091670987535fc58f0164813ff0155f606f98df15ff150000f5ff9f78cd000cf10f0510f159ff3895046f520010f50958f029f00ff0814f004f08f660f00ff81f14f15f0f06f59500f500f2501f06c05f7f60f9111800552bf6ff808f61583255f50f5bf2150f000ff2615458f5fff3f9561455f1286f0cf521f7bf001f010aefff0923b0955061106f5be600f3fffff56770f297f0f31b501f5ff20151f1f68870610f322f0f9700bf005230ff60fb00ff6f1f5011ff5b240054f086f50b1f016138c0f36cff992000995820ffcfff055d78f905950160f1015008016f1b01b09fff5f500781f6f03f00df801922f008131901f5210ffffa35f76f2105f1009ff8f60250066ff68cf6ffdf16018fffd81f900b3701f8af90011035315f25185f46f0060506191900010ff516a0f0263f020f91f061b1685f1000f22642cff01000f8f7f8f830bf7800528ff00ff5801ff0810496ffffaf62014216f50f2821f30f0b6f11ff9b830010f116ff1b08ff5f0f63651260633f5f54b90066600fffc551068ff0f8f20a62e0c7f09335f21ff54f0526f6f6191200f50805ff550ff870ff106ff105151520f7f53f0f0f5685fff3b46915f1700886f07f5501ff0201100f1f1fbff5659f39f40f01ffffba10234060055f3f52d58ff1c5935f1f6f220f00f8f0b130f6f6f6090ff2ffb0f02910251600ff0f0f1617290016f0052fff9f5080f2f9f0f0f6f9a8f11ff11bb408ff6535fff020027f6fb0f6f9010f0f1f00f00f0f8611946496fbff5f6f5f08f168831f551ef01c70f35759004026850f0105180b21f5f15f1051f15806f10fa913862ffff9090fae65815f05008b00920f9f1f8f6f87850f234b066f59106f0990f0f0f579426f2ff215f0f19610e05c1f7f0f6f58ff07606716081f0902600f00500f9f5010b0f0f10f586206f7501050070fff1c5108f6765500ff1f2108fad01f65856f41fff91fbf001f08d518090a300168f10f86f2f836351200fbf0ff9642ff0ff499f0549089f5ffbf6fff9f1a3000f60f7f82134bf521f506086067788bf6038e045695911f0f6f011f55ff301b526ff3e94001f916012f06f010030ff8459662ff10c989f4f5ff4ff1fff6f690818fa1b8f5f120ff5ff61d083060f1f09d607f84616491f03f2f0a689f11f7063100f828f860f1f1f2004df280b05c651f5fff005100ff6f5f1f00ff0fcf88178ff8f110f9658f0f1ffb82f205ff3f110f00f1f006f0626f81f0efff8ff3f82ff6f050fbff7f2572f1f744f8fb01007351f06090f1ff0f70f0000d94f39f57511060f96907";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"_add\",\"type\":\"address\"}],\"name\":\"isValidAccount\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cname\",\"type\":\"string\"},{\"name\":\"cadd\",\"type\":\"address\"},{\"name\":\"initMoney\",\"type\":\"uint256\"},{\"name\":\"ctype\",\"type\":\"uint256\"}],\"name\":\"createBankAccount\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_add\",\"type\":\"address\"},{\"name\":\"_money\",\"type\":\"uint256\"}],\"name\":\"moneyIncrease\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cadd\",\"type\":\"address\"}],\"name\":\"judgeIfBelieve\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_add\",\"type\":\"address\"},{\"name\":\"_money\",\"type\":\"uint256\"}],\"name\":\"moneyDecrease\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_add\",\"type\":\"address\"}],\"name\":\"searchMoney\",\"outputs\":[{\"name\":\"money\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"BankOriMoney\",\"type\":\"uint256\"},{\"name\":\"_believeThreshold\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"money\",\"type\":\"uint256\"}],\"name\":\"SearchMoneyEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"isIncrease\",\"type\":\"bool\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"amount_after\",\"type\":\"uint256\"}],\"name\":\"MoneyChange\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"pass\",\"type\":\"bool\"}],\"name\":\"judegBelief\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"status\",\"type\":\"bool\"},{\"indexed\":false,\"name\":\"companyAdd\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"companyName\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"companyType\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"money\",\"type\":\"uint256\"}],\"name\":\"AddAccount\",\"type\":\"event\"}]";

    public static final String FUNC_ISVALIDACCOUNT = "isValidAccount";

    public static final String FUNC_CREATEBANKACCOUNT = "createBankAccount";

    public static final String FUNC_MONEYINCREASE = "moneyIncrease";

    public static final String FUNC_JUDGEIFBELIEVE = "judgeIfBelieve";

    public static final String FUNC_MONEYDECREASE = "moneyDecrease";

    public static final String FUNC_SEARCHMONEY = "searchMoney";

    public static final Event SEARCHMONEYEVENT_EVENT = new Event("SearchMoneyEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event MONEYCHANGE_EVENT = new Event("MoneyChange", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event JUDEGBELIEF_EVENT = new Event("judegBelief", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
    ;

    public static final Event ADDACCOUNT_EVENT = new Event("AddAccount", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Bank(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Bank(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Bank(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Bank(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> isValidAccount(String _add) {
        final Function function = new Function(
                FUNC_ISVALIDACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_add)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void isValidAccount(String _add, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ISVALIDACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_add)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String isValidAccountSeq(String _add) {
        final Function function = new Function(
                FUNC_ISVALIDACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_add)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> createBankAccount(String cname, String cadd, BigInteger initMoney, BigInteger ctype) {
        final Function function = new Function(
                FUNC_CREATEBANKACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(cname), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(cadd), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(initMoney), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(ctype)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void createBankAccount(String cname, String cadd, BigInteger initMoney, BigInteger ctype, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATEBANKACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(cname), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(cadd), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(initMoney), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(ctype)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String createBankAccountSeq(String cname, String cadd, BigInteger initMoney, BigInteger ctype) {
        final Function function = new Function(
                FUNC_CREATEBANKACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(cname), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(cadd), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(initMoney), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(ctype)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> moneyIncrease(String _add, BigInteger _money) {
        final Function function = new Function(
                FUNC_MONEYINCREASE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_add), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_money)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void moneyIncrease(String _add, BigInteger _money, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_MONEYINCREASE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_add), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_money)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String moneyIncreaseSeq(String _add, BigInteger _money) {
        final Function function = new Function(
                FUNC_MONEYINCREASE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_add), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_money)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> judgeIfBelieve(String cadd) {
        final Function function = new Function(
                FUNC_JUDGEIFBELIEVE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(cadd)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void judgeIfBelieve(String cadd, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_JUDGEIFBELIEVE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(cadd)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String judgeIfBelieveSeq(String cadd) {
        final Function function = new Function(
                FUNC_JUDGEIFBELIEVE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(cadd)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> moneyDecrease(String _add, BigInteger _money) {
        final Function function = new Function(
                FUNC_MONEYDECREASE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_add), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_money)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void moneyDecrease(String _add, BigInteger _money, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_MONEYDECREASE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_add), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_money)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String moneyDecreaseSeq(String _add, BigInteger _money) {
        final Function function = new Function(
                FUNC_MONEYDECREASE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_add), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_money)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> searchMoney(String _add) {
        final Function function = new Function(
                FUNC_SEARCHMONEY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_add)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void searchMoney(String _add, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SEARCHMONEY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_add)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String searchMoneySeq(String _add) {
        final Function function = new Function(
                FUNC_SEARCHMONEY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_add)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public List<SearchMoneyEventEventResponse> getSearchMoneyEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SEARCHMONEYEVENT_EVENT, transactionReceipt);
        ArrayList<SearchMoneyEventEventResponse> responses = new ArrayList<SearchMoneyEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SearchMoneyEventEventResponse typedResponse = new SearchMoneyEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.money = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerSearchMoneyEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(SEARCHMONEYEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerSearchMoneyEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(SEARCHMONEYEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<MoneyChangeEventResponse> getMoneyChangeEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MONEYCHANGE_EVENT, transactionReceipt);
        ArrayList<MoneyChangeEventResponse> responses = new ArrayList<MoneyChangeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MoneyChangeEventResponse typedResponse = new MoneyChangeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.isIncrease = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount_after = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerMoneyChangeEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(MONEYCHANGE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerMoneyChangeEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(MONEYCHANGE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<JudegBeliefEventResponse> getJudegBeliefEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(JUDEGBELIEF_EVENT, transactionReceipt);
        ArrayList<JudegBeliefEventResponse> responses = new ArrayList<JudegBeliefEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            JudegBeliefEventResponse typedResponse = new JudegBeliefEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.pass = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerjudegBeliefEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(JUDEGBELIEF_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerjudegBeliefEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(JUDEGBELIEF_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<AddAccountEventResponse> getAddAccountEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDACCOUNT_EVENT, transactionReceipt);
        ArrayList<AddAccountEventResponse> responses = new ArrayList<AddAccountEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddAccountEventResponse typedResponse = new AddAccountEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.status = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.companyAdd = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.companyName = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.companyType = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.money = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerAddAccountEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(ADDACCOUNT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerAddAccountEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(ADDACCOUNT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static Bank load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Bank(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Bank load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Bank(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Bank load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Bank(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Bank load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Bank(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Bank> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger BankOriMoney, BigInteger _believeThreshold) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(BankOriMoney), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_believeThreshold)));
        return deployRemoteCall(Bank.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Bank> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger BankOriMoney, BigInteger _believeThreshold) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(BankOriMoney), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_believeThreshold)));
        return deployRemoteCall(Bank.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Bank> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger BankOriMoney, BigInteger _believeThreshold) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(BankOriMoney), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_believeThreshold)));
        return deployRemoteCall(Bank.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Bank> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger BankOriMoney, BigInteger _believeThreshold) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(BankOriMoney), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_believeThreshold)));
        return deployRemoteCall(Bank.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class SearchMoneyEventEventResponse {
        public Log log;

        public BigInteger money;
    }

    public static class MoneyChangeEventResponse {
        public Log log;

        public Boolean isIncrease;

        public BigInteger amount;

        public BigInteger amount_after;
    }

    public static class JudegBeliefEventResponse {
        public Log log;

        public Boolean pass;
    }

    public static class AddAccountEventResponse {
        public Log log;

        public Boolean status;

        public String companyAdd;

        public String companyName;

        public BigInteger companyType;

        public BigInteger money;
    }
}
