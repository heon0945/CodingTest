import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static List<Integer>[] tasks;
    static int[] inDegree;
    static int[] time;
    static int[] result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        tasks = new List[n];
        inDegree = new int[n];
        time = new int[n];
        result = new int[n];

        for(int i = 0; i < n; i++){
            tasks[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            time[i] = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            for(int j = 0; j < cnt; j++){
                int next = Integer.parseInt(st.nextToken()) - 1;
                tasks[i].add(next);
                inDegree[next]++;
            }
        }

        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            if(inDegree[i] == 0){
                q.add(i);
                result[i] = time[i];
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int i = 0; i < tasks[cur].size(); i++){
                int next = tasks[cur].get(i);

                result[next] = Math.max(result[next], result[cur] + time[next]);

                
                if(--inDegree[next] == 0) {
                    q.add(next);
                }
            }


        }


        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(result[i], ans);
        }
        System.out.println(ans);

    }
}
