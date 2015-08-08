class Solution {
    public int solution(int[] array) {
        int num = array.length;
        int[] maxToPosition = new int[num];
        int max = maxToPosition[0] = array[0];
        for (int i=1; i<num; i++) {
            maxToPosition[i] = Math.max(maxToPosition[i-1] + array[i], array[i]);
            if (max < maxToPosition[i]) {
                max = maxToPosition[i];
            }
        }

        return max;
    }
}
