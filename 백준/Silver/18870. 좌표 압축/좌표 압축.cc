#include <iostream>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;
	cin >> n;

	map<int, int> m;
	vector<int> order;
	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		order.push_back(tmp);
		m.insert({ tmp, n + 1 });
	}

	int i = 0;
	for (auto iter = m.begin(); iter != m.end(); iter++)
	{
		iter->second = i++;
	}

	for (int i : order) {
		cout << m[i] << " ";
	}

	
	return 0;
}