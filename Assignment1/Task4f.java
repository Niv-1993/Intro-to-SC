/*
 * Task4f
 *
 * Authentic author: <Niv Dan>
 * I.D.: <311598031>
 * Last update: <11/11/2019>
 */

import java.util.Scanner;

public class Task4f {

    public static void main(String[] args) {
        //---------------write your code BELOW this line only!--------------
        Scanner myScanner = new Scanner(System.in);
        int n = myScanner.nextInt();
        int s = myScanner.nextInt();
        int d = myScanner.nextInt();
        int k = myScanner.nextInt();
        int t = 2;
        boolean flag3 = true;
        while ((t <= k) && flag3) {
            int b = (int) (Math.random() * (n - 2) + 2);
            int newBd = 1; // SAME ALGORITHM OF TASK4E WITH AN ADDITION OF BOOLEAN FLAG3 AND K*LOOPS
            boolean flag1 = true;
            for (int j = 1; j <= d; j++) {
                newBd = ((newBd % n) * (b % n) % n);
                if (newBd == 1) {
                    flag1 = false;
                }
            }
            int i = 0; // index of s^i
            int twoI = 1;
            int base = 2;
            int twoIzero = 1;
            boolean flag2 = true;
            while (i <= (s - 1) && flag2) {
                if (i == 0) {
                    twoI = twoIzero;
                } else {
                    twoI = twoI * base;
                }
                i = i + 1;
                int bTwoId = 1;
                for (int j = 1; j <= twoI && flag2; j++) {
                    bTwoId = (((bTwoId % n) * (newBd)) % n);
                    if (bTwoId == (n - 1)) {
                        flag2 = false;
                    }
                }
            }
            if (flag1 && flag2) {
                System.out.println(b + " is a witness. " + n + " is composite.");
                flag3 = false;
            }
            t = t + 1;
        }
        if (flag3){
            System.out.println("We assume " + n + " is prime.");
        }
        //---------------write your code ABOVE this line only!--------------
    }


}

    
