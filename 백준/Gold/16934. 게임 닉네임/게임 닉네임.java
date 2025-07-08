import java.io.*;
import java.util.*;

public class Main {

    static int n;

    //각 닉네임 별로 카운트 (닉네임 뒤에 숫자 표시)
    static Map<String, Integer> names;

    //Trie 알고리즘에 사용될 트리 구조
    static class TrieNode {
        //닉네임의 각 글자를 키로, 이후 다음 글자를 값으로 -> 닉네임을 트리 형식으로 표현
        Map<Character, TrieNode> children = new HashMap<>();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        names = new HashMap<>();
        TrieNode root = new TrieNode();


        //닉네임 별로 작업 수행
        for(int i = 0; i < n; i++){
            String name = br.readLine();

            //현재까지 나온 닉네임 중에 이미 있다면 그 값을, 없다면 0을 반환 -> 현재까지의 닉네임 개수 업데이트
            int cnt = names.getOrDefault(name, 0) + 1;
            names.put(name, cnt);

            //현재까지의 트리 구조 루트
            TrieNode cur = root;
            //만들어낼 별칭
            String alias = null;

            //닉네임의 캐릭터 따라가면서 트리 진행
            for(int k = 0; k < name.length(); k++){
                //현재 문자
                char c = name.charAt(k);

                //처음 나오는 글자라면,
                if(!cur.children.containsKey(c)){
                    if (alias == null) { //현재 별칭이 정의되지 않은 경우
                        //지금까지 진행된 글자를 모두 별칭으로
                        alias = name.substring(0, k + 1);
                    }
                    //새로운 자식 트리 추가 (별칭은 결정되었지만 이후 글자들에 영향이 있으므로 끝까지 트리 형성)
                    cur.children.put(c, new TrieNode());
                }

                //다음 문자 트리로 계속 진행
                cur = cur.children.get(c);
            }

            //모든 문자 경로가 이미 존재한다면 별칭에 숫자 추가
            if(alias == null){
                if(cnt == 1) alias = name;
                else alias = name + cnt;
            }

            //별칭 결정
            sb.append(alias).append('\n');
        }
        System.out.print(sb);
    }

}
