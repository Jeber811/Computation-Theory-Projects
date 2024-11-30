// Jake Weber
//10/23/24

import java.util.*;

public class primrootcheck {
	public static void main (String[] args) {
		Scanner myScan = new Scanner(System.in);
		int n = myScan.nextInt();
		for (int loop = 0; loop < n; loop++) {
			long p = myScan.nextLong(); // p is prime & 11 ≤ p < 10^12
			long g = myScan.nextLong(); // 1 < g < p-1
			System.out.println(isPrimRoot(p, g));
		}
	}
	
	// O(√n)
	public static int isPrimRoot(long p, long g) {
		HashSet<Long> primeFactors = new HashSet<>();
		primeFactors = primeFactorization(p);
		
		for (long primeFactor: primeFactors) {
			if (fastModExp(g, primeFactor, p) == 1)
				return 0;
		}
		return 1;
	}
	
	// Guha's fastModExp function
	public static long fastModExp(long base, long exp, long n) {
		if (exp == 0)
			return 1;
		if (exp == 1)
			return base % n;
		if (exp % 2 == 0) {
			long temp = fastModExp(base, exp / 2, n);
			return (temp * temp) %  n;
		}
		return (base * fastModExp(base, exp - 1, n)) % n;
	}

	
	// Returns set of unique prime factors of p - 1
	// O(√n)
	public static HashSet<Long> primeFactorization(long p) {
		HashSet<Long> temp = new HashSet<>();
		long pMinusOne = p - 1;
		for (long i = 2; i * i <= pMinusOne; i++) {
			while (pMinusOne % i == 0) {
				temp.add(i);
				pMinusOne /= i;
			}
		}
		if (pMinusOne > 1)
			temp.add(pMinusOne);
		return temp;
	}

}