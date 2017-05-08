package russell.tests.algorithms.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Numbers {

	public static void main(String[] args) {

	}

	@Test
	public void testGCD() {
		System.out.println(greatestCommonDenominator(48, 180));
	}

	@Test
	public void testGetPrimes() {
		System.out.println(getPrimes(10));
	}

	public static List<Integer> getPrimes(int n) {
		List<Integer> result = new ArrayList<>();

		int num = 2;
		int i = 0;
		while (i < n) {
			if (isPrime(num)) {
				result.add(num);
				i++;
			}
			num++;
		}

		return result;
	}

	public static boolean isPrime(int n) {
		if (n > 2 && n % 2 == 0) {
			return false;
		}

		double sqrt = Math.sqrt(n);
		for (int i = 3; i < sqrt; i = i + 2) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static int greatestCommonDenominator(int a, int b) {
		if (b == 0) {
			return a;
		}
		return greatestCommonDenominator(b, a % b);
	}
}
