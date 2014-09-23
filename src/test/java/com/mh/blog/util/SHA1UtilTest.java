package com.mh.blog.util;

import junit.framework.Assert;

import org.junit.Test;

public class SHA1UtilTest {
	
	@Test
	public void getSummaryPassword() {
		String password = "testpass";
		String actual = SHA1Util.getSummaryPassword(password);
		System.out.println(actual);
		Assert.assertEquals("206c80413b9a96c1312cc346b7d2517b84463edd", actual);
	}
	
	@Test
	public void getSummaryPassword1() {
		String password = "Passw0rd880521";
		String actual = SHA1Util.getSummaryPassword(password);
		System.out.println(actual);
	}

}
