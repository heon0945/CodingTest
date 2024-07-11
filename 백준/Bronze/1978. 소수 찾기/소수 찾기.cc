#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

bool prime(int k) {
	for (int d = 2; d < k; d++) {
		if (k % d == 0)
			return false;
	}
	return true;
}

int main(){

	int n;
	cin >> n;

	vector<int> nums;
	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		nums.push_back(tmp);
	}

	int ans = 0;
	for (int i = 0; i < n; i++) {
		if (nums[i] != 1 && prime(nums[i]))
			ans++;
	}

	cout << ans;


	return 0;

}