import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static List<Integer>[] compare;
    static int inDegree[];
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        inDegree = new int[n];
        visited = new boolean[n];
        compare = new List[n];

        for(int i = 0; i < n; i++){
            compare[i] = new ArrayList<>();
        }


        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;

            compare[s].add(e);
            inDegree[e] ++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            if(inDegree[i] == 0) {
                visited[i] = true;
                q.add(i);
            }

        }

        while(!q.isEmpty()){
            int cur = q.poll();

            sb.append(cur+1).append(" ");

            for(int i = 0; i < compare[cur].size(); i++){
                int next = compare[cur].get(i);

                if(visited[next]) continue;

                if(--inDegree[next] == 0){
                    q.add(next);
                }
            }

        }

        System.out.println(sb);
    }


}
