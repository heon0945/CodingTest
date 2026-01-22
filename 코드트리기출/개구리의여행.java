import java.io.*;
import java.util.*;

public class 개구리의여행 {
    static int n;
    static int q;
    static int[][] map;
    static int[] dx = {0, -1, 0, 1, 0};
    static int[] dy = {0, 0, -1, 0, 1};
    static int[][][] minDist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < n; j++){
                char c = str.charAt(j);
                if(c == '.') map[i][j] = 0; //돌
                else if(c == 'S') map[i][j] = 1; //미끄러운돌
                else map[i][j] = 2; //천적
            }
        }

        q = Integer.parseInt(br.readLine());

        while(q-- > 0){

            StringTokenizer st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;

            //여행 시작
            sb.append(traveling(sx, sy, ex, ey)).append('\n');
        }

        System.out.println(sb);
    }

    public static int traveling(int sx, int sy, int ex, int ey){
        //최소 거리 초기화
        minDist = new int[n][n][6];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 1; k < 6; k++) {
                    minDist[i][j][k] = 1000000; //최댓값
                }
            }
        }

        //출발지부터 여행 시작 (위치, 경과 시간, 점프력)
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        q.add(new int[]{sx, sy, 0, 1});
        minDist[sx][sy][1] = 0;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            int curx = cur[0];
            int cury = cur[1];
            int curTime = cur[2];
            int curJump = cur[3];

            if(curx == ex && cury == ey) return curTime;


            //각 방향으로, 각 점프력마다 이동
            for(int i = 0; i < 5; i++){
                for(int j = 1; j <= curJump + 1; j++){

                    if(j > 5) break;

                    int time = 0;
                    if(j == curJump) time = 0; //점프력 유지
                    else if(j < curJump) time = 1; //점프력 감소
                    else time = j * j; //점프력 증가

                    if(i != 0) time += 1;

                    //점프 후 새로운 위치 계산
                    int nx = curx + dx[i] * j;
                    int ny = cury + dy[i] * j;

                    if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                    if(minDist[nx][ny][j] <= curTime + time) continue;
                    if(map[nx][ny] == 2) break; //점프 하려는 방향에 천적 있으면 이후로 이동 불가
                    if(map[nx][ny] == 1) continue;

                    q.add(new int[]{nx, ny, curTime + time, j});
                    minDist[nx][ny][j] = curTime + time;
                }
            }
        }

        int answer = 1000000;
        for(int i = 1; i <= 5; i++){
            answer = Math.min(answer, minDist[ex][ey][i]);
        }

        if(answer == 1000000) return -1;
        return answer;
    }
}