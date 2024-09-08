import java.util.*;
import java.io.*;

public class Solution {
	static int tc;
	static int n;
	static int pos[];
	static int answer;
	
	static class Pair{
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			answer = 0;
			n = sc.nextInt();
			pos = new int[n];
			dfs(0);
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static boolean promising(int order, int cur) {
		// same column check
		// diagonal check
		for(int i = 0; i < order; i++) {
			if(pos[i] == cur) return false;
			if(Math.abs(pos[i] - cur) == Math.abs(i - order))
					return false;
		}
		return true;
	}
	
	static void dfs(int order) {
		if(order == n)
			answer++;	
		
		for(int i = 0; i < n; i++) {
			if(!promising(order, i)) continue;
			pos[order] = i;
			dfs(order + 1);
		}
	}
	
}