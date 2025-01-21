import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static int table[][];
	static String str1, str2;
	static int len1, len2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		str1 = br.readLine();
		str2 = br.readLine();
		
		len1 = str1.length();
		len2 = str2.length();
		table = new int[len1+1][len2+1];
		
		for(int i = 0; i <= len1; i++) {
			table[i][0] = 0;
		}
		for(int i = 0; i <= len2; i++) {
			table[0][i] = 0;
		}
		
		for(int i = 1; i <= len1; i++) {
			for(int j = 1; j <= len2; j++) {
				if(str1.charAt(i-1) == str2.charAt(j-1)) {
					table[i][j] = table[i-1][j-1] + 1;
				}
				else {
					table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
				}
			}
		}
		
		System.out.println(table[len1][len2]);
		System.out.println(findLcs());
	}
	
	public static String findLcs() {
		Stack<Character> st = new Stack<>();
		int i = len1;
		int j = len2;
		
		while(true) {
			if(i <= 0 || j <= 0) break;
			
			if(table[i][j] == table[i][j-1]) {
				j--;
			}
			else if(table[i][j] == table[i-1][j]) {
				i--;
			}
			else {
				st.push(str1.charAt(i-1));
				i--; j--;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!st.isEmpty()) {
			sb.append(st.pop());
		}
		
		return sb.toString();
	}
}