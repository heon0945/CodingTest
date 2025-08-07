import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] cnt;
    static char[] permu;
    static Set<String> words;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        while(n-- > 0){
            char[] word = br.readLine().toCharArray();

            words = new HashSet<>();
            cnt = new int[26];
            permu = new char[word.length];

            for(char c : word){
                cnt[c- 'a']++;
            }

            permutation(word, 0);

            List<String> list = new ArrayList<>(words);
            Collections.sort(list);
            for(String str : list) sb.append(str).append('\n');
        }

        System.out.println(sb);

    }


    static void permutation(char[] word, int cur){
        if (cur == word.length){
            words.add(new String(permu));
            return;
        }

        for(int i = 0; i < 26; i++){
            if(cnt[i] <= 0) continue;

            cnt[i]--;
            permu[cur] = (char)('a' + i);
            permutation(word, cur + 1);
            cnt[i]++;
        }
    }
}