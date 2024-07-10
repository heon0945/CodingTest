#include <iostream>
#include <vector>
#include <algorithm>
 
using namespace std;
 
int main() {
 
    int tc;
    cin >> tc;
 
    vector<int> answer;
    while (tc) {
        int n, b;
        cin >> n >> b;
 
        //키 입력
        vector<int> h;
        for (int i = 0; i < n; i++) {
            int hi;
            cin >> hi;
            h.push_back(hi);
        }
 
        //조합 계산
        vector<int> s;
        //k개의 수를 조합하는 경우
        for (int k = 1; k <= n; k++) {
 
            vector<int> cb;
             
            //조합에서 제외될 수
            for (int i = 0; i < n - k; i++) {
                cb.push_back(0);
            }
            //조합에 소속될 수
            for (int i = 0; i < k; i++) {
                cb.push_back(1);
            }
 
            //permutation 계산
            do {
                int sum = 0;
                // 출력
                for (int i = 0; i < cb.size(); i++) {
                    if (cb[i] == 1) {
                        sum += h[i];
                    }
                }
 
                s.push_back(sum);
 
            } while (next_permutation(cb.begin(), cb.end()));
 
        }
     
 
        //조합 중 차이 최솟값 계산
        vector<int> d;
        for (int i = 0; i < s.size(); i++) {
            if (s[i] >= b)
                d.push_back(s[i] - b);
        }
 
        //정렬 후 최솟값 출력
        sort(d.begin(), d.end());
         
        answer.push_back(d[0]);
        
        tc--;
    }
 
    for (int i = 0; i < answer.size(); i++)
        cout << "#" << i + 1 << " " << answer[i] << '\n';
 
    return 0;
}