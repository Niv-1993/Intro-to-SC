/*
 * Task1
 *
 * Authentic author: <Niv Dan>
 * I.D.: <311598031>
 * Last update: <01/11/2019>
 */

import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int a = myScanner.nextInt();
        //---------------write your code BELOW this line only!--------------
        int b = myScanner.nextInt();
        int q = myScanner.nextInt();
        int r = myScanner.nextInt();

        if ((r < b) && (b != 0)) {

            if ((a % b == r) && (a / b == q)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }

        } else {
            System.out.println("no");

        }
        //---------------write your code ABOVE this line only!--------------


    }


}