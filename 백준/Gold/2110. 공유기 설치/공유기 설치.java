import java.io.*;
import java.util.*;

public class Main {
    static int n, c;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int max = arr[n-1] - arr[0];
        int min = 1;

        int answer = 0;
        while(min <= max){
            int mid = (min + max) / 2;

            if(install(mid) < c){
                max = mid - 1;
            }

            else{
                answer = Math.max(answer, mid);
                min = mid + 1;
            }
        }

        System.out.println(answer);
    }

    public static int install(int dist){
        int cnt = 1;
        int cur = arr[0];

        for(int i = 1; i < arr.length; i++){

            if(arr[i] - cur >= dist){
                cnt++;
                cur = arr[i];
            }
        }

        return cnt;
    }
}
