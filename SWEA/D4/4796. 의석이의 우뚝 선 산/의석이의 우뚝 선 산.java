import java.util.*;
import java.io.*;

public class Solution {
	//높은 산의 좌우 차례대로 살펴봄
		//좌와 우가 존재하면 가능, 개수 1
		//좌 : 높이 업데이트 하면서 작아지면 개수 증가
		//우 : 높이 업데이트 하면서 작아지면 개수 증가
		//개수 전체에서 -3 한 개수의 부분집합 개수 저장
	//다음 높이 중 탐색하지 않은 높이 선택해서 반복
	//전체 높이를 탐색한 경우 종료
	
	
	static int t;
	static int n;
	static int[] hi;
	static int answer;
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		t = scanner.nextInt();
		
		for(int tc = 1; tc <= t; tc++) {
			answer = 0;
			n = scanner.nextInt();
			hi = new int[n];
			for(int i = 0; i < n; i++)
				hi[i] = scanner.nextInt();
			
			int start = 0;
			while(true) {
				int lcnt = 0, rcnt = 0;
				int peak = -1;
				
				if(start >= n-1)
					break;
				
				//오르막 구간
				for(int i = start; i < n-1; i++) {
					if(hi[i] < hi[i + 1])
						lcnt++;
					else {
						peak = i;
						break;
					}
				}
				if(peak == -1)
					break;
		
				//내리막 구간
				for(int i = peak; i < n-1; i++) {
					if(hi[i] > hi[i + 1])
						rcnt++;
					else {
						break;
					}
				}
				
				//구간 수 구하기
				if(lcnt != 0 || rcnt != 0)
					answer += lcnt * rcnt;
				
				
				start = peak + rcnt;
				
				
			}
			
			
			
			sb.append("#").append(tc).append(" ").append(answer).append('\n');
		}
		System.out.println(sb);
		
	}
}