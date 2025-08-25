import java.io.*;
import java.util.*;

// 과제 n개와 낮잠 순서 배정
// 과제 n개는 마감기한이 빠른 순서대로 진행
// 낮잠을 자지 않거나 낮잠을 각 과제의 앞 순서에 진행
// 이때 해당 과제를 수행해도 마감기한을 넘지 않는 경우만 수행하기

public class Main {

    static int n, a, b;
    static int[] tasks;

    static int ans;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        tasks = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            tasks[i] = Integer.parseInt(st.nextToken());
        }

        // 마감시간이 빠른 순서대로 정렬
        Arrays.sort(tasks);

        // 낮잠을 자지 않는 경우
        int time = 0;
        for(int i = 0; i < n; i++){
            time += a;
            if(time <= tasks[i]) ans++;
        }

        // 각 과제 진행 전 낮잠을 자는 경우
        for(int i = 0; i < n; i++){
            calculateTasks(i);
        }

        System.out.println(ans);
    }

    public static void calculateTasks(int order){

        for(int nap = 0; nap <= a-1; nap++) {
            int time = 0;
            int completed = 0;

            // 낮잠 전 과제
            for(int i = 0; i < order; i++) {
                if(time + a <= tasks[i]){
                    time += a;
                    completed++;
                }
            }

            // 낮잠 시간
            time += nap * b;

            // 낮잠 후 과제
            for(int i = order; i < n; i++) {
                if(time + (a-nap) <= tasks[i]){
                    time += (a - nap);
                    completed++;
                }
            }

            ans = Math.max(ans, completed);
        }

    }
}