import java.util.*;
import java.io.*;

public class Solution {
	
	//햄버거의 재료를 입력 받음
	//재료의 크기만큼의 이차원 배열을 생성
	//궁합이 맞지 않은 칸에 1을 할당 (반대의 경우도 할당)
	//햄버거의 재료를 바탕으로 부분집합 구함
	//이때 궁합이 맞지 않는 재료를 기록한 2차원 배열을 참고하여 해당 값이 1인 경우 포함 x
	
	static int tc;
	static int n, m;
	static int[][] info;
	static boolean[] select;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		tc = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= tc; t++) {
			answer = 0;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			select = new boolean[n];
			info = new int[n][n];
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				info[a-1][b-1] = 1;
				info[b-1][a-1] = 1;
			}
			
			subset(0);
			
			sb.append("#" + t + " " + answer + '\n');
		}
		
		System.out.print(sb);
	}
	
	static void subset(int order) {
		if(order == n){
			answer++;
			return;
		}
		
		select[order] = false;
		subset(order + 1);
		
		for(int i = 0; i <= order; i++) {
			if(select[i]) {
				if(info[i][order] == 1)
					break;
			}
			if(i == order) {
				select[order] = true;
				subset(order + 1);
			}
		}
		
	}
}