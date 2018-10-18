package com.dk.gfm.utli;

import java.math.BigDecimal;

/**
 * 算术运算工具类
 *
 * @description 
 *
 * @author huangyunsong
 * @createDate 2015年12月4日
 */
public class Arith {

	/**
	 * 加法运算
	 *
	 * @param valA 加数
	 * @param valB 加数
	 * @return
	 *
	 * @author huangyunsong
	 * @createDate 2015年12月4日
	 */
	public static double add(double valA, double valB) {
		BigDecimal a = new BigDecimal(Double.toString(valA));
		BigDecimal b = new BigDecimal(Double.toString(valB));
		return a.add(b).doubleValue();
	}
	
	/**
	 * 多个加数的加法运算
	 * @param valA 被加数
	 * @param ds 多个加数
	 * @return
	 *
	 * @author panxili
	 * @createDate 2018年1月28日
	 */
	public static double add(double valA, double ... ds) {
		BigDecimal sum = new BigDecimal(Double.toString(valA));
		for (int i = 0; i < ds.length; i++) {
            sum = sum.add(new BigDecimal(Double.toString(ds[i])));
        }
		
		return sum.doubleValue();
	}

	/**
	 * 减法运算
	 *
	 * @param valA 被减数A
	 * @param valB 减数B
	 * @return 差
	 * 
	 * @author huangyunsong
	 * @createDate 2015年12月1日 
	 */
	public static double sub(double valA, double valB) {
		BigDecimal a = new BigDecimal(Double.toString(valA));
		BigDecimal b = new BigDecimal(Double.toString(valB));
		return a.subtract(b).doubleValue();
	}
	
	/**
	 * 多个减数的减法运算
	 * @param valA 被减数
	 * @param ds 多个减数
	 * @return
	 *
	 * @author panxili
	 * @createDate 2018年1月28日
	 */
	public static double sub(double valA, double ... ds) {
		BigDecimal result = new BigDecimal(Double.toString(valA));
		for (int i = 0; i < ds.length; i++) {
			result = result.subtract(new BigDecimal(Double.toString(ds[i])));
        }
		
		return result.doubleValue();
	}

	/**
	 * 乘法运算
	 *
	 * @param valA 被乘数A
	 * @param valB 乘数B
	 * @return 积
	 * 
	 * @author huangyunsong
	 * @createDate 2015年12月1日 
	 */
	public static double mul(double valA, double valB) {
		BigDecimal a = new BigDecimal(Double.toString(valA));
		BigDecimal b = new BigDecimal(Double.toString(valB));
		return a.multiply(b).doubleValue();
	}
	
	/**
	 * 多个乘数的乘法运算
	 * @param valA
	 * @param ds
	 * @return
	 *
	 * @author panxili
	 * @createDate 2018年1月28日
	 */
	public static double mul(double valA, double ... ds) {
		BigDecimal result = new BigDecimal(Double.toString(valA));
		for (int i = 0; i < ds.length; i++) {
			result = result.multiply(new BigDecimal(Double.toString(ds[i])));
        }
		
		return result.doubleValue();
	}

	/**
	 * 除法运算。当发生除不尽的情况时，数字四舍五入，由scale参数指 定精度。
	 *
	 * @param valA 被除数
	 * @param valB 除数
	 * @param scale 精度
	 * @return
	 * 
	 * @author huangyunsong
	 * @createDate 2015年12月1日 
	 */
	public static double div(double valA, double valB, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("精确度不能小于0！");
		}

		BigDecimal a = new BigDecimal(Double.toString(valA));
		BigDecimal b = new BigDecimal(Double.toString(valB));
		return a.divide(b, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 多个除数的除法运算,中间过程四舍五入取小数点后10位，最终结果四舍五入取指定的小数点后位数
	 * @param valA
	 * @param scale 最终结果的精度
	 * @param ds
	 * @return
	 *
	 * @author panxili
	 * @createDate 2018年1月28日
	 */
	public static double div(double valA, int scale, double ... ds) {
		double result = valA;
		for (int i = 0; i < ds.length; i++) {
			if (ds[i] == 0) {
				throw new IllegalArgumentException("除数不能为0！");
			}
			result = Arith.div(result, ds[i], 10);
        }
		
		return Arith.round(result, scale);
	}

	/**
	 * 除法运算。当发生除不尽的情况时，数字向上舍入，由scale参数指 定精度。此舍入模式始终不会减少计算值的大小。
	 *
	 * @param valA 被除数
	 * @param valB 除数
	 * @param scale 精度
	 * @return
	 * 
	 * @author huangyunsong
	 * @createDate 2015年12月1日
	 */
	public static double divUp(double valA, double valB, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("精确度不能小于0！");
		}

		BigDecimal a = new BigDecimal(Double.toString(valA));
		BigDecimal b = new BigDecimal(Double.toString(valB));
		return a.divide(b, scale, BigDecimal.ROUND_UP).doubleValue();
	}

	/**
	 * 除法运算。当发生除不尽的情况时，数字向下舍入，由scale参数指 定精度。此舍入模式始终不会增加计算值的大小。
	 *
	 * @param valA 被除数
	 * @param valB 除数
	 * @param scale 精度
	 * @return
	 * 
	 * @author huangyunsong
	 * @createDate 2015年12月1日 
	 */
	public static double divDown(double valA, double valB, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("精确度不能小于0！");
		}

		BigDecimal a = new BigDecimal(Double.toString(valA));
		BigDecimal b = new BigDecimal(Double.toString(valB));
		return a.divide(b, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 四舍五入。由scale参数指 定精度。
	 *
	 * @param val 原始数字
	 * @param scale
	 * @return 四舍五入后的数字
	 * 
	 * @author hys
	 * @createDate 2015年12月1日
	 */
	public static double round(double val, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("精确度不能小于0！");
		}

		BigDecimal b = new BigDecimal(Double.toString(val));
		return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 舍入远离零的舍入模式。在丢弃非零部分之前始终增加数字。注意，此舍入模式始终不会减少计算值的大小。由scale参数指 定精度。
	 *
	 * @param val 原始数字
	 * @param scale
	 * @return 向上舍入的数字
	 * 
	 * @author hys
	 * @createDate 2015年12月1日
	 */
	public static double up(double val, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("精确度不能小于0！");
		}

		BigDecimal b = new BigDecimal(Double.toString(val));
		return b.setScale(scale, BigDecimal.ROUND_UP).doubleValue();
	}
	
	/**
	 * 接近零的舍入模式。在丢弃某部分之前始终不增加数字（即截短）。注意，此舍入模式始终不会增加计算值的大小。由scale参数指 定精度。
	 *
	 * @param val 原始数字
	 * @param scale
	 * @return 向下舍入的数字
	 * 
	 * @author hys
	 * @createDate 2015年12月1日
	 */
	public static double down(double val, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("精确度不能小于0！");
		}

		BigDecimal b = new BigDecimal(Double.toString(val));
		return b.setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	public static void main(String[] args) {
		double a = 10.01 ;
		double b = 10 ;
		BigDecimal ba = new BigDecimal(Double.toString(a));
		System.out.println(ba);
		System.out.println(a+b);
	}
	
}
