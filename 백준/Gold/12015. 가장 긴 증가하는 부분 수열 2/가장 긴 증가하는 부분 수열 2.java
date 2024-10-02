import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n =Integer.parseInt(br.readLine());
        int[] map = new int[n];
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<n;i++) {
        	map[i] = Integer.parseInt(st.nextToken());
        }
        list.add(map[0]); // 초기값 세팅(최악의 경우에도 1은 보장됨)
        
        for(int i=1;i<n;i++) {
        	if(map[i] > list.get(list.size()-1)) { // 최장길이 갱신이 가능한경우
        		list.add(map[i]); //그냥 추가
        	}else { //아닌경우
        		int idx = Collections.binarySearch(list, map[i]);
        		if(idx < 0) idx = -(idx+1);
        		list.set(idx, map[i]);
        	}
        }
        System.out.println(list.size());
    }
}