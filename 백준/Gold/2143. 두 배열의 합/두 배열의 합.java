import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static int n, m;
    static int[] arr1;
    static int[] arr2;
    static List<Integer> accum1;
    static List<Integer> accum2;
    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        arr1 = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        arr2 = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        //누적합 계산
        accum1 = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int a = 0;
            for(int j = i; j < n; j++){
                //i ~ j까지 배열의 합 -> A
                a += arr1[j];
                accum1.add(a);
            }
        }

        accum2 = new ArrayList<>();
        for(int i = 0; i < m; i++){
            int b = 0;
            for(int j = i; j < m; j++){
                //i ~ j까지 배열의 합 -> A
                b += arr2[j];
                accum2.add(b);
            }
        }

        //리스트 정렬
        Collections.sort(accum1);
        Collections.sort(accum2);

        //배열의 값을 하나를 결정
        for(int a : accum1){
            solution(a);
        }

        System.out.println(answer);
    }

    public static void solution(int a){
        int target = t - a;
        //accum2에는 t-a에 해당하는 값이 여러 개 존재 -> 일반 이분탐색으로는 하나 밖에 찾지 못함
        //따라서 lower_bound, upper_bound를 구해서, 답에 해당하는 값을 가지는 범위를 구하는 것

        int lower = lower_bound(target);
        int upper = upper_bound(target);

        //upper 바운드의 경우 조건을 만족하는 값의 다음 인덱스를 반환
        answer += (long)(upper - lower);
    }

    public static int lower_bound(int target){
        int left = 0;
        int right = accum2.size(); //마지막까지 탐색해야 하므로 offset 1

        //지금은 정확한 값을 찾는 경우가 아니라, 범위를 찾는 경우
        //left < right, right = mid -> mid가 답이 될 수도 있는 가능성 고려
        while(left < right){
            int mid = (left + right) / 2;

            if(accum2.get(mid) < target) left = mid + 1;
            else right = mid;
        }

        return left;
    }

    public static int upper_bound(int target){
        int left = 0;
        int right = accum2.size(); //마지막까지 탐색해야 하므로 offset 1

        while(left < right){
            int mid = (left + right) / 2;

            if(accum2.get(mid) <= target) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}