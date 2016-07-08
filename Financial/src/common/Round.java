package common;

import java.math.BigDecimal;

public class Round {
	public static double round(double original){
		BigDecimal b = new BigDecimal(original);
		//四舍五入，保留两位小数 jzx
		double result = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(result);
		return result;		
	}
}
