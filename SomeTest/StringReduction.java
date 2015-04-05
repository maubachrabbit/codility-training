/**
 * AB or BA -> C, AC or CA -> B, BC or CB -> A
 * find the minLength after transformations
 *
 */

import java.io.File;
import java.util.Scanner;

public class StringReduction {
    public int solution(String s) {
        while (s.length() > 1) {
                if (allSame(s)) {
                    return s.length();
                }
                char max = getMaxCount(s);
                for (int i = 0; i < s.length() - 1; i++) {
                    char v1 = s.charAt(i);
                    char v2 = s.charAt(i + 1);
                    if (v1 != v2 && (v1 == max || v2 == max)) {
                        s = (s.substring(0, i) + replace(v1, v2) + (i == s.length() - 2 ? "" : s.substring(i+2)));
                        break;
                    }
                }
            }

        return s.length();
    }

    static char getMaxCount(String s) {
        int a = 0, b = 0, c = 0;
        for (char t : s.toCharArray()) {
            switch (t) {
                case 'a' : a++; break;
                case 'b' : b++; break;
                case 'c' : c++; break;
            }
        }
        if (a > b) {
            if (a > c) return 'a'; else return 'c';
        } else {
            if (b > c) return 'b'; else return 'c';
        }
    }

    static char replace(char v1, char v2) {
        if (v1 == 'a' && v2 == 'b' || v1 == 'b' && v2 == 'a') {
            return 'c';
        }

        if (v1 == 'a' && v2 == 'c' || v1 == 'c' && v2 == 'a') {
            return 'b';
        }

        return 'a';
    }

    static boolean allSame(String s) {
        for (int i=0; i<s.length() -1; i++) {
            if (s.charAt(i) != s.charAt(i+1)) {
                return false;
            }
        }

        return true;
    }

    //code is written and tested in Intellij, using personal input test file
    //please ignore this part, it does not need to be part of the solution
    public static void main(String args[]) throws Exception{
        Scanner in = new Scanner(new File("input2"));

        StringReduction stringReduction = new StringReduction();

        while (in.hasNextLine()) {
            String line = in.nextLine();

            System.out.println(stringReduction.solution(line));
        }
    }
}

/*
import java.util.Scanner;

public class StringReduction {
    public int solution(String s) {
        int a=0, b=0, c=0;

        for (char t : s.toCharArray()) {
            switch (t) {
                case 'a' : a++; break;
                case 'b' : b++; break;
                case 'c' : c++; break;
            }
        }

        while (s.length() > 1) {

                //string with all chars are same: number of 2 other chars == 0
                if ((a == 0 && b == 0) || (a == 0 && c == 0) || (b == 0 && c == 0)) {
                    return s.length();
                }

                char maxChar;

                if (a > b) {
                    if (a > c) {
                        maxChar = 'a';
                    } else {
                        maxChar = 'c';
                    }
                } else {
                    if (b > c) {
                        maxChar = 'b';
                    } else {
                        maxChar = 'c';
                    }
                }

                for (int i = 0; i < s.length() - 1; i++) {
                    char v1 = s.charAt(i);
                    char v2 = s.charAt(i + 1);

                    if (v1 != v2 && (v1 == maxChar || v2 == maxChar)) {
                        char replaceTo;
                        if ((v1 == 'a' && v2 =='b') || (v1 == 'b' && v2 == 'a')) {
                            c++; a--; b--; replaceTo = 'c';
                        } else if ((v1 == 'a' && v2 =='c') || (v1 == 'c' && v2 == 'a')) {
                            b++; a--; c--; replaceTo = 'b';
                        } else {
                            a++; b--; c--; replaceTo = 'a';
                        }
                        s = s.substring(0, i) + replaceTo + (i == s.length() - 2 ? "" : s.substring(i+2));
                        break;
                    }
                }
            }

        return s.length();
    }

    //code is written and tested in Intellij, using personal input test file
    //please ignore this part, it does not need to be part of the solution
    public static void main(String args[]) throws Exception{
        Scanner in = new Scanner(System.in);

        StringReduction stringReduction = new StringReduction();

        int numCases = Integer.parseInt(in.nextLine());

        for (int i=0; i< numCases; i++) {
            String line = in.nextLine();

            System.out.println(stringReduction.solution(line));
        }
    }
}


 */

