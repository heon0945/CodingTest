#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(pair<int, int> a, pair<int, int> b) {
	if (a.second == b.second) {
		return a.first < b.first;
	}
	return a.second < b.second;
}

int main() {

	int n;
	cin >> n;
	
	vector<pair<int, int>> meet;

	while (n--) {
		int s, d;
		cin >> s >> d;
		meet.push_back({ s, d });
	}

	sort(meet.begin(), meet.end(), compare);

	int time = 0;
	int cnt = 0;
	for (int i = 0; i < meet.size(); i++) {
		
		if (meet[i].first >= time) {
			cnt++;
			time = meet[i].second;
		}

	}

	cout << cnt;


	
	return 0;
}