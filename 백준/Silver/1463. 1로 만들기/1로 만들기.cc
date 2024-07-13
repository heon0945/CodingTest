#include <iostream>

using namespace std;

int main() {

	int n;
	cin >> n;

	//dp
	//1.	3으로 나누어 떨어지면 /3	-> *3
	//2.	2로 나누어 떨어지면 /2		-> *2
	//3.	-1							-> +1

	int dp[1000000];

	dp[1] = 0;
	for (int i = 2; i <= n; i++) {
		//1.
		dp[i] = dp[i - 1] + 1;
		//2.
		if (i % 2 == 0)
			dp[i] = min(dp[i], dp[i / 2] + 1);
		//3.
		if (i % 3 == 0)
			dp[i] = min(dp[i], dp[i / 3] + 1);
	}

	cout << dp[n];

	return 0;
}