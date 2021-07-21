/*
 * Task4e
 *
 * Authentic author: <Niv Dan>
 * I.D.: <311598031>
 * Last update: <11/11/2019>
 */

import java.util.Scanner;

public class Task4e {

    public static void main(String[] args) {
        //---------------write your code BELOW this line only!--------------
        Scanner myScanner = new Scanner(System.in);
        int n = myScanner.nextInt();
        int b = myScanner.nextInt();
        int s = myScanner.nextInt();
        int d = myScanner.nextInt();
        // building = (newBd == 1) phrase
        int newBd = 1;//in order to multiply each time by b and not each loop by b=b*b that grows every loop
        boolean flag1 = true;
        for (int j = 1; j <= d; j++) {
            newBd = ((newBd % n) * (b % n) % n); // using it for big numbers like in Task3b
            //newBd = ((B^d) % n)
            if (newBd == 1) {
                flag1 = false;
            }
        }
        int i = 0;// index of s^i
        int twoI = 1; //min 2^0 for i=0
        int base = 2;
        int twoIzero = 1;
        boolean flag2 = true;
        while (i <= (s - 1) && flag2) {
            if (i == 0) { // 2^0 = 1 the only case it enters the statement
                twoI = twoIzero;
            } else { // all other index options go here i>=1
                twoI = twoI * base;
            }
            i = i + 1;
            int bTwoId = 1;
            for (int j = 1; j <= twoI && flag2; j++) {
                bTwoId = (((bTwoId % n) * (newBd)) % n); // use (a*b%n) like in task3b
                // newBd = b^d % n --> no need to "% n" again
                if (bTwoId == (n - 1)) {
                    flag2 = false;
                }
            }
        }
        if (flag1 && flag2) {
            System.out.println(b + " is a witness. " + n + " is composite.");
        } else {
            System.out.println("We assume " + n + " is prime.");
        }


//---------------write your code ABOVE this line only!--------------

    }
}




