package function;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import login.Login;
import usermanage.UserUpdate;

class MainFrame extends JFrame {
	private PhotoPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel label_4;
	private short recexist=0;
	private short sear1exist=0;
	private short sear2exist=0;
	
	private RecordDialog rec;
	private SearchDialog search;
	private StatisticsDialog stat;

	// 余额
	public double getlabel_4() {
		return Double.parseDouble(label_4.getText());
	}

	public void setlabel_4(double balance) {
		this.label_4.setText(balance + "");
	}

	// 今日支出
	public double getLblNewLabel() {
		return Double.parseDouble(lblNewLabel.getText());
	}

	public void setLblNewLabel(double d) {
		this.lblNewLabel.setText(d + "");
	}

	// 今日收入
	public double getLblNewLabel_1() {
		return Double.parseDouble(lblNewLabel_1.getText());
	}

	public void setLblNewLabel_1(double v) {
		this.lblNewLabel_1.setText(v + "");
	}

	// 本月支出
	public double getLblNewLabel_2() {
		return Double.parseDouble(lblNewLabel_2.getText());
	}

	public void setLblNewLabel_2(double d) {
		this.lblNewLabel_2.setText(d + "");
	}

	// 本月收入
	public double getLblNewLabel_3() {
		return Double.parseDouble(lblNewLabel_3.getText());
	}

	public void setLblNewLabel_3(double v) {
		this.lblNewLabel_3.setText(v + "");
	}

	public MainFrame() {
		setResizable(false);
		setTitle("理财软件");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		int screenWIDE = screensize.width;
		int screenHIGH = screensize.height;
		int FrameWith = 400;
		int FrameHith = 310;
		setBounds((screenWIDE - FrameWith) / 2, (screenHIGH - FrameHith) / 2,
				FrameWith, FrameHith);
		// setBounds(100, 100, 392, 248);
		
		contentPane = new PhotoPanel(new ImageIcon("bg.jpg").getImage());
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("欢迎使用“理财软件”");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(10, 0, 234, 25);
		contentPane.add(label);

		JLabel label_1 = new JLabel("今日记录：");
		label_1.setBounds(29, 57, 73, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("本月记录：");
		label_2.setBounds(145, 57, 73, 15);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("余额：");
		label_3.setBounds(271, 57, 48, 15);
		contentPane.add(label_3);

		label_4 = new JLabel("0.00");
		label_4.setBounds(310, 57, 80, 15);
		contentPane.add(label_4);

		lblNewLabel = new JLabel("0.00");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(95, 57, 80, 15);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("0.00");
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setBounds(95, 82, 80, 15);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("0.00");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(209, 57, 80, 15);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("0.00");
		lblNewLabel_3.setForeground(Color.GREEN);
		lblNewLabel_3.setBounds(209, 82, 80, 15);
		contentPane.add(lblNewLabel_3);

		final JRadioButton radioButton = new JRadioButton("按月份");
		radioButton.setSelected(true);
		radioButton.setBounds(220, 123, 73, 23);
		contentPane.add(radioButton);

		final JRadioButton radioButton_1 = new JRadioButton("按种类");
		radioButton_1.setBounds(295, 123, 85, 23);
		contentPane.add(radioButton_1);

		ButtonGroup g1 = new ButtonGroup();
		g1.add(radioButton_1);
		g1.add(radioButton);

		final JRadioButton radioButton_2 = new JRadioButton("支出");
		radioButton_2.setSelected(true);
		radioButton_2.setBounds(68, 123, 62, 23);
		contentPane.add(radioButton_2);

		final JRadioButton radioButton_3 = new JRadioButton("收入");
		radioButton_3.setBounds(132, 123, 62, 23);
		contentPane.add(radioButton_3);

		ButtonGroup g2 = new ButtonGroup();
		g2.add(radioButton_2);
		g2.add(radioButton_3);
		JButton btnNewButton = new JButton("记一记");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(recexist==1)
				{	if (radioButton_2.isSelected()) {
						rec.setList(1);
						rec.setVisible(true);
					} else if (radioButton_3.isSelected()) {
						rec.setList(2);
						rec.setVisible(true);
					}
					rec.setTextNull();
				}
				else
				{
					rec = new RecordDialog(MainFrame.this);
					recexist=1;
					if (radioButton_2.isSelected()) {
						rec.setList(1);
						rec.setVisible(true);
					} else if (radioButton_3.isSelected()) {
						rec.setList(2);
						rec.setVisible(true);
					}
					rec.setTextNull();
				}
			}
		});
		btnNewButton.setBounds(80, 157, 93, 36);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("查一查");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				search=new SearchDialog(MainFrame.this);
				search.setVisible(true);
				
//				if (radioButton.isSelected()) {
//					if(sear2exist==1)
//					{	sear2.setTextNull();
//						sear2.setVisible(true);
//					}
//					else
//					{
//						sear2 = new SearchDialog2(MainFrame.this);
//						sear2exist=1;
//						sear2.setTextNull();
//						sear2.setVisible(true);
//					}
//				}
//
//				else if (radioButton_1.isSelected()) {
//					if(sear1exist==1)
//					{	sear1.setVisible(true);
//						sear1.setTextNull();
//					}
//					else
//					{
//						sear1 = new SearchDialog1(MainFrame.this);
//						sear1exist=1;
//						sear1.setVisible(true);
//						sear1.setTextNull();
//					}
//				}

			}
		});
		btnNewButton_1.setBounds(241, 157, 93, 36);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("支出统计图");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					stat = new StatisticsDialog(MainFrame.this);
					stat.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(155, 210, 100, 36);
		contentPane.add(btnNewButton_2);
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("设置");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("修改个人信息");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserUpdate.TEST1();
			}
		});
		JMenuItem tmNewMenuItem = new JMenuItem("注销登陆");
		mnNewMenu.add(tmNewMenuItem);
		tmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login window = null;
				try {
					window = new Login();
					window.frmLogin.setVisible(true);
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}				
				dispose();
			}
		});
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
	}

}