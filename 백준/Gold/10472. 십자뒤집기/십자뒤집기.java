import java.util.*;
import java.io.*;

public class Main {

    static int tc;
    static boolean map[][];

    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};

    static int ans;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        tc = Integer.parseInt(br.readLine());

        while(tc > 0){
            tc--;

            ans = Integer.MAX_VALUE;
            map = new boolean[3][3];

            for(int i = 0; i < 3; i++){
                String str = br.readLine();

                for(int j = 0; j < 3; j++){
                    char c = str.charAt(j);

                    if(c == '*') map[i][j] = false;
                    else map[i][j] = true;
                }
            }

            solving(0, 0);

            sb.append(ans).append('\n');
        }

        System.out.println(sb);
    }

    public static void solving(int click, int cur){

        //종료 조건
        if(cur == 9){
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(!map[i][j]) return;
                }
            }

            ans = Math.min(click, ans);
            return;
        }

        //조기 종료
        if(click > ans) return;


        //원복 위한 원래 배열 백업
        boolean tmp[][] = new boolean[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                tmp[i][j] = map[i][j];
            }
        }

        //클릭하는 경우 색 전환
        int x = cur / 3;
        int y = cur % 3;
        map[x][y] = !map[x][y];

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= 3 || ny < 0 || ny >= 3) continue;

            map[nx][ny] = !map[nx][ny];
        }

        solving(click + 1, cur + 1);

        // 원복
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                map[i][j] = tmp[i][j];
            }
        }

        solving(click, cur + 1);

    }
}
