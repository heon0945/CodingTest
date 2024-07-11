#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

bool checking(string str) {
	for (int i = 0; i < str.size() / 2; i++) {
		if (str[i] != str[str.size() - i - 1])
			return false;
	}
	return true;
}

int main() {

	while (true) {
		int num;
		cin >> num;

		if (num == 0)
			break;

		string answer;

		string str = to_string(num);

		if (checking(str))
			cout << "yes" << '\n';
		else
			cout << "no" << '\n';
	}

	return 0;

}