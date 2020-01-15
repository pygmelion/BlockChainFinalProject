import java.awt.EventQueue;
import java.math.BigInteger;
import java.util.List;

import org.fisco.bcos.asset.contract.Asset;
import org.fisco.bcos.asset.contract.Bank;
import org.fisco.bcos.asset.contract.Company;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class main {
	public static String pub_username;
	public static Web3j web3j; 
	public static Credentials credentials;
	
	public static String getGBKNameByEnName(String Enname) {
		if(Enname.equals("CarFactory")) return "Car Company";
		if(Enname.equals("LuntaiFactory"))return "Wheel Company";
		if(Enname.equals("LunguFactory")) return "Wheel hub Company";
		if(Enname.equals("Bank")) return "Bank";
		return "null";
	}
	
	public static String getEnNameByPubUserKey(String pubkey) {
		if(pubkey.equals("0x8e2f3554b3e53b921efbf80ccb64775ab8f83190"))return "CarFactory";

		if(pubkey.equals("0x223ecf1680fb2d02241e8f92c3d1b39d84ed2480"))return "LunguFactory";
		
		if(pubkey.equals("0xbf75837302328e53e3f4eac8334d1688f1c33a5b"))return "Bank";
		if(pubkey.equals("0xd306724607191925c0d47e2963cc030afaec33b1"))return "LuntaiFactory";
		return "null";
		
	}
	
	public static String getPubUserKey(String username) {
		if (username.equals("CarFactory") || username.equals("Car Company")) return "0x8e2f3554b3e53b921efbf80ccb64775ab8f83190";
		if (username.equals("Bank") || username.equals("Bank")) return "0xbf75837302328e53e3f4eac8334d1688f1c33a5b";
		if (username.equals("LuntaiFactory") || username.equals("Wheel Company"))return "0xd306724607191925c0d47e2963cc030afaec33b1";
		if (username.equals("LunguFactory") || username.equals("Wheel hub Company")) return "0x223ecf1680fb2d02241e8f92c3d1b39d84ed2480";
		return "null";
	}
	public static int Str2Int(String s) {
		s = s.substring(2, s.length());
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) != '0') {
				return Integer.parseInt(s.substring(i, s.length()));
			}
		}
		return 0;
	}
	
	public static void main_login(String username) {
		pub_username = username;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage(username);
					window.jfframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public static void startLogInPage() {
		try {
			LogIn window = new LogIn();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[]) throws Exception { 
        
        ApplicationContext con = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Service ser = con.getBean(Service.class);
        ser.run(); 
        
        ChannelEthereumService channel_Service = new ChannelEthereumService();
        channel_Service.setChannelService(ser);
        credentials = Credentials.create(Keys.createEcKeyPair());
        
        web3j = Web3j.build(channel_Service, ser.getGroupId());
        
        
        startLogInPage();
    } 

}
