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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import entity.User;
import function.InitMainData;
 
 

//class newDBHelper {  
//    public static final String url = "jdbc:mysql://127.0.0.1:3306/economic";  
//    public static final String name = "com.mysql.jdbc.Driver";  
//    public static final String user = "com";  
//    public static final String password ="123456";  
//  
//    public Connection conn = null;  
//    public PreparedStatement pst = null;  
//  
//    public newDBHelper(String sql) {  
//        try {  
//            Class.forName(name);//指定连接类型  
//            conn = DriverManager.getConnection(url, user, password);//获取连接  
//            pst = conn.prepareStatement(sql);//准备执行语句  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        try {
//			pst.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        
//    }  
//  
//    public void close() {  
//        try {  
//            this.conn.close();  
//            this.pst.close();  
//        } catch (SQLException e) {  
//            e.printStackTrace();  
//        }  
//    }  
//}  


public class UserUpdate implements ActionListener,KeyListener ,FocusListener {
	final String aid = Login.user.getID()+"";
	private JFrame frame;
	private User user;
	private JTextField textField;
	private JTextField textField_5;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	final JLabel lblNewLabel = new JLabel("");
	final JLabel label_1 = new JLabel("");
	final JLabel label_7 = new JLabel("");
	final JLabel lblNewLabel_1 = new JLabel("");
	
	final JLabel lblNewLabel_2 = new JLabel("");
	private JTextField textField_2;
	String bpassword;
	private JPasswordField passwordField_2;
	/**
	 * Launch the application.
	 */
	public static void TEST1() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserUpdate window = new UserUpdate();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public UserUpdate() {
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
		frame.getContentPane().setForeground(Color.RED);
		frame.setTitle("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		//frame.setBounds(100, 100, 450, 412);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		int screenWIDE = screensize.width;
		int screenHIGH = screensize.height;
		int FrameWith = 410;
		int FrameHith = 380;
		frame.setBounds((screenWIDE - FrameWith) / 2, (screenHIGH - FrameHith) / 2,
				FrameWith, FrameHith);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));

		JLabel label = new JLabel("\u6CE8\u518C\u540D");
		label.setBounds(29, 43, 61, 27);
		frame.getContentPane().add(label);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(300, 55, 124, 15);
		frame.getContentPane().add(lblNewLabel); 
//		Connection conn = null;
		
//		try {  
//				Class.forName("com.mysql.jdbc.Driver");//指定连接类型  
//				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/economic", "com", "123456");//获取连接  
//				String sql="select * from user where id ="+ aid;
//				
//				PreparedStatement pst = conn.prepareStatement(sql);//准备执行语句  
//				ResultSet rs = pst.executeQuery();
//				while (rs.next())
//				{
//					bpassword = rs.getString("password");
//				}
//		} catch (Exception e1) {  
//            e1.printStackTrace();  
//        }  
		
		
		String query="select * from user where id ="+ aid;
		user=Data.login(query);
		bpassword = user.getPassword();
		String aname= user.getName();
		
		
		label_1.setForeground(Color.RED);
		label_1.setBounds(300, 212, 124, 15);
		frame.getContentPane().add(label_1);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(300, 123, 124, 15);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(300, 169, 124, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(122, 46, 146, 21);
		
//		String sql="select name from user where id ="+ aid;
//		String aname = null;
		
		
		
//		try {  
//			PreparedStatement pst = conn.prepareStatement(sql);//准备执行语句  
//			ResultSet rs = pst.executeQuery();
//			while (rs.next())
//			{
//				aname = rs.getString("name");
//			}
//		 }catch (Exception e1) {  
//             e1.printStackTrace();  
//         }  
		
		textField.setText(aname);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u65B0\u5BC6\u7801");
		label_2.setBounds(29, 132, 54, -19);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_3.setBounds(29, 169, 54, 15);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u65B0\u5BC6\u7801");
		label_4.setBounds(29, 123, 54, 15);
		frame.getContentPane().add(label_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(122, 206, 146, 21);
//		String sql2="select mail from user where id ="+ aid;
		
		String amail = user.getMail();
		
//		try {  
//			PreparedStatement pst = conn.prepareStatement(sql2);//准备执行语句  
//			ResultSet rs = pst.executeQuery();
//			while (rs.next())
//			{
//				amail = rs.getString("mail");
//			}
//		 }catch (Exception e1) {  
//             e1.printStackTrace();  
//         }  
		textField_5.setText(amail);
		
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel label_5 = new JLabel("\u90AE\u7BB1");
		label_5.setBounds(29, 209, 54, 15);
		frame.getContentPane().add(label_5);
		
		JButton button = new JButton("保存更改");
		/*button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String astring="" ;
				String aname=textField.getText() ;
				String aid=textField_1.getText() ;
				String apwd=passwordField.getText() ;
				String amail=textField_5.getText() ;
				astring="insert into user values("+
						aid+","+
                        "'"+aname+"',"+
                        "'"+apwd+"',"+
                        "'"+amail+"',"+
                        "''"+
                        ");";
				DBHelper a = new DBHelper(astring);
				frame.setVisible(false);
				
			}
		});*/
		button.addActionListener(this);

		button.setBounds(77, 262, 93, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.setActionCommand("Cancel");
		button_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				return ;
			}
		});
		button_1.setBounds(261, 262, 93, 23);
		frame.getContentPane().add(button_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(122, 118, 146, 27);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();

	
		passwordField_1.setBounds(122, 163, 146, 27);
		frame.getContentPane().add(passwordField_1);
		
		JLabel label_6 = new JLabel("\u539F\u5BC6\u7801");
		label_6.setBounds(29, 80, 54, 15);
		frame.getContentPane().add(label_6);
		
		JLabel lblid = new JLabel("\u7528\u6237ID");
		lblid.setBounds(29, 18, 54, 15);
		frame.getContentPane().add(lblid);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setText(aid);
		textField_2.setBounds(122, 15, 146, 21);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
		label_7.setForeground(Color.RED);
		label_7.setBounds(300, 90, 124, 15);
		frame.getContentPane().add(label_7);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(122, 77, 146, 21);
		frame.getContentPane().add(passwordField_2);
		passwordField_2.addFocusListener(this);
		textField.addFocusListener(this);
		passwordField.addFocusListener(this);
		passwordField_1.addFocusListener(this);
		textField_5.addFocusListener(this);

	}
	
	public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
         
    }
	public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
         
    }
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
         
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
        String apass=String.valueOf(passwordField_2.getPassword());
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
        if (e.getSource()==passwordField_2) {
        	System.out.println(apass);
        	System.out.println(bpassword);
            if (!apass.equals(bpassword)) {
            	
                System.out.println("密码错误");
                label_7.setText("原密码错误");
                label_7.setForeground(Color.red);  
                 
            }else {
            	label_7.setText("正确");
            	label_7.setForeground(Color.green);
                
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
		 String btnstring = e.getActionCommand();
	      if (btnstring.equals("保存更改")) {
	          //System.out.println("提交");
	           
	          String nameString=textField.getText().trim();
	          String pass=String.valueOf(passwordField.getPassword());
	          String repass=String.valueOf(passwordField_1.getPassword());
	          String mailString=textField_5.getText().trim(); 
	          String apass=String.valueOf(passwordField_2.getPassword());
	          String sql="select count(*) from user";
	          int id=0;

	          if (!nameString.equals("") && !pass.equals("") && pass.equals(repass) && !mailString.equals("")&&apass.equals(bpassword)&&isEmail(mailString)) {
	        	  if(UserCheck.CheckUsername(nameString)){
	        		  
		        	  
		        	  System.out.println("更新");
		              String query=
		                      "update user set name = '"+nameString+"',password='"+pass+"',mail='"+mailString+"' where id="+aid;
		                               
		              System.out.println(sql);
		              
		              System.out.println(query);
		              if(Data.modify(query))	  
		            	  JOptionPane.showMessageDialog(null,"保存成功","提示",JOptionPane.INFORMATION_MESSAGE);
		              //newDBHelper b = new newDBHelper(sql);
		              else
		            	  JOptionPane.showMessageDialog(null,"请检查网络连接","失败",JOptionPane.INFORMATION_MESSAGE);
		              
					frame.setVisible(false);
		        	  }else{
		        		  JOptionPane.showMessageDialog(null,"用户名重复","失败",JOptionPane.INFORMATION_MESSAGE);
		        	  }

	          }           
	      }
		
	}
}

