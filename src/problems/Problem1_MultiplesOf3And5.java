package problems;

import java.awt.List;
import java.util.ArrayList;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we
 * get 3, 5, 6 and 9. The sum of these multiples is 23. Find the sum of all the
 * multiples of 3 or 5 below 1000.
 * 
 * @author Nelly
 * @Logic: 1000 is not a big number but we are looking at adding up 334
 *         Multiples of three, 200 multiples of 5 minus 67 multiples of both
 *         fives and threes. Since the subproblems are independent i.e finding
 *         multiples of 5 does not affect multiples of 3, we can split the
 *         problems into two subproblems, one thread finds multiples of 5 and
 *         another one finds multiples of 3. There are two ways to solving the
 *         problem: 1. for (int i = 0; i < 1000; i++) {if (i % 3 == 0 || i % 5
 *         == 0) sum += i { 2. while(multiple<1000) {multiple = multiple*3, sum
 *         +=multiple} and while(multiple<1000) {multiple = multiple*5, sum
 *         +=multiple} the first approach looks at 1000 entries while the second
 *         looks at 200 multiples of 5 plus 334 multiples of 3 = 534. The second
 *         approach is all better set of dynamic programming. we should be
 *         careful to only consider multiples of both five and threes only once.
 * 
 */
public class Problem1_MultiplesOf3And5 {

	/**
	 * 
	 * Here we ignore multiples of both int1 and int2 and calculate all of them
	 * at once when by looking at int1*int2. To do this we can say
	 * 3*1,3*2,3*3,3*4,3*6,3*7,3*8,3*9,3*11.... and so on.For int1 We have a
	 * thread take an integer i where i is an integer between 1 and int2-1 and
	 * computes all its multiples starting at i, i+int2, i+2*int2,i+3*int2 . For
	 * instance; 1*3,6*3,11*3,16*3 and another one 2*3,7*3,12*3. Below 1000 we
	 * have and multiples of 3 and 5, 1 thread doing multiples of 15 which is
	 * 1000/15 = 67 calculations. For 3 we have 4 threads doing 67 calculation
	 * ,1000/3 = 333 ignoring 15 we have 333 - 67 = 266, 266/4 = 67. For 5 2
	 * threads doing 67 calculations, 1000/5 = 200, 200 -67 = 133, 133/2 = 67.
	 * For sum(1000, 3,5) we have 1+4+2=7, 7 threads doing 67 calculations each.
	 */
	public long sum(long maximum, int int1, int int2) throws InterruptedException {
		// Java doesn't have pass by reference. This one element array keeps
		// track of our sum
		long[] sum = { 0 };

		ArrayList<Thread> threads = new ArrayList<Thread>();
		Thread lowestMultipleOfBoth = new MultipleSummer(sum, maximum, int1 * int2, 1, 1);

		threads.add(lowestMultipleOfBoth);
		lowestMultipleOfBoth.start();
		for (int i = 1; i < int2; i++) {
			MultipleSummer t0 = new MultipleSummer(sum, maximum, int1, int2, i);
			t0.start();
			threads.add(t0);
		}
		for (int i = 1; i < int1; i++) {
			MultipleSummer t0 = new MultipleSummer(sum, maximum, int2, int1, i);
			t0.start();
			threads.add(t0);
		}

		for (Thread t1 : threads) {

			t1.join();

		}

		return sum[0];
	}
	
	public long bruteForceSum(long maximum, int int1, int int2){
		long sum = 0;
		for(int i = 0; i< maximum; i++){
			if(i%int1==0||i%int2==0){
				sum += i;
			}
		}
		return sum;
	}

	class MultipleSummer extends Thread {
		private int multipleOf;
		private int start;
		private long maximum;
		private int other;
		public long[] sum;

		public MultipleSummer(long[] sum, long maximum, int multipleOf, int other, int start) {
			this.multipleOf = multipleOf;
			this.start = start;
			this.other = other;
			this.maximum = maximum;
			this.sum = sum;
		}

		@Override
		public void run() {

			long product = start * multipleOf;
			long sum = 0;
			while (product < maximum) {

				sum += product;
				start += other;
				product = start * multipleOf;
			}
			addAll(sum);
		}

		// making the addition to our one element array thread safe.
		public synchronized void addAll(long subtotal) {
			sum[0] += subtotal;
		}
	}

}
