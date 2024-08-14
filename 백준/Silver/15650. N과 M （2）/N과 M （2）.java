import java.io.*;
import java.util.*;

public class Main {
	//조합
	
	
	static int n, m;
	static int[] input;
	static boolean[] select;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		n = scanner.nextInt();
		m = scanner.nextInt();
		
		input = new int[n];
		select = new boolean[n];
		for(int i = 0; i < n; i++) {
			input[i] = i+1;
		}
		
		combi(0, 0);
		System.out.println(sb);
	}
	
	static void combi(int start, int cur) {
		if(cur == m) {
			for(int i = 0; i < n; i++) {
				if(select[i]) {
					sb.append(input[i] + " ");
				}
			}
			sb.append('\n');
			return;
		}
		for(int i = start; i < n; i++) {
			if(!select[i]) {
				select[i] = true;
				combi(i, cur + 1);
				select[i] = false;
			}
		}
		
	}
}