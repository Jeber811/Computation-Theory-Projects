// Jake Weber
// 10/20/2024

import java.util.*;

public class chomsky {
	public static int o;
	public static void main (String[] args) {
		Scanner myScan = new Scanner(System.in);
		int n = myScan.nextInt(); // number of grammars
		for (int z = 0; z < n; z++) {
			HashMap<Character , ArrayList<String>> varRule = new HashMap<>();
			int v = myScan.nextInt(); // number of variables
			for (int x = 0; x < v; x++) {
				int r = myScan.nextInt(); // number of rules per variable
				char c = myScan.next().charAt(0); // rule variable
				ArrayList<String> temp = new ArrayList<>();
				for (int y = 0; y < r; y++) 
					temp.add(myScan.next());
				varRule.put(c, temp);
			}
			int s = myScan.nextInt(); // number of strings to test
			//ArrayList<String> strings = new ArrayList<>();
			System.out.println("Grammar #" + (z + 1) + ":");
			for (int y = 0; y < s; y++) {
				o = 0;
				String p = myScan.next();
				ArrayList<Character> cur = new ArrayList<>();
				cur.add('S');
				inGrammar(p, varRule, cur);
				if (o > 0)
					System.out.println(p + ": YES");
				else
					System.out.println(p + ": NO");
			}
			System.out.print("\n");
		}
	}
	public static void inGrammar(String s, HashMap<Character, ArrayList<String>> varRule, ArrayList<Character> cur) {
		// Check for epsilon string
		if (s.compareTo("@") == 0) {
			for (String j : varRule.get('S'))
				if (j.compareTo("@") == 0)
					o += 1;
		}
		if (cur.size() == s.length()) {
			for (int g = 0; g < cur.size(); g++) {
				for (String  l : varRule.get(cur.get(g)))
					if (l.charAt(0) == s.charAt(g))
						cur.set(g, s.charAt(g));
			}
			
			/*System.out.println("real string: " + s);
			for (int f = 0; f < cur.size(); f++)
				System.out.print(cur.get(f));
			System.out.print("\n");*/
			
			for (int u = 0; u < s.length(); u++) {
				if (s.charAt(u) != cur.get(u))
					break;
				if (u == (s.length() - 1))
					o += 1;
			}
		} else {
			for (int a = 0; a < cur.size(); a++) {
				if (Character.isUpperCase(cur.get(a))) {
					for (String  l : varRule.get(cur.get(a))) {
						if (!Character.isUpperCase(l.charAt(0)))
							continue;
						ArrayList<Character> temp = new ArrayList<>();
						for (char k : cur)
							temp.add(k);
						temp.set(a, l.charAt(0));
						if (l.length() > 1)
							temp.add(a + 1, l.charAt(1));
						/*for (int f = 0; f < temp.size(); f++)
							System.out.print(temp.get(f));
						System.out.print("\n");*/
						inGrammar(s, varRule, temp);
					}
				}
			}		
		}
	}
}