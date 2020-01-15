import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogIn {

	public Account auther;
	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public LogIn() {
		System.out.println("logInpage()");
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 545, 385);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Log in");
		label.setBounds(199, 27, 220, 24);
		frame.getContentPane().add(label);
		label.setFont(new Font("宋体", Font.PLAIN, 21));
		
		JLabel label_1 = new JLabel("User Name");
		label_1.setBounds(71, 88, 55, 72);
		frame.getContentPane().add(label_1);
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		textField_1 = new JTextField();
		textField_1.setBounds(215, 206, 179, 40);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		JButton btn_new_bu = new JButton("Log in");
		btn_new_bu.setBounds(273, 293, 121, 24);
		frame.getContentPane().add(btn_new_bu);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setBounds(71, 191, 37, 66);
		frame.getContentPane().add(label_2);
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		

		
		textField = new JTextField();
		textField.setBounds(215, 106, 179, 40);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		btn_new_bu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String un = textField.getText(), pd = textField_1.getText();
				Account acc = new Account();
				System.out.println(acc.check_login(un, pd));
				if (acc.check_login(un, pd)) {
					main.main_login(un);
					frame.dispose();
				}else {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Warning window = new Warning("Please check your User Name and Password！");
								window.jfframe.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
	}
	
	public List<Integer> find_acc(String S, String[] L) {
	    List<Integer> result = new ArrayList<>();
	    int size = L[0].length();
	    if (L.length == 0 || L[0].isEmpty() || L[0].length() > S.length()) 
	        return result;
	    Map<String, Integer> hist = new HashMap<>();
	    for (String w : L) {
	        hist.put(w, !hist.containsKey(w) ? 1 : hist.get(w)+1);
	    }
	    for (int i = 0; i+size*L.length <= S.length(); i++) {
	        if (hist.containsKey(S.substring(i, i+size))) {
	            Map<String, Integer> currHist = new HashMap<>();
	            for (int j = 0; j < L.length; j++) {
	                String word = S.substring(i+j*size, i+(j+1)*size);
	                currHist.put(word, !currHist.containsKey(word) ? 
	                        1 : currHist.get(word)+1);
	            }
	            if (currHist.equals(hist)) result.add(i);
	        }
	    }
	    return result;
	}
	
	public int reload(String word1, String word2) {
		if (word1.equals(word2)) {
		    return 0;
		}
		if (word1.length() == 0 || word2.length() == 0) {
		    return Math.abs(word1.length() - word2.length());
		}
		int[][] dp = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 0; i <= word1.length(); i++) {
		    dp[i][0] = i;
		}
		for (int i = 0; i <= word2.length(); i++) {
		    dp[0][i] = i;
		}
		string s = "try it";
		string t = "try again";
        if (s == null || s.length() == 0 || t == null || t.length() == 0) return "";
        int[] map = new int[256];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        int count = 0;
        int left = 0;
        int start = 0;
        int end = 0;
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i)]-- > 0) {
                count++;
            }
            while (count == t.length()) {
                if (minLen > i - left + 1) {
                    minLen = i - left + 1;
                    start = left;
                    end = i;
                }
                if (++map[s.charAt(left++)] > 0) {
                    count--;
                }
            }
        }
		int alphabetSize = 256;
		int T = t.length(); //The number of characters in t
		    int S = s.length(); 
		   
		    if (S < T) return "";
		int[] tCount = new int[alphabetSize];
		//Create  frequency table for the string t
		    for(char c : t.toCharArray()){
		        tCount[c]++;
		    }
		   
		  
		    int left = 0;
		    int right = 0;
		   
		    int N = s.length();
		    int min = Integer.MAX_VALUE;
		    int[] res = new int[]{-1, -1};
		   
		    while (right < N){
		        while(right < N && T > 0){
		            if (--tCount[s.charAt(right++)] >= 0) T--;
		        }
		        //At this point we are either at the end or we have a substring in S that covers all of T.
		    while (left < right && T == 0){
		        if (right-left < min){
		            min = right - left;
		            res = new int[]{left, right};
		       }
		        if (++tCount[s.charAt(left++)] > 0) T++;
		    }
		}
		 for (int i = 1; i <= word1.length(); i++) {
		     for (int j = 1; j <= word2.length(); j++) {
		         if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
		             dp[i][j] = dp[i - 1][j - 1];
		         } else {
		             dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
		         }
		     }
		 }
		 return dp[word1.length()][word2.length()];
}

}
