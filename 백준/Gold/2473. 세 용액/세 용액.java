import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long[] arr;
    static long[] pick;
    static long answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new long[n];
        pick = new long[3];
        answer = 3000000000L;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) arr[i] = Long.parseLong(st.nextToken());

        Arrays.sort(arr);

        for(int i = 0; i < n; i++){
            solution(i); //세 용액 중 하나 용액 지정 arr[i];
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 3; i++) sb.append(pick[i]).append(" ");

        System.out.println(sb);
    }

    static void solution(int i){

        //투포인터로 절댓값을 0에 가까이 만드는 두 용액 선택
        int left = i + 1;
        int right = n - 1;

        while(left < right){
            long sum = arr[i] + arr[left] + arr[right];
            long absSum = Math.abs(sum);

            //절댓값이 0에 더 가까우면 answer 업데이트
            if(absSum < answer){
                pick[0] = arr[i];
                pick[1] = arr[left];
                pick[2] = arr[right];
                answer = absSum;
            }

            if(sum > 0) right--;
            else left++;
        }
    }
}
