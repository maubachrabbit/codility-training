class Solution {
    public int solution(int[] array) {
        if (array == null || array.length==0) {
            return 0;
        }

        int start = 0;
        int globalMax = 0;
        while (start < array.length) {
            int indexMax = start; int max = array[start];
            for (int i=start + 1; i< array.length; i++) {
                if (max <= array[i]) {
                    max = array[i];
                    indexMax = i;
                }
            }

            int min = array[start];
            for (int i=start + 1; i < indexMax; i++) {
                if (min >= array[i]) {
                    min = array[i];
                }
            }

            if (max - min > globalMax) {
                globalMax = max - min;
            }
            start = indexMax + 1;
        }

        return globalMax > 0 ? globalMax : 0;
    }
}
