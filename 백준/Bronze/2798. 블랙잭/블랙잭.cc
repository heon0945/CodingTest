#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

int main() {

	int n, m;
	cin >> n >> m;


	vector<int> nums;
	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		nums.push_back(tmp);
	}

	vector<int> s;
	vector<int> cb;

	//조합에서 제외될 수
	for (int i = 0; i < n-3; i++) {
		cb.push_back(0);
	}
	//조합에 소속될 수
	for (int i = 0; i < 3; i++) {
		cb.push_back(1);
	}

	//permutation 계산
	do {
		int sum = 0;
		// 출력
		for (int i = 0; i < cb.size(); i++) {
			if (cb[i] == 1) {
				sum += nums[i];
			}
		}


		if (sum <= m) {
			s.push_back(sum);
		}

	} while (next_permutation(cb.begin(), cb.end()));


	sort(s.begin(), s.end());

	cout << s[s.size() - 1] << " ";


	return 0;

}