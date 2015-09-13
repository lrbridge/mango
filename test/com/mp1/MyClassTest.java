package com.mp1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MyClassTest {

	@Test
	public void test() {
		MyClass x = new MyClass();
		assertEquals(x.getNum(), 7);
	}

}
