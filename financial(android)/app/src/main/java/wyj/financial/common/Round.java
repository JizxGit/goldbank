package wyj.financial.common;

import java.math.BigDecimal;

public class Round {
	public static String round(double original){
		BigDecimal b = new BigDecimal(original);
		//四舍五入，保留两位小数
		String result = b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		System.out.println(result);
		return result;
	}
}
