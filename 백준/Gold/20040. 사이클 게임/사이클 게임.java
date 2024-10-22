import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m;
	static int parents[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		parents = new int[n];
		
		make();
		
		for(int i = 1; i <= m; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			
			if(!union(start, end)) {
				System.out.println(i);
				System.exit(0);
			}
		}
		
		System.out.println(0);
	}
	
	static void make() {
		for(int i = 0; i < n; i++) {
			parents[i] = -1;
		}
	}
	
	static int find(int a) {
		if(parents[a] < 0) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			//System.out.println("false");
			return false;
		}
		
		parents[aRoot] = bRoot;
		//System.out.println("true");
		return true;
	}
	
}