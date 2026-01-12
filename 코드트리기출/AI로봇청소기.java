import java.util.*;
import java.io.*;

public class AI로봇청소기 {
    public static int n, k, l; //격자 크기, 로봇 수, 테스트 수
    public static int[][] map; //격자 별 먼지 양, 물건
    public static int[][] robots; //로봇 위치(행, 열)
    public static boolean[][] robotMap;
    public static int[] dx = {-1, 0, 0, 1}; //이동, 확산에 사용되는 델타x (상, 좌, 우 하)
    public static int[] dy = {0, -1, 1, 0}; //이동, 확산에 사용되는 델타y
    public static int[] dxClean = {0, -1, 0, 1}; //청소에 사용되는 델타x (좌, 상, 우, 하)
    public static int[] dyClean = {-1, 0, 1, 0}; //청소에 사용되는 델타y

    public static void main(String[] args) throws IOException {

        //1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        robotMap = new boolean[n][n];
        robots = new int[k][2];
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            robots[i][0] = Integer.parseInt(st.nextToken()) - 1;
            robots[i][1] = Integer.parseInt(st.nextToken()) - 1;
            robotMap[robots[i][0]][robots[i][1]] = true;
        }

        //테스트
        while(l-- > 0){
            //1. 이동
            move();

            //2. 청소
            clean();

            //3. 먼지 축적
            accumulate();

            //4. 먼지 확산
            diffusion();

            //5. 출력
            int answer = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(map[i][j] > 0) answer += map[i][j];
                }
            }
            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    public static void move() {
        for (int i = 0; i < robots.length; i++) {

            boolean moved = false;
            robotMap[robots[i][0]][robots[i][1]] = false;

            ArrayDeque<int[]> q = new ArrayDeque<>();
            boolean[][] visited = new boolean[n][n];

            q.add(robots[i]);
            visited[robots[i][0]][robots[i][1]] = true;

            while(!q.isEmpty()){
                int size = q.size();
                PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
                    if(o1[0] == o2[0]) return o1[1] - o2[1];
                    return o1[0] - o2[0];
                });

                while(size-- > 0){

                    int[] rPos = q.poll();
                    int rx = rPos[0];
                    int ry = rPos[1];

                    if(map[rx][ry] > 0) {
                        pq.add(new int[]{rx, ry});
                    }

                    for(int k = 0; k < 4; k++){
                        int nx = rx + dx[k];
                        int ny = ry + dy[k];

                        if(!isDustArea(nx, ny)) continue;
                        if(robotMap[nx][ny]) continue;
                        if(visited[nx][ny]) continue;

                        q.add(new int[]{nx, ny});
                        visited[nx][ny] = true;

                    }
                }
                if(!pq.isEmpty()){
                    int[] min = pq.poll();
                    robots[i][0] = min[0];
                    robots[i][1] = min[1];
                    robotMap[min[0]][min[1]] = true;
                    moved = true;
                    break;
                }

            }

            if(!moved){
                robotMap[robots[i][0]][robots[i][1]] = true;
            }
        }
    }

    public static void clean(){
        for(int[] robot : robots){
            int rx = robot[0];
            int ry = robot[1];

            //청소할 구역 정하기
            int dust = Math.min(map[rx][ry], 20); //청소할 양

            //사방 청소 양 구하기
            for(int k = 0; k < 4; k++){
                int nx = rx + dxClean[k];
                int ny = ry + dyClean[k];

                if(!isDustArea(nx, ny)) continue;

                dust += Math.min(map[nx][ny], 20);
            }

            //가장 많이 청소할 수 있는 청소 구역 정하기
            int cleanArea = -1;
            int maxDust = -1;
            for(int k = 0; k < 4; k++){
                int nx = rx + dxClean[k];
                int ny = ry + dyClean[k];

                int areaDust = dust;
                if(isDustArea(nx, ny)) areaDust -= Math.min(map[nx][ny], 20);

                if(areaDust > maxDust){
                    maxDust = areaDust;
                    cleanArea = k;
                }
            }

            //지정된 구역 청소하기
            map[rx][ry] = map[rx][ry] > 20 ? map[rx][ry] - 20 : 0;
            for(int k = 0; k < 4; k++){

                if(k == cleanArea) continue;

                int nx = rx + dxClean[k];
                int ny = ry + dyClean[k];

                if(!isDustArea(nx, ny)) continue;

                map[nx][ny] = map[nx][ny] > 20 ? map[nx][ny] - 20 : 0;
            }
        }
    }

    public static boolean isDustArea(int x, int y){
        //해당 좌표가 격자 벗어나지 않는가
        if(x < 0 || x >= n || y < 0 || y >= n) return false;
        //해당 좌표가 물건이 있는 칸은 아닌가
        if(map[x][y] == -1) return false;
        return true;
    }
    public static void diffusion(){

        //확산된 먼지 잠시 저장할 공간
        int[][] tmp = new int[n][n];

        //확산
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){

                //먼지가 없는 칸에 대해서만 수행
                if(map[i][j] != 0) continue;

                //주변의 먼지 양 계산
                int around = 0;
                for(int k = 0; k < 4; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(!isDustArea(nx, ny)) continue;

                    around += map[nx][ny];
                }

                //확산된 양 계산
                tmp[i][j] = around / 10;
            }
        }

        //동시에 격자에 먼지 양 반영하기
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                map[i][j] += tmp[i][j];
            }
        }
    }

    public static void accumulate(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] > 0) map[i][j] += 5;
            }
        }
    }
}