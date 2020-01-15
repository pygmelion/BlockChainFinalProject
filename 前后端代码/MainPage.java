import java.awt.EventQueue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.asset.contract.Asset;
import org.fisco.bcos.asset.contract.Bank;
import org.fisco.bcos.asset.contract.Company;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

import org.fisco.bcos.web3j.tx.txdecode.EventResultEntity;
import org.fisco.bcos.web3j.tx.txdecode.ResultEntity;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoderFactory;

import net.bytebuddy.asm.Advice.This;

import javax.swing.JSplitPane;

import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class MainPage {
String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"_add\",\"type\":\"address\"}],\"name\":\"isValidAccount\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cname\",\"type\":\"string\"},{\"name\":\"cadd\",\"type\":\"address\"},{\"name\":\"initMoney\",\"type\":\"uint256\"},{\"name\":\"ctype\",\"type\":\"uint256\"}],\"name\":\"createBankAccount\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_add\",\"type\":\"address\"},{\"name\":\"_money\",\"type\":\"uint256\"}],\"name\":\"moneyIncrease\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"cadd\",\"type\":\"address\"}],\"name\":\"judgeIfBelieve\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_add\",\"type\":\"address\"},{\"name\":\"_money\",\"type\":\"uint256\"}],\"name\":\"moneyDecrease\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_add\",\"type\":\"address\"}],\"name\":\"searchMoney\",\"outputs\":[{\"name\":\"money\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"BankOriMoney\",\"type\":\"uint256\"},{\"name\":\"_believeThreshold\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"money\",\"type\":\"uint256\"}],\"name\":\"SearchMoneyEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"isIncrease\",\"type\":\"bool\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"amount_after\",\"type\":\"uint256\"}],\"name\":\"MoneyChange\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"pass\",\"type\":\"bool\"}],\"name\":\"judegBelief\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"status\",\"type\":\"bool\"},{\"indexed\":false,\"name\":\"companyAdd\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"companyName\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"companyType\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"money\",\"type\":\"uint256\"}],\"name\":\"AddAccount\",\"type\":\"event\"}]";
	String receipyABI = "[{\"constant\":true,\"inputs\":[],\"name\":\"BankUserAdd\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_add\",\"type\":\"address\"}],\"name\":\"logAllReceipy\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_newTo\",\"type\":\"address\"},{\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"transferReceipy\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"CreateReceipy\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"number\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"}],\"name\":\"deleteReceipy\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"receipy2BankMoney\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"BankAdd\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"receipy2Money\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"ifBelief\",\"type\":\"bool\"},{\"indexed\":false,\"name\":\"fromAdd\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"toAdd\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"refreshReceipyEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"ifPass\",\"type\":\"bool\"}],\"name\":\"createReceipyEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"ifPass\",\"type\":\"bool\"}],\"name\":\"deleteReceipyEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"ifBelief\",\"type\":\"bool\"},{\"indexed\":false,\"name\":\"fromAdd\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"toAdd\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"logReceipyEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"_log\",\"type\":\"string\"}],\"name\":\"transferReceipyFailedEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"ifBelief\",\"type\":\"bool\"},{\"indexed\":false,\"name\":\"oriFromAdd\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"oriToAdd\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"toAdd\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"isOriDeleted\",\"type\":\"bool\"}],\"name\":\"transferReceipyEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"_log\",\"type\":\"string\"}],\"name\":\"payForReceipyErrorEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"isPayAll\",\"type\":\"bool\"},{\"indexed\":false,\"name\":\"fromAdd\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"toAdd\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"payForReceipyEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"_log\",\"type\":\"string\"}],\"name\":\"bankHelpErrorEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"fromAdd\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"toAdd\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"bankHelpEvent\",\"type\":\"event\"}]";
	public JFrame jfframe;
	public BigInteger gas_price, gas_imit;
	public JLabel label_3;
	public MainPage  mp;
	public JTextPane t_pane;
	public JTextPane t_pane_1;
	public Company com;
	public String func_get_money_addr;
	public String __user_name__;
	public String add_contract_com;
	public String pulic_user_key;
	private JTextField text_feild;

	JLabel label_4;
	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize()  {
		this.mp = this;
		jfframe = new JFrame();
		jfframe.getContentPane().setFont(new Font("黑体", Font.PLAIN, 12));
		jfframe.setBounds(100, 100, 949, 601);
		jfframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfframe.getContentPane().setLayout(null);
		
		//初始化区块链接口
		gas_price =  BigInteger.valueOf(10000); gas_imit = BigInteger.valueOf(10000000);
	    add_contract_com = "0xcd1856e9700e54feb66d7359302c0183786f3ee1";
	    com = Company.load(add_contract_com, main.web3j, main.credentials, new StaticGasProvider(gas_price, gas_imit));
	    func_get_money_addr = "0x223ecf1680fb2d02241e8f92c3d1b39d84ed2480";
	    this.pulic_user_key = main.getPubUserKey(this.__user_name__);
	    
		JLabel label = new JLabel("欢迎");
		label.setFont(new Font("黑体", Font.PLAIN, 17));
		label.setBounds(38, 21, 64, 38);
		jfframe.getContentPane().add(label);
		
		JLabel label_1 = new JLabel(this.__user_name__);
		label_1.setFont(new Font("黑体", Font.PLAIN, 17));
		label_1.setBounds(81, 21, 118, 38);
		jfframe.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("现有余额(元)：");
		label_2.setFont(new Font("黑体", Font.PLAIN, 17));
		label_2.setBounds(81, 105, 118, 38);
		jfframe.getContentPane().add(label_2);
		
		//初始化银行账户显示
		try {
			this.label_3 = new JLabel(String.valueOf(get_money()));
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		label_3.setFont(new Font("黑体", Font.PLAIN, 17));
		label_3.setBounds(205, 105, 118, 38);
		jfframe.getContentPane().add(label_3);
		
		JButton button = new JButton("Deposit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							SaveMoneyPage window = new SaveMoneyPage(pulic_user_key,ABI,com, mp);
							window.jfframe.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		button.setBounds(91, 161, 97, 23);
		jfframe.getContentPane().add(button);
		
		
		
		JLabel label_6 = new JLabel("Initial balance (RMB)：");
		label_6.setFont(new Font("黑体", Font.PLAIN, 17));
		label_6.setBounds(205, 21, 118, 38);
		jfframe.getContentPane().add(label_6);
		
		text_feild = new JTextField();
		text_feild.setBounds(333, 31, 66, 21);
		jfframe.getContentPane().add(text_feild);
		text_feild.setColumns(10);
		
		JButton button_2 = new JButton("Create Bank Account");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					create_bank_account(new BigInteger(text_feild.getText(), 10));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_2.setBounds(427, 30, 118, 23);
		jfframe.getContentPane().add(button_2);
		

		
		JButton btnNewButton = new JButton("Bank financing");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							QueryBankDeposit window = new QueryBankDeposit(mp);
							window.jfframe.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton.setBounds(716, 294, 97, 23);
		jfframe.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Payment and settlement");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							PayTheBill window = new PayTheBill(mp);
							window.jfframe.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_1.setBounds(716, 357, 97, 23);
		jfframe.getContentPane().add(btnNewButton_1);
		JLabel label_8 = new JLabel("Arrear Recipy：");
		label_8.setFont(new Font("黑体", Font.PLAIN, 17));
		label_8.setBounds(56, 412, 118, 38);
		jfframe.getContentPane().add(label_8);
		
		JButton button_5 = new JButton("Log out");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jfframe.dispose();
				main.startLogInPage();
			}
		});
		button_5.setBounds(91, 69, 97, 23);
		jfframe.getContentPane().add(button_5);
		t_pane = new JTextPane();
		t_pane.setBounds(138, 244, 462, 121);
		jfframe.getContentPane().add(t_pane);
		JButton button_3 = new JButton("Build Recipy");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							BuildReceipy window = new BuildReceipy(mp);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		button_3.setBounds(716, 161, 97, 23);
		jfframe.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("Transfer Recipy");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							TransferReceipy window = new TransferReceipy(mp);
							window.jfframe.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		button_4.setBounds(716, 225, 97, 23);
		jfframe.getContentPane().add(button_4);
		
		JLabel label_5 = new JLabel("Recipy condition");
		label_5.setFont(new Font("黑体", Font.PLAIN, 17));
		label_5.setBounds(32, 210, 118, 38);
		jfframe.getContentPane().add(label_5);
		JButton button_1 = new JButton("Withdraw");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							DeclineMoney window = new DeclineMoney(pulic_user_key,ABI,com, mp);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		button_1.setBounds(235, 161, 97, 23);
		jfframe.getContentPane().add(button_1);
		
	    label_4 = new JLabel("Risk assessment certification");
		label_4.setFont(new Font("黑体", Font.PLAIN, 17));
		label_4.setBounds(397, 105, 203, 38);
		jfframe.getContentPane().add(label_4);
		set_bank_account_page();
		t_pane_1 = new JTextPane();
		t_pane_1.setBounds(138, 412, 462, 121);
		jfframe.getContentPane().add(t_pane_1);
		
		JLabel label_7 = new JLabel("Debt Recipy：");
		label_7.setFont(new Font("黑体", Font.PLAIN, 17));
		label_7.setBounds(56, 244, 118, 38);
		jfframe.getContentPane().add(label_7);
		

		
		try {
			set_rec_data();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public MainPage(String username) {
		this.__user_name__ = username;
		initialize();
	}
    public List<List<String>> refresh_page(String start, String end, Set<String> dict) {
    	//long t1=System.currentTimeMillis();
        List<List<String>> results= new ArrayList<List<String>>();
        if (dict.size() == 0)
			return results;
        
        int min=Integer.MAX_VALUE;
        
        Queue<String> queue= new ArrayDeque<String>();
        
		Map<String,LinkedList<String>> map = new HashMap<String,LinkedList<String>>();
		
		Map<String,Integer> ladder = new HashMap<String,Integer>();
		for (String string:dict){
		    ladder.put(string, Integer.MAX_VALUE);
		    map.put(string, new LinkedList<String>());//Find the next
		}
			
		ladder.put(start, 0);
		
		queue.add(start);
		
		dict.add(end);
		//BFS
		while (!queue.isEmpty()) {
		   
			String word = queue.poll();
			
			int step = ladder.get(word)+1;
			
			if (step>min) break;
			
			for (int i = 0; i < word.length(); i++){
			   StringBuilder builder = new StringBuilder(word); 
				for (char ch='a';  ch <= 'z'; ch++){
					builder.setCharAt(i,ch);
					String new_word=builder.toString();				
					if (ch!=word.charAt(i)&&(ladder.containsKey(new_word))&&(step<=ladder.get(new_word))) {
					    ladder.put(new_word, step);
					    map.get(new_word).add(word);						    
					    if (new_word.equals(end))
					    	min=step;
					    else
					    	queue.add(new_word);
					}//End if dict contains new_word
				}//End:Iteration from 'a' to 'z'
			}//End:Iteration from the first to the last
		}//End While

    	//Back Trace
    	for (String str:map.get(end)){
	    	LinkedList<String> result = new LinkedList<String>();
	    	result.add(end);
    		result.addFirst(str);
    		LinkedList<String> backtrace= map.get(str);
	    	while (backtrace.size()>0){//The recent added word is not the start
	    		int index=backtrace.size()-1;//Find the position of the word
	    		String back= backtrace.get(index);//Find one ancestor
	    		result.add(0,back);//Add the ancestor to the result list
	    		if (index>0)
	    			backtrace.remove(index);// Remove the recent added ancestor						    		
	    		backtrace=map.get(back);//Continue back tracking
	    	}
	    	results.add(result);
    	}			
    	//long t2=System.currentTimeMillis();
    	//System.out.print("Consumes: "+(t2-t1)+"\n");
		return results;        
    }
	public void set_bank_account_page() {
		try {
			jfframe.remove(label_3);
			int amount = get_money();
			this.label_3 = new JLabel(String.valueOf(amount));
			if (amount >= 10000) {
				jfframe.remove(label_4);
				this.label_4.setText("User has been certified by risk assessment！");
				jfframe.getContentPane().add(label_4);
			}else {
				jfframe.remove(label_4);
				this.label_4.setText("No risk assessment certification！");
				jfframe.getContentPane().add(label_4);
			}
			System.out.println(label_3.getText());
			label_3.setFont(new Font("黑体", Font.PLAIN, 17));
			label_3.setBounds(195, 115, 113, 34);
			jfframe.getContentPane().add(label_3);
			jfframe.repaint();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public void create_bank_account(BigInteger initMoney) throws Exception {
		TransactionReceipt rec = com.CreateBankAccount(this.__user_name__, this.pulic_user_key, initMoney).send();
       List<Log> logs = rec.getLogs();
       TransactionDecoder td  = TransactionDecoderFactory.buildTransactionDecoder(this.ABI, "");
       Map<String, List<List<EventResultEntity>>>  map_l_l = td.decodeEventReturnObject(logs);
       System.out.println(map_l_l);
       set_bank_account_page();
	}
	
	public int get_money() throws Exception {
       TransactionReceipt rec = com.getMoney(this.pulic_user_key).send();
       List<Log> logs = rec.getLogs();
       TransactionDecoder td  = TransactionDecoderFactory.buildTransactionDecoder(this.ABI, "");
       Map<String, List<List<EventResultEntity>>>  map_l_l = td.decodeEventReturnObject(logs);
       return  ((BigInteger) map_l_l.get("SearchMoneyEvent(uint256)").get(0).get(0).getData()).intValue();
	}


	public static int get_rec_retrieve(String s, String t) {
	if (s.length() < t.length())
		return 0;
	int lenS = s.length(), lenT = t.length();
	int[][] dp = new int[lenS + 1][lenT + 1];
	for (int i = 1; i <= lenT; i++)
		dp[0][i] = 0;
	for (int i = 0; i <= lenS; i++)
		dp[i][0] = 1;
	for (int i = 1; i <= lenS; i++)
		for (int j = 1; j <= lenT; j++) {
			if (s.charAt(i - 1) == t.charAt(j - 1))
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
			else
				dp[i][j] = dp[i - 1][j];
		}

	return dp[lenS][lenT];
	}
    
	public int counter(String s, String t) {
        if(t.length() > s.length()) return 0;
        int[] count = new int[t.length()];
        char[] arrt = t.toCharArray();
        char[] arrs = s.toCharArray();
        Map<Character, LinkedList<Integer>> m = new HashMap<Character, LinkedList<Integer>>();
        LinkedList<Integer> temp = null;
        int i = 0;
       //keep a list of indexes of the same character
        for(char c : arrt){
            temp = m.get(c);
            if(temp == null){
                temp = new LinkedList<Integer>();
                m.put(c, temp);
            }
            temp.addFirst(i++);
        }
        // count[i] = count[i]+count[i-1]
        for(char c : arrs){
            temp = m.get(c);
            if(temp != null){
                for(int k : temp){
                    if(k == 0){
                        count[k]++;
                    }else{
                        count[k] += count[k-1];
                    }
                }
            }
        }
        return count[t.length()-1];
    }
	
    public void get_all_rec() throws Exception {
    	TransactionReceipt receipt = com.logAllReceipy(this.pulic_user_key).send();
    	List<Log> logs = receipt.getLogs();
       TransactionDecoder td  = TransactionDecoderFactory.buildTransactionDecoder(this.receipyABI, "");
       Map<String, List<List<EventResultEntity>>>  map_list_list = td.decodeEventReturnObject(logs);
       int receipiy_num;
       if (map_list_list.get("logReceipyEvent(bool,address,address,uint256)") == null) {
    	   receipiy_num = 0;
       }else {
    	   receipiy_num =  map_list_list.get("logReceipyEvent(bool,address,address,uint256)").size();   
       }
        
       System.out.println(receipiy_num);
       String reList = new String();
       String reerList = new String();
       for(int i = 0; i < receipiy_num; i++) {
    	   for(int j = 0; j < 4; j++)
    	   System.out.println(map_list_list.get("logReceipyEvent(bool,address,address,uint256)").get(i).get(j));
    	   String rece_name = main.getEnNameByPubUserKey((String) map_list_list.get("logReceipyEvent(bool,address,address,uint256)").get(i).get(1).getData());
    	   String reer_name = main.getEnNameByPubUserKey((String) map_list_list.get("logReceipyEvent(bool,address,address,uint256)").get(i).get(2).getData());
    	   int amount = Integer.valueOf((map_list_list.get("logReceipyEvent(bool,address,address,uint256)").get(i).get(3).getData().toString()));
    	   System.out.println(rece_name);
    	   System.out.println(reer_name);
    	   if(main.pub_username.equals(rece_name)) {
    		   String temp = "Owe：" + main.getGBKNameByEnName(reer_name) + " : " + String.valueOf(amount) + "元。\n";
    		   reList += temp;
    	   }else {
    		   String temp = "Having Recipy：" + main.getGBKNameByEnName(rece_name) + " : " + String.valueOf(amount) + "元。\n";
    		   reerList += temp;
    	   }
       }
       this.t_pane_1.setText(reList);
       this.t_pane.setText(reerList);
       System.out.println(map_list_list);
	}
	
	public void set_rec_data() {
		try {
//			frame.remove(textPane);
			get_all_rec();
//			frame.getContentPane().add(textPane);
			jfframe.repaint();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
}
