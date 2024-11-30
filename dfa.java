// Jake Weber
// 8/28/24

// Given a DFA, and several input strings to that DFA, determine whether or not each of the strings
// is in the language described by the DFA.

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		// For each DFA...
		Scanner myScan = new Scanner(System.in);
		int n = myScan.nextInt(); // number of DFAs (n ≤ 10)
		for (int z = 0; z < n; z++) {
			
			// Input
			int s = myScan.nextInt(); // number of states (s ≤ 1000)
			int v = myScan.nextInt(); // size of the input alphabet (v ≤ 10)
			int m = myScan.nextInt(); // number of strings to test (m ≤ 10)
			int a = myScan.nextInt(); // number of accept states (a ≤ s)
			int aStates[] = new int[a]; // accept states
			for (int i = 0; i < a; i++) // inserted in increasing order
				aStates[i] = myScan.nextInt();
			int[][] transFun = new int[s][v]; // transition function
			for (int i = 0; i < s; i++)
				for (int j = 0; j < v; j++)
					transFun[i][j] = myScan.nextInt();
			String[] testables = new String[m]; // strings to test
			for (int i = 0; i < m; i++)
				testables[i] = myScan.next();
			
			// Calculations and Output
			System.out.println("DFA #" + (z + 1) + ":");
			for (int i = 0; i < m; i++) { // test each string
				boolean inDFA = false;
				int curState = 0;
				for (int j = 0; j < testables[i].length(); j++)
					curState = transFun[curState][testables[i].charAt(j) - 'a']; // index transFun to modify curState
				for (int j = 0; j < a; j++)
					if (curState == aStates[j]) { // check if end state is an accept state
						inDFA = true;
						break;
					}
				if (inDFA)
					System.out.println(testables[i] + " is in L");
				else
					System.out.println(testables[i] + " is not in L");
			}
			System.out.print("\n");
			
		}
	}
}
