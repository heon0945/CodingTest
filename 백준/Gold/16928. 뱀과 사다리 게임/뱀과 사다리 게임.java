import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] ladders;
    static int[] snakes;
    static boolean[] visited;

    static class Pair{
        int pos, cnt;
        Pair(int pos, int cnt){
            this.pos = pos;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ladders = new int[101];
        snakes = new int[101];
        visited = new boolean[101];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            ladders[start] = end;
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            snakes[start] = end;
        }

        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(1, 0));
        visited[1] = true;

        while(!q.isEmpty()){
            Pair cur = q.poll();

            if(cur.pos == 100) {
                System.out.println(cur.cnt);
                System.exit(0);
            }

            for(int i = 1; i <= 6; i++){

                int next = cur.pos + i;

                if(next > 100) continue;
                if(visited[next]) continue;

                visited[next] = true;
                if(ladders[next] != 0) next = ladders[next];
                else if(snakes[next] != 0) next = snakes[next];

                visited[next] = true;
                q.add(new Pair(next, cur.cnt + 1));
            }
        }
    }

}
