public class SortAlgos {
    public static final boolean print = false;

    public static void main(String args[]) {
        final int N = 1000;
        final int LIMIT = 4*N;
        int[] array = new int[N];
        for (int i=0; i<array.length; i++) {
            array[i] = (int) (Math.random() * LIMIT);
            //array[i] = N - i;
        }
        printArray(array);
        long t1, t2;

        /*t1 = System.currentTimeMillis();
        BubbleSort(copyArray(array));
        t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

        t1 = System.currentTimeMillis();
        SelectionSort(copyArray(array));
        t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);*/

        t1 = System.currentTimeMillis();
        InsertionSort(copyArray(array));
        t2 = System.currentTimeMillis();
        System.out.println("InsertionSort " + (t2 - t1));

        t1 = System.currentTimeMillis();
        MergeSort(copyArray(array));
        t2 = System.currentTimeMillis();
        System.out.println("Merge Sort " + (t2 - t1));

        t1 = System.currentTimeMillis();
        QuickSort(copyArray(array));
        t2 = System.currentTimeMillis();
        System.out.println("Quick Sort " + (t2 - t1));
    }

    public static void QuickSort(int[] array) {
        quickSort(array, 0, array.length);
        printArray(array);
    }

    public static void quickSort(int[] array, int start, int end) {
        if (end - start <= 1) {
            return;
        }
        if (end - start == 2) {
            if (array[start] > array[start+1]) {
                int temp = array[start];
                array[start] = array[start+1];
                array[start+1] = temp;
            }
            return;
        }

        int pivot = (int) (Math.random() * (end - start)) + start;
        int temp = array[pivot];
        array[pivot] = array[end - 1];
        array[end - 1] = temp;
        int index = start;
        for (int i=start; i<end - 1; i++) {
            if (array[i] < array[end - 1]) {
                if (i != index) {
                    temp = array[i];
                    array[i] = array[index];
                    array[index++] = temp;
                } else {
                    index++;
                }
            }
        }
        temp = array[index];
        array[index] = array[end - 1];
        array[end - 1] = temp;

        quickSort(array, start, index);
        quickSort(array, index + 1, end);
    }

    public static void printArray(int[] array) {
        if (print) {
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
    }

    public static int[] copyArray(int[] array) {
        int[] copy = new int[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }

    /*public static void BubbleSort(int[] array) {
        int temp;
        for (int i=0; i<array.length - 1; i++) {
            for (int j = 0; j<array.length - i - 1; j++) {
                if (array[j] > array[j+1]) {
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        printArray(array);
    }

    public static void SelectionSort(int[] array) {
        int temp;
        for (int i=0; i<array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j<array.length; j++) {
                //select the min element from the remaining part of array
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (min != i) {
                temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
        printArray(array);
    }*/

    public static void InsertionSort(int[] array) {
        int temp;
        for (int i=1; i<array.length; i++) {
            for (int j = 0; j<i; j++) {
                if (array[j] > array[i]) {
                    temp = array[i];
                    System.arraycopy(array, j, array, j + 1, i - j);
                    array[j] = temp;
                    break;
                }
            }
        }
        printArray(array);
    }

    public static void MergeSort(int[] array) {
        mergeShort(array, 0, array.length);
        printArray(array);
    }

    public static void mergeShort(int[] array, int start, int end) {
        if (end - start <= 1) {
            return;
        }
        if (end - start == 2) {
            if (array[start] > array[start+1]) {
                int temp = array[start];
                array[start] = array[start+1];
                array[start+1] = temp;
            }
            return;
        }

        int mid = (end + start)/2;
        mergeShort(array, start, mid);
        mergeShort(array, mid, end);
        int[] temp = new int[end - start];
        merge(array, start, mid, array, mid, end, temp, 0);
        System.arraycopy(temp, 0, array, start, end - start);
    }

    public static void merge(int[] a, int starta, int enda, int[] b, int startb, int endb, int[] c, int startc) {
        while (starta < enda && startb < endb) {
            if (a[starta] < b[startb]) {
                c[startc++] = a[starta++];
            } else {
                c[startc++] = b[startb++];
            }
        }

        while (starta< enda) {
            c[startc++] = a[starta++];
        }

        while (startb< endb) {
            c[startc++] = b[startb++];
        }
    }
 }
