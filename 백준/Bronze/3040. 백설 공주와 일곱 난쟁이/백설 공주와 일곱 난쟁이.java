import java.util.*;
import java.io.*;

public class Main {
	
	//9개의 숫자에서 7개의 숫자를 선택 -> 중복 없는 조합
	//조합된 숫자에서 합이 100이라면 answer
	
	static int[] input = new int[9];
	static boolean[] select = new boolean[9]; //선택된 수 표시
	static int max = 7; //뽑을 수
	static int n = 9; //전체 개수

	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		for(int i = 0; i < 9; i++) {
			input[i] = scanner.nextInt();
		}
		
		combi(0, 0);
	}
	
	static void combi(int start, int cur) {
		if(cur == max) {
			int total = 0;
			for(int i = 0; i < n; i++) {
				if(select[i] == true)
					total += input[i];
			}
			if(total == 100) {
				for(int i = 0; i < n; i++) {
					if(select[i] == true)
						System.out.println(input[i]);
				}
			}
				
		}
		
		for(int i = start; i < n; i++) {
			if(select[i] == false) {
				select[i] = true;
				combi(i, cur + 1);
				select[i] = false;
			}
		}
		
	}
	
}