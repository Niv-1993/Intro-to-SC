/*
 * Task4d
 *
 * Authentic author: <Niv Dan>
 * I.D.: <311598031>
 * Last update: <08/11/2019>
 */

import java.util.Scanner;

public class Task4d {

    public static void main(String[] args) {
        //---------------write your code BELOW this line only!--------------
        Scanner myScanner = new Scanner(System.in);
        int n = myScanner.nextInt();
        int s = 0; // min value
        int d = 1; // min value
        n = n - 1; // n-1 = 2^s * d
        boolean flag = true;
        while (flag) {
            if (n % 2 == 0) {
                s = s + 1;
                n = n / 2;
            } else {
                flag = false;
            }
        }
        d = n; // what ever is left
        System.out.println(s);
        System.out.println(d);


        //---------------write your code ABOVE this line only!--------------
    }


}