class Solution {
    public long solution(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;

        long[] count = new long[2];
        count[0] = 1;
        count[1] = 2;

        for (int i = 3; i <= n; i++) {
            long temp = count[0];
            count[0] = count[1];
            count[1] = (temp + count[0])%1234567;
        }

        return count[1];
    }
}
