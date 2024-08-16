import java.io.*;
import java.util.*;


public class Solution {

	//재료의 수, 제한 칼로리
	//재료의 수 n에서 부분집합을 구함
	//맛의 합을 매개변수 -> 해당 합이 최댓값보다 크면 업데이트
	//가지치기 : 칼로리가 이상이되면 return
	
	
	static int tc;
	static int n, k;
	static int answer;
	static int flavor[];
	static int kcal[];
	static boolean[] select;
	static int mx;
	
 public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		tc = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= tc; t++) {
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			mx = 0;
			
			select = new boolean[n];
			flavor = new int[n];
			kcal = new int[n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				flavor[i] = Integer.parseInt(st.nextToken());
				kcal[i] = Integer.parseInt(st.nextToken());
			}
			
			combi(0, 0, 0);
			
			sb.append("#" + t + " " + mx + '\n');
			
		}
		
		System.out.print(sb);
	}
	
	
	static void combi(int order, int tk, int tf) {
		if(order == n) {
			if(tf > mx)
				mx = tf;
			return;
		}
		combi(order + 1, tk, tf);
		if(tk + kcal[order] <= k) {
			combi(order + 1, tk + kcal[order], tf + flavor[order]);
		}
	}
	
}