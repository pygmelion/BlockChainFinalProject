import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.fisco.bcos.asset.contract.Company;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.txdecode.EventResultEntity;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoderFactory;

import javax.swing.JButton;
import java.awt.Font;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaveMoneyPage {

	public String pub_user_key;
	public String ABI;
	public Company cccompany;
	public MainPage mpt;
	public JFrame jfframe;
	private JTextField text_feild;
	
	public void increase_money(BigInteger _money) throws Exception {
	       TransactionReceipt receipt = this.cccompany.moneyIncrease(this.pub_user_key, _money).send();
	       List<Log> logs = receipt.getLogs();
	       TransactionDecoder td  = TransactionDecoderFactory.buildTransactionDecoder(this.ABI, "");
	       Map<String, List<List<EventResultEntity>>>  map_list_list = td.decodeEventReturnObject(logs);
	       System.out.println(map_list_list);
	       //return  ((BigInteger) map_list_list.get("SearchMoneyEvent(uint256)").get(0).get(0).getData()).intValue();
	}
	
	public void dec_money(BigInteger _money) throws Exception {
	       TransactionReceipt rec = cccompany.moneyDecrease(this.pub_user_key, _money).send();
	       List<Log> logs = rec.getLogs();
	       TransactionDecoder td  = TransactionDecoderFactory.buildTransactionDecoder(this.ABI, "");
	       Map<String, List<List<EventResultEntity>>>  map_list_list = td.decodeEventReturnObject(logs);
	       System.out.println(map_list_list);
//	       return  ((BigInteger) map_list_list.get("SearchMoneyEvent(uint256)").get(0).get(0).getData()).intValue();
		}


	public List<String> all_clean_save(String[] words, int maxWidth) {
		List<String> res = new ArrayList<>();
		int i = 0;
		while (i < words.length) {
			List<String> curLine = new ArrayList<>();
			int num = 0, curLen = 0;
			// start building current string line 
			while (i < words.length && (curLen + words[i].length() + num <= maxWidth)) {
				curLen += words[i].length();
				curLine.add(words[i]);
				num++;
				i++;
			}
			// left justify
			if (num == 1 || i == words.length) {
				res.add(clean_save_1(curLine, maxWidth));
			}
			// regular build with more space on the left side
			else {
				res.add(clean_save(maxWidth - curLen, num, curLine));
			}
		}
		return res;
	}
	public String clean_save_1(List<String> curLine, int maxWidth) {
		StringBuilder sb = new StringBuilder();
		int rest = maxWidth;
		for (int j = 0; j < curLine.size(); j++) {
			String str = curLine.get(j);
			rest -= str.length();
			sb.append(str);
			if (rest != 0) {
				sb.append(" ");
				rest--;
			}
		}
		// append rest of spaces
		while (rest != 0) {
			sb.append(" ");
			rest--;
		} 
		return new String(sb);
	}
	public String clean_save(int space, int num, List<String> curLine) {
		StringBuilder sb = new StringBuilder();
		int extra = space % (num - 1);
		int each = space / (num - 1);
		for (int j = 0; j < curLine.size(); j++) {
			String str = curLine.get(j);
			sb.append(str);
			if (j == curLine.size() - 1) continue;
			for (int k = 1; k <= each; k++) {
				sb.append(" ");
			}
			if (extra > 0) {
				sb.append(" ");
				extra--;
			}
		}
		return new String(sb);
	}
	public SaveMoneyPage(String _userkey, String abi, Company _company, MainPage _mpt) {
		this.cccompany = _company;
		this.ABI = abi;
		this.pub_user_key = _userkey;
		this.mpt = _mpt;
		initialize();
	}
	private void initialize() {
		jfframe = new JFrame();
		jfframe.setBounds(100, 100, 450, 300);
		jfframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfframe.getContentPane().setLayout(null);
		
		text_feild = new JTextField();
		text_feild.setBounds(88, 83, 143, 41);
		jfframe.getContentPane().add(text_feild);
		text_feild.setColumns(10);
		
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					increase_money(new BigInteger(String.valueOf(text_feild.getText())));
					
					mpt.set_bank_account_page();
					jfframe.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		button.setBounds(264, 92, 97, 32);
		jfframe.getContentPane().add(button);
		
		JLabel label = new JLabel("存入");
		label.setFont(new Font("黑体", Font.PLAIN, 17));
		label.setBounds(20, 96, 68, 28);
		jfframe.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("元");
		label_1.setFont(new Font("黑体", Font.PLAIN, 17));
		label_1.setBounds(241, 96, 68, 28);
		jfframe.getContentPane().add(label_1);
	}

}
