import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int map[][];


    static StringBuilder sb;

    public static class Pair{
        int x, y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        //맵 만들기
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < N; j++){
                map[i][j] = str.charAt(j)-'0';
            }
        }

        sb = new StringBuilder();


        qtree(new Pair(0, 0), new Pair(N, N));

        System.out.println(sb);
    }


    public static void qtree(Pair start, Pair end){

        int pivot = map[start.x][start.y];
        boolean valid = true;

        //현재 종료조건(범위가 1인경우)
        if(end.x-start.x == 1){
            sb.append(pivot);
            return;
        }


        //현재 영상이 하나로 표현 가능한지
        for(int i = start.x; i < end.x; i++){
            for(int j = start.y; j < end.y; j++){
                if(map[i][j] != pivot) {
                    valid = false;
                    break;
                }
            }
            if(!valid) break;
        }

        //하나의 영상으로 표현 가능
        if(valid){
            sb.append(pivot);
            return;
        }

        //하나의 영상으로 표현 불가능
        int range = (end.x-start.x) / 2;
        sb.append('(');
        qtree(new Pair(start.x, start.y), new Pair(start.x + range, start.y + range));
        qtree(new Pair(start.x, start.y + range), new Pair(start.x + range, end.y));
        qtree(new Pair(start.x + range, start.y), new Pair(end.x, start.y + range));
        qtree(new Pair(start.x + range, start.y + range), new Pair(end.x, end.y));
        sb.append(')');

    }
}