#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;


int main() {

	//벡터 -> 시간 초과
	//int n;
	//cin >> n;

	//vector<int> s;
	//vector<int> all;
	//for (int i = 1; i <= 20; i++) {
	//	all.push_back(i);
	//}

	//while (n--) {
	//	string str;
	//	cin >> str;
	//	int tmp = 0;
	//	if (str == "add") {
	//		cin >> tmp;
	//		if (find(s.begin(), s.end(), tmp) == s.end()) {
	//			s.push_back(tmp);
	//		}
	//	}
	//	else if (str == "remove") {
	//		cin >> tmp;
	//		if (find(s.begin(), s.end(), tmp) != s.end()) {
	//			s.erase(find(s.begin(), s.end(), tmp));
	//		}
	//	}
	//	else if (str == "check") {
	//		cin >> tmp;
	//		if (find(s.begin(), s.end(), tmp) == s.end())
	//			cout << 0 << '\n';
	//		else
	//			cout << 1 << '\n';
	//	}
	//	else if (str == "toggle") {
	//		cin >> tmp;
	//		if (find(s.begin(), s.end(), tmp) == s.end())
	//			s.push_back(tmp);
	//		else
	//			s.erase(find(s.begin(), s.end(), tmp));
	//	}
	//	else if (str == "all") {
	//		s = all;
	//	}
	//	else if (str == "empty") {
	//		s.clear();
	//	}


	//}


	//비트 마스킹
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	int n;
	cin >> n;

	unsigned int s = 0;
	int tmp = 0;
	while (n--) {
		string str;
			cin >> str;
			if (str == "add") {
				cin >> tmp;
				s |= (1 << tmp);
			}
			else if (str == "remove") {
				cin >> tmp;
				s &= ~(1 << tmp);
			}
			else if (str == "check") {
				cin >> tmp;
				if (s & (1 << tmp)) {
					cout << 1 << '\n';
				}
				else {
					cout << 0 << '\n';
				}
			}
			else if (str == "toggle") {
				cin >> tmp;
				s ^= (1 << tmp);
			}
			else if (str == "all") {
				s = (1 << 21) - 1;
			}
			else if (str == "empty") {
				s = 0;
			}
	}
}