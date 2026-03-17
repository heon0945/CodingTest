import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static List<Integer>[] info;
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        info = new List[n+1];
        indegree =  new int[n+1];
        for(int i = 1; i <= n; i++) info[i] = new ArrayList<>();

        while(m-- > 0){
            st = new StringTokenizer(br.readLine());
            int before = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());
            info[before].add(after);
            indegree[after]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 1; i <= n; i++){
            if(indegree[i] == 0) pq.add(i);
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            int cur = pq.poll();

            sb.append(cur).append(" ");

            for(int next : info[cur]){
                indegree[next]--;

                if(indegree[next] == 0) pq.add(next);
            }
        }

        System.out.println(sb);
    }
}