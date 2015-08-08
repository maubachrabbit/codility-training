import java.io.File;
import java.util.Scanner;

public class Solution {
        public int solution(int[] A) {
            int num = A.length;
            int[] one = new int[num+1];
            int[] zero = new int[num+1];
            one[0] = zero[0] = 0;
            one[1] = A[0] == 1 ? 1 : 0;
            zero[1] = A[0] == 0 ? 1 : 0;
            for (int i=2; i<num+1; i++) {
                if (A[i-1] == 0) {
                    zero[i] = zero[i-1] + 1;
                    one[i] = one[i-1];
                } else { //A[i] == 1
                    zero[i] = zero[i-1];
                    one[i] = one[i-1] + 1;
                }
            }

            int max = 0;
            for (int i=1; i< num; i++) {
                if (A[i-1] == 0 && A[i] == 1) {
                    int maxPossible = num - (one[i] >= zero[i]? one[i] - zero[i] +1 : 0) - (zero[num] - zero[i] >= one[num] - one[i] ? zero[num] - zero[i] - one[num] + one[i] + 1 : 0);
                    if (max < maxPossible) {
                        int o = num;
                        int z = 0;
                        while ((o > i) && (one[o] - one[i] <= zero[o] - zero[i])) o--;
                        while (i > z && (zero[i] - zero[z] <= one[i] - one[z])) z++;
                        if (max < (o - z + 1)) max = o - z;
                    }
                }
            }

            return max;
        }

    //code is written and tested in Intellij, using local input test file
    //please ignore this part, it does not need to be part of the solution
    public static void main(String args[]) throws Exception{
        Scanner in = new Scanner(new File("input"));

        int num = in.nextInt();

        int[] array = new int[num];

        for (int i=0; i<num; i++) {
            array[i] = in.nextInt();
        }

        Solution s = new Solution();
        System.out.println(s.solution(array));
    }
}

