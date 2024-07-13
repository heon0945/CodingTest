#include <iostream>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	//binary_search
	/*string name;
	vector<string> hear, answer;
	int n, m;

	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		cin >> name;
		hear.push_back(name);
	}

	sort(hear.begin(), hear.end());

	for (int i = 0; i < m; i++) {
		cin >> name;
		if (binary_search(hear.begin(), hear.end(), name) == true) {
			answer.push_back(name);
		}
	}

	sort(answer.begin(), answer.end());

	cout << answer.size() << "\n";

	for (auto a : answer) {
		cout << a << "\n";
	}*/

	//maps
	int n, m;
	map<string, int> map; //name, count
	vector<string> answer;
	string name;

	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		cin >> name;
		map.insert({ name, 0 });
	}

	for (int i = 0; i < m; i++) {
		cin >> name;
		if (map.find(name) != map.end())
			answer.push_back(name);
	}

	sort(answer.begin(), answer.end());

	cout << answer.size() << "\n";

	for (auto a : answer) {
		cout << a << "\n";
	}


	return 0;
}