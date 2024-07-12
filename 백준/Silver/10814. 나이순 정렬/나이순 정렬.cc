#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;


bool compare(pair<int, string> a, pair<int, string> b) {

		return a.first < b.first;


}

int main() {

	int n;
	cin >> n;

	vector<pair<int, string>> pp;
	for (int i = 0; i < n; i++) {
		int num;
		string name;
		cin >> num >> name;
		pp.push_back({ num, name });

	}

	stable_sort(pp.begin(), pp.end(), compare);

	for (int i = 0; i < pp.size(); i++) {
		cout << pp[i].first << " " << pp[i].second << '\n';
	}

	return 0;
}