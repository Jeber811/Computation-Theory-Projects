// Jake Weber
// COT 4210
// P5
// 11/26/24

import java.util.Scanner;
import java.lang.Math;

public class dfacount {
	public static void main(String[] args) {
		
		final long mod = 1000000007;
		
		// For each DFA...
		Scanner myScan = new Scanner(System.in);
		int n = myScan.nextInt(); // number of DFAs (n ≤ 25)
		for (int dfaNum = 0; dfaNum < n; dfaNum++) {
			
			// Input
			int s = myScan.nextInt(); // number of states (s ≤ 50)
			int v = myScan.nextInt(); // size of the input alphabet (v ≤ 10)
			int k = myScan.nextInt(); // length of strings to consider
			int a = myScan.nextInt(); // number of accept states (a ≤ s)
			int aStates[] = new int[a]; // accept states
			for (int i = 0; i < a; i++) // inserted in increasing order
				aStates[i] = myScan.nextInt();
			int[][] transFun = new int[s][v]; // transition function
			for (int i = 0; i < s; i++)
				for (int j = 0; j < v; j++)
					transFun[i][j] = myScan.nextInt();
			
			// Create a "this to that" 2D array
			int thisToThat[][] = new int[s][s];
			for (int z = 0; z < s; z++) {
				for (int x = 0; x < s; x++) {
					int temp = 0;
					for (int y = 0; y < v; y++) {
						if (transFun[x][y] == z)
							temp++;
					}
					// There are temp ways to go from state x to state z
					thisToThat[x][z] = temp;
				}
			}
			
			// Dynamic programming algorithm implementation
			long res[][] = new long[k + 1][s];
			res[0][0] = 1;
			for (int y = 1; y < s; y++)
			    res[0][y] = 0;
			
			for (int x = 1; x <= k; x++) {
				for (int y = 0; y < s; y++) {
					for (int i = 0; i < s; i++)
						res[x][y] = (res[x][y] + ((res[x - 1][i] * thisToThat[i][y]) % mod)) % mod;
					
				}
			}
			
			// Count the number of strings at length k accepted by the DFA in accepted states
			long total = 0;
			for (int i = 0; i < aStates.length; i++)
				total = (total + res[k][aStates[i]]) % mod;
			System.out.println(total);
			
		}
	}
}