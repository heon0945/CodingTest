import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static StringBuilder sb;
    static int cnt;

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        Hanoi(N, 1, 2, 3);
        System.out.println(cnt);
        System.out.println(sb);
    }

    static void Hanoi(int n, int from, int mid, int to){

        if(n == 1){
            sb.append(from + " " + to + '\n');
            cnt++;
            return;
        }

        Hanoi(n-1, from, to, mid);

        sb.append(from + " " + to + '\n');
        cnt++;

        Hanoi(n-1, mid, from, to);
    }

}
