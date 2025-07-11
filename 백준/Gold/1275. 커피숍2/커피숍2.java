import java.io.*;
import java.util.*;

public class Main {

    static int n, q;
    static long[] arr; //숫자 입력 배열
    static long[] tree; //세그먼트 트리 배열
    static StringBuilder sb;


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        n = sc.nextInt();
        q = sc.nextInt();

        arr = new long[n+1];
        tree = new long[4 * n]; // 세그먼트 트리의 크기 4 * n

        for(int i = 1; i <= n; i++){
            arr[i] = sc.nextLong();
        }

        init(1, n, 1);

        for(int i = 0; i < q; i++){
            long x = sc.nextLong();
            long y = sc.nextLong();
            int a = sc.nextInt();
            long b = sc.nextLong();

            int left = (int) Math.min(x, y);
            int right = (int)Math.max(x, y);

            sb.append(query(1, n, 1, left, right)).append('\n');

            update(1, n, 1, a, b-arr[a]);
            arr[a] = b;
        }

        System.out.println(sb);



    }

    //세그먼트 트리 초기화
    //start : 현재 구간 시작, end : 현재 구간 끝, node : 현재 노드 번호
    static long init(int start, int end, int node){
        if(start == end){
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2  + 1);
    }

    //a번째 수를 diff만큼 증가
    //start : 현재 구간 시작, end : 현재 구간 끝, node : 현재 노드 번호, idx : 갱신할 인덱스, diff : 변경될 값
    static void update(int start, int end, int node, int idx, long diff){
        if(idx < start || idx > end) return; //idx가 현재 구간에 속하지 않으면 리턴


        tree[node] += diff; //현재 노드 값 갱신

        if(start == end) return; //말단 노드면 종료

        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, diff);
        update(mid + 1, end, node * 2 + 1, idx, diff);
    }

    //구간 합치기
    //start : 현재 구간 시작, end : 혅 구간 끝, node : 현재 노드 번호, left : 질문 구간 시작, right : 질문 구간 끝
    static long query(int start, int end, int node, int left, int right){
        if(right < start || end < left){
            return 0;
        }

        if(left <= start && end <= right){
            return tree[node];
        }

        int mid = (start + end) / 2;
        return query(start, mid, node * 2, left, right)
                + query(mid + 1, end, node * 2 + 1, left, right);
    }
}