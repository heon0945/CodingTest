import java.io.*;
import java.util.*;

public class Main {

    static String str;
    static String target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();
        target = br.readLine();

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < str.length(); i++){
            stack.push(str.charAt(i));

            if(stack.size() >= target.length()){
                boolean valid = true;

                for(int j = 0; j < target.length(); j++){
                    if(stack.get(stack.size()-target.length() + j) != target.charAt(j)){
                        valid = false;
                        break;
                    }
                }

                if(valid){
                    for(int j = 0; j < target.length(); j++) stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        sb.reverse();
        if(sb.toString().equals("")) sb.append("FRULA");
        System.out.println(sb);
    }
}