/*
I, <Niv Dan> (<311598031>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.math.BigInteger;
import java.util.Random;

class Part1 {

    public static BigInteger sumSmaller(BigInteger n) {
        BigInteger sum = new BigInteger("0");
        //Task 1.1
        BigInteger index = BigInteger.ONE;
        BigInteger addOne = BigInteger.ONE;
        while (index.compareTo(n) < 0) {
            sum = sum.add(index);
            index = index.add(addOne);
        }
        return sum;
    }

    public static void printRandoms(int n) {
        //Task 1.2
        Random input = new Random(n);
        for (int i = 0; i < n; i = i + 1) {
            int output = input.nextInt();
            System.out.println(output);
        }
    }

    public static boolean isPrime(BigInteger n) {
        //Task 1.3
        boolean ans = true;
        BigInteger divisor = new BigInteger("2");
        if (n.compareTo(BigInteger.ONE) <= 0)
            ans = false;
        else {
            while ((((divisor.multiply(divisor).compareTo(n)) <= 0) & ans)){
                if ((n.remainder(divisor)).equals(BigInteger.ZERO)) {
                    ans = false;
                }
                divisor = divisor.add(BigInteger.ONE);
            }
        }
        return ans;
    }

    public static BigInteger randomPrime(int n) {
        //Task 1.4
        BigInteger output = new BigInteger("0");
        boolean isRandomPrime = false;
        while (!(isRandomPrime)) {
            BigInteger randBig = new BigInteger(n, new Random()); //method random number in range 0 to (2^n - 1), inclusive.
            if (isPrime(randBig) & (randBig.compareTo(BigInteger.ONE) > 0)) {
                isRandomPrime = true;
                output = randBig;
            }
        }
        return output;
    }

    public static void main(String[] args) {

//        test for part 1.1
//        BigInteger biMinus = new BigInteger("-10");
//        System.out.println("sumSmaller test expected 0 - got " + sumSmaller(biMinus));
//
//        BigInteger bi0 = new BigInteger("0");
//        System.out.println("sumSmaller test expected 0 - got " + sumSmaller(bi0));

//        BigInteger bi7 = new BigInteger("7");
//        System.out.println("sumSmaller test expected 21 - got " + sumSmaller(bi7));
//
//        BigInteger biHigh = new BigInteger("99999");
//        System.out.println("sumSmaller test expected 4999850001 - got " + sumSmaller(biHigh) + "\n");
//
//        test for part 1.2
//        System.out.println("printRandoms 5");
//        printRandoms(5);
//        System.out.println("");
//
//        test for part 1.3
//        BigInteger biVeryHigh = new BigInteger("0");
//        System.out.println("isPrime test expected false got " + isPrime(biVeryHigh));
//         biVeryHigh = new BigInteger("2147521921");
//         System.out.println(("isPrime test expected false got " +  isPrime(biVeryHigh))+"\n");

        //test for part 1.4
//        System.out.println("randomPrime test");
//        System.out.println(randomPrime(20));
//        System.out.println(randomPrime(40));
    }
}