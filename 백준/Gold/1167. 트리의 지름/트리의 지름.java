import java.io.*;
import java.util.*;

public class Main {

    static int v;
    static List<Node>[] graph;
    static int answer;
    static boolean[] visited;
    static int max;

    static class Node{
        int to, weight;
        Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        v = Integer.parseInt(br.readLine());

        graph = new List[v+1];
        for(int i = 1; i <= v; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < v; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int cur = Integer.parseInt(st.nextToken());

            while(true){
                int to = Integer.parseInt(st.nextToken());

                if(to == -1) break;

                int weight = Integer.parseInt(st.nextToken());

                graph[cur].add(new Node(to, weight));
            }

        }

        //가장 먼 노드 찾기 (길이를 구하는 시작점이 될 것)
        visited = new boolean[v+1];
        visited[1] = true;
        dfs(1, 0);

        //가장 긴 트리 지름 찾기
        visited = new boolean[v+1];
        visited[max] = true;
        dfs(max, 0);

        System.out.println(answer);

    }

    public static void dfs(int cur, int len){

        int cnt = 0;
        for(Node next : graph[cur]){
            if(visited[next.to]) continue;

            visited[next.to] = true;
            cnt++;
            dfs(next.to, len + next.weight);
        }

        if(cnt == 0){

            if(answer < len){
                answer = len;
                max = cur;
            }
        }
    }
}
