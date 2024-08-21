import java.util.*;
import java.io.*;

public class Solution {

	static int tc;
	static int d, w, k;
	static int[][] layer;
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			layer = new int[d][w];
			
			for(int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					layer[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			for(int i = 0; i < d; i++) {
				if (combi(0, 0, i)) {
					answer = i;
					break;
				}
			}
			
			
			sb.append("#").append(t).append(" ").append(answer).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static boolean combi(int start, int order, int limit) {
		if(order == limit) {
			
			if(check()) {
				return true;
			}
			return false;
		}
		
		for(int i = start; i < d; i++) {
			int buffer[] = new int[w];
			for(int j = 0; j < w; j++) {
				buffer[j] = layer[i][j];
			}
			
			
			for(int j = 0; j < w; j++)
				layer[i][j] = 0;
			if(combi(i + 1, order + 1, limit))
				return true;
			
			for(int j = 0; j < w; j++)
				layer[i][j] = 1;
			if(combi(i + 1, order + 1, limit))
				return true;
			
			for(int j = 0; j < w; j++) {
				layer[i][j] = buffer[j];
			}
			
		}
		return false;
	}
	
	static boolean check() {
		for(int i = 0; i < w; i++) {
			int cnt = 1;
			int mx = 0;
			
			for(int j = 1; j < d; j++) {
				if(layer[j-1][i] == layer[j][i])
					cnt++;
				else {
					if(cnt > mx)
						mx = cnt;
					cnt = 1;
				}
			}
			if(cnt > mx)
				mx = cnt;
			if(mx < k)
				return false;
		}
		return true;
	}
}