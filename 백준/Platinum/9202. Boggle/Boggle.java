import java.io.*;
import java.util.*;

// 단어 탐색
    // dfs : 16 x 8^8 -> 시간 초과 "백트래킹" 필요
    // dfs + trie : 사전 단어를 trie로 저장 -> dfs 진행하면서 접두어가 아닌 경우 가지치기
// 찾은 단어 저장
    // hashset : 중복없이 저장하기 위해서
    // 결과 출력을 위해 사전 순으로 정렬
    // 순회하면서 최대 길이 단어 찾기

// 1. trie 구성하여 사전 단어 저장하기
// 2. 보드판에서 단어 찾아 저장하기
// 3. 정답 출력하기

public class Main {

    static int w, b;
    static int[] dx = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static char[][] board;
    static Trie trie;
    static Set<String> words;
    static boolean[][] visited;

    public static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    public static class Trie{
        TrieNode root = new TrieNode();

        // 사전 단어로 trie 구성
        public void insert(String s){
            TrieNode cur = root;

            for(char c : s.toCharArray()){
                int idx = c - 'A';

                if(cur.children[idx] == null) cur.children[idx] = new TrieNode();
                cur = cur.children[idx];
            }
            cur.isEnd = true;
        }

        // 사전에 포함되지 않은 단어라면 0
        // 현재 단어가 사전에 존재할 가능성이 있다면(접두사라면) 1
        // 현재 단어가 사전에 존재하면 2
        public int check(String s){
            TrieNode cur = root;

            for(char c : s.toCharArray()){
                cur = cur.children[c - 'A'];
                if(cur == null) return 0;
            }
            if(cur.isEnd) return 2;
            else return 1;
        }
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //1. 사전 단어로 trie 구성
        w = Integer.parseInt(br.readLine());
        trie = new Trie();

        for(int i = 0; i < w; i++){
            trie.insert(br.readLine());
        }

        br.readLine();

        // 보드 개수만큼 순회
        b = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(b-- > 0){

            // 보드 구성
            board = new char[4][4];
            for(int i = 0; i < 4; i++){
                String tmp = br.readLine();
                for(int j= 0; j < 4; j++){
                    board[i][j] = tmp.charAt(j);
                }
            }
            if(b > 0) br.readLine();

            // 2. 단어 탐색
            words = new HashSet<>();
            visited = new boolean[4][4];

            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    visited[i][j] = true;
                    dfs(i, j, ""+board[i][j]);
                    visited[i][j] = false;
                }
            }

            // 3. 정답 출력
            List<String> list = new ArrayList<>(words);
            Collections.sort(list);
            int total = 0;
            int maxLen = 0;
            String maxStr = "";
            for(int i = 0; i < list.size(); i++){
                String cur = list.get(i);

                total += getScore(cur);

                if(maxLen < cur.length()){
                    maxLen = cur.length();
                    maxStr = cur;
                }
            }

            sb.append(total).append(" ");
            sb.append(maxStr).append(" ");
            sb.append(list.size()).append('\n');

        }


        System.out.println(sb);


    }

    static int getScore(String s){
        int len = s.length();

        if(len <= 2) return 0;
        if(len <= 4) return 1;
        if(len <= 5) return 2;
        if(len <= 6) return 3;
        if(len <= 7) return 5;
        return 11;
    }

    // 단어의 마지막 위치, 현재까지 완성된 단어
    static void dfs(int x, int y, String cur){
        int result = trie.check(cur);

        // 사전에 포함된 단어가 될 수 없는 경우
        if(result == 0) return;
        // 사전에 포함된 단어인 경우
        if(result == 2){
            words.add(cur);
        }

        //더이상 단어를 만들 수 없는 최대 길이인 경우
        if(cur.length() >= 8) return;

        // 계속해서 단어 이어나가기
        for(int i = 0; i < 9; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
            if(visited[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, cur + board[nx][ny]);
            visited[nx][ny] = false;
        }
    }
}