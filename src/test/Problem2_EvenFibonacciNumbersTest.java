package test;

import static org.junit.Assert.*;

import org.junit.Test;

import problems.Problem2_EvenFibonacciNumbers;

public class Problem2_EvenFibonacciNumbersTest {
	Problem2_EvenFibonacciNumbers p1 = new Problem2_EvenFibonacciNumbers();
	
	@Test
	public void test() {
		assertEquals(10, p1.evenFibSumUnder(10));
		assertEquals(4613732, p1.evenFibSumUnder(4000000));
		assertEquals(19544084, p1.evenFibSumUnder(40000000));
		assertEquals(350704366, p1.evenFibSumUnder(400000000));
		assertEquals(1998167216, p1.evenFibSumUnder(4000000000l));
	}

}
