/*
 * Task4a
 *
 * Authentic author: <Niv Dan>
 * I.D.: <311598031>
 * Last update: <08/11/2019>
 */

import java.util.Scanner;

public class Task4a {

    public static void main(String[] args) {
        //---------------write your code BELOW this line only!--------------
        Scanner myScanner = new Scanner(System.in);
        int n = myScanner.nextInt();
        boolean prime = true;
        if (n > 1) {
            for (int i = 2; (i < n) && prime; i++) { // start from 2 because number/1 is = number
                if (n % i == 0) {
                    prime = false;
                }
            }
            if (prime) {
                System.out.println("prime");
            } else {
                System.out.println("composite");

            }

        }
        //---------------write your code ABOVE this line only!--------------


    }


}










    
