import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] dices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        dices = new int[n][6]; //0-1, 2-3, 4-5
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 6; j++){
                if(j == 0) dices[i][0] = Integer.parseInt(st.nextToken());
                else if(j == 1) dices[i][2] = Integer.parseInt(st.nextToken());
                else if(j == 2) dices[i][4] = Integer.parseInt(st.nextToken());
                else if(j == 3) dices[i][3] = Integer.parseInt(st.nextToken());
                else if(j == 4) dices[i][5] = Integer.parseInt(st.nextToken());
                else dices[i][1] = Integer.parseInt(st.nextToken());
            }
        }

        //0번 주사위(가장 밑 주사위)의 밑면 값에 따라 주사위 최댓값 정해짐
        int ans = 0;
        for(int i = 0; i < 6; i++){
            ans = Math.max(ans, findMax(0, dices[0][i], 0));
        }

        System.out.println(ans);
    }

    public static int findMax(int order, int bottomValue, int ans){

        if(order == n){
            return ans;
        }

        int bottomPos = -1;
        for(int i = 0; i < 6; i++){
            if(dices[order][i] == bottomValue) {
                bottomPos = i;
                break;
            }
        }

        int topPos = -1;
        if(bottomPos % 2 == 1){
            topPos = bottomPos - 1;
        }
        else{
            topPos = bottomPos + 1;
        }

        int topValue = dices[order][topPos];

        int maxValue = -1;
        for(int i = 0; i < 6; i++){
            if(i == bottomPos || i == topPos) continue;
            maxValue = Math.max(maxValue, dices[order][i]);
        }

        return findMax(order + 1, topValue, ans + maxValue);
    }

}