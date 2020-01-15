import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.txdecode.EventResultEntity;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoderFactory;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class QueryBankDeposit {

	JFrame jfframe;
	public MainPage mp;
	private JTextField text_feild;
	public JComboBox com_box;
	/**
	 * Launch the application.
	 */
//	
	public void query_deposit_from_bank(BigInteger _amount) throws Exception {
		String selectedStr = (String) com_box.getSelectedItem();
		String targetPubUserKey = main.getPubUserKey(selectedStr);
        TransactionReceipt receipt = this.mp.com.AskMoneyFromBank(targetPubUserKey, mp.pulic_user_key, _amount).send();
        List<Log> logs = receipt.getLogs();
        TransactionDecoder td  = TransactionDecoderFactory.buildTransactionDecoder(this.mp.receipyABI, "");
        Map<String, List<List<EventResultEntity>>>  map_list_list = td.decodeEventReturnObject(logs);
       
        this.mp.set_rec_data();
        this.mp.set_bank_account_page();
	}
	



	private void initialize() {
		jfframe = new JFrame();
		jfframe.setBounds(100, 100, 450, 300);
		jfframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfframe.getContentPane().setLayout(null);
		
		
		String []address = {"",""};
		if (main.pub_username.equals("LuntaiFactory")) {
			address[0] = "Wheel hub Company";
			address[1] = "Car Company";
		}else if (main.pub_username.equals("LunguFactory")) {
			address[0] = "Wheel Company";
			address[1] = "Car Company";
		}else if (main.pub_username.equals("CarFactory")) {
			address[0] = "Wheel hub Company";
			address[1] = "Wheel Company";
		}
		com_box = new JComboBox(address);
		com_box.setBounds(167, 97, 90, 23);
		jfframe.getContentPane().add(com_box);
		
		text_feild = new JTextField();
		text_feild.setBounds(285, 98, 84, 21);
		jfframe.getContentPane().add(text_feild);
		text_feild.setColumns(10);
		
		JButton button = new JButton("Create Recipy");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					query_deposit_from_bank(new BigInteger(text_feild.getText()));
					jfframe.dispose();
					mp.get_all_rec();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Financing from banks：");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 17));
		lblNewLabel.setBounds(51, 90, 146, 34);
		jfframe.getContentPane().add(lblNewLabel);
		

		button.setBounds(289, 196, 97, 23);
		jfframe.getContentPane().add(button);
	}
	
	public QueryBankDeposit(MainPage _mp) {
		this.mp = _mp;
		initialize();
	}
}
