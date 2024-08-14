import java.io.*;
import java.util.*;

public class Main {
	//n개의 카드에서 3개의 카드를 뽑을 수 있는 경우의 수 계산 -> 중복 없는 조합
	//해당 조합 수의 합을 계산
	//위에서 계산한 합과 한계 값과의 차가 가장 적은 경우 정답 업데이트
	
	static int n, m; //카드 수, 한계 값
	static int[] input;
	static boolean[] select;
	static int gap = 10000000;
	static int answer = 0;
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
		
		input = new int[n];
		select = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			input[i] = scanner.nextInt();
		}
		
		combi(0, 0);
		
		System.out.println(answer);
		
	}
	
	static void combi(int start, int cur) {
		if(cur == 3) {
			int total = 0;
			for(int i = 0; i < n; i++) {
				if(select[i]) {
					total += input[i];
				}
			}
			
			if( m >= total && gap > m - total) {
				answer = total;
				gap = m - total;
			}
			return;
		}
		
		for(int i = start; i < n; i++) {
			if(!select[i]) {
				select[i] = true;
				combi(i + 1, cur + 1);
				select[i] = false;
			}
		}
		
	}
	
}