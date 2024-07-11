#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

int main() {

	int n;
	cin >> n;
	
	vector<int> scores;
	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		scores.push_back(tmp);
	}

	sort(scores.begin(), scores.end());

	double m = scores[scores.size() - 1];

	double sum = 0;
	for (int i = 0; i < n; i++) {
		double tmp = scores[i] / m * 100;
		sum += tmp;
	}
	
	cout << sum / n;


	return 0;
}