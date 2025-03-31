// Dá uma boa ideia sobre a estratégia de percorrer um vetor pela direita e pela esquerda ao mesmo tempo

// Link do problema: https://leetcode.com/problems/container-with-most-water/

class Solution {
public:
    int maxArea(vector<int>& height) {
        unsigned long long L = 0;
        unsigned long long R = height.size()-1;

        long long max_container = 0;
        while (R > L){

            unsigned long long container = min(height[L], height[R]) * (R - L);
            if(container > max_container) max_container = container;
            

            if(height[L] > height[R]) R--;
            else                      L++;
        }
        return max_container;
    }
};