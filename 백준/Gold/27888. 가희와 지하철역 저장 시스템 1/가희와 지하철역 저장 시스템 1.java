import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 저장해야할 정보 : 역 이름, 역 별 특징 정보 (역 이름 중복 x, 순서 중요하지 않음)
// 역의 특징을 조회하고 업데이트 하는 과정의 속도 빨라야 함
// 역과 특징 매핑은 해시, 특징 정보는 비트마스킹으로 관리
// 역 특징 , 기준 파싱 필요 .split(",")

public class Main {

    static int n, r;
    static Map<String, Integer> stationMask; // 역 이름, 특징(비트마스킹) 매핑
    static Map<String, Integer> featureIndex; // 각 특징 별 인덱스 값 (특징 비트값 내에서 위치)
    static Map<Integer, Integer> maskCnt; // 각 특징 조합을 가진 역의 개수 카운트

    static String[] stations;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        stationMask = new HashMap<>();
        featureIndex = new HashMap<>();
        maskCnt = new HashMap<>();

        // 역 이름 저장
        n = Integer.parseInt(br.readLine());
        stations = new String[n];
        for(int i = 0; i < n; i++){
            stations[i] = br.readLine();
        }

        // 역과 특징 매핑하는 해시맵 초기화 (현재 특징 0개)
        for (String st : stations) stationMask.put(st, 0);


        r = Integer.parseInt(br.readLine());
        int nextBit = 0;
        maskCnt.put(0, 0);

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            if(cmd.equals("U")){

                // 명령어가 가리키는 역
                String cur = st.nextToken();
                // 업데이트할 특징 정보 (파싱하여 저장)
                String[] features = st.nextToken().split(",");


                // 해당 역의 이전 특징 정보(비트값)
                int oldMask = stationMask.get(cur);
                // 이전 특징 정보를 가진 개수 업데이트 (-1)
                maskCnt.put(oldMask, maskCnt.get(oldMask) - 1);

                // 새로운 특징 정보 가져오기
                int newMask = 0;
                for(String f : features){

                    // 해당 특징이 새로운 특징이라면, 몇번째 특징인지 기록 (해당 값이 비트값 내 위치)
                    if(!featureIndex.containsKey(f)){
                        featureIndex.put(f, nextBit++);
                    }

                    // 해당 특징의 비트 위치를 1로 변경 (해당 특징을 포함한다는 뜻)
                    int bit = featureIndex.get(f);
                    newMask |= (1 << bit);
                }

                // 새로운 역 정보 비트값에 대해 저장 (역과 특징 매핑, 특징 별 역 개수 업데이트)
                stationMask.put(cur, newMask);
                maskCnt.put(newMask, maskCnt.getOrDefault(newMask, 0) + 1);
            }
            else{
                String[] features = st.nextToken().split(",");

                // 찾아야 하는 특징의 조합 비트
                int queryMask = 0;
                // 주어진 특징을 가진 역의 개수
                int total = 0;
                // 찾아야 하는 특징 정보가 올바른지 판단
                boolean valid = true;

                // 주어진 특징에 따라 조합
                for(String f : features){
                    Integer idx = featureIndex.get(f);

                    // 존재하지 않는 특징인 경우
                    if(idx == null){
                        valid = false;
                        break;
                    }
                    queryMask |= (1 << idx);
                }

                if(!valid){
                    sb.append(0).append('\n'); continue;
                }
                for(Map.Entry<Integer, Integer> e : maskCnt.entrySet()){
                    // 우리가 찾는 특징을 가지고 있는 경우 파악
                    if((e.getKey() & queryMask) == queryMask){
                        total += e.getValue();
                    }
                }

                sb.append(total).append('\n');
            }
        }

        System.out.println(sb);


    }
}