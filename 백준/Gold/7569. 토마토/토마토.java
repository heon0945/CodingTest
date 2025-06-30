import java.io.*;
import java.util.*;

public class Main {

    static int M, N, H;
    static int map[][][];

    static int dx[] = {0, 0, -1, 1, 0, 0};
    static int dy[] = {1, -1, 0, 0, 0, 0};
    static int dz[] = {0, 0, 0, 0, -1, 1};

    static int total = 0;
    static int get = 0;
    static int days = 0;

    public static class Tuple{
        int x, y, z;

        public Tuple(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static boolean OOB(int x, int y, int z){
        return x < 0 || x >= H || y < 0 || y >= N || z < 0 || z >= M;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        N = sc.nextInt();
        H = sc.nextInt();

        map = new int[H][N][M];


        //3차원 토마토 상태 채우기
        for(int i = 0; i < H; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < M; k++){
                    map[i][j][k] = sc.nextInt();

                    if(map[i][j][k] != -1) {
                        total++;
                        if(map[i][j][k] == 1)
                            get++;
                    }

                }
            }
        }


        //이미 다 익은 상태
        if(total == get) {
            System.out.println(days);
            System.exit(0);
        }

        //bfs
        ArrayDeque<Tuple> q = new ArrayDeque<>();
        for(int i = 0; i < H; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < M; k++){
                    if(map[i][j][k] == 1) {
                        q.add(new Tuple(i, j, k));
                    }
                }
            }
        }


        while(!q.isEmpty()){

            int sz = q.size();

            while(sz > 0){
                Tuple cur = q.poll();

                for(int i = 0; i < 6; i++){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    int nz = cur.z + dz[i];

                    if(OOB(nx, ny, nz)) continue;
                    if(map[nx][ny][nz] != 0) continue;

                    q.add(new Tuple(nx, ny, nz));
                    map[nx][ny][nz] = 1;
                    get++;
                }

                sz--;
            }

            days++;

            if(total == get) {
                System.out.println(days);
                System.exit(0);
            }
        }

        if(total == get)
            System.out.println(days);
        else
            System.out.println(-1);

    }
}
