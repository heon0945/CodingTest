import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new boolean[n][m];

        st = new StringTokenizer(br.readLine());
        for(int j = 0; j < m; j++){
            int h = Integer.parseInt(st.nextToken());
            for(int i = n-1; i >= 0; i--){
                if(h > 0){
                    map[i][j] = true;
                    h--;
                }
                else break;
            }
        }

        int cnt = 0;
        for(int i = 0; i < n; i++){
            boolean block = false;
            int tmp = 0;
            for(int j = 0; j < m; j++){
                if(!map[i][j]) tmp++;
                else if(block) {
                    cnt += tmp;
                    tmp = 0;
                }
                else if(!block) {
                    block = true;
                    tmp = 0;
                }
            }
        }

        System.out.println(cnt);
    }
}
