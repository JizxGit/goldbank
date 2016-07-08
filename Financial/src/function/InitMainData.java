package function;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import common.DBcom;
import common.Round;
import login.Login;

public class InitMainData {

	public static void MAIN() {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			GregorianCalendar getMessageTime = new GregorianCalendar();
			int year = getMessageTime.get(Calendar.YEAR);
			int month = getMessageTime.get(Calendar.MONTH) + 1;
			int day = getMessageTime.get(Calendar.DATE);

			public void run() {
				DBcom db = new DBcom();
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					// 初始化界面数据
					double balance = 0, amountinday = 0, amountoutday = 0, amountinmonth = 0, amountoutmonth = 0;
					try {
						// 从数据库读取用户信息
						Statement st = db.initState();
						String sql = "select * from economic where year='" + year + "'and month='" + month
								+ "' and userid="+Login.user.getID()+" order by id";

						ResultSet rs = db.query(sql);
						while (rs.next()) {
							//
							double moneyout = rs.getDouble("moneyout");
							double moneyin = rs.getDouble("moneyin");
							if (moneyin != 0)
								amountinmonth = amountinmonth + moneyin;
							if (moneyout != 0)
								amountoutmonth = amountoutmonth + moneyout;
						}
						rs = st.executeQuery("select * from economic where year='" + year + "'and month='" + month
								+ "'and day='" + day + "' and userid="+Login.user.getID()+" order by id");
						while (rs.next()) {
							double moneyout = rs.getDouble("moneyout");
							double moneyin = rs.getDouble("moneyin");
							if (moneyin != 0)
								amountinday = amountinday + moneyin;
							if (moneyout != 0)
								amountoutday = amountoutday + moneyout;
						}
						rs = st.executeQuery("select * from economic where userid="+Login.user.getID()+" order by id DESC");
						if (rs.first()) {
							balance = rs.getDouble("balance");
						} else {
							System.out.println("您还未有记录！");
						}

						frame.setlabel_4(Round.round(balance));
						frame.setLblNewLabel(Round.round(amountoutday));
						frame.setLblNewLabel_1(Round.round(amountinday));
						frame.setLblNewLabel_2(Round.round(amountoutmonth));
						frame.setLblNewLabel_3(Round.round(amountinmonth));
						db.close(rs);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}