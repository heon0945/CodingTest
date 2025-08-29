import java.io.*;
import java.util.*;

public class Main {

    static int r, c;
    static char[][] map;
    static int[][] time;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Pair{
        int x, y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        time = new int[r][c];

        Queue<Pair> jihoon = new ArrayDeque<>();
        Queue<Pair> fires = new ArrayDeque<>();

        for(int i = 0; i < r; i++){
            String str = br.readLine();
            for(int j = 0; j < c; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'J') {
                    jihoon.add(new Pair(i, j));
                    time[i][j] = 0;
                }
                if(map[i][j] == 'F') fires.add(new Pair(i, j));
            }
        }

        while(!jihoon.isEmpty()){

            //불 확산
            int sz = fires.size();
            while(sz-- > 0){
                Pair cur = fires.poll();

                for(int i = 0; i < 4; i++){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                    if(map[nx][ny] == '#' || map[nx][ny] == 'F') continue;
                    map[nx][ny] = 'F';
                    fires.add(new Pair(nx, ny));
                }
            }

            //지훈 이동
            sz = jihoon.size();
            while(sz -- > 0){
                Pair cur = jihoon.poll();
                int curTime = time[cur.x][cur.y];

                for(int i = 0; i < 4; i++){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if(nx < 0 || nx >= r || ny < 0 || ny >= c) {
                        System.out.println(curTime + 1);
                        System.exit(0);
                    }
                    if(map[nx][ny] != '.') continue;
                    map[nx][ny] = 'J';
                    time[nx][ny] = curTime + 1;
                    jihoon.add(new Pair(nx, ny));
                }
            }

        }


        System.out.println("IMPOSSIBLE");
    }
}
