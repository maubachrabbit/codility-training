import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution {

    public int solution(int[] A, int[] B, int[] C) {
        final int num = A.length;

        if (num == 0) return 0;

        Weight[] all = new Weight[num];
        for (int i=0; i< num; i++) {
            Weight c = new Weight(B[i], A[i], C[i] == -1 ? null : all[C[i]]);
            if (c.sumW > c.maxW) {
                return i;
            }
            if (c.parent != null) {
                if (!c.parent.addChild(c)) return i;
            }
            all[i] = c;
        }
        return num;
    }



    //code is written and tested in Intellij, using local input test file
    //please ignore this part, it does not need to be part of the solution
    public static void main(String args[]) throws Exception{
        Scanner in = new Scanner(new File("input"));

        int num = in.nextInt();

        int[] A = new int[num];
        int[] B = new int[num];
        int[] C = new int[num];

        for (int i=0; i<num; i++) {
            A[i] = in.nextInt();
        }

        for (int i=0; i<num; i++) {
            B[i] = in.nextInt();
        }

        for (int i=0; i<num; i++) {
            C[i] = in.nextInt();
        }

        Solution s = new Solution();
        System.out.println(s.solution(A, B, C));
    }
}

class Weight {
    int selfW;
    long sumW;
    int maxW;
    Weight parent;
    private LinkedList<Weight> children;

    public Weight(int selfW, int maxW, Weight parent) {
        this.selfW = selfW;
        this.maxW = maxW;
        this.parent = parent;
        sumW = this.selfW;
        children = new LinkedList<Weight>();
    }

    public boolean addChild(Weight c) {
        children.add(c);
        c.parent = this;
        Weight current = this;
        while (current!=null) {
            current.sumW += c.sumW;
            if (current.sumW > current.maxW) {
                return false;
            }
            current = current.parent;
        }
        return true;
    }
}
