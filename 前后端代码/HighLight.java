import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Warning {

	JFrame jfframe;
	public String t;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Warning(String _text) {
		this.t = _text;
		initialize();
	}
	
	public static void ShowWarning(String s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Warning window = new Warning(s);
					window.jfframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jfframe = new JFrame();
		jfframe.setBounds(100, 100, 629, 279);
		jfframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfframe.getContentPane().setLayout(null);
		
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jfframe.dispose();
			}
		});
		button.setBounds(427, 173, 97, 23);
		
		JLabel lblNewLabel = new JLabel(this.t);
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 17));
		lblNewLabel.setBounds(54, 38, 495, 25);
		jfframe.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("通知：");
		label.setFont(new Font("黑体", Font.PLAIN, 17));
		label.setBounds(10, 10, 79, 30);
		jfframe.getContentPane().add(label);
		jfframe.getContentPane().add(button);
	}
}
