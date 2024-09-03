import java.util.*;
import java.io.*;

public class Main {
	
	static int N, L, D; //요청 간격;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		int start = L;
		int end = L + 5;
		int res = D;
		for(int i = 0; i < N; i++) {
//			System.out.println("start" + start +  " end" + end);
			while(res < start) res += D;
			
			if(start <= res && res < end) {
				System.out.print(res);
				return;
			}
			
			start = end + L;
			end = start + 5;
			
			
		}

		System.out.println(res);
		return;
	}
}