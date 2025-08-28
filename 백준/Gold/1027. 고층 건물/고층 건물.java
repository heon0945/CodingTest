import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] building;
    static int[] cnt;

    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        building = new int[n];
        cnt = new int[n];
        visited = new boolean[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            building[i] = Integer.parseInt(st.nextToken());
        }

        selecting(0, 0);

        int max = -1;
        for(int i = 0; i < n; i++){
            if(cnt[i] > max){
                max = cnt[i];
            }
        }

        System.out.println(max);
    }

    public static void selecting(int cur, int order){

        if(order == 2){

            int[] two = {-1, -1};
            for(int i = 0; i < n; i++){
                if(visited[i]){
                    if(two[0] == -1) two[0] = i;
                    else {
                        two[1] = i;
                        break;
                    }
                }
            }

            comparing(two[0], two[1]);

            return;
        }

        for(int i = cur; i < n; i++){
            if(visited[i]) continue;
            visited[i] = true;
            selecting(i, order + 1);
            visited[i] = false;
        }
    }

    public static void comparing(int a, int b){
        double slide = (building[b] - building[a]) / (double)(b - a);

        boolean flag = true;
        for(int i = a + 1; i < b; i++){
            double cur = building[a] + slide * (i - a);

            if(cur <= building[i]){
                flag = false;
                break;
            }
        }

        if(flag){
            cnt[a]++;
            cnt[b]++;
        }
    }
}
