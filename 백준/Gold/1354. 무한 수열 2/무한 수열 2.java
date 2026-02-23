import java.io.*;
import java.util.*;

public class Main {

    static Map<Long, Long> dp = new HashMap<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        System.out.println(recur(n, p, q, x, y));

    }

    static long recur(long n, int p, int q, int x, int y){
        if(n <= 0) return 1;

        //메모이제이션 (매번 계산하지 않음)
        if(dp.containsKey(n)) return dp.get(n);

        dp.put(n, recur(n / p - x, p, q, x, y) + recur(n / q - y, p, q, x, y));

        return dp.get(n);
    }
}
