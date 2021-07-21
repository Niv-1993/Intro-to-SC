/*
 * Task3a
 *
 * Authentic author: <Niv Dan>
 * I.D.: <311598031>
 * Last update: <01/11/2019>
 */

import java.util.Scanner;

public class Task3a {

    public static void main(String[] args) {
        //---------------write your code BELOW this line only!--------------
        Scanner myScanner = new Scanner(System.in);
        int n = myScanner.nextInt();
        int two = 1; //the minimum result that can be is 1 (2^0)
        for (int i = 1; i <= n; i++) {
            two = two * 2;
        }
        System.out.println(two);
        //---------------write your code ABOVE this line only!--------------

    }


}