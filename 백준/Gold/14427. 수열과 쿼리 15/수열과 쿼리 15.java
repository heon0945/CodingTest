import java.io.*;
import java.util.*;

public class Main {

    // 세그먼트 트리를 구성할 노드 (배열의 인덱스와 값)
    static class Node{
        int idx;
        int value;
        Node(int idx, int value){
            this.idx = idx;
            this.value = value;
        }
    }

    // 세그먼트 트리
    static class SegmentTree{
        Node[] tree; // 세그먼트 트리
        int n; // 배열 전체 길이

        SegmentTree(int[] arr, int n){

            // 세그먼트 트리의 최대 크기 4N (이진 검색 트리)
            this.n = n;
            tree = new Node[4 * n];

            //세그먼트 트리 구성
            build(arr, 1, 0, n - 1);
        }

        // 만들고자 하는 세그먼트 트리 형태 : 최솟값
        // 배열의 두 값을 비교해서 작은 노드가 부모가 되도록 트리 위치 구성
        public Node better(Node a, Node b){

            // 값이 작은 노드 반환
            if(a.value != b.value)
                return a.value < b.value ? a : b;

            // 값이 같은 경우 인덱스 값이 작은 노드 반환
            return a.idx < b.idx ? a : b;
        }

        // 배열의 구간을 분할하면서 세그먼트 트리를 구성
        // 자식 먼저 구성하고, 부모를 구성하는 방식
        // 부모는 왼쪽과 오른쪽 자식 중 더 작은 값을 가지는 노드
        public void build(int[] arr, int node, int left, int right){

            // 구간이 하나로 최종 분할되면, 노드 생성 (리프 노드)
            if(left == right){
                tree[node] = new Node(left, arr[left]);
            }
            else{
                // 왼쪽 트리와 오른쪽 트리를 각각 구성
                int mid = (left + right) / 2;
                build(arr, node * 2, left, mid);
                build(arr, node * 2 + 1, mid + 1, right);

                // 왼쪽 자식 트리와 오른쪽 자식 트리가 구성되면, 양 쪽을 비교하여 더 작은 노드를 부모로 선택
                tree[node] = better(tree[node * 2], tree[node * 2 + 1]);
            }
        }

        // 세그먼트 트리의 특정 값 갱신 후 트리 재구성
        public void update(int node, int left, int right, int idx, int value){

            // 가장 리프 노드까지 내려갔다면 업데이트 할 노드의 값을 갱신
            if(left == right){
                tree[node] = new Node(idx, value);
            }
            else{
                // 양 쪽 자식을 타고 리프 노드까지 내려가기
                int mid = (left + right) / 2;
                if(idx <= mid)
                    update(node * 2, left, mid, idx, value);
                else
                    update(node * 2 + 1, mid + 1, right, idx, value);

                // 값 갱신한 후 차례대로 다시 트리 재구성
                tree[node] = better(tree[node * 2], tree[node * 2 + 1]);
            }
        }
    }

    static int n, m; // 배열의 길이, 쿼리 수
    static int[] arr; // 주어진 배열

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        SegmentTree tree = new SegmentTree(arr, n);

        m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int i = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken());
                tree.update(1, 0, n - 1, i, v);
            } else {  // type == 2
                Node res = tree.tree[1];
                sb.append(res.idx + 1).append('\n');
            }
        }

        System.out.println(sb);
    }
}
