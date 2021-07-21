import javax.swing.plaf.RootPaneUI;
import javax.xml.stream.FactoryConfigurationError;
import java.sql.SQLOutput;

/*
I, <Niv Dan> (<311598031>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/
class Part2 {

    public static boolean change(int[] coins, int n) {
        boolean ans = false;
        ans = changeRecursion(coins, n, 0);
        //Task 2.1
        return ans;
    }

    public static boolean changeRecursion(int[] coins, int n, int index) {
        boolean output = false;
        if (n == 0) {
            output = true;
        } else if (n < 0) {
            output = false;
        } else if (index == coins.length) {
            output = false;
        } else {
            // P || Q --->
            // P: calling recursion with n minus firstIndex in array multiple times
            // Q: the same just with the next index in array
            output = changeRecursion(coins, n - coins[index], index) || changeRecursion(coins, n - coins[index + 1], index + 1);
        }
        return output;
    }

    public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse) {
        boolean ans = false;
        ans = changeLimitedRecursion(coins, n, numOfCoinsToUse, 0);
        //Task 2.2
        return ans;
    }

    public static boolean changeLimitedRecursion(int[] coins, int n, int numOfCoinsToUse, int index) {
        boolean output;
        if (n == 0 && (numOfCoinsToUse == 0))
            output = true;
        else if ((n < 0) | (index == coins.length) | numOfCoinsToUse < 0)
            output = false;
        else {
            output = changeLimitedRecursion(coins, n - coins[index], numOfCoinsToUse - 1, index)
                    || changeLimitedRecursion(coins, n, numOfCoinsToUse, index + 1); // moving to next index
        }
        return output;

    }


    public static void printChangeLimited(int[] coins, int n, int numOfCoinsToUse) {
        printChangeLimitedRecursion(coins, n, numOfCoinsToUse, 0, "");
        //Task 2.3
    }

    public static boolean printChangeLimitedRecursion(int[] coins, int n, int numOfCoinsToUse, int index, String acc) {
        boolean isTrue = false;
        if ((acc.length() > 0) & (n == 0) & (numOfCoinsToUse == 0)) {//base condition
            isTrue = true;
            System.out.println(acc.substring(0, acc.length() - 1)); // to print without last ","
        } else if (!((n < 0) | (index == coins.length) | numOfCoinsToUse < 0)) {
            // calling recursion ; if isTrue = true it wont check the next call to the recursion; else it will call the next to see next index in array.
            isTrue = printChangeLimitedRecursion(coins, n - coins[index], numOfCoinsToUse - 1, index, acc + coins[index] + ",") ||
                    printChangeLimitedRecursion(coins, n, numOfCoinsToUse, index + 1, acc);
        }
        return isTrue;
    }

    public static int countChangeLimited(int[] coins, int n, int numOfCoinsToUse) {
        int ans = countChangeLimitedRecursion(coins, n, numOfCoinsToUse, 0);
        //Task 2.4
        return ans;
    }

    public static int countChangeLimitedRecursion(int[] coins, int n, int numOfCoinsToUse, int index) {
        int ans;
        if (n == 0 & numOfCoinsToUse == 0) {
            ans = 1;
        } else if (n < 0 | index == coins.length | numOfCoinsToUse < 0) {
            ans = 0;
        } else {
            // adding two recursion counts together (index + next index in array)
            ans = countChangeLimitedRecursion(coins, n - coins[index], numOfCoinsToUse - 1, index) +
                    countChangeLimitedRecursion(coins, n, numOfCoinsToUse, index + 1);
        }
        return ans;
    }

    public static void printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse) {
        printAllChangeLimitedRecursion(coins, n, numOfCoinsToUse, 0, "");
        //Task 2.5
    }

    public static void printAllChangeLimitedRecursion(int[] coins, int n, int numOfCoinsToUse, int index, String acc) {
        if ((acc.length() > 0) & n == 0 & (numOfCoinsToUse == 0)) {
            System.out.println(acc.substring(0, acc.length() - 1)); // without last ","
        } else if (!((n < 0) | (index == coins.length) | numOfCoinsToUse < 0)) {
            // similar to Task2.3 just that here we want to print all options
            printAllChangeLimitedRecursion(coins, n - coins[index], numOfCoinsToUse - 1, index, acc + coins[index] + ",");
            printAllChangeLimitedRecursion(coins, n, numOfCoinsToUse, index + 1, acc);
        }
    }


    public static int changeInCuba(int cuc) {
        // Appending two CUC/CUP in one array and considering all in CUP in order to get all options
        int[] coins = {1, 3, 3, 5, 9, 10, 15, 20, 30, 50, 60, 100, 150, 300};
        int ans = changeInCubaRecursion(coins, 3 * cuc, 0);

        //Task 2.6
        return ans;
    }

    public static int changeInCubaRecursion(int[] coins, int cuc, int index) {
        int ans;
        if (cuc == 0)
            ans = 1;
        else if (cuc < 0 | index == coins.length)
            ans = 0;
        else {
            //option with first index in array + next index in array
            ans = changeInCubaRecursion(coins, cuc - coins[index], index) + changeInCubaRecursion(coins, cuc, index + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        //tests for part 2.1
//        int[] change1 = {2, 4, 10, 11};
//        int n = 31;
//        System.out.println("change test 1:expected true, got " + change(change1, n));
//        int[] change2 = {2, 20, 10, 100};
//        n = 15;
//        System.out.println("change test 2: expected false, got " + change(change2, n) + "\n");

        // tests for part 2.2
//        int[] changeLimited1 = {1, 12, 17, 19};
//        int n = 20;
//        int numOfCoinsToUse = 2;
//        System.out.println("ChangeLimited test 1: expected true, got " + changeLimited(changeLimited1, n, numOfCoinsToUse));
//        int[] changeLimited2 = {5, 7, 12};
//        n = 8;
//        numOfCoinsToUse = 2;
//        System.out.println("ChangeLimited test 2: expected false, got " + changeLimited(changeLimited2, n, numOfCoinsToUse));
//        int[] changeLimited3 = {1, 7, 12, 10};
//        n = 10;
//        numOfCoinsToUse = 5;
//        System.out.println("ChangeLimited test 3: expected false, got " + changeLimited(changeLimited3, n, numOfCoinsToUse) + "\n");

        // tests for part 2.3
//        int[] printChangeLimited1 = {1, 2, 3};
//        int n = 4;
//        int numOfCoinsToUse = 2;
//        System.out.println("PrintChangeLimited test 1: expected 2,2 or 1,3 , got ");
//        printChangeLimited(printChangeLimited1, n, numOfCoinsToUse);
//        int[] printChangeLimited2 = {1, 7, 12};
//        n = 10;
//        numOfCoinsToUse = 5;
//        System.out.println("PrintChangeLimited test 2: expected printing nothing, got ");
//        printChangeLimited(printChangeLimited2, n, numOfCoinsToUse);
//        System.out.println("");

        //tests for part 2.4
//         int []countChangeLimited1 = {1,2,3};
//         int n = 4;
//         int numOfCoinsToUse = 2;
//         System.out.println("CountChangeLimited test 1: expected 2, got " + countChangeLimited(countChangeLimited1,n ,numOfCoinsToUse));
//         int []countChangeLimited2 = {5,10,20,50,100};
//         n = 100;
//         numOfCoinsToUse = 5;
//         System.out.println("CountChangeLimited test 2: expected 3, got " + countChangeLimited(countChangeLimited2,n ,numOfCoinsToUse));
//         int []countChangeLimited3 ={5,10,50};
//         n = 65;
//         numOfCoinsToUse = 2;
//         System.out.println("CountChangeLimited test 3: expected 0, got " + countChangeLimited(countChangeLimited3,n ,numOfCoinsToUse)+"\n");

        //tests for part 2.5
//        int[] printAllChangeLimited1 = {1, 2, 3};
//        int n = 4;
//        int numOfCoinsToUse = 2;
//        System.out.println("PrintAllChangeLimited test 1: expected : \n 2,2 \n 1,3 \n or \n 1,3 \n 2,2 , \n got: ");
//        printAllChangeLimited(printAllChangeLimited1, n, numOfCoinsToUse);
//        int[] printAllChangeLimited2 = {1, 5, 10, 20};
//        n = 13;
//        numOfCoinsToUse = 2;
//        System.out.println("PrintAllChangeLimited  test 2: expected printing nothing, got ");
//        printAllChangeLimited(printAllChangeLimited2, n, numOfCoinsToUse);
//        int[] printAllChangeLimited3 = {1, 5, 10, 15};
//        n = 15;
//        numOfCoinsToUse = 3;
//        System.out.println("PrintAllChangeLimited  test 2: expected printing 5,5,5, got ");
//        printAllChangeLimited(printAllChangeLimited3, n, numOfCoinsToUse);
//        System.out.println("");
//
        //tests for part 2.6
//        System.out.println("ChangeInCuba 1");
//        System.out.println(changeInCuba(1));
//        System.out.println("ChangeInCuba 2");
//        System.out.println(changeInCuba(2));
//        System.out.println("ChangeInCuba 20");
//        System.out.println(changeInCuba(20));
//        System.out.println("ChangeInCuba 50");
//        System.out.println(changeInCuba(50));

    }
}
