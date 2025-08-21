import java.io.*;
import java.util.*;

public class Main {

    public static class Node{
        String order;
        int value;
        public Node(String order, int value){
            this.order = order;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int q = Integer.parseInt(br.readLine());

        StringTokenizer st;
        while(q-- > 0){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(bfs(a, b)).append('\n');
        }

        System.out.println(sb);
    }

    public static String bfs(int a, int b){

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node("", a));

        boolean[] visited = new boolean[10000];
        visited[a] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.value == b) return cur.order;

            //d
            int d = (cur.value * 2) % 10000;
            if(!visited[d]){
                q.add(new Node(cur.order + "D", d));
                visited[d] = true;
            }

            //s
            int s = cur.value - 1;
            if(s == -1) s = 9999;
            if(!visited[s]){
                q.add(new Node(cur.order + "S", s));
                visited[s] = true;
            }

            int d4 = cur.value % 10;
            cur.value = cur.value / 10;
            int d3 = cur.value % 10;
            cur.value = cur.value / 10;
            int d2 = cur.value % 10;
            cur.value = cur.value / 10;
            int d1 = cur.value % 10;
            cur.value = cur.value / 10;

            //l
            int l = d2 * 1000 + d3 * 100 + d4 * 10 + d1;
            if(!visited[l]){
                q.add(new Node(cur.order + "L", l));
                visited[l] = true;
            }

            //r
            int r = d4 * 1000 + d1 * 100 + d2 * 10 + d3;
            if(!visited[r]){
                q.add(new Node(cur.order + "R", r));
                visited[r] = true;
            }
        }

        return "-1";
    }
}
