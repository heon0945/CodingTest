import java.util.*;

public class Main {
	//요리의 개수만큼의 부분 집합 구하기
	//해당 부분 집합에 포함된 요리가 적어도 하나인지 확인
	//조건에 부합하는 경우 해당 요리의 신맛과 쓴맛을 계산
	//그 차와 현재 저장된 차이를 비교하여 더 작은 차를 저장
	//부분집합만큼 계산 후 저장된 차를 출력
	
	static int n;
	static int[] sour;
	static int[] bitter;
	static boolean[] select;
	static int mn = 1000000000;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		n = scanner.nextInt();
		sour = new int[n];
		bitter = new int[n];
		select = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			sour[i] = scanner.nextInt();
			bitter[i] = scanner.nextInt();
		}
		
		subset(0);
		
		System.out.print(mn);
		
		
	}
	
	static void subset(int order) {
		if(order == n) {
			int totsour = 1, totbitter = 0;
			for(int i = 0; i < n; i++) {
				if(select[i] == true) {
					totsour *= sour[i];
					totbitter += bitter[i];
				}
			}
			if(totbitter != 0 && totsour != 0) {
				mn = Math.min(mn, Math.abs(totbitter - totsour));
			}
			return;
		}
		select[order] = false;
		subset(order + 1);
		select[order] = true;
		subset(order + 1);
	}
}