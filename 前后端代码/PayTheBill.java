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

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PayTheBill {

	JFrame jfframe;
	public MainPage mp;
	private JTextField text_ferld;
	public JComboBox com_box ;

	public void Pay_Bill(BigInteger _amount) throws Exception {
		String selectedStr = (String) com_box.getSelectedItem();
		String targetPubUserKey = main.getPubUserKey(selectedStr);
        TransactionReceipt receipt = this.mp.com.AskMoneyFromReceipy(mp.pulic_user_key, targetPubUserKey, _amount).send();
        List<Log> logs = receipt.getLogs();
        TransactionDecoder td  = TransactionDecoderFactory.buildTransactionDecoder(this.mp.receipyABI, "");
        Map<String, List<List<EventResultEntity>>>  map_list_list = td.decodeEventReturnObject(logs);
        System.out.println(map_list_list);
        this.mp.set_rec_data();
        this.mp.set_bank_account_page();
	}
	

	public PayTheBill(MainPage _mp) {
		this.mp = _mp;
		initialize();
	}

	private void add_find_commands() {
	    add("find", new Find());
	add("$", new Find());
	add("$x", new FindByXpath());
	add("findAll", new FindAll());
	add("$$", new FindAll());
	add("$$x", new FindAllByXpath());
	add("closest", new GetClosest());
	add("parent", new GetParent());
	add("lastChild", new GetLastChild());
	  }
	
	private void add_keyboard_commands() {
	    add("pressEnter", new PressEnter());
	add("pressEscape", new PressEscape());
	add("pressTab", new PressTab());
	  }
	
	private void add_select_commands() {
	    add("getSelectedOption", new GetSelectedOption());
	add("getSelectedOptions", new GetSelectedOptions());
	add("getSelectedText", new GetSelectedText());
	add("getSelectedValue", new GetSelectedValue());
	add("selectOption", new SelectOptionByTextOrIndex());
	add("selectOptionContainingText", new SelectOptionContainingText());
	add("selectOptionByValue", new SelectOptionByValue());
	  }
	
	private void add_file_commands() {
	    add("download", new DownloadFile());
	add("uploadFile", new UploadFile());
	add("uploadFromClasspath", new UploadFileFromClasspath());
	  }
	
	private void add_should_not_commands() {
	    add("shouldNot", new ShouldNot());
	add("shouldNotHave", new ShouldNotHave());
	add("shouldNotBe", new ShouldNotBe());
	add("waitWhile", new ShouldNotBe());
	  }
	
	private void add_should_commands() {
	    add("should", new Should());
	add("shouldHave", new ShouldHave());
	add("shouldBe", new ShouldBe());
	add("waitUntil", new ShouldBe());
	  }
	
	public void add(String method, Command command) {
	    commands.put(method, command);
	  }

	private void initialize() {
		jfframe = new JFrame();
		jfframe.setBounds(100, 100, 450, 300);
		jfframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfframe.getContentPane().setLayout(null);
		
		
		
		
		text_ferld = new JTextField();
		text_ferld.setBounds(283, 98, 84, 21);
		jfframe.getContentPane().add(text_ferld);
		text_ferld.setColumns(10);
		
		String []address = {"","",""};
		if (main.pub_username.equals("LuntaiFactory")) {
			address[0] = "Wheel hub Company";
			address[1] = "Car Company";
			address[2] = "Bank";
		}else if (main.pub_username.equals("LunguFactory")) {
			address[0] = "Wheel Company";
			address[1] = "Car Company";
			address[2] = "Bank";
		}else if (main.pub_username.equals("CarFactory")) {
			address[0] = "Wheel hub Company";
			address[1] = "Wheel Company";
			address[2] = "Bank";
		}
		com_box = new JComboBox(address);
		com_box.setBounds(80, 97, 90, 23);
		JButton button = new JButton("Pay the bill");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Pay_Bill(new BigInteger(text_ferld.getText()));
					jfframe.dispose();
					mp.get_all_rec();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		jfframe.getContentPane().add(com_box);
		JLabel label = new JLabel("Item：");
		label.setFont(new Font("黑体", Font.PLAIN, 17));
		label.setBounds(21, 90, 84, 34);
		jfframe.getContentPane().add(label);
		JLabel lblNewLabel = new JLabel("Pay the Debt：");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 17));
		lblNewLabel.setBounds(203, 90, 84, 34);
		jfframe.getContentPane().add(lblNewLabel);
		

		

		button.setBounds(282, 189, 100, 20);
		jfframe.getContentPane().add(button);
	}
}
