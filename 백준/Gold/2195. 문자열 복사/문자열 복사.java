import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String P = br.readLine();

        int answer = 0;
        int sp = 0; // P에서 현재 위치

        while (sp < P.length()) {
            int maxLen = 0;

            for (int i = 0; i < S.length(); i++) {
                if (S.charAt(i) == P.charAt(sp)) {
                    int len = 0;
                    while (i + len < S.length() && sp + len < P.length()) {
                        if (S.charAt(i + len) != P.charAt(sp + len)) break;
                        len++;
                    }
                    maxLen = Math.max(maxLen, len);
                }
            }

            sp += maxLen;
            answer++;
        }

        System.out.println(answer);
    }
}