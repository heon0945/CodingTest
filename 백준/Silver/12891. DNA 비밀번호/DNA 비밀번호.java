import java.io.*;
import java.util.*;

public class Main {
	//부분문자열 셍성, 전체 탐색 : 각 인덱스마다 A, G, C, T 포함 개수 축적 저장
		//2차원 배열 accum[S][4]
	//주어진 문자열 0부터 S-P까지 탐색
	//축적 저장한 배열 참고해서 조건 부합 판단 
		// i : 0-3 -> accum[start + P][i]-accum[start - 1][i]
		//but start-1이 -1인 경우 계산  x
	
	static int s; //주어진 문자열 길이
	static int p; //만들 문자열 길이
	static String str;
	static int[] con = new int[4];
	static int[] cur = new int[4];
	static int answer;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		s = scanner.nextInt();
		p = scanner.nextInt();
		str = scanner.next();
		
		for(int i = 0; i < 4; i++) {
			con[i] = scanner.nextInt();
		}
		
		for(int i = 0; i < p; i++) {
			if(str.charAt(i) == 'A') {
				cur[0]++;
			}
			else if(str.charAt(i) == 'C') {
				cur[1]++;
			}
			else if(str.charAt(i) == 'G') {
				cur[2]++;
			}
			else if(str.charAt(i) == 'T') {
				cur[3]++;
			}
		}
		
		for(int i = 0; i < 4; i++) {
			if(con[i] > cur[i])
				break;
			if(i == 3)
				answer++;
		}
		
		for(int i = 1; i <= s-p; i++) {

			if(str.charAt(i-1) == 'A') {
				cur[0]--;
			}
			else if(str.charAt(i-1) == 'C') {
				cur[1]--;
			}
			else if(str.charAt(i-1) == 'G') {
				cur[2]--;
			}
			else if(str.charAt(i-1) == 'T') {
				cur[3]--;
			}
			
			if(str.charAt(i + p - 1) == 'A') {
				cur[0]++;
			}
			else if(str.charAt(i + p - 1) == 'C') {
				cur[1]++;
			}
			else if(str.charAt(i + p - 1) == 'G') {
				cur[2]++;
			}
			else if(str.charAt(i + p - 1) == 'T') {
				cur[3]++;
			}
			
			for(int j = 0; j < 4; j++) {
				if(con[j] > cur[j])
					break;
				if(j == 3)
					answer++;
			}
			
			
		}
		
		
		System.out.println(answer);
		
	}
}