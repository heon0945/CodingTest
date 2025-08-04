import java.io.*;
import java.util.*;

public class Main {

    static final long INF = 100000000000L;
    static int t;
    static int k, w, h;
    static int[][] map;
    static long[][] dist;
    static int ex, ey;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static class Node implements Comparable<Node>{
        int x, y;
        long weight;

        Node(int x, int y, long weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        public int compareTo(Node o){
            return Long.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-- > 0){
            StringTokenizer st;

            st = new StringTokenizer(br.readLine());

            k = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new int[h][w];
            dist = new long[h][w];

            Map<Character, Integer> klingon = new HashMap<>();

            for(int i = 0; i < k; i++){
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                int time = Integer.parseInt(st.nextToken());

                klingon.put(c, time);
            }

            for(int i = 0; i < h; i++){
                String tmp = br.readLine();
                for(int j = 0; j < w; j++){

                    char key = tmp.charAt(j);

                    if(key == 'E') {
                        map[i][j] = 0;
                        ex = i;
                        ey = j;
                    }
                    else{
                        map[i][j] = klingon.get(key);
                    }
                }
            }

            //2차원 다익스트라
            PriorityQueue<Node> pq = new PriorityQueue<>();

            for(int i = 0; i < h; i++){
                for(int j = 0; j < w; j++){
                    dist[i][j] = INF;
                }
            }
            dist[ex][ey] = 0;


            pq.add(new Node(ex, ey, 0));

            while(!pq.isEmpty()){

                Node cur = pq.poll();

                if(dist[cur.x][cur.y] < cur.weight) continue;


                for(int i = 0; i < 4; i++){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;

                    long newDist = cur.weight + map[nx][ny];

                    if(newDist < dist[nx][ny]){
                        pq.add(new Node(nx, ny, newDist));
                        dist[nx][ny] = newDist;
                    }


                }
            }

            long min = INF;
            for(int i = 0; i < h; i++){
                for(int j = 0; j < w; j++){

                    if(i == 0  || i == h-1 || j == 0 || j == w-1){
                        min = Math.min(min, dist[i][j]);
                    }
                }
            }

            sb.append(min).append('\n');


        }

        System.out.print(sb);
    }
}