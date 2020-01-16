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

public class TransferReceipy {

	JFrame jfframe;
	public MainPage mp;
	private JTextField text_feild;
	public JComboBox com_box ;
	private JLabel label_1;
	private JComboBox com_box_1;

	public void TransferReceipy(BigInteger _amount) throws Exception {
		String selectedStr = (String) com_box.getSelectedItem();
		String alreadyTargetPubUserKey = main.getPubUserKey(selectedStr);
		String _to = (String) com_box_1.getSelectedItem();
		String targetPubUserKey = main.getPubUserKey(_to);
        TransactionReceipt receipt = this.mp.com.transferReceipy(alreadyTargetPubUserKey, mp.pulic_user_key, targetPubUserKey, _amount).send();
        List<Log> logs = receipt.getLogs();
        TransactionDecoder td  = TransactionDecoderFactory.buildTransactionDecoder(this.mp.receipyABI, "");
        Map<String, List<List<EventResultEntity>>>  map_list_list = td.decodeEventReturnObject(logs);
        System.out.println("iningienigeignei");
        System.out.println(map_list_list);
        if(map_list_list.get("transferReceipyFailedEvent(string)") != null) {
        	String data = (String) map_list_list.get("transferReceipyFailedEvent(string)").get(0).get(0).getData();
            if (data.equals("not believed error")){
            	Warning.ShowWarning("Denied！");
            }
        }
        this.mp.set_rec_data();
        

	}
	

	public TransferReceipy(MainPage _mp) {
		this.mp = _mp;
		initialize();
	}

	public int evaluate(String[] tokens) {
        Deque<Integer> s = new LinkedList<Integer>();
        s.push(Integer.parseInt(tokens[0]));
        for(int i = 1;i<tokens.length;i++){
            switch (tokens[i]){
                case "+":
                    s.push(s.pop()+s.pop());
                    break;
                case "-":
                    s.push(-1*(s.pop()-s.pop()));
                    break;
                case "*":
                    s.push(s.pop()*s.pop());
                    break;
                case "/":
                    int n1 = s.pop();
                    s.push(s.pop()/n1);
                    break;
                default:
                    s.push(Integer.parseInt(tokens[i]));
                    break;
            }
        }
        return s.pop();
    }
	public void test_2(ListNode head) {
        // base case
        if(head==null) return null;
        if(head.next==null) {
            TreeNode root = new TreeNode(head.val);
            return root;
        }

        ListNode slow = head, fast = head, pre = null;
        while(fast!=null && fast.next!=null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }


        pre.next = null;
        fast = slow.next;
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(fast);
        
 }
	public int test(String[] tokens) {
		Deque<Integer> stack = new LinkedList<>();
	    
		for (String token : tokens) {
	        BiFunction<Integer, Integer, Integer> op = ops.get(token);
	        if (op == null) stack.push(Integer.parseInt(token));
	        else stack.push(op.apply(stack.pop(), stack.pop()));
		}
	    
		return stack.pop();
	}
	private void initialize() {
		jfframe = new JFrame();
		jfframe.setBounds(100, 100, 450, 300);
		jfframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfframe.getContentPane().setLayout(null);
		
		
		
		
		text_feild = new JTextField();
		text_feild.setBounds(203, 154, 84, 21);
		jfframe.getContentPane().add(text_feild);
		text_feild.setColumns(10);
		
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
		com_box.setBounds(203, 41, 90, 23);
		jfframe.getContentPane().add(com_box);
		
		JLabel lblNewLabel = new JLabel("Transfer Debt：");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 17));
		lblNewLabel.setBounds(101, 146, 108, 34);
		jfframe.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("Transfer target");
		label.setFont(new Font("黑体", Font.PLAIN, 17));
		label.setBounds(102, 91, 97, 34);
		jfframe.getContentPane().add(label);
		
		
		label_1 = new JLabel("Transferee");
		label_1.setFont(new Font("黑体", Font.PLAIN, 17));
		label_1.setBounds(102, 34, 97, 34);
		jfframe.getContentPane().add(label_1);
		
		com_box_1 = new JComboBox(address);
		com_box_1.setBounds(203, 98, 90, 23);
		jfframe.getContentPane().add(com_box_1);
		
		
		
		JButton button = new JButton("Transfer Recipy");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TransferReceipy(new BigInteger(text_feild.getText()));
					jfframe.dispose();
					mp.get_all_rec();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(289, 196, 97, 23);
		jfframe.getContentPane().add(button);
	}
}
