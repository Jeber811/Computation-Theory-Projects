// Jake Weber
// 11/3/24

// Given a description of a Turing Machine and a string, simulate the string running on the Turing
// Machine for a given number of steps. At the end of the simulation, you are to determine if the
// machine accepted the string, rejected the string, or did not halt in the required number of steps.

import java.util.*;

public class tm {
	
	public static void main (String[] args) {
		
		Scanner myScan = new Scanner(System.in);
		int n = myScan.nextInt(); // number of Turing Machines (n < 100)
		
		for (int tmLoop = 1; tmLoop <= n; tmLoop++) {
			
			int q = myScan.nextInt(); // number of states (2 < q < 100)
			int r = myScan.nextInt(); // number of rules (r < 1000)
			
			// δ : Q × Γ → Q × Γ × {L, R} (state transition function)
			
			// Rule inputs: Q × Γ (indexes the output arrays)
			int inputState; 
			char charRead; // character being read within the tape
			
			// Rule outputs: Q × Γ × {L, R} (indexed by the inputs)
			int[][] outputState = new int[q][4];
			char[][] charWrite = new char[q][4]; // // character being written within the tape
			char[][] LorR = new char[q][4]; // contains 'L' or 'R'
			
			// Populate rule arrays
			for (int ruleLoop = 0; ruleLoop < r; ruleLoop++) {
				// Rule inputs: Q × Γ
				inputState = myScan.nextInt();
				charRead = myScan.next().charAt(0);
				
				// Rule outputs: Q × Γ × {L, R}
				outputState[inputState][tapeSymbolIndex(charRead)] = myScan.nextInt();
				charWrite[inputState][tapeSymbolIndex(charRead)] = myScan.next().charAt(0); // // character being written within the tape
				LorR[inputState][tapeSymbolIndex(charRead)] = myScan.next().charAt(0); // contains 'L' or 'R'
			}
			
			int numStrings = myScan.nextInt(); // number of input strings to test(numStrings < 20)
			int s = myScan.nextInt(); // maximum number of steps to run each simulation
			
			System.out.println("Machine #" + tmLoop + ":");
			for (int testLoop = 0; testLoop < numStrings; testLoop++) {
				
				// The input alphabet will always be {a,b}. The tape alphabet will always be
				// {a,b,B,#}, where B stands for a blank and # is a special tape symbol
				String inputString = myScan.next();
				ArrayList<Character> tape = new ArrayList<>();
				
				// Add input string to the tape
				for (int i = 0; i < inputString.length(); i++)
					tape.add(inputString.charAt(i));
				tape.add('B');
				
				// The states will be labeled 0 through q-1. 0 will be the designated start state,
				// 1 will be the designated accept state and 2 will be the designated reject state.
				int curState = 0;
				int curTapeIndex = 0;
				
				// ---- RUN TM ON THE GIVEN INPUT STRING -----
				for (int curStep = 0; curStep < s; curStep++) {
					if (curTapeIndex >= tape.size())
						tape.add('B');
					
					int tempIndex = tapeSymbolIndex(tape.get(curTapeIndex));
					int nextState = outputState[curState][tempIndex];
					tape.set(curTapeIndex, charWrite[curState][tempIndex]);
					if (LorR[curState][tempIndex] == 'L' && curTapeIndex > 0)
						curTapeIndex--;
					else if (LorR[curState][tempIndex] == 'R')
						curTapeIndex++;
					curState = nextState;
					if (curState == 1 || curState == 2)
						break;
				}
				
				switch (curState) {
					case 1:
						System.out.println(inputString + ": YES");
						break;
					case 2:
						System.out.println(inputString + ": NO");
						break;
					default:
						System.out.println(inputString + ": DOES NOT HALT IN " + s + " STEPS");
						break;
				}
			}
			
		}
		
	}
	
	public static int tapeSymbolIndex(char c) {
		switch (c) {
			case 'a':
				return 0;
			case 'b':
				return 1;
			case 'B':
				return 2;
			case '#':
				return 3;
		}
		
		return 4; // Invalid input found
	}
	
}
