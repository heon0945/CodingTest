import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static char[][] map;

    static int answer;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        answer = n*m;

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                map[i][j] = str.charAt(j);
            }
        }

        for(int i = 0; i < n-7; i++){
            for(int j = 0; j < m-7; j++){
                answer = Math.min(answer, search(i, j));
            }
        }

        System.out.println(answer);
    }

    public static int search(int sx, int sy){

        char[][] chess = new char[8][8];

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                chess[i][j] = map[sx + i][sy + j];
            }
        }

        //black
        int black = 0;
        if(chess[0][0] == 'W'){
            black++;
            chess[0][0] = 'B';
        }
        char pre = 'B';
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i == 0 && j == 0) continue;
                if(j == 0){
                    if(pre != chess[i][j]){
                        if(pre == 'B') chess[i][j] = 'B';
                        else chess[i][j] = 'W';
                        black++;
                    }
                }
                else{
                    if(pre == chess[i][j]){
                        if(pre == 'B') chess[i][j] = 'W';
                        else chess[i][j] = 'B';
                        black++;
                    }
                    pre = chess[i][j];
                }
            }
        }

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                chess[i][j] = map[sx + i][sy + j];
            }
        }

        //white
        int white = 0;
        if(chess[0][0] == 'B'){
            white++;
            chess[0][0] = 'W';
        }
        pre = 'W';
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i == 0 && j == 0) continue;
                if(j == 0){
                    if(pre != chess[i][j]){
                        if(pre == 'B') chess[i][j] = 'B';
                        else chess[i][j] = 'W';
                        white++;
                    }
                }
                else{
                    if(pre == chess[i][j]){
                        if(pre == 'B') chess[i][j] = 'W';
                        else chess[i][j] = 'B';
                        white++;
                    }
                }

                pre = chess[i][j];
            }
        }

        return Math.min(black, white);
    }
}