import java.util.*;
import java.io.*;

public class Solution {

	static int tc;
	static long n;
	static int cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			n = sc.nextLong();
			cnt = 0;
			
			findSqrt(n);
			
			sb.append("#").append(t).append(" ").append(cnt).append('\n');
		}
		System.out.print(sb);
	}
	
	static void findSqrt(long n) {
		if(n == 2) {
			return ;
		}
		
		if(Math.sqrt(n) % 1 == 0) {
			cnt++;
			findSqrt((long)Math.sqrt(n));
		}
		else {
			long tmp = (int)(Math.sqrt(n)) + 1;
			
			cnt = (int)(cnt + (long) Math.pow(tmp, 2) - n);
			
			findSqrt((long) Math.pow(tmp, 2));
		}
	}
	
	
}