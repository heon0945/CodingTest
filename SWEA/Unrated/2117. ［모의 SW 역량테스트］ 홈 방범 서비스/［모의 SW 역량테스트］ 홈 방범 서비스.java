import java.util.*;
import java.io.*;

public class Solution {
	
	static int tc;
	static int n, m;
	static int map[][];
	static int answer;
	static ArrayList<Pair> house;
	
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
			 n = sc.nextInt();
			 m = sc.nextInt();
			 house = new ArrayList<>();
			 map = new int[n][n];
			 for(int i = 0; i < n; i++) {
				 for(int j = 0; j < n; j++) {
					 map[i][j] = sc.nextInt();
					 if(map[i][j] == 1) house.add(new Pair(i, j));
				 }
			 }
			 
			 
			 int answer = 1;
			 int k = 2;
			 
			 while(true) {
				 int price = k * k + (k-1) * (k-1);
				 
				 if(k > n+1 || price > m * house.size()) break;
				 
				 for(int i = 0; i < n; i++) {
					 for(int j = 0; j < n; j++) {
						 int tmp = 0;
						 
						 for(Pair h : house) {
							 int hx = h.x;
							 int hy = h.y;
							 if(Math.abs(h.x - i) + Math.abs(h.y - j) < k)
								 tmp++;
						 }
						 if(price <= tmp * m)
							 answer = Math.max(answer, tmp);
						 
					 }
				 }
				 
				 k++;
			 }
			
			
			
			
			
			sb.append(answer).append('\n');
		}
		
		System.out.println(sb);
	}
}