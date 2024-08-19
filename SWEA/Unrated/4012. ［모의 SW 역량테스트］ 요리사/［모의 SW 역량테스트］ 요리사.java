import java.util.*;
import java.io.*;

public class Solution {
	static int tc;
	static int n;
	static int[][] synergy;
	static int[][] flavor;
	static boolean[] select;
	static int answer;
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = scanner.nextInt();
		for(int t = 1; t <= tc; t++) {
			answer = 2560000;
			n = scanner.nextInt();
			synergy = new int[n][n];
			flavor = new int[n][n];
			select = new boolean[n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					synergy[i][j] = scanner.nextInt();
				}
			}
			
			
			combi(0, 0);
			
			
			sb.append("#").append(t).append(" ").append(answer).append('\n');
		}
		System.out.println(sb);
		
	}
	
	static void calc() {
		int tota = 0, totb = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i == j) continue;
				
				if(flavor[i][j] == 2) {
					tota += synergy[i][j];
				}
				else if(flavor[i][j] == -2) {
					totb += synergy[i][j];
				}
			}
		}
		
		answer = Math.min(answer, Math.abs(tota-totb));
	}
	
	static void combi(int start, int order) {
		if(order == n/2) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					flavor[i][j] = 0;
				}
			}
			
			
			for(int i = 0; i < n; i++) {
				if(select[i]) {
					for(int j = 0; j < n; j++) {
						flavor[i][j]++;
						flavor[j][i]++;
					}
				}
				else {
					for(int j = 0; j < n; j++) {
						flavor[i][j]--;
						flavor[j][i]--;
					}
				}
			}
			calc();
			
			return;
		}
		
		for(int i = start; i < n; i++) {
			if(!select[i]) {
				select[i] = true;
				combi(i + 1, order + 1);
				select[i] = false;
			}
		}
	}
}