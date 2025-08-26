import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        stack = new Stack<>();

        int answer = 0;

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            while(!stack.isEmpty() && stack.peek() > y){
                stack.pop();
                answer++;
            }

            if(!stack.isEmpty() && stack.peek() == y)
            {
                continue;
            }


            stack.push(y);
        }

        while(!stack.isEmpty()){

            if(stack.peek() > 0) answer++;
            stack.pop();
        }

        System.out.println(answer);
    }
}
