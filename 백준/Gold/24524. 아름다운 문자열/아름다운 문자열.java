import java.io.*;
import java.util.*;

public class Main {

    static String t;
    static String s;
    static int[] candidate;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        t = br.readLine();
        candidate = new int[t.length()];

        for(int i = 0; i < s.length(); i++){

            char sc = s.charAt(i);

            for(int j = t.length()-1; j >= 0; j--){

                char st = t.charAt(j);

                if(sc == st){

                    if(j == 0) {
                        candidate[0]++;
                        break;
                    }
                    else if (candidate[j-1] > 0){
                        candidate[j]++;
                        candidate[j-1]--;
                        break;
                    }

                }

            }

        }

        System.out.println(candidate[t.length()-1]);

    }
}