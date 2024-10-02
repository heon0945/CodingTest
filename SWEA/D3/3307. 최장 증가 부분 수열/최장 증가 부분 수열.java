import java.util.*;
import java.io.*;

public class Solution {
	
	static int tc;
	static int n;
	static long number[];
	static int table[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			n = sc.nextInt();
			
			number = new long[n];
			for(int i = 0; i < n; i++) {
				number[i] = sc.nextLong();
			}
			
			table = new int[n];
			int max = 0;
			
			for(int i = 0; i < n; i++) {
				table[i] = 1;
				for(int j = 0; j < i; j++) {
					if(number[i] > number[j])
						table[i] = Math.max(table[i], table[j]+1);
				}
				max = Math.max(max, table[i]);
			}
			
			
			sb.append("#").append(t).append(" ").append(max).append('\n');
		}
		System.out.println(sb);
	}
}