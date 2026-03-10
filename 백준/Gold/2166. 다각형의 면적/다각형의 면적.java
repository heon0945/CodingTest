import java.io.*;
import java.util.*;

public class Main{
    static int n;
    static long[][] points;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        points = new long[n][2];

        StringTokenizer st;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            points[i][0] = Long.parseLong(st.nextToken());
            points[i][1] = Long.parseLong(st.nextToken());
        }

        long sum = 0;

        for(int i = 0; i < n-1; i++){
            sum += points[i][0] * points[i+1][1] - points[i][1] * points[i+1][0];
        }

        sum += points[n-1][0] * points[0][1] - points[n-1][1] * points[0][0];

        System.out.printf("%.1f", Math.abs(sum) / 2.0);
    }
}