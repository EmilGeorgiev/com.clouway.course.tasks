package com.clouway.sumator;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface Sum {

	public int sum(int a, int b);
	public double sum(double a, double b);
	public int sum(String a, String b);
	public BigInteger sum(BigInteger a, BigInteger b);
	public BigDecimal sum(BigDecimal a, BigDecimal b);
}
