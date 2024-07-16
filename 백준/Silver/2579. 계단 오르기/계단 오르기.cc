#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int score[301];
int step[301]; //연속된 개수, 합산된 점수

int main() {
	//dp
	//연속 세번 안 됨
	//두 칸 전에서 이동 : i = sum[i-2] + s[i]
	//이전 칸에서 이동 ( i-2칸에서 이동했을 것) : sum[i - 3] + s[i - 1] + s[i]

	int n;
	cin >> n;


	int tmp = 0;
	for (int i = 0; i < n; i++) {
		cin >> tmp;
		score[i + 1] = tmp;
	}

	//step 초기화
	step[0] = 0;
	step[1] = score[1];
	step[2] = score[2] + score[1];

	
	for (int i = 3; i <= n; i++) {
		step[i] = max(step[i - 2] + score[i], step[i - 3] + score[i - 1] + score[i]);
	}

	cout << step[n];


	return 0;
}