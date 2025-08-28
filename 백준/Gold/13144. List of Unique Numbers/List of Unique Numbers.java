import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] position;
    static int[] arr;
    static long total;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        position = new int[100001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(position, -1);

        long cnt = 0;
        int pivot = -1;
        for(int i = 0; i < n; i++){
            int number = arr[i];
            if(position[number] != -1 && position[number] > pivot){
                cnt = i - position[number];
                pivot = position[number];
            }else {
                cnt++;
            }
            total += cnt;
            position[number] = i;
        }
        System.out.println(total);
    }
}
