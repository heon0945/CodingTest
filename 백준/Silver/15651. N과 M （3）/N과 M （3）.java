import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int numbers[];

    static StringBuilder sb;


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        sb = new StringBuilder();

        n = sc.nextInt();
        m = sc.nextInt();

        numbers = new int[m];

        permutation(0);

        System.out.println(sb);

    }

    static void permutation(int cnt){

        if(cnt == m){
            for(int i = 0; i < m; i++){
                sb.append(numbers[i]).append(" ");
            }
            sb.append('\n');
            return;
        }

        for(int i = 1; i <= n; i++){
            numbers[cnt] = i;
            permutation(cnt + 1);
        }

    }

}