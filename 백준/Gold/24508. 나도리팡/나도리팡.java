import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static long t;
    static int[] nadori;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        t = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int notZeroCnt = 0;
        nadori = new int[n];
        for(int i = 0; i < n; i++){
            nadori[i] = Integer.parseInt(st.nextToken());
            if(nadori[i] != 0) notZeroCnt++;
        }

        if(notZeroCnt == 0) {
            System.out.println("YES");
            return;
        }
        else if(notZeroCnt == 1){
            System.out.println("NO");
            return;
        }

        Arrays.sort(nadori);

        int cur = n - notZeroCnt;
        int target = n-1;

        while(t > 0 && cur < target){
            int remain = k - nadori[target];
            if(nadori[cur] < remain){
                nadori[target] += nadori[cur];
                t -= nadori[cur];
                cur++;
            }
            else{
                nadori[target] += remain;
                nadori[cur] -= remain;
                t -= remain;
                target--;
                if(nadori[cur] == 0) cur++;
            }
        }

        System.out.println(cur > target && t >= 0 ? "YES" : "NO");
    }
}