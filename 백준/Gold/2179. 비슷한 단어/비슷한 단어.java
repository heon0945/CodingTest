import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static String[] words;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        words = new String[n];

        for(int i = 0; i < n; i++){
            words[i] = br.readLine();
        }

        int maxLen = 0;
        String a = "";
        String b = "";

        for(int i = 0; i < n; i++){
            String str = words[i];

            if(str.length() <= maxLen) continue;

            for(int j = i + 1; j < n; j++){
                String next = words[j];
                if(next.length() <= maxLen) continue;

                int length = prefix(words[i], words[j]);

                if(length > maxLen){
                    maxLen = length;
                    a = words[i];
                    b = words[j];

                }
            }
        }
        System.out.println(a);
        System.out.println(b);
    }

    static int prefix(String a, String b){
        int length = Math.min(a.length(), b.length());
        int idx = 0;
        for(int i = 0; i < length; i++){
            if(a.charAt(i) == b.charAt(i)) idx++;
            else break;
        }
        return idx;
    }
}
