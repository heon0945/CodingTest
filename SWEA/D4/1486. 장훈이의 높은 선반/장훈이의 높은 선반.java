import java.util.*;
import java.io.*;

public class Solution {
	//점원들의 키로 부분집합 생성
		//파라메터로 현재까지 선택된 점원들의 키의 합을 저장
	//부분집합 완성 시 해당 키가 선반보다 큰 경우 정답과 비교하여 최솟값을 정답에 저장
			//처음 정답은 200000으로 저장
	
	static int t;
	static int n, b;
	static int[] hi;
	static int answer;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		t = scanner.nextInt();
		
		for(int tc = 1; tc <= t; tc++) {
			answer =200000;
			n = scanner.nextInt();
			b = scanner.nextInt();
			hi = new int[n];
			for(int i = 0; i < n; i++) {
				hi[i] = scanner.nextInt();
			}
			
			
			subset(0, 0);
			
			
			sb.append("#").append(tc).append(" ").append(answer).append('\n');
		}
		
		System.out.print(sb);
		
		
	}
	
	static void subset(int order, int height) {
		if(order == n) {
			if(height >= b) {
				answer = Math.min(answer, height - b);
			}
			return;
		}
		
		subset(order + 1, height);
		subset(order + 1, height + hi[order]);
	}
	
}