import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int inDegree[];
    static List<Integer>[] order;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        order = new List[n];
        inDegree = new int[n];

        for(int i = 0; i < n; i++){
            order[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int cnt = Integer.parseInt(st.nextToken());
            int pre = Integer.parseInt(st.nextToken()) - 1;

            for(int j = 1; j < cnt; j++){

                int next = Integer.parseInt(st.nextToken()) - 1;
                order[pre].add(next);
                inDegree[next]++;
                pre = next;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 0; i < n; i++){
            if(inDegree[i] == 0){
                q.add(i);
                sb.append(i + 1).append('\n');
            }
        }

        while(!q.isEmpty()){

            int cur = q.poll();

            for(int i = 0; i < order[cur].size(); i++){

                int next = order[cur].get(i);

                if(--inDegree[next] == 0){
                    q.add(next);
                    sb.append(next + 1).append('\n');
                }

            }

        }

        for(int i = 0; i < n; i++){
            if(inDegree[i] != 0) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(sb);


    }

}
