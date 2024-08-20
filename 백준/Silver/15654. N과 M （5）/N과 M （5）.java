import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[] arr;
	static int[] output;
	static boolean[] visit;
	static StringBuilder sb = new StringBuilder();
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		n = scanner.nextInt();
		m = scanner.nextInt();
		
		arr = new int[n];
		visit = new boolean[n];
		output = new int[m];
		for(int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}
		
		Arrays.sort(arr);
		permu(0);
		
		System.out.print(sb);
	}
	
	static void permu(int order) {
		if(order == m) {
			for(int i = 0; i < m; i++) {
				sb.append(output[i]).append(" ");
			}
			sb.append('\n');
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!visit[i]) {
				visit[i] = true;
				output[order] = arr[i];
				permu(order + 1);
				visit[i] = false;
			}
		}
		
	}
	
	
}