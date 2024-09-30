import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            int low = 0;
            int high = N-1;
            int mid;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            while(low <= high) {
                mid = (low+high)/2;
//                시작점보다 mid값이 같거나 클 때
                if(arr[mid] >= start) {
                    high = mid - 1;
                    min = Math.min(min, mid);
                }
                else {
                    low = mid + 1;
                }
            }
            low = 0;
            high = N-1;
            while(low <= high) {
                mid = (low+high)/2;
//                끝점보다 mid값이 같거나 작을 때
                if(arr[mid] <= end) {
                    low = mid + 1;
                    max = Math.max(max, mid);
                }
                else {
                    high = mid - 1;
                }
            }
            if(min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) sb.append(0).append('\n');
            else sb.append(max - min + 1).append('\n');
        }
        
        System.out.println(sb);
    }

}