import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class DiscIntersect {
    public int solution(int[] A) {
        int num = A.length;

        if (num <=1) return 0;

        StartEnd[] minArray = new StartEnd[num];

        for (int i = 0; i< num; i++) {
            minArray[i] = new StartEnd(i - (long) A[i], i + (long) A[i]);
        }

        Arrays.sort(minArray, new StartComparator());

        long[] minArrayLong = new long[num];

        for (int i=0; i< num; i++) {
            minArrayLong[i] = minArray[i].start;
        }

        long result = 0;

        for (int i=0; i< num - 1; i++) {
            if (result > 10000000) return -1;

            long currentMax = minArray[i].end;
            int indexMax = Arrays.binarySearch(minArrayLong, currentMax);


            if (indexMax >= 0) {
                //move to the last possible index, as there might be duplications, and binarySearch cannot resolve to the last possible index
                while (indexMax < num - 1) {
                    if (minArrayLong[indexMax] == minArrayLong[indexMax + 1]) {
                        indexMax++;
                    } else break;
                }
                indexMax++; //indexMax is the number of left elements, including this position
            } else { //binary search result in (-index - 1)
                indexMax = -indexMax - 1; //number of left elements
            }

            result += indexMax - i - 1; //-1: remove self; -i : remove duplicated
        }

        return (int) result;
    }

    public static class StartEnd {
        public Long start;
        public Long end;
        public StartEnd(long s, long e) {
            start = s; end = e;
        }
    }

    public static class StartComparator implements Comparator<StartEnd> {
        @Override
        public int compare(StartEnd o1, StartEnd o2) {
            long result = o1.start - o2.start;
            return result == 0 ? 0 : result > 0 ? 1 : -1;
        }
    }

    public static void main(String args[]) throws Exception{
        Scanner in = new Scanner(new File("input"));

        DiscIntersect solve = new DiscIntersect();

        int[] A = new int[1000000];

        while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());

            int numElements = 0;



            while (line.hasNextInt()) {
                A[numElements++] = line.nextInt();
            }


            int[] array = new int[numElements];

            System.arraycopy(A, 0, array, 0, numElements);

            System.out.println(solve.solution(array));
        }
    }
}

