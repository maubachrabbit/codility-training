import java.io.File;
import java.util.Scanner;

class SumSubsequence {
    public int solution(int[] arrayA) {
        int num = arrayA.length;

        if (num == 1) {
            return Math.abs(arrayA[0]);
        }

        long[] sumToIndex = new long[num];
        sumToIndex[0] = arrayA[0];
        for (int i=1; i<num; i++) {
            sumToIndex[i] = sumToIndex[i-1] + arrayA[i];
        }

        long[] bestEndInIndex = new long[num];
        for (int i=0; i<num; i++) {
            long value = sumToIndex[i];
            long currentMin = value;
            for (int j=0; j<i; j++) {
                value -= arrayA[j];
                if (Math.abs(value) < Math.abs(currentMin)) {
                    currentMin = value;
                }
            }
            bestEndInIndex[i] = currentMin;

        }

        long[] bestToIndex = new long[num];
        bestToIndex[0] = arrayA[0];
        for (int i=1; i<num; i++) {
            long currentBest = arrayA[i];
            if (Math.abs(bestEndInIndex[i]) < Math.abs(currentBest)) {
                currentBest = bestEndInIndex[i];
            }
            if (Math.abs(bestToIndex[i-1]) < Math.abs(currentBest)) {
                currentBest = bestToIndex[i-1];
            }

            bestToIndex[i] = currentBest;
            //System.out.println("bestToIndex i =" + i + " " + bestEndInIndex[i]);
        }

        long best = bestToIndex[0];
        for (int i=1; i<num; i++) {
            if (Math.abs(best) > Math.abs(bestToIndex[i])) {
                best = bestToIndex[i];
            }
        }

        return Math.abs((int) best);
    }

    public static void main(String args[]) throws Exception{
        Scanner in = new Scanner(new File("input"));

        SumSubsequence solve = new SumSubsequence();

        int[] A = new int[100000];

        while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());

            int numA = 0;

            while (line.hasNextInt()) {
                A[numA++] = line.nextInt();
            }

            int[] arrayA = new int[numA];

            System.arraycopy(A, 0, arrayA, 0, numA);

            System.out.println(solve.solution(arrayA));
        }
    }
}

