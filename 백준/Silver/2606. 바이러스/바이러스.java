import java.io.*;
import java.util.*;

public class Main {
    static int V, E;
    static int map[][];
    static boolean visit[];

    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();

        map = new int[V+1][V+1];
        visit = new boolean[V+1];


        for(int i = 0; i < E; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            map[a][b] = 1;
            map[b][a] = 1;
        }

        ArrayDeque<Integer> st = new ArrayDeque<>();

        st.add(1);
        visit[1] = true;

        while(!st.isEmpty()){
            int cur = st.poll();
            cnt++;

            for(int i = 1; i <= V; i++){
                if(map[cur][i] != 1) continue;
                if(visit[i]) continue;

                st.add(i);
                visit[i] = true;
            }
        }

        System.out.println(cnt-1);
    }
}
