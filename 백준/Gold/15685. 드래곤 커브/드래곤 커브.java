import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int n;
    static boolean map[][];
    static int dx[] = {0, -1, 0, 1};
    static int dy[] = {1, 0, -1, 0};
    static int ex, ey;
    static int sx, sy;

    public static class Pair{
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new boolean[101][101];

        for(int i = 0; i < n; i++) {
            //해당 드래곤 커브 표시
            boolean tmap[][] = new boolean[101][101];

            //0세대 시작점 체크
            sy = sc.nextInt();
            int sx = sc.nextInt();
            tmap[sx][sy] = true;

            //0세대 끝점 체크
            int dir = sc.nextInt();
            ex = sx + dx[dir];
            ey = sy + dy[dir];
            tmap[ex][ey] = true;

            //원하는 세대만큼 드래곤 커브 생성
            int gen = sc.nextInt();
            for(int k = 0; k < gen; k++){

                drawLine(ex, ey, tmap);
                //기준점 바꾸기
                int subx = ex - sx;
                int suby = ey - sy;
                ex = ex - suby;
                ey = ey + subx;
            }

            //해당 드래곤 커브 전체 맵에 그리기
            for(int r = 0; r <= 100; r++){
                for(int c = 0; c <= 100; c++){
                    if(tmap[r][c]) map[r][c] = true;
                }
            }
        }

        //사각형 카운트 세기
        int square = 0;
        int tdx[] = {0, 0, 1, 1};
        int tdy[] = {0, 1, 1, 0};
        for(int x = 0; x <= 100; x++){
            for(int y = 0; y <= 100; y++){

                //네 가지 꼭짓점 살펴보기
                int cnt = 0;
                for(int i = 0; i < 4; i++){
                    int tx = x + tdx[i];
                    int ty = y + tdy[i];

                    if(OOB(tx, ty)) break;
                    if(!map[tx][ty]) break;
                    cnt++;
                }

                if(cnt >= 4) square++;
            }
        }

        System.out.println(square);
    }

    static void drawLine(int px, int py, boolean[][] tmap){
        boolean visit[][] = new boolean[101][101];
        boolean newline[][] = new boolean[101][101];
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(px, py));
        visit[px][py] = true;

        //bfs 실행
        while(!q.isEmpty()){
            int x = q.peek().x;
            int y = q.peek().y;
            q.poll();

            for(int i = 0; i < 4; i++){
                int tx = x + dx[i];
                int ty = y + dy[i];

                if(OOB(tx, ty)) continue;
                if(visit[tx][ty]) continue;
                if(!tmap[tx][ty]) continue;

                q.add(new Pair(tx, ty));
                visit[tx][ty] = true;

                //드래곤 커브 새로운 부분 그리기
                int subx = px-tx;
                int suby = py-ty;
                int nx = px - suby;
                int ny = py + subx;
                if(!OOB(nx, ny)) newline[nx][ny] = true;
            }
        }

        for(int x = 0; x <= 100; x++) {
            for (int y = 0; y <= 100; y++) {
                if(newline[x][y]) tmap[x][y] = true;
            }
        }
    }




    static boolean OOB(int x, int y){
        return x < 0 || x > 100 || y < 0 || y > 100;
    }


}