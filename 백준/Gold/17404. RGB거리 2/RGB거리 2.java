import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] rDP;
    static int[][] gDP;
    static int[][] bDP;
    static int[][] colors;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        
        rDP = new int[3][n+1];
        gDP = new int[3][n+1];
        bDP = new int[3][n+1];
        colors = new int[3][n+1];
        
        StringTokenizer st;
        
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            
            colors[0][i] = Integer.parseInt(st.nextToken());
            colors[1][i] = Integer.parseInt(st.nextToken());
            colors[2][i] = Integer.parseInt(st.nextToken());
        }
        
        
        int answer = 10000000;
        
        //첫번째 블럭이 빨간색인 경우
        rDP[0][1] = colors[0][1];
        for(int i = 2; i <= n; i++){
            
            //현재 빨, 파, 초 (i)
            for(int j = 0; j < 3; j++){
                int min = 10000000;
                //이전 빨, 파, 초 (i-1)
                for(int k = 0; k < 3; k++){
                    if(j == k) continue; //같은 색인 경우
                    
                    if(rDP[k][i-1] == 0) continue; //이전 색이 칠해진 적 없는 경우
                    
                    min = Math.min(rDP[k][i-1], min);
                }
                
                if(min == 10000000) continue;
                
                rDP[j][i] = min + colors[j][i];
            }
        }
        
        answer = Math.min(answer, rDP[1][n]);
        answer = Math.min(answer, rDP[2][n]);

        //첫번째 블럭이 초록색인 경우
        gDP[1][1] = colors[1][1];
        for(int i = 2; i <= n; i++){

            //현재 빨, 파, 초 (i)
            for(int j = 0; j < 3; j++){
                int min = 10000000;
                //이전 빨, 파, 초 (i-1)
                for(int k = 0; k < 3; k++){
                    if(j == k) continue; //같은 색인 경우

                    if(gDP[k][i-1] == 0) continue; //이전 색이 칠해진 적 없는 경우

                    min = Math.min(gDP[k][i-1], min);
                }

                if(min == 10000000) continue;

                gDP[j][i] = min + colors[j][i];
            }
        }

        answer = Math.min(answer, gDP[0][n]);
        answer = Math.min(answer, gDP[2][n]);

        //첫번째 블럭이 파란색인 경우
        bDP[2][1] = colors[2][1];
        for(int i = 2; i <= n; i++){

            //현재 빨, 파, 초 (i)
            for(int j = 0; j < 3; j++){
                int min = 10000000;
                //이전 빨, 파, 초 (i-1)
                for(int k = 0; k < 3; k++){
                    if(j == k) continue; //같은 색인 경우

                    if(bDP[k][i-1] == 0) continue; //이전 색이 칠해진 적 없는 경우

                    min = Math.min(bDP[k][i-1], min);
                }

                if(min == 10000000) continue;

                bDP[j][i] = min + colors[j][i];
            }
        }

        answer = Math.min(answer, bDP[0][n]);
        answer = Math.min(answer, bDP[1][n]);


        System.out.println(answer);
    }
}