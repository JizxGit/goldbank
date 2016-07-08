package function;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import common.DBcom;
import common.Round;
import login.Login;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

class SearchDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JTextField incomeField;
	private JTextField expenseField;
	private JTextField balanceFiled;

	private JComboBox monthComboBox;
	private JComboBox yearComboBox;
	private JComboBox typeComboBox;

	private JCheckBox typeCheckBox;
	private JCheckBox timeCheckBox;

	JTable table;
	JScrollPane sp;

	private JLabel lblNewLabel;

	public void setTextNull() {
		expenseField.setText("");
		incomeField.setText("");
		// balanceFiled.setText("");
	}

	public SearchDialog(JFrame owner) {
		super(owner, "查询窗口", true);
		setTitle("查询窗口");
		setResizable(false);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		int screenWIDE = screensize.width;
		int screenHIGH = screensize.height;
		int FrameWith = 450;
		int FrameHith = 362;
		setBounds((screenWIDE - FrameWith) / 2, (screenHIGH - FrameHith) / 2, 450, 445);
		// setBounds(100, 100, 369, 362);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// 检查数据库驱动
		DBcom db = new DBcom();

		{

			timeCheckBox = new JCheckBox("时间：");
			timeCheckBox.setBounds(6, 20, 68, 23);
			contentPanel.add(timeCheckBox);

			timeCheckBox.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					if (!timeCheckBox.isSelected()) {
						yearComboBox.setEnabled(false);
						monthComboBox.setEnabled(false);
					} else {
						yearComboBox.setEnabled(true);
						monthComboBox.setEnabled(true);
					}
				}

			});

			typeCheckBox = new JCheckBox("种类：");
			typeCheckBox.setBounds(6, 63, 68, 23);
			contentPanel.add(typeCheckBox);

			typeCheckBox.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					if (!typeCheckBox.isSelected())
						typeComboBox.setEnabled(false);
					else
						typeComboBox.setEnabled(true);
				}

			});

			// 设置组合框
			{
				yearComboBox = new JComboBox();
				GregorianCalendar getMessageTime = new GregorianCalendar();
				int year = getMessageTime.get(Calendar.YEAR);
				// for(int i=0;)
				for (int i = 0; i <= 10; i++)
					yearComboBox.addItem("" + (year - i));
				yearComboBox.setBounds(80, 21, 90, 21);
				contentPanel.add(yearComboBox);
			}
			{
				monthComboBox = new JComboBox();
				for (int i = 1; i <= 12; i++)
					monthComboBox.addItem("" + i);
				monthComboBox.setBounds(180, 21, 60, 21);
				contentPanel.add(monthComboBox);
			}
			if (!timeCheckBox.isSelected()) {
				yearComboBox.setEnabled(false);
				monthComboBox.setEnabled(false);
			} else {
				yearComboBox.setEnabled(true);
				monthComboBox.setEnabled(true);
			}

			{
				typeComboBox = new JComboBox();
				String[] kind = { "餐饮", "电子产品", "服饰", "娱乐", "通信", "书籍", "日用品", "医疗", "旅游", "丢失", "工资", "奖金", "偶然获得",
						"租金收入", "股利股息", "社会福利", "其他支出", "其他收入" };
				for (int i = 0; i < kind.length; i++)
					typeComboBox.addItem(kind[i]);
				typeComboBox.setBounds(80, 65, 125, 21);
				contentPanel.add(typeComboBox);
			}
			if (!typeCheckBox.isSelected())
				typeComboBox.setEnabled(false);
			else
				typeComboBox.setEnabled(true);

			{
				JLabel label = new JLabel("总收入：");
				label.setBounds(35, 355, 54, 15);
				contentPanel.add(label);
			}
			{
				JLabel label = new JLabel("总支出：");
				label.setBounds(178, 355, 54, 15);
				contentPanel.add(label);
			}
			{
				incomeField = new JTextField();
				// textField.setEnabled(false);
				incomeField.setEditable(false);
				incomeField.setBounds(90, 352, 66, 21);
				contentPanel.add(incomeField);
				incomeField.setColumns(10);
			}
			{
				expenseField = new JTextField();
				// textField_1.setEnabled(false);
				expenseField.setEditable(false);
				expenseField.setBounds(227, 352, 66, 21);
				contentPanel.add(expenseField);
				expenseField.setColumns(10);
			}

			JButton okButton = new JButton("查询");
			okButton.setBounds(316, 25, 76, 53);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
			{
				lblNewLabel = new JLabel("不勾选条件默认为查询所有记录");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				lblNewLabel.setForeground(Color.RED);
				lblNewLabel.setBounds(50, 100, 335, 20);
				contentPanel.add(lblNewLabel);
			}

			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String query = "select * from economic where userid= " + Login.user.getID() + " ";
					if (timeCheckBox.isSelected()) {
						int year = Integer.parseInt(yearComboBox.getSelectedItem() + "");
						int month = Integer.parseInt(monthComboBox.getSelectedItem() + "");
						query += "and year='" + year + "'and month='" + month + "' ";
					}
					if (typeCheckBox.isSelected()) {
						String kd = (String) typeComboBox.getSelectedItem();
						query += "and kind='" + kd + "' ";
					}

					query += "order by id";

					if (!(timeCheckBox.isSelected() || typeCheckBox.isSelected()))
						query = "select * from economic where userid = " + Login.user.getID() + " order by id";

					Vector columnNames = new Vector();
					columnNames.add("日期");
					columnNames.add("支出");
					columnNames.add("收入");
					columnNames.add("当时余额");
					columnNames.add("种类");
					columnNames.add("具体内容");

					double amountin = 0, amountout = 0, balanceamount = 0;
					Vector<Vector<String>> rowData = new Vector();

					try {
						// 从数据库读取用户信息
						Statement st = db.initState();

						// 开始条件查询
						// rs = st.executeQuery(query);
						ResultSet rs = db.query(query);

						System.out.println(query);

						while (rs.next()) {
							// 将信息写到表格项中
							Vector<String> row = new Vector<String>();
							String s1 = rs.getInt("year") + "." + rs.getInt("month") + "." + rs.getInt("day") + " "
									+ rs.getString("time");
							double moneyout = rs.getDouble("moneyout");
							double moneyin = rs.getDouble("moneyin");
							double balance = rs.getDouble("balance");
							String s2 = rs.getString("kind");
							String s3 = rs.getString("detail");
							if (moneyin != 0)
								amountin = amountin + moneyin;
							if (moneyout != 0)
								amountout = amountout + moneyout;
							row.add(s1);
							row.add("" + Round.round(moneyout));
							row.add("" + Round.round(moneyin));
							row.add("" + Round.round(balance));
							row.add(s2);
							row.add(s3);
							rowData.add(row);
							System.out
									.println(s1 + " " + moneyout + " " + moneyin + " " + balance + " " + s2 + " " + s3);
						}
						db.close(rs);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					table = new JTable(rowData, columnNames);
					table.getColumnModel().getColumn(0).setPreferredWidth(190);

					// 把表格添加到JScrollPane中
					System.out.println("1");

					if (sp != null)
						sp.setVisible(false);
					sp = new JScrollPane(table);
					sp.setBounds(10, 150, 430, 152);
					contentPanel.add(sp);
					incomeField.setText(Round.round(amountin) + "");
					expenseField.setText(Round.round(amountout) + "");

				}
			});
		}

		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton cancelButton = new JButton("取消");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}