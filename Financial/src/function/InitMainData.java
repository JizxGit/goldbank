package function;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import common.DBcom;
import common.Data;
import common.Round;
import entity.Record;
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
				
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					// 初始化界面数据
					double balance = 0, amountinday = 0, amountoutday = 0, amountinmonth = 0, amountoutmonth = 0;
					String query = "select * from economic where year='" + year + "'and month='" + month
							+ "' and userid="+Login.user.getID()+" order by id";
					
					List<Record> Records =  Data.search(query);

			        //balance=Records.get(0).getBalance();
					if(Records!=null){
			        Iterator it = Records.iterator();

			        while (it.hasNext()) {
			            Record record = (Record) it.next();
			          
			            if (record.getMoneyin() != 0)
			            	amountinmonth = amountinmonth + record.getMoneyin();
			            if (record.getMoneyout()!= 0)
			            	amountoutmonth = amountoutmonth + record.getMoneyout();
			        }
					}
			        query = "select * from economic where year='" + year + "'and month='" + month
					+ "'and day='" + day + "' and userid="+Login.user.getID()+" order by id";
			        List<Record> Records1 =  Data.search(query);

			        //balance=Records.get(0).getBalance();
			        if(Records1!=null){
			        Iterator it1 = Records1.iterator();

			        while (it1.hasNext()) {
			            Record record = (Record) it1.next();
			          
			            if (record.getMoneyin() != 0)
			            	amountinday = amountinday + record.getMoneyin();
			            if (record.getMoneyout()!= 0)
			            	amountoutday = amountoutday + record.getMoneyout();
			        }
			        }
			        query = "select * from economic where userid="+Login.user.getID()+" order by id DESC";
			        if(Records==null){
			        	Records =  Data.search(query);
			        	if(Records!=null)
			        		balance=Records.get(0).getBalance();
			        	else
			        		balance=0;
			        }
			        
			        frame.setlabel_4(Round.round(balance));
					frame.setLblNewLabel(Round.round(amountoutday));
					frame.setLblNewLabel_1(Round.round(amountinday));
					frame.setLblNewLabel_2(Round.round(amountoutmonth));
					frame.setLblNewLabel_3(Round.round(amountinmonth));
			}
		}       	
			
		);
	}
	
}