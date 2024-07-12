#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {

	vector<int> nums;
	int n;

	cin >> n;

	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		nums.push_back(tmp);
	}

	sort(nums.begin(), nums.end());
	nums.erase(unique(nums.begin(), nums.end()), nums.end());

	for (int i = 0; i < nums.size(); i++) {
		cout << nums[i] << '\n';
	}
}