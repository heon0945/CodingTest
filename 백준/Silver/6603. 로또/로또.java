import java.io.*;
import java.util.*;

public class Main {

    static int k;
    static int numbers[];
    static boolean visited[];
    static StringBuilder sb;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        while(true){
            k = sc.nextInt();

            if(k == 0) break;

            numbers = new int[k];
            visited = new boolean[k];

            for(int i = 0; i < k; i++){
                numbers[i] = sc.nextInt();
            }

            combi(0, 0);

            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void combi(int cur, int cnt){

        if(cnt == 6){
            for(int i = 0; i < k; i++){
                if(visited[i]){
                    sb.append(numbers[i]).append(" ");
                }
            }
            sb.append('\n');
            return;
        }


        for(int i = cur; i < k; i++){
            if(visited[i]) continue;

            visited[i] = true;
            combi(i, cnt + 1);
            visited[i] = false;
        }


    }

}
