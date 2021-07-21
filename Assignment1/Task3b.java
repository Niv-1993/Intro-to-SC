/*
 * Task3b
 *
 * Authentic author: <Niv Dan>
 * I.D.: <311598031>
 * Last update: <08/11/2019>
 */

import java.util.Scanner;

public class Task3b {

    public static void main(String[] args) {
        //---------------write your code BELOW this line only!--------------
        Scanner myScanner = new Scanner(System.in);
        int n = myScanner.nextInt();
        int k = myScanner.nextInt();
        int two = 1;
        for (int i = 1; i <= n; i++) {
            two = (((two % k) * (2 % k)) % k); // (two*2)%k = number = ((two%k)(2%k)%k)
        }
        System.out.println(two);
        //---------------write your code ABOVE this line only!--------------
    }


}