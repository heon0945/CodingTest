import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static Node[] arr;

    static int depth;

    public static class Node{
        int left, right;
        Node(int left, int right){
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new Node[n+1];

        StringTokenizer st;
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            arr[idx] = new Node(left, right);
        }

        traversal(1, 0);

        System.out.println(2 * (n-1) - depth);

    }

    public static void traversal(int cur, int d){
        Node node = arr[cur];

        if(node.right == -1){
            // 리프 노드
            depth = Math.max(depth, d);
            return;
        }

        traversal(node.right, d+1);
    }




}