import java.io.*;
import java.util.*;

public class Main {
    static char map[][];
    static boolean visited[];
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[5][5];
        visited = new boolean[25];

        for(int i = 0; i < 5; i++){
            String str = br.readLine();
            for(int j = 0; j < 5; j++){
                map[i][j] = str.charAt(j);
            }
        }


        combi(0, 0, 0);

        System.out.println(ans);

    }

    static void combi(int cur, int cnt, int ycnt){

        if(ycnt > 3) return;

        if(cnt == 7){
            if(isAdj()) ans++;
            return;
        }

        for(int i = cur; i < 25; i++){

            if(visited[i]) continue;
            visited[i] = true;

            if(map[i / 5][i % 5] == 'Y')
                combi(i, cnt + 1, ycnt + 1);
            else
                combi(i, cnt + 1, ycnt);

            visited[i] = false;
        }

    }

    static boolean isAdj(){

        int start = -1;
        for(int i = 0; i < 25; i++){
            if(visited[i]){
                start = i;
                break;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean adjVisited[] = new boolean[25];
        int dx[] = {-1, 0, 1, 0};
        int dy[] = {0, -1, 0, 1};
        int cnt = 0;

        q.add(start);
        adjVisited[start] = true;
        cnt++;

        while(!q.isEmpty()){

            int cur = q.poll();

            int x = cur / 5;
            int y = cur % 5;

            for(int i = 0; i < 4; i++){

                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx > 4 || ny < 0 || ny > 4) continue;

                if(!visited[nx * 5 + ny]) continue;

                if(adjVisited[nx * 5 + ny]) continue;

                cnt++;
                adjVisited[nx * 5 + ny] = true;
                q.add(nx * 5 + ny);
            }

        }

        if(cnt == 7) return true;
        else return false;

    }


}
