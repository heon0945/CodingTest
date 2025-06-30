import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main{
    static int N, M, V;
    static int map[][];
    static boolean visit[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visit = new boolean[N];

        // 2차원 배열로 그래프 만들기
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            map[a][b] = 1;
            map[b][a] = 1;
        }

        // dfs (stack)
        Stack<Integer> stack = new Stack<>();
        stack.push(V-1);

        while(!stack.isEmpty()){
            int cur = stack.pop();
            if(visit[cur]) continue;
            visit[cur] = true;
            sb.append(cur+1).append(" ");

            for(int i = N-1; i >= 0; i--){ // 작은 값부터 탐색하기 위함
                if(visit[i]) continue;
                if(map[cur][i] != 1) continue;

                stack.push(i);
            }
        }

        sb.append('\n');
        for(int i = 0; i < N; i++){
            visit[i] = false;
        }


        // bfs
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.push(V-1);
        visit[V-1] = true;

        while(!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur+1).append(" ");

            for(int i = 0; i < N; i++){
                if(visit[i]) continue;
                if(map[cur][i] != 1) continue;

                q.add(i);
                visit[i] = true;
            }
        }


        System.out.print(sb);

    }
}