class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        int[] gain = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;
        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                gain[i] += freq[j];
            }
            gain[i]--;
        }

        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));
        int ans = 0;
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            if (price >= 2L * minPrice || price > budget) {
                break;
            }

            int count = Math.min(gain[factor], budget / price);
            ans += count * 2;
            budget -= count * price;
        }

        ans += budget / minPrice;
        return ans;
    }
}