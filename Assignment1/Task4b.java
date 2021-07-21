/*
 * Task4b
 *
 * Authentic author: <Niv Dan>
 * I.D.: <311598031>
 * Last update: <08/11/2019>
 */

import java.util.Scanner;

public class Task4b {

    public static void main(String[] args) {
        //---------------write your code BELOW this line only!--------------
        Scanner myScanner = new Scanner(System.in);
        int n = myScanner.nextInt();
        int counter = 0;
        if (n <= 1) {
            System.out.println(counter);
        }
        if (n > 1) {
            for (int i = 2; i <= n; i++) {
                boolean isPrime = true;
                for (int j = 2; j < i && isPrime; j++) { //inner loop starts from 2 every time a new i is entered to see if its true or false
                    if (i % j == 0) {
                        isPrime = false;
                    }
                }
                if (isPrime) {
                    counter = counter + 1; // every time it is true the counter adds 1
                }
            }
            System.out.println(counter);
            //---------------write your code ABOVE this line only!--------------
        }
    }

}



