import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int tc;
	static int[] player = new int[9];
	static int[] opposite = new int[9];
	static int[] output = new int[9];
	static boolean[] visit = new boolean[9];
	static int wn, ln;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			wn = 0;
			ln = 0;
			boolean[] card = new boolean[18];
			StringTokenizer st = new StringTokenizer(br.readLine());
			//make player card
			for(int i = 0; i < 9; i++) {
				player[i] = Integer.parseInt(st.nextToken());
				card[player[i] - 1] = true;
			}
			//make opposite card
			int i = 0;
			for(int n = 0; n < 18; n++) {
				if(!card[n]) {
					opposite[i] = n + 1;
					i++;
					
				}
			}
			
			permu(0);
			
			
			System.out.println("#" + t + " " + wn + " " + ln);
		}
		
	}
	
	public static void permu(int order) {
		if(order == 9) {
			int pv = 0, ov = 0;
			
			for(int i = 0; i < 9; i++) {
				if(player[i] > output[i])
					pv += player[i] + output[i];
				else
					ov += player[i] + output[i];
			}
			if(pv > ov)
				wn++;
			else
				ln++;
			return;
		}
		for(int i = 0; i < 9; i++) {
			if(!visit[i]) {
				visit[i] = true;
				output[order] = opposite[i];
				permu(order + 1);
				visit[i] = false;
			}
		}
	}
}