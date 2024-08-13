import java.util.*;
import java.io.*;


public class Main {
	// 순열 공식으로 풀이
	// 재귀 형태로 첫 숫자부터 마지막 숫자까지 고르는 형태
	// 파라메터로 몇번째 숫자를 채우고 있는지 전달
	// 마지막 숫자까지 채워진 경우 출력하고 종료
	
	static int n, m;
	static int[] output, arr, visit;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
		
		output = new int[m];
		arr = new int[n];
		visit = new int[n];
		for(int i = 1; i <= n; i++) {
			arr[i-1] = i;
		}
		
		permu(0);
		
		System.out.println(sb);
	}
	
	public static void permu(int order) {
		if(order == m) {
			for(int i = 0; i < m; i++) {
				sb.append(output[i]).append(" ");
			}
			sb.append('\n');
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(visit[i] == 0) {
				visit[i] = 1;
				output[order] = arr[i];
				permu(order + 1);
				visit[i] = 0;				
			}
			
		}
	}
}