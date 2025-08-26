import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int s;

    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int total = 0;

        while(start <= n && end <= n){
            if (total >= s) {
                min = Math.min(min, end - start);
                total -= arr[start++];
            } else {
                total += arr[end++];
            }
        }

        if(min == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(min);
    }
}
