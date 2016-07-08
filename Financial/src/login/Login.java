package login;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import common.DBcom;
import entity.User;
import function.InitMainData;
import usermanage.SignUp;

public class Login {

	public JFrame frmLogin;
	public JTextField name;
	public JPasswordField password;
	public static User user;
	public int ID = 0;
	public String username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 */
	public Login() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 */
	private void initialize() throws SQLException {
		frmLogin = new JFrame();
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		frmLogin.setTitle("\u767B\u9646");
		// frmLogin.setBounds(100, 100, 450, 309);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		int screenWIDE = screensize.width;
		int screenHIGH = screensize.height;
		int FrameWith = 450;
		int FrameHith = 309;
		frmLogin.setBounds((screenWIDE - FrameWith) / 2, (screenHIGH - FrameHith) / 2, FrameWith, FrameHith);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);

		JLabel lblid = new JLabel("\u7528\u6237ID\uFF1A");
		lblid.setFont(new Font("宋体", Font.PLAIN, 16));
		lblid.setBounds(80, 53, 66, 28);
		frmLogin.getContentPane().add(lblid);

		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(80, 116, 66, 28);
		frmLogin.getContentPane().add(label_1);

		name = new JTextField();
		name.setBounds(181, 53, 173, 28);
		frmLogin.getContentPane().add(name);
		name.setColumns(10);

		JButton login = new JButton("\u767B\u9646");
		login.setFont(new Font("宋体", Font.PLAIN, 16));
		login.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				int un = 0;
				username = name.getText();
				try {
					un = Integer.parseInt(name.getText());
				} catch (Exception ex) {

				}
				String pw = password.getText();
				String sql = "select * from user where id = "+un+" or name= '"+username+"'";
				DBcom db=new DBcom();
				Statement st=db.initState();
				ResultSet rs = null;
				try {
					rs = db.query(sql);
					user = null;
					while (rs.next()) {
						user = new User();
						ID = rs.getInt("id");
						username = rs.getString("name");
						user.setID(ID);
						user.setPassword(rs.getString("password"));
						user.setName(username);
					}
					if (user != null) {
						if (pw.equals(user.getPassword())) {
							InitMainData.MAIN();
							frmLogin.setVisible(false);

						} else {
							JOptionPane.showMessageDialog(null, "密码输入错！请重新输入！");
						}
					} else {
						JOptionPane.showMessageDialog(null, "该账号不存在！");
					}
				} catch (SQLException e2) {
					// TODO 自动生成的 catch 块
					e2.printStackTrace();
				}
				db.close(rs);
			}
		});
		login.setBounds(82, 191, 93, 34);
		frmLogin.getContentPane().add(login);

		JButton register = new JButton("\u6CE8\u518C");
		register.setFont(new Font("宋体", Font.PLAIN, 16));
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp.TEST();
				// frmLogin.setVisible(false);
			}
		});
		register.setBounds(257, 191, 93, 34);
		frmLogin.getContentPane().add(register);

		password = new JPasswordField();
		password.setEchoChar('*');
		password.setBounds(181, 116, 173, 28);
		frmLogin.getContentPane().add(password);

	}
}


