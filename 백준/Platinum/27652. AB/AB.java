import java.io.*;
import java.util.*;

public class Main {

    static class TrieNode{
        TrieNode[] children = new TrieNode[26];
        int count = 0;
    }

    static class Trie{
        TrieNode root = new TrieNode();

        void insert(String s){

            char[] word = s.toCharArray();
            TrieNode cur = root;

            for(char c : word){
                int idx = c-'a';

                if(cur.children[idx] == null){
                    cur.children[idx] = new TrieNode();
                }

                cur = cur.children[idx];
                cur.count++;

            }
        }

        void insert_reverse(String s){

            char[] word = s.toCharArray();
            TrieNode cur = root;

            for(int i = word.length-1; i >= 0; i--){
                int idx = word[i] - 'a';

                if(cur.children[idx] == null){
                    cur.children[idx] = new TrieNode();
                }

                cur = cur.children[idx];
                cur.count++;

            }

        }
        void delete(String s){
            char[] word = s.toCharArray();
            TrieNode cur = root;

            for(char c : word){
                int idx = c - 'a';

                cur.children[idx].count--;
                cur = cur.children[idx];

            }
        }

        void delete_reverse(String s){
            char[] word = s.toCharArray();
            TrieNode cur = root;

            for(int i = word.length-1; i >= 0; i--){
                int idx = word[i]- 'a';

                cur.children[idx].count--;
                cur = cur.children[idx];

            }
        }

        int search(String s, int start, int end){
            char[] word = s.toCharArray();
            TrieNode cur = root;

            for(int i = start; i < end; i++){
                int idx = word[i]-'a';
                if(cur.children[idx] == null){
                    return 0;
                }
                cur = cur.children[idx];
            }

            return cur.count;
        }

        int search_reverse(String s, int start, int end){
            char[] word = s.toCharArray();
            TrieNode cur = root;

            for(int i = end-1; i >= start; i--){
                int idx = word[i]- 'a';
                if(cur.children[idx] == null){
                    return 0;
                }
                cur = cur.children[idx];
            }

            return cur.count;
        }



    }


    static Trie setA, setB;
    static int query;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        setA = new Trie();
        setB = new Trie();

        query = Integer.parseInt(br.readLine());


        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(query-- > 0){
            st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            if(cmd.equals("add")){
                char set = st.nextToken().charAt(0);
                String str = st.nextToken();

                if(set == 'A') setA.insert(str);
                else setB.insert_reverse(str);

            }
            else if(cmd.equals("delete")){
                char set = st.nextToken().charAt(0);
                String str = st.nextToken();

                if(set == 'A') setA.delete(str);
                else setB.delete_reverse(str);


            }
            else if(cmd.equals("find")){
                String str = st.nextToken();
                int len = str.length();
                int total = 0;

                for(int i = 1; i < len; i++){
                    total += (setA.search(str, 0, i) * setB.search_reverse(str, i, len));
                }

                sb.append(total).append('\n');
            }
        }

        System.out.println(sb);

    }
}