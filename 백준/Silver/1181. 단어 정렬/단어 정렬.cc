#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int main() {

	int n;
	cin >> n;

	vector<string> words;

	for (int i = 0; i < n; i++) {
		string str;
		cin >> str;

		words.push_back(str);
	}

	//중복 제거
	sort(words.begin(), words.end());
	words.erase(unique(words.begin(), words.end()), words.end());

	for (int i = 1; i <= 51; i++) {
		for (int j = 0; j < words.size(); j++) {
			if (words[j].size() == i)
				cout << words[j] << '\n';
		}
	}

	return 0;
}