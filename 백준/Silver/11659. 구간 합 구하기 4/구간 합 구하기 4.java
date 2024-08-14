import java.io.*;
import java.util.*;

//236340kb, 908ms
 
public class Main {
	
	static int n, m;
	static int[] arr;
	static int[] tot;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		n = scanner.nextInt();
		m = scanner.nextInt();
		
		arr = new int[n + 1];
		tot = new int[n + 1];
		int total = 0;
		for(int i = 1; i < n + 1; i++) {
			arr[i] = scanner.nextInt();
			total += arr[i];
			tot[i] = total;
		}
		
		for(int i = 0; i < m; i++) {
			int a, b;
			a = scanner.nextInt();
			b = scanner.nextInt();
			
			sb.append(tot[b] - tot[a-1]).append('\n');
		}
		System.out.print(sb);
		
	}
}