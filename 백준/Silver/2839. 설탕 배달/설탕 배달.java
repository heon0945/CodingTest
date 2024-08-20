import java.util.*;
import java.io.*;

public class Main {
	//dp
	//n사이즈의 배열 생성, max로 초기화
	//인덱스 3과 5를 1로 초기화
	//6무터 차례대로 탐색
		//인덱스 -3 or -5의 배열 값에 + 1한 값, 현재 값 중 작은 걸로 업데이트
	
	
	static int[] arr;
	static int n;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		
		arr = new int[n + 1];
		for(int i = 0; i <= n; i++) {
			if(i == 3 || i == 5)
				arr[i] = 1;
			else
				arr[i] = 2000;
		}
		
		for(int i = 6; i <= n; i++) {
			int tmp = Math.min(arr[i-3] + 1, arr[i-5] + 1);
			arr[i] = Math.min(arr[i], tmp);
		}
		
		if(arr[n] == 2000) {
			System.out.println(-1);
		}
		else {
			System.out.println(arr[n]);
		}
		
	}
}