import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int map[][];
    static int ages = 0;

    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};

    public static class Pair{
        int x, y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static boolean OOB(int x, int y){
        return x < 0 || x >= N || y < 0 || y >= M;
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = sc.nextInt();
            }
        }


        while(true){

            ages++;

            //빙산 녹이기
            ArrayDeque<Pair> q = new ArrayDeque<>();
            boolean visit[][] = new boolean[N][M];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(map[i][j] != 0){
                        q.add(new Pair(i, j));
                        visit[i][j] = true;
                    }
                }
            }

            while(!q.isEmpty()){
                Pair cur = q.poll();
                int sea = 0;
                for(int i = 0; i < 4; i++){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if(OOB(nx, ny)) continue;
                    if(map[nx][ny] == 0 && !visit[nx][ny]) sea++;
                }

                map[cur.x][cur.y] = Math.max(map[cur.x][cur.y] - sea, 0);
            }



            //빙산 개수 세기
            int cnt = 0;
            visit = new boolean[N][M];
            q = new ArrayDeque<>();
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(map[i][j] == 0) continue;
                    if(visit[i][j]) continue;

                    q.add(new Pair(i, j));
                    visit[i][j] = true;
                    cnt++;

                    while(!q.isEmpty()){
                        Pair cur = q.poll();

                        for(int k = 0; k < 4; k++) {
                            int x = cur.x + dx[k];
                            int y = cur.y + dy[k];

                            if (OOB(x, y)) continue;
                            if (visit[x][y]) continue;
                            if (map[x][y] == 0) continue;

                            q.add(new Pair(x, y));
                            visit[x][y] = true;
                        }
                    }
                }
            }


            if (cnt == 0) {
                System.out.println(0);
                break;
            }

            if(cnt >= 2){
                System.out.println(ages);
                break;
            }

        }
    }
}
