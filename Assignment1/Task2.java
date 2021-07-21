/*
 * Task2
 *
 * Authentic author: <Niv Dan>
 * I.D.: <311598031>
 * Last update: <01/11/2019>
 */

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        //---------------write your code BELOW this line only!--------------
        int a = myScanner.nextInt();
        int b = myScanner.nextInt();

        if (a < b) {

            System.out.println((int) ((Math.random() * (b - a + 1))) + a);

            // " * (b-a +1)" =  multiply the range of the two numbers we chose
            // the int is to change the default double of Math.random to int - casting
            // "+ a" is for min because Math.random starts from 0 so we move it to the real min point
        }
        //---------------write your code ABOVE this line only!--------------

    }

}