#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

int n;
stack<pair<int, int>> st; //idx, height



int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	for (int i = 0; i < n; i++) {
		int height;
		cin >>  height;

		while (true) {
			if (st.empty()) {
				cout << 0 << " ";
				st.push({ i + 1, height });
				break;
			}

			else {
				if (st.top().second < height) {
					st.pop();
				}
				else {
					if (st.top().second == height) {
						cout << st.top().first << " ";
						st.pop();
						st.push({ i + 1, height });
					}
					else if (st.top().second > height) {
						cout << st.top().first << " ";
						st.push({ i + 1, height });
					}
					break;
				}
			}

		}

		
	}





	return 0;
}