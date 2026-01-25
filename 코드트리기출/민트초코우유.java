import java.io.*;
import java.util.*;

public class 민트초코우유 {
    public static int n, t;
    public static String[][] favorite; //선호 음식
    public static int[][] believe; //신앙심
    public static PriorityQueue<int[]> represent; //대표자

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        favorite = new String[n][n];
        believe = new int[n][n];

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < n; j++){
                char c = str.charAt(j);

                if(c == 'T') favorite[i][j] = "100"; //민트
                else if(c == 'C') favorite[i][j] = "010"; //초코
                else favorite[i][j] = "001"; //우유
            }
        }

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                believe[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        while(t-- > 0){
            //아침 : 신앙심 증가
            morning();

            //점심 : 그룹 형성, 대표 선임
            represent = new PriorityQueue<>((o1, o2) -> {
                if(o1[2] == o2[2]){
                    if(o1[3] == o2[3]){
                        if(o1[0] == o2[0]) return o1[1] - o2[1]; //열이 작은 순서
                        return o1[0] - o2[0]; //행이 작은 순서
                    }
                    return o2[3] - o1[3]; //신앙심 높은 순서
                }
                return o1[2] - o2[2]; //그룹 우선순위
            });
            lunch();

            //저녁 : 전파
            dinner();

            //출력
            int[] answer = new int[7];
            for(int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    String str = favorite[i][j];
                    if (str.equals("111")) answer[0] += believe[i][j];
                    else if (str.equals("110")) answer[1] += believe[i][j];
                    else if (str.equals("101")) answer[2] += believe[i][j];
                    else if (str.equals("011")) answer[3] += believe[i][j];
                    else if (str.equals("001")) answer[4] += believe[i][j];
                    else if (str.equals("010")) answer[5] += believe[i][j];
                    else if (str.equals("100")) answer[6] += believe[i][j];
                }
            }

            for(int a : answer){
                sb.append(a).append(" ");
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    public static void dinner(){
        //전파된 위치
        boolean[][] spread = new boolean[n][n];

        //대표자 별로 전파 시작
        while(!represent.isEmpty()){
            int[] r = represent.poll();
            int rx = r[0]; int ry = r[1];
            String rf = favorite[rx][ry];

            //이미 전파를 당했다면, 방어 상태 (전파 하지 않음)
            if(spread[rx][ry]) continue;

            //간절함 계산
            int x = believe[rx][ry] - 1;
            //전파 방향
            int dir = believe[rx][ry] % 4;
            believe[rx][ry] = 1;


            //전파 시작
            int curx = rx; int cury = ry; //전파 위치
            while(true){
                curx += dx[dir];
                cury += dy[dir];

                //종료 조건
                if(x <= 0) break;
                if(curx < 0 || curx >= n || cury < 0 || cury >= n) break;

                //선호 음식이 같은 경우, 패스
                if(favorite[curx][cury].equals(rf)) continue;

                spread[curx][cury] = true;

                int y = believe[curx][cury];

                if(x > y){ //강한 전파
                    favorite[curx][cury] = rf;
                    x -= (y + 1);
                    believe[curx][cury] += 1;
                }
                else{ //약한 전파
                    String nf = "";
                    for(int i = 0; i < 3; i++){
                        if(rf.charAt(i) == '1')
                            nf += '1';
                        else nf += favorite[curx][cury].charAt(i);
                    }
                    favorite[curx][cury] = nf;
                    believe[curx][cury] += x;
                    x = 0;
                }
            }

        }
    }

    public static void lunch(){

        boolean[][] visited = new boolean[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(visited[i][j]) continue;

                //그룹 생성
                grouping(i, j, visited);
            }
        }

    }

    public static void grouping(int x, int y, boolean[][] visited){
        int cnt = 0; //그룹원 수

        //대표 뽑기 위한 우선순위큐
        PriorityQueue<int[]> candidate = new PriorityQueue<>((o1, o2) -> {
            if(o1[2] == o2[2]){
                if(o1[0] == o2[0]) return o1[1] - o2[1]; //열이 작은 순서
                return o1[0] - o2[0]; //행이 작은 순서
            }
            return o2[2] - o1[2]; //신앙심 높은 순서
        });

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        cnt++;
        believe[x][y]--;
        candidate.add(new int[]{x, y, believe[x][y]});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(visited[nx][ny]) continue;
                if(!favorite[x][y].equals(favorite[nx][ny])) continue;

                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                cnt++;
                believe[nx][ny]--;
                candidate.add(new int[]{nx, ny, believe[nx][ny]});
            }
        }

        //대표 선임
        int[] top = candidate.poll();
        int rx = top[0];
        int ry = top[1];

        //현재 그룹 순위
        int group = 0;
        for (char c : favorite[x][y].toCharArray()) {
            group += c - '0';
        }

        believe[rx][ry] += cnt;
        represent.add(new int[]{rx, ry, group, believe[rx][ry]});
    }

    public static void morning(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                believe[i][j]++;
            }
        }
    }
}