import java.io.*;
import java.util.*;

public class Main{

    static String s;
    static String t;

    static int sa, sb, ta, tb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        t = br.readLine();

        dfs(s, t);

        System.out.println(0);
    }

    static void dfs(String s, String t){
        if (s.length() == t.length()) {
            if (s.equals(t)) {
                System.out.println(1);
                System.exit(0);
            }

            return;
        }

        if (t.charAt(t.length() - 1) == 'A') {
            dfs(s, t.substring(0, t.length() - 1));
        }
        
        if (t.charAt(0) == 'B') {
            String substring = t.substring(1);
            StringBuilder sb = new StringBuilder(substring);
            String string = sb.reverse().toString();
            dfs(s, string);
        }

    }
}
