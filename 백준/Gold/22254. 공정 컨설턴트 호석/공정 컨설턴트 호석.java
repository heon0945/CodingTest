import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static long x;
    static long[] present;

    public static boolean canProcess(int k){
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for(int i = 0; i < k; i++){
            pq.add(0L);
        }
        for(int i = 0; i < n; i++){
            long cur = pq.poll();
            cur += present[i];
            if(cur > x) return false;
            pq.add(cur);
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Long.parseLong(st.nextToken());

        present = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            present[i] = Long.parseLong(st.nextToken());
        }

        int low = 1, high = n;
        int answer = n;
        while(low <= high){
            int mid = (low + high) / 2;
            if(canProcess(mid)){
                answer = mid;
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
