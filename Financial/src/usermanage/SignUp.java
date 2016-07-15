package usermanage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import login.Login;
import common.Data;



public class SignUp implements ActionListener,FocusListener {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_5;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	final JLabel lblNewLabel = new JLabel("");
	final JLabel label_1 = new JLabel("");
	
	final JLabel lblNewLabel_1 = new JLabel("");
	
	final JLabel lblNewLabel_2 = new JLabel("");
	
	/**
	 * Launch the application.
	 */
	public static void TEST() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignUp() {
		initialize();
	}
	public boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);


		return m.matches();
		}
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6CE8\u518C");
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		int screenWIDE = screensize.width;
		int screenHIGH = screensize.height;
		int FrameWith = 450;
		int FrameHith = 412;
		frame.setBounds((screenWIDE - FrameWith) / 2, (screenHIGH - FrameHith) / 2,
				FrameWith, FrameHith);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));

		JLabel label = new JLabel("\u6CE8\u518C\u540D");
		label.setBounds(29, 25, 61, 27);
		frame.getContentPane().add(label);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(300, 31, 124, 15);
		frame.getContentPane().add(lblNewLabel);
		
		
		label_1.setForeground(Color.RED);
		label_1.setBounds(300, 143, 124, 15);
		frame.getContentPane().add(label_1);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(300, 68, 124, 15);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(300, 105, 124, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(122, 28, 146, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("密码");
		label_2.setBounds(29, 132, 54, -19);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_3.setBounds(29, 105, 54, 15);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("密码");
		label_4.setBounds(29, 62, 54, 15);
		frame.getContentPane().add(label_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(122, 140, 146, 21);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel label_5 = new JLabel("\u90AE\u7BB1");
		label_5.setBounds(29, 143, 54, 15);
		frame.getContentPane().add(label_5);
		
		JButton button = new JButton("注册");
	
		button.addActionListener(this);

		button.setBounds(77, 262, 93, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		
		button_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Login window = null;
				try {
					window = new Login();
					window.frmLogin.setVisible(true);
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}				
				frame.setVisible(false);
				return ;
			}
		});
		button_1.setBounds(261, 262, 93, 23);
		frame.getContentPane().add(button_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(122, 56, 146, 27);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();

	
		passwordField_1.setBounds(122, 99, 146, 27);
		frame.getContentPane().add(passwordField_1);
		textField.addFocusListener(this);
		passwordField.addFocusListener(this);
		passwordField_1.addFocusListener(this);
		textField_5.addFocusListener(this);
	}
	     
    //文本框焦点事件处理
    public void focusGained(FocusEvent e) {
        // TODO Auto-generated method stub
        }
    
    public void focusLost(FocusEvent e) {
        // TODO Auto-generated method stub
        String nameString=textField.getText().trim();   
        String pass=String.valueOf(passwordField.getPassword());
        String repass=String.valueOf(passwordField_1.getPassword());
        String mailString=textField_5.getText().trim();   
         
        if (e.getSource()==textField) {
            if (nameString.equals("")) {
                System.out.println("用户名为空");
                lblNewLabel.setText("用户名不能为空！");
                lblNewLabel.setForeground(Color.red);  
                 
            }else {
                lblNewLabel.setText("正确");
                lblNewLabel.setForeground(Color.green);                
            }           
        }
        if (e.getSource()==passwordField) {
            if (pass.equals("")) {
                System.out.println("密码为空");
                lblNewLabel_1.setText("密码不能为空！");
                lblNewLabel_1.setForeground(Color.red);  
            }else {
            	lblNewLabel_1.setText("正确");
            	lblNewLabel_1.setForeground(Color.green);  
            }   
        }
        if (e.getSource()==passwordField_1) {
            if (!repass.equals(pass)||repass.equals("")) {
                System.out.println("两次密码不一致，请重新输入");
                lblNewLabel_2.setText("两次密码不一致！");
                lblNewLabel_2.setForeground(Color.red);  
            }else {
            	lblNewLabel_2.setText("正确");
            	lblNewLabel_2.setForeground(Color.green);    
            }   
        }
        if (e.getSource()==textField_5) {
            if (mailString.equals("")) {
                System.out.println("邮箱为空");
                label_1.setText("邮箱不能为空！");
                label_1.setForeground(Color.red);  
                 
            }
            if(!isEmail(mailString)){
            	System.out.println("邮箱格式不正确");
                label_1.setText("邮箱格式不正确！");
                label_1.setForeground(Color.red);              	
            }
            	else {
            		label_1.setText("正确");
            		label_1.setForeground(Color.green);                
            }           
        }
        
    }

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (Data.linkTest()) {
		 String btnstring = e.getActionCommand();
	      if (btnstring.equals("注册")) {

	          String nameString=textField.getText().trim();
	          String pass=String.valueOf(passwordField.getPassword());
	          String repass=String.valueOf(passwordField_1.getPassword());
	          String mailString=textField_5.getText().trim(); 
	         	          
	          String query;	        
	          
	          if (!nameString.equals("") && !pass.equals("") && pass.equals(repass) && !mailString.equals("")&&isEmail(mailString)) {
	              System.out.println("注册");
	              if(UserCheck.CheckUsername(nameString)){
	              query=
	                      "insert into user values("+
	                    		  "null,"+
	                              "'"+nameString+"',"+
	                              "'"+pass+"',"+
	                              "'"+mailString+"',"+
	                              "''"+
	                              ")";
	                               
	              System.out.println(query);
	              if(Data.modify(query))	              
	            	  JOptionPane.showMessageDialog(null,"注册成功","提示",JOptionPane.INFORMATION_MESSAGE);
	              else
	            	  JOptionPane.showMessageDialog(null,"注册失败请检查网络连接","提示",JOptionPane.INFORMATION_MESSAGE);
				
	              frame.setVisible(false);

	          }           
	         }else 
	         {
	        	 JOptionPane.showMessageDialog(null,"用户名重复","注册失败",JOptionPane.INFORMATION_MESSAGE);
	         }
	      }
		}else{
			JOptionPane.showMessageDialog(null,"连接服务器失败，请检查网络连接","提示",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

