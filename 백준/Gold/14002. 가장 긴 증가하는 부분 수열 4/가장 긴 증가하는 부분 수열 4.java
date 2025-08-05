import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int arr[];
    static int dp[];
    static int prev[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];
        prev = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(prev, -1);
        Arrays.fill(dp, 1);

        dp[0] = 1;
        int maxLen = 1;
        int maxIdx = 0;
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j] && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if(dp[i] > maxLen){
                maxLen = dp[i];
                maxIdx = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(maxLen).append('\n');

        ArrayList<Integer> list = new ArrayList<>();
        int cur = maxIdx;
        while(true){
            if(cur == -1) break;

            list.add(arr[cur]);
            cur = prev[cur];
        }

        Collections.reverse(list);

        for(int num : list)
            sb.append(num).append(" ");

        System.out.println(sb);
    }

}
