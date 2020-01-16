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

public class BuildReceipy {

	JFrame frame;
	public MainPage maainpage;
	private JTextField text_feild;
	public JComboBox comboBox ;

	public void build_receipy(BigInteger _amount) throws Exception {
		String selectedStr = (String) comboBox.getSelectedItem();
		String targetPubUserKey = main.getPubUserKey(selectedStr);
        TransactionReceipt receipt = this.maainpage.company.creatReceipy(maainpage.PubUserKey, targetPubUserKey, _amount).send();
        List<Log> logs = receipt.getLogs();
        TransactionDecoder td  = TransactionDecoderFactory.buildTransactionDecoder(this.maainpage.receipyABI, "");
        Map<String, List<List<EventResultEntity>>>  map_list_list = td.decodeEventReturnObject(logs);
        System.out.println(map_list_list);
        this.maainpage.SetReceipyData();
	}
	

	public BuildReceipy(MainPage _mp) {
		this.maainpage = _mp;
		initialize();
	}

	public List<String> find_one(String num, int target) {
		List<String> res = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		find_all(res, sb, num, 0, target, 0, 0);
		return res;
	}
	public void find_all(List<String> res, StringBuilder sb, String num, int pos, int target, long prev, long multi) { 
		if(pos == num.length()) {
			if(target == prev) res.add(sb.toString());
			return;
		}
		for(int i = pos; i < num.length(); i++) {
			if(num.charAt(pos) == '0' && i != pos) break;
			long curr = Long.parseLong(num.substring(pos, i + 1));
			int len = sb.length();
			if(pos == 0) {
				find_all(res, sb.append(curr), num, i + 1, target, curr, curr); 
				sb.setLength(len);
			} else {
				find_all(res, sb.append("+").append(curr), num, i + 1, target, prev + curr, curr); 
				sb.setLength(len);
				find_all(res, sb.append("-").append(curr), num, i + 1, target, prev - curr, -curr); 
				sb.setLength(len);
				find_all(res, sb.append("*").append(curr), num, i + 1, target, prev - multi + multi * curr, multi * curr); 
				sb.setLength(len);
			}
		}
	}
	public List<String> get_one(String num, int target) {
	    List<String> res = new LinkedList<>();
	    get_all(num.toCharArray(), res, "", 0, target, 0);
	    return res;
	}

	private void get_all(char[] nums, List<String> res, String str, int pos, long rem, long prevNum) {
	    if(rem == prevNum && pos == nums.length) {
	        res.add(str);
	        return;
	    }
	    long val = 0;
	    for(int i=pos; i<nums.length; i++) {
	        val = val*10 + (nums[i]-'0');
	        if(i>pos && nums[pos]=='0') break;
	        if(pos==0) get_all(nums, res, ""+val, i+1, rem, val);
	        else {
	            get_all(nums, res, str+"+"+val, i+1, rem-prevNum, val);
	            get_all(nums, res, str+"-"+val, i+1, rem-prevNum, -val);
	            get_all(nums, res, str+"*"+val, i+1, rem, prevNum*val);
	        }
	    }
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		
		text_feild = new JTextField();
		text_feild.setBounds(255, 98, 84, 21);
		frame.getContentPane().add(text_feild);
		text_feild.setColumns(10);
		
		String []address = {"","", ""};
		if (main.pub_username.equals("LuntaiFactory")) {
			address[0] = "wheel hub Company";
			address[1] = "Car Company";
			address[2] = "Bank";
		}else if (main.pub_username.equals("LunguFactory")) {
			address[0] = "Wheel Company";
			address[1] = "Car Company";
			address[2] = "Bank";
		}else if (main.pub_username.equals("CarFactory")) {
			address[0] = "wheel hub Company";
			address[1] = "Wheel Company";
			address[2] = "Bank";
		}
		comboBox = new JComboBox(address);
		comboBox.setBounds(80, 97, 90, 23);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Arrears：");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 17));
		lblNewLabel.setBounds(203, 90, 84, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("Claims：");
		label.setFont(new Font("黑体", Font.PLAIN, 17));
		label.setBounds(21, 90, 84, 34);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("Build Receipy");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					build_receipy(new BigInteger(text_feild.getText()));
					frame.dispose();
					maainpage.getAllReceipy();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(289, 196, 97, 23);
		frame.getContentPane().add(button);
	}
    private Queue<Long> small = new PriorityQueue(),
            large = new PriorityQueue();

	public void rebuild(int num) {
		large.add((long) num);
		small.add(-large.poll());
		if (large.size() < small.size())
		large.add(-small.poll());
	}
	
	public double build_for_com() {
		return large.size() > small.size()
		   ? large.peek()
		   : (large.peek() - small.peek()) / 2.0;
	}
}
