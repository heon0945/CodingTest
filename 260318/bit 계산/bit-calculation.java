import java.io.*;
import java.util.*;

public class Main {

    static int q;
    static int s;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        q = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(q-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            if(cmd.equals("add")){
                int x = Integer.parseInt(st.nextToken());
                s |= (1 << x);
            }
            else if(cmd.equals("delete")){
                int x = Integer.parseInt(st.nextToken());
                s &= ~(1 << x);
            }
            else if(cmd.equals("print")){
                int x = Integer.parseInt(st.nextToken());
                if((s & (1 << x)) != 0) sb.append(1);
                else sb.append(0);
                sb.append('\n');
            }
            else if(cmd.equals("toggle")){
                int x = Integer.parseInt(st.nextToken());
                s ^= (1 << x);
            }
            else{
                s = 0;
            }
        }
        System.out.println(sb);
    }
}
