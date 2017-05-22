package test;

import static org.junit.Assert.*;

import org.junit.Test;

import problems.Problem1_MultiplesOf3And5;

public class Problem1_MultiplesTest {
	Problem1_MultiplesOf3And5 p1 = new Problem1_MultiplesOf3And5 ();
	@Test
	public void dynamicProgrammingApproachTest() throws InterruptedException {
		assertEquals(0,p1.sum(0,3,5));
		assertEquals(23,p1.sum(10,3,5));
		assertEquals(2318,p1.sum(100,3,5));
		assertEquals(233168,p1.sum(1000,3,5));
		assertEquals(23331668,p1.sum(10000,3,5));
		assertEquals(2333316668l,p1.sum(100000,3,5));
	}
	@Test
	public void bruteForceApproachTest() throws InterruptedException {
		assertEquals(0,p1.bruteForceSum(0,3,5));
		assertEquals(23,p1.bruteForceSum(10,3,5));
		assertEquals(2318,p1.bruteForceSum(100,3,5));
		assertEquals(233168,p1.bruteForceSum(1000,3,5));
		assertEquals(23331668,p1.bruteForceSum(10000,3,5));
		assertEquals(2333316668l,p1.bruteForceSum(100000,3,5));
	}
}
