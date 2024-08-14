import java.io.*;
import java.util.*;

public class Main {

	
	static int n, m;
	static int[] arr;
	static int[] tot;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		n = scanner.nextInt();
		m = scanner.nextInt();
		
		arr = new int[n];
		tot = new int[n];
		int total = 0;
		for(int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
			total += arr[i];
			tot[i] = total;
		}
		
		for(int i = 0; i < m; i++) {
			int a, b;
			a = scanner.nextInt();
			b = scanner.nextInt();
			
			if(a - 2 < 0) {
				sb.append(tot[b-1]).append('\n');
			}
			else
				sb.append(tot[b-1] - tot[a-2]).append('\n');
		}
		System.out.print(sb);
		
	}
}