package com.yatsulka.training;

import org.junit.Assert;
import org.junit.Test;

public class EquationsTest {

	@Test
	public void testResolveEquation_minusOne() {
		double actual = Equations.resolveEquation(1, 2, 1);
		Assert.assertEquals(-1, actual, 0);
	}

	@Test
	public void testResolveEquation_zero() {
		Assert.assertEquals(0, Equations.resolveEquation(1, 0, 0), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testResolveEquation_noRoots() {
		Equations.resolveEquation(1, 1, 1);
	}
}
