import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Building[] buildings;

    static class Building{
        int idx;
        int h;
        int cnt = 0;
        int near = -1;
        Building(int idx, int h){
            this.idx = idx;
            this.h = h;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        buildings = new Building[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            buildings[i] = new Building(i, Integer.parseInt(st.nextToken()));
        }

        //왼쪽
        Stack<Building> stack = new Stack<>();
        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && stack.peek().h <= buildings[i].h){
                stack.pop();
            }

            buildings[i].cnt = stack.size();
            if(stack.size() > 0)
                buildings[i].near = stack.peek().idx;
            stack.push(buildings[i]);
        }

        //오른쪽
        stack = new Stack<>();
        for(int i = n-1; i >= 0; i--){
            while(!stack.isEmpty() && stack.peek().h <= buildings[i].h){
                stack.pop();
            }
            buildings[i].cnt += stack.size();
            if(stack.size() > 0){
                if(buildings[i].near == -1 ||
                        stack.peek().idx - i < i - buildings[i].near){
                    buildings[i].near = stack.peek().idx;
                }
            }
            stack.push(buildings[i]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            sb.append(buildings[i].cnt);
            if(buildings[i].cnt > 0){
                sb.append(" ").append(buildings[i].near + 1);
            }
            sb.append('\n');
        }

        System.out.println(sb);


    }
}
