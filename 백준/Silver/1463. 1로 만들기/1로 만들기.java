import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int dp[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		dp = new int[n+1];
		
		
		dp[0] = 0;
		dp[1] = 0;
		
		for(int i = 2; i <= n; i++) {
			//빼서 하나 작은 숫자로 만들기
			dp[i] = dp[i-1] + 1;
			//2로 나누는 경우
			if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i/2] + 1);
			//3으로 나누는 경우
			if(i % 3 == 0) dp[i] = Math.min(dp[i], dp[i/3] + 1);
		}
		
		System.out.println(dp[n]);
	}
	
}