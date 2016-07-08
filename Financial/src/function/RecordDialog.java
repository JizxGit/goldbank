package function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import common.DBcom;
import login.Login;

class RecordDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JLabel warn1;
	private JLabel warn2;
	JComboBox comboBox = new JComboBox();
	private int k;

	private int year, month, day;
	private double in, out, balance;
	private String detail, kind, time;
	private String[] kind1 = { "餐饮", "电子产品", "服饰", "娱乐", "通信", "书籍", "日用品",
			"医疗", "旅游", "丢失", "其他支出" };
	private String[] kind2 = { "工资", "奖金", "偶然获得", "租金收入", "股利股息", "社会福利",
			"其他收入" };

	public void setList(int k) {
		if (k == 1) {
			this.k = k;
			comboBox.removeAllItems();
			for (int i = 0; i < kind1.length; i++)
				comboBox.addItem(kind1[i]);
		} else if (k == 2) {
			this.k = k;
			comboBox.removeAllItems();
			for (int i = 0; i < kind2.length; i++)
				comboBox.addItem(kind2[i]);
		}
	}

	public void setTextNull() {
		textField_1.setText("");
		textField.setText("");
		warn1.setText("");
		warn2.setText("");
	}

	public RecordDialog(final MainFrame owner) {
		super(owner, "记账窗口", true);
		// setTitle("记账窗口");
		setResizable(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		int screenWIDE = screensize.width;
		int screenHIGH = screensize.height;
		int FrameWith = 250;
		int FrameHith = 208;
		setBounds((screenWIDE - FrameWith) / 2, (screenHIGH - FrameHith) / 2,
				FrameWith, FrameHith);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		DBcom db=new DBcom();
		
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("种类：");
			label.setBounds(10, 26, 54, 15);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("金额：");
			label.setBounds(10, 51, 54, 15);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("具体内容：");
			label.setBounds(10, 76, 71, 15);
			contentPanel.add(label);
		}

		comboBox.setBounds(77, 23, 81, 21);
		contentPanel.add(comboBox);

		warn1 = new JLabel();
		warn1.setForeground(Color.RED);
		warn1.setBounds(160, 50, 150, 21);
		contentPanel.add(warn1);

		warn2 = new JLabel();
		warn2.setForeground(Color.RED);
		warn2.setBounds(160, 71, 150, 21);
		contentPanel.add(warn2);

		textField = new JTextField();
		textField.setBounds(77, 48, 81, 21);
		contentPanel.add(textField);
		textField.setColumns(10);

		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)
						|| keyChar == KeyEvent.VK_PERIOD
						|| keyChar == KeyEvent.VK_BACK_SPACE || keyChar == KeyEvent.VK_SHIFT)) {
					warn1.setText("请输入数字!");
					e.consume();
				} else {
					warn1.setText("");
				}
				if (textField.getText().length() > 10) {
					e.consume();
					warn1.setText("数值位数过多！");
				}
			}
		});

		textField_1 = new JTextField();
		textField_1.setBounds(77, 73, 81, 21);
		textField_1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (textField_1.getText().length() == 0)
					warn2.setText("内容不能为空!");
				else
					warn2.setText("");
			}
		});
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确定");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!(textField_1.getText().equals(""))
								&& !(textField.getText().equals(""))) {
							Statement st=db.initState();
							try {	
//								ResultSet rs1 = st
//										.executeQuery("select * from economic order by id DESC");
								ResultSet rs1=db.query("select * from economic where userid="+Login.user.getID()+" order by id DESC");
								if(rs1.first()){
									balance = rs1.getDouble("balance");
								}else{
									System.out.println("您还未有记录！");
								}
								
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							GregorianCalendar getMessageTime = new GregorianCalendar();
							year = getMessageTime.get(Calendar.YEAR);
							month = getMessageTime.get(Calendar.MONTH) + 1;
							day = getMessageTime.get(Calendar.DATE);
							kind = (String) comboBox.getSelectedItem();
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat(
									"HHmmss");
							time = sdf.format(d);
							// time=getMessageTime.get(Calendar.HOUR_OF_DAY)
							// +""+ getMessageTime.get(Calendar.MINUTE) +
							// ""+getMessageTime.get(Calendar.SECOND);
							detail = textField_1.getText();
							if (k == 1) {
								try {
									out = (double) Double.parseDouble(textField
											.getText());
								} catch (Exception e1) {
									JOptionPane.showMessageDialog(null,
											"请输入数值", "提示",
											JOptionPane.INFORMATION_MESSAGE);
								}
								balance = balance - out;
								try {
									st.executeUpdate("insert into economic (year,month,day,time,kind,moneyout,balance,detail,userid) values("
											+ year
											+ ","
											+ month
											+ ","
											+ day
											+ ",'"
											+ time
											+ "','"
											+ kind
											+ "','"
											+ out
											+ "','"
											+ balance
											+ "','" + detail + "',"+Login.user.getID()+")");
									db.close(null);

									owner.setLblNewLabel(owner.getLblNewLabel()
											+ out);
									owner.setLblNewLabel_2(owner
											.getLblNewLabel_2() + out);
									owner.setlabel_4(balance);

									JOptionPane.showMessageDialog(null, "记录成功",
											"提示",
											JOptionPane.INFORMATION_MESSAGE);
									setVisible(false);
									textField.setText("");
									textField_1.setText("");
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							} else if (k == 2) {
								try {
									in = Double.parseDouble(textField.getText());
								} catch (Exception e1) {
									JOptionPane.showMessageDialog(null,
											"请输入数值", "提示",
											JOptionPane.INFORMATION_MESSAGE);
								}

								balance = balance + in;
								try {
									st.executeUpdate("insert into economic (year,month,day,time,kind,moneyin,balance,detail,userid) values("
											+ year
											+ ","
											+ month
											+ ","
											+ day
											+ ",'"
											+ time
											+ "','"
											+ kind
											+ "','"
											+ in
											+ "','"
											+ balance
											+ "','" + detail + "',"+Login.user.getID()+")");
									db.close(null);

									owner.setLblNewLabel_1(owner
											.getLblNewLabel_1() + in);
									owner.setLblNewLabel_3(owner
											.getLblNewLabel_3() + in);
									owner.setlabel_4(balance);

									JOptionPane.showMessageDialog(null, "记录成功",
											"提示",
											JOptionPane.INFORMATION_MESSAGE);
									setVisible(false);
									textField.setText("");
									textField_1.setText("");
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}
						} else {
							if (textField.getText().equals(""))
								warn1.setText("内容不能为空!");
							if (textField_1.getText().equals(""))
								warn2.setText("内容不能为空!");
						}
					}
				});
			}
			{
				JButton cancelButton = new JButton("取消");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
			}
		}
	}
}