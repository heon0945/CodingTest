import java.util.*;
import java.io.*;

public class Main {

    static int N, Q;
    static int arr[];

    static int sum[];

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        sum = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //정렬
        Arrays.sort(arr);

        //누적합 계산
        sum[1] = arr[1];
        for(int i = 2; i <= N; i++){
            sum[i] = sum[i-1] + arr[i];
        }

        //질문에 대한 대답
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            sb.append(sum[R] - sum[L-1]).append('\n');

        }

        System.out.println(sb);
    }
}
