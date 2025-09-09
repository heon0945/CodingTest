import java.io.*;
import java.util.*;

public class Main {

    static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();

        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(br.readLine());

            if(left.isEmpty() || num <= left.peek()) left.add(num);
            else right.add(num);

            if(left.size() > right.size() + 1) right.add(left.poll());
            else if(right.size() > left.size()) left.add(right.poll());

            sb.append(left.peek()).append('\n');
        }

        System.out.println(sb);

    }
}
