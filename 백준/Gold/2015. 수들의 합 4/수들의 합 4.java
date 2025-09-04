import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] arr;
    static int[] sum;
    static Map<Integer, Long> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        sum = new int[n];

        int tmp = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            tmp += Integer.parseInt(st.nextToken());
            sum[i] = tmp;
        }

        long answer = 0L;
        map = new HashMap<>();
        for(int i = 0; i < n; i++){
            int value = sum[i];

            if(value == k) answer++;

            answer += map.getOrDefault(value - k, 0L);

            map.put(sum[i], map.getOrDefault(sum[i], 0L) + 1);

        }

        System.out.println(answer);

    }
}
