import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 1000000;
    static int n, k;
    static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[100001];

        Arrays.fill(map, INF);
        map[n] = 0;

        Queue<Integer> q = new ArrayDeque<>();

        q.add(n);

        while(!q.isEmpty()){

            int cur = q.poll();

            if(cur - 1 >= 0 && map[cur] + 1 < map[cur - 1]){
                map[cur - 1] = map[cur] + 1;
                q.add(cur - 1);
            }


            if(cur + 1 <= 100000 && map[cur] + 1 < map[cur + 1]){
                map[cur + 1] = map[cur] + 1;
                q.add(cur + 1);
            }

            if(2 * cur <= 100000 && map[cur] < map[2 * cur]){
                map[2 * cur] = map[cur];
                q.add(2 * cur);
            }

        }

        System.out.println(map[k]);
    }
}
