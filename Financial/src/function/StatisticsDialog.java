package function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import common.DBcom;
import common.Round;
import login.Login;

class StatisticsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	
	public StatisticsDialog(JFrame owner) {
		super(owner, "支出统计图", true);
		setResizable(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		int screenWIDE = screensize.width;
		int screenHIGH = screensize.height;
		int FrameWith = 376;
		int FrameHith = 552;
		setBounds((screenWIDE - FrameWith) / 2, (screenHIGH - FrameHith) / 2,
				FrameWith, FrameHith);
		//setBounds(100, 100, 376, 552);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel label = new JLabel("支出统计图：");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(23, 24, 108, 39);
		contentPanel.add(label);

		JLabel lblNewLabel = new JLabel("\u9910\u996E\uFF1A");
		lblNewLabel.setBounds(23, 90, 54, 15);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u7535\u5B50\u4EA7\u54C1\uFF1A");
		lblNewLabel_1.setBounds(23, 120, 80, 15);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\u670D\u9970\uFF1A");
		lblNewLabel_2.setBounds(23, 150, 54, 15);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\u5A31\u4E50\uFF1A");
		lblNewLabel_3.setBounds(23, 180, 54, 15);
		contentPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\u901A\u4FE1\uFF1A");
		lblNewLabel_4.setBounds(23, 210, 54, 15);
		contentPanel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("\u4E66\u7C4D\uFF1A");
		lblNewLabel_5.setBounds(23, 240, 54, 15);
		contentPanel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("\u65E5\u7528\u54C1\uFF1A");
		lblNewLabel_6.setBounds(23, 270, 54, 15);
		contentPanel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("\u533B\u7597\uFF1A");
		lblNewLabel_7.setBounds(23, 300, 54, 15);
		contentPanel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("\u65C5\u6E38\uFF1A");
		lblNewLabel_8.setBounds(23, 330, 54, 15);
		contentPanel.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("\u4E22\u5931\uFF1A");
		lblNewLabel_9.setBounds(23, 360, 54, 15);
		contentPanel.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("\u5176\u4ED6\uFF1A");
		lblNewLabel_10.setBounds(23, 390, 54, 15);
		contentPanel.add(lblNewLabel_10);
		
		DBcom db=new DBcom();
		
		// 获得数据库信息，统计百分比
		double progressBar_value = 0;
		double progressBar1_value = 0;
		double progressBar2_value = 0;
		double progressBar3_value = 0;
		double progressBar4_value = 0;
		double progressBar5_value = 0;
		double progressBar6_value = 0;
		double progressBar7_value = 0;
		double progressBar8_value = 0;
		double progressBar9_value = 0;
		double progressBar10_value = 0;

		double sum = 0;
		
		String[] kind = { "餐饮", "电子产品", "服饰", "娱乐", "通信", "书籍", "日用品",
				"医疗", "旅游", "丢失","其他" };
		Statement st=db.initState();
		try {
			// 从数据库读取用户信息
			
			//修改
			ResultSet rs = st.executeQuery("select * from economic where kind='"+kind[0]+"' and userid = "+Login.user.getID()+" order by id");
			while (rs.next()) {
				progressBar_value =progressBar_value+ rs.getDouble("moneyout");
				
			}
			rs = st.executeQuery("select * from economic where kind='"+kind[1]+"' and userid = "+Login.user.getID()+" order by id");
			while (rs.next()) {
				progressBar1_value =progressBar1_value+ rs.getDouble("moneyout");
				
			}
			rs = st.executeQuery("select * from economic where kind='"+kind[2]+"' and userid = "+Login.user.getID()+" order by id");
			while (rs.next()) {
				progressBar2_value =progressBar2_value+ rs.getDouble("moneyout");
				
			}
			rs = st.executeQuery("select * from economic where kind='"+kind[3]+"' and userid = "+Login.user.getID()+" order by id");
			while (rs.next()) {
				progressBar3_value =progressBar3_value+ rs.getDouble("moneyout");
				
			}
			rs = st.executeQuery("select * from economic where kind='"+kind[4]+"' and userid = "+Login.user.getID()+" order by id");
			while (rs.next()) {
				progressBar4_value =progressBar4_value+ rs.getDouble("moneyout");
				
			}
			rs = st.executeQuery("select * from economic where kind='"+kind[5]+"' and userid = "+Login.user.getID()+" order by id");
			while (rs.next()) {
				progressBar5_value =progressBar5_value+ rs.getDouble("moneyout");
				
			}
			rs = st.executeQuery("select * from economic where kind='"+kind[6]+"' and userid = "+Login.user.getID()+" order by id");
			while (rs.next()) {
				progressBar6_value =progressBar6_value+ rs.getDouble("moneyout");
				
			}
			rs = st.executeQuery("select * from economic where kind='"+kind[7]+"' and userid = "+Login.user.getID()+" order by id");
			while (rs.next()) {
				progressBar7_value =progressBar7_value+ rs.getDouble("moneyout");
				
			}
			rs = st.executeQuery("select * from economic where kind='"+kind[8]+"' and userid = "+Login.user.getID()+" order by id");
			while (rs.next()) {
				progressBar8_value =progressBar8_value+ rs.getDouble("moneyout");
				
			}rs = st.executeQuery("select * from economic where kind='"+kind[9]+"' and userid = "+Login.user.getID()+" order by id");
			while (rs.next()) {
				progressBar9_value =progressBar9_value+ rs.getDouble("moneyout");
				
			}rs = st.executeQuery("select * from economic where kind='"+kind[10]+"' and userid = "+Login.user.getID()+" order by id");
			while (rs.next()) {
				progressBar10_value =progressBar10_value+ rs.getDouble("moneyout");
				
			}
			sum=progressBar_value+progressBar1_value+progressBar2_value+progressBar3_value+progressBar4_value+progressBar5_value+progressBar6_value+progressBar7_value+progressBar8_value+progressBar9_value+progressBar10_value;
			db.close(rs);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		JLabel lable = new JLabel(Round.round(progressBar_value)+"元");
		lable.setBounds(270, 90, 54, 15);
		contentPanel.add(lable);
		
		JLabel lable_1 = new JLabel(Round.round(progressBar1_value)+"元");
		lable_1.setBounds(270, 120, 54, 15);
		contentPanel.add(lable_1);
		
		JLabel lable_2 = new JLabel(Round.round(progressBar2_value)+"元");
		lable_2.setBounds(270, 150, 54, 15);
		contentPanel.add(lable_2);
		
		JLabel lable_3 = new JLabel(Round.round(progressBar3_value)+"元");
		lable_3.setBounds(270, 180, 54, 15);
		contentPanel.add(lable_3);
		
		JLabel lable_4 = new JLabel(Round.round(progressBar4_value)+"元");
		lable_4.setBounds(270, 210, 54, 15);
		contentPanel.add(lable_4);
		
		JLabel lable_5 = new JLabel(Round.round(progressBar5_value)+"元");
		lable_5.setBounds(270, 240, 54, 15);
		contentPanel.add(lable_5);
		
		JLabel lable_6 = new JLabel(Round.round(progressBar6_value)+"元");
		lable_6.setBounds(270, 270, 54, 15);
		contentPanel.add(lable_6);
		
		JLabel lable_7 = new JLabel(Round.round(progressBar7_value)+"元");
		lable_7.setBounds(270, 300, 54, 15);
		contentPanel.add(lable_7);
		
		JLabel lable_8 = new JLabel(Round.round(progressBar8_value)+"元");
		lable_8.setBounds(270, 330, 54, 15);
		contentPanel.add(lable_8);
		
		JLabel lable_9 = new JLabel(Round.round(progressBar9_value)+"元");
		lable_9.setBounds(270, 360, 54, 15);
		contentPanel.add(lable_9);
		
		JLabel lable_10 = new JLabel(Round.round(progressBar10_value)+"元");
		lable_10.setBounds(270, 390, 54, 15);
		contentPanel.add(lable_10);
		
	
		int lengthOfBar = 100;
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(113, 90, 146, 14);
		progressBar.setStringPainted(true);
		progressBar.setValue((int) (progressBar_value / sum * lengthOfBar));
		contentPanel.add(progressBar);

		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setBounds(113, 120, 146, 14);
		progressBar_1.setStringPainted(true);
		progressBar_1.setValue((int) (progressBar1_value / sum * lengthOfBar));
		contentPanel.add(progressBar_1);

		JProgressBar progressBar_2 = new JProgressBar();
		progressBar_2.setBounds(113, 150, 146, 14);
		progressBar_2.setStringPainted(true);
		progressBar_2.setValue((int) (progressBar2_value / sum * lengthOfBar));
		contentPanel.add(progressBar_2);

		JProgressBar progressBar_3 = new JProgressBar();
		progressBar_3.setBounds(113, 180, 146, 14);
		progressBar_3.setStringPainted(true);
		progressBar_3.setValue((int) (progressBar3_value / sum * lengthOfBar));
		contentPanel.add(progressBar_3);

		JProgressBar progressBar_4 = new JProgressBar();
		progressBar_4.setBounds(113, 210, 146, 14);
		progressBar_4.setStringPainted(true);
		progressBar_4.setValue((int) (progressBar4_value / sum * lengthOfBar));
		contentPanel.add(progressBar_4);

		JProgressBar progressBar_5 = new JProgressBar();
		progressBar_5.setBounds(113, 240, 146, 14);
		progressBar_5.setStringPainted(true);
		progressBar_5.setValue((int) (progressBar5_value / sum * lengthOfBar));
		contentPanel.add(progressBar_5);

		JProgressBar progressBar_6 = new JProgressBar();
		progressBar_6.setBounds(113, 270, 146, 14);
		progressBar_6.setStringPainted(true);
		progressBar_6.setValue((int) (progressBar6_value / sum * lengthOfBar));
		contentPanel.add(progressBar_6);

		JProgressBar progressBar_7 = new JProgressBar();
		progressBar_7.setBounds(113, 300, 146, 14);
		progressBar_7.setStringPainted(true);
		progressBar_7.setValue((int) (progressBar7_value / sum * lengthOfBar));
		contentPanel.add(progressBar_7);

		JProgressBar progressBar_8 = new JProgressBar();
		progressBar_8.setBounds(113, 330, 146, 14);
		progressBar_8.setStringPainted(true);
		progressBar_8.setValue((int) (progressBar8_value / sum * lengthOfBar));
		contentPanel.add(progressBar_8);

		JProgressBar progressBar_9 = new JProgressBar();
		progressBar_9.setBounds(113, 360, 146, 14);
		progressBar_9.setStringPainted(true);
		progressBar_9.setValue((int) (progressBar9_value / sum * lengthOfBar));
		contentPanel.add(progressBar_9);

		JProgressBar progressBar_10 = new JProgressBar();
		progressBar_10.setBounds(113, 390, 146, 14);
		progressBar_10.setStringPainted(true);
		progressBar_10.setValue((int) (progressBar10_value / sum * lengthOfBar));
		contentPanel.add(progressBar_10);

		JLabel label_1 = new JLabel("支出总额：");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("宋体", Font.PLAIN, 15));
		label_1.setBounds(81, 434, 88, 18);

		contentPanel.add(label_1);

		textField = new JTextField();
		textField.setBounds(169, 433, 66, 21);
		textField.setEditable(false);
		textField.setText(Round.round(sum)+"元");
		contentPanel.add(textField);
		textField.setColumns(10);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("确定");
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