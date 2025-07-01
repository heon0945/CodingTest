import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int map[][];

    static int white = 0;
    static int blue = 0;

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);


        N = sc.nextInt();

        map = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] = sc.nextInt();
            }
        }

        cutting(0, 0, N, N);

        System.out.println(white);
        System.out.println(blue);
    }

    public static void cutting(int sx, int sy, int ex, int ey){
        int pivot = map[sx][sy];
        boolean valid = true;

        if(ex - sx == 1){
            if(map[sx][sy] == 0) white++;
            else blue++;
            return;
        }

        for(int i = sx; i < ex; i++){
            for(int j = sy; j < ey; j++){
                if(map[i][j] != pivot){
                    valid = false;
                    break;
                }
            }
            if(!valid) break;
        }

        if(valid){
            if(pivot == 0) white++;
            else blue++;
            return;
        }

        int range = (ex - sx) / 2;
        cutting(sx, sy, sx + range, sy + range);
        cutting(sx, sy + range, sx + range, ey);
        cutting(sx + range, sy, ex, sy + range);
        cutting(sx + range, sy + range, ex, ey);
    }
}
