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

public class DeclineMoney {

	public String PubUserKey;
	public String ABI;
	public Company company;
	public MainPage mpt;
	public JFrame frame;
	private JTextField text_ferld;
	
	public void DecreaseMoney(BigInteger _money) throws Exception {
	       TransactionReceipt receipt = this.company.moneyDecrease(this.PubUserKey, _money).send();
	       List<Log> logs = receipt.getLogs();
	       TransactionDecoder td  = TransactionDecoderFactory.buildTransactionDecoder(this.ABI, "");
	       Map<String, List<List<EventResultEntity>>>  map_list_list = td.decodeEventReturnObject(logs);
	       System.out.println(map_list_list);
	       //return  ((BigInteger) map_list_list.get("SearchMoneyEvent(uint256)").get(0).get(0).getData()).intValue();
	}
	public ListNode reverse(ListNode head, int m, int n) {
		ListNode dummy = new ListNode(0), oldTail = dummy, newTail = head, newHead = null;
		dummy.next = head;
		
		for (int i = 1; i <= n; i++) {
		    if (i < m) {
		        oldTail = oldTail.next;
		        head = head.next;
		        newTail = head;
		    }
		    else {
		        ListNode cur = head;
	            head = head.next;
	            cur.next = newHead;
	            newHead = cur;
		    }
		}
	    
	    oldTail.next = newHead;
	    if (newTail != null) newTail.next = head;
	    
	    return dummy.next;
	}

	public DeclineMoney(String _userkey, String abi, Company _company, MainPage _mpt) {
		this.company = _company;
		this.ABI = abi;
		this.PubUserKey = _userkey;
		this.mpt = _mpt;
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		text_ferld = new JTextField();
		text_ferld.setBounds(88, 83, 143, 41);
		frame.getContentPane().add(text_ferld);
		text_ferld.setColumns(10);
		
		JButton button = new JButton("Confirm");
		JLabel label_1 = new JLabel("RMB");
		label_1.setFont(new Font("黑体", Font.PLAIN, 17));
		label_1.setBounds(241, 96, 68, 28);
		frame.getContentPane().add(label_1);

		button.setFont(new Font("宋体", Font.PLAIN, 17));
		button.setBounds(264, 92, 97, 32);
		frame.getContentPane().add(button);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DecreaseMoney(new BigInteger(String.valueOf(text_ferld.getText())));
					mpt.set_bank_account_page();
					frame.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JLabel label = new JLabel("Withdrawal deposit");
		label.setFont(new Font("黑体", Font.PLAIN, 17));
		label.setBounds(20, 96, 68, 28);
		frame.getContentPane().add(label);
		

	}
	public ListNode reversere(ListNode head, int m, int n) {
	    ListNode current = head;
	    ListNode reverseHead = null;
	    ListNode dummy = null;
	    ListNode tail = new ListNode(0);
	    for (int i = 1; i <= n; i++) {
	        if (i == m - 1) {
	            reverseHead = current;
	        } else if (i >= m) {
	            if (i == m)
	                tail = current;
	            ListNode node = current;
	            current = current.next;
	            node.next = dummy;
	            dummy = node;
	            continue;
	        }
	        current = current.next;
	    }
	    tail.next = current;
	    if (reverseHead == null)
	        return dummy;
	    else
	        reverseHead.next = dummy;
	    return head;
	}
	public List<String> restore_money(String s) {
	    List<String> ret = new ArrayList<>();
	    search(s, 0, 0, "", ret);
	    return ret;
	}

	private void search(String s, int idx, int c, String path, List<String> ret) {
	    if (c >= 4) {
	        if (idx == s.length()) {
	            ret.add(path.substring(0, path.length()-1));
	        }
	        return;
	    }
	    for (int i = idx+1; i <= s.length(); i++) {
	        if (isValid(s.substring(idx, i))) {
	            search(s, i, c+1, path + s.substring(idx, i) + '.', ret);
	        }
	    }
	}

	private boolean isValid(String s) {
	    if (s.startsWith("0") && !s.equals("0")) {
	        return false;
	    }
	    return s.length() < 4 && 0 <= Integer.valueOf(s) && Integer.valueOf(s) < 256;
	}

}
