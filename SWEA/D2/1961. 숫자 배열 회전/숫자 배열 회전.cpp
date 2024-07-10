#include <iostream>
#include <vector>

using namespace std;

int main() {

	int tc;
	cin >> tc;

	for (int t = 0; t < tc; t++) {
		int n;
		cin >> n;


		int mat[7][7];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int tmp;
				cin >> tmp;
				mat[i][j] = tmp;
			}
		}
		
		

		cout << "#" << t + 1 << '\n';

		for (int i = 0; i < n; i++) {
			//rotation 90
			//col++ -> row--
			for (int j = 0; j < n; j++) {
				cout << mat[n - 1 - j][i];
			}
			cout << " ";

			//rotation 180
			//row-- -> col--
			for (int j = 0; j < n; j++) {
				cout << mat[n - 1 - i][n - 1 - j];
			}
			cout << " ";
			
			//rotation 270
			//col-- -> row++
			for (int j = 0; j < n; j++) {
				cout << mat[j][n - 1 - i];
			}
			cout << '\n';
		}
	}
	return 0;
}
