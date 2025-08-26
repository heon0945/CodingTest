import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] prev1;
    static int[] prev2;
    static int cnt1;
    static int cnt2;
    static int[] after;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        prev1 = new int[n];
        prev2 = new int[n];
        after = new int[n];

        String tmp = br.readLine();
        for(int i = 0; i < n; i++){
            int b = tmp.charAt(i) - '0';
            prev1[i] = b;
            prev2[i] = b;

        }
        tmp = br.readLine();
        for(int i = 0; i < n; i++){
            int b = tmp.charAt(i) - '0';
            after[i] = b;
        }

        prev2[0] = prev2[0] == 0 ? 1 : 0;
        prev2[1] = prev2[1] == 0 ? 1 : 0;
        cnt2++;

        for(int i = 0; i < n-2; i++){
            if(prev1[i] != after[i]){
                prev1[i] = prev1[i] == 0 ? 1 : 0;
                prev1[i+1] = prev1[i+1] == 0 ? 1 : 0;
                prev1[i+2] = prev1[i+2] == 0 ? 1 : 0;
                cnt1++;
            }

            if(prev2[i] != after[i]){
                prev2[i] = prev2[i] == 0 ? 1 : 0;
                prev2[i+1] = prev2[i+1] == 0 ? 1 : 0;
                prev2[i+2] = prev2[i+2] == 0 ? 1 : 0;
                cnt2++;
            }

        }

        //n-1칸
        if(prev1[n-2] != after[n-2]){
            prev1[n-2] = prev1[n-2] == 0 ? 1 : 0;
            prev1[n-1] = prev1[n-1] == 0 ? 1 : 0;
            cnt1++;
        }
        if(prev2[n-2] != after[n-2]){
            prev2[n-2] = prev2[n-2] == 0 ? 1 : 0;
            prev2[n-1] = prev2[n-1] == 0 ? 1 : 0;
            cnt2++;
        }


        boolean same1 = true;
        boolean same2 = true;
        for(int i = 0; i < n; i++){
            if(prev1[i] != after[i]) {
                same1 = false;
            }
            if(prev2[i] != after[i]) {
                same2 = false;
            }
        }

        int ans = -1;
        if(same1 && same2) ans = Math.min(cnt1, cnt2);
        else if(same1) ans = cnt1;
        else if(same2) ans = cnt2;

        System.out.println(ans);


    }
}