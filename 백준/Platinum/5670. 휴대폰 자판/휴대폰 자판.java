import java.io.*;
import java.util.*;

public class Main {

    static int n;

    static class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }
    static class Trie{
        TrieNode root = new TrieNode();

        void insert(String s){
            TrieNode cur = this.root;

            for(char c : s.toCharArray()){
                int idx = c - 'a';

                if(cur.children[idx] == null)
                    cur.children[idx] = new TrieNode();

                cur = cur.children[idx];
            }

            cur.isEnd = true;
        }

        double check(String s){
            TrieNode cur = root;
            int click = 1;
            char[] tmp = s.toCharArray();
            for(int idx = 0; idx < tmp.length-1; idx++){
                cur = cur.children[tmp[idx] - 'a'];

                double total = 0;
                for(int i = 0; i < 26; i++){
                    if(cur.children[i] != null) total++;
                }

                if(total > 1 || cur.isEnd) click++;
            }

            return click;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){

            String tmp = br.readLine();
            if(tmp == null) break;

            n = Integer.parseInt(tmp);
            Trie trie = new Trie();

            String[] words = new String[n];

            for(int i = 0; i < n; i++){
                words[i] = br.readLine();
            }

            for(int i = 0; i < n; i++){
                trie.insert(words[i]);
            }

            double click = 0;

            for(int i = 0; i < n; i++){
                click += trie.check(words[i]);
            }

            sb.append(String.format("%.2f", click / words.length)).append('\n');
        }

        System.out.println(sb);

    }
}