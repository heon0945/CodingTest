#include <iostream>
#include <vector>

using namespace std;

int main() {

	int tc;
	cin >> tc;
	
	vector<int> answer;

	while (tc) {


		int n, m;
		cin >> n >> m;


		vector<int> a, b;
		for (int i = 0; i < n; i++) {
			int tp;
			cin >> tp;
			a.push_back(tp);
		}
		for (int i = 0; i < m; i++) {
			int tp;
			cin >> tp;
			b.push_back(tp);
		}

		int ans = -1000000;
		if (n > m) {
			for (int i = 0; i <= n - m; i++) {
				int sum = 0;
				for (int j = i; j < i + m; j++) {
					sum += a[j] * b[j - i];
				}
				ans = max(ans, sum);
			}
			answer.push_back(ans);

		}
		else {
			for (int i = 0; i <= m - n; i++) {
				int sum = 0;
				for (int j = i; j < i + n; j++) {
					sum += b[j] * a[j - i];
				}
				ans = max(ans, sum);
			}
			answer.push_back(ans);
		}
		tc--;
	}

	for (int i = 0; i < answer.size(); i++) {
		cout << "#" << i + 1 << " " << answer[i] << '\n';
	}


	return 0;
}