#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <deque>

using namespace std;


deque<int> read(string str) {
	deque<int> input;
	string s = "";
	for (char c : str) {
		if (isdigit(c)) {
			s += c;
		}
		else {
			if (!s.empty()) {
				input.push_back(stoi(s));
				s = "";
			}
		}
	}

	return input;
}


int main() {

	int tc;
	string op;
	int n;


	cin >> tc;

	while (tc--) {

		cin >> op;

		cin >> n;

		string str;
		cin >> str;

		deque<int> input = read(str);

		bool flag = false;
		bool order = true;

		for (char c : op) {
			if (c == 'R') {
				if (order)
					order = false;
				else
					order = true;
			}
			else if (c == 'D') {
				if (input.empty()) {
					flag = true;
					break;
				}
				if (order)
					input.pop_front();
				else
					input.pop_back();
			}
		}

		if (flag) {
			cout << "error" << '\n';
		}
		else {
			cout << '[';
			if (!input.empty()) {
                if (!order)
				    reverse(input.begin(), input.end());
				for (int i = 0; i < input.size() - 1; i++) {
					cout << input[i] << ",";
				}
				cout << input[input.size() - 1];
			}
			cout << "]" << '\n';
		}

	}

	return 0;
}