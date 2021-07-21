/*
I, <Niv Dan> (<311598031>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Iterator;


public class BinaryNumber implements Comparable<BinaryNumber> {

    private static final BinaryNumber ZERO = new BinaryNumber(0);
    private static final BinaryNumber ONE = new BinaryNumber(1);
    private BitList bits;

    // Copy constructor
    //Do not change this constructor
    public BinaryNumber(BinaryNumber number) {
        bits = new BitList(number.bits);
    }

    //Do not change this constructor
    private BinaryNumber(int i) {
        bits = new BitList();
        bits.addFirst(Bit.ZERO);
        if (i == 1)
            bits.addFirst(Bit.ONE);
        else if (i != 0)
            throw new IllegalArgumentException("This Constructor may only get either zero or one.");
    }

    //Do not change this method
    public int length() {
        return bits.size();
    }

    //Do not change this method
    public boolean isLegal() {
        return bits.isNumber() & bits.isReduced();
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.1 ================================================
    public BinaryNumber(char c) {
        int cValue = c - '0';
        if (cValue < 0 | cValue > 9)
            throw new IllegalArgumentException(c + "is not a valid digit");
        this.bits = new BitList();
        while (cValue != 0) {
            int r = cValue % 2;
            this.bits.addLast(new Bit(r));
            cValue = cValue / 2;
        }
        bits.addLast(new Bit(0));
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.2 ================================================
    public String toString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        String output = "";
        for (Bit bit : bits)
            output = bit + output;
        return output;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.3 ================================================
    public boolean equals(Object other) {
        if (other == null)
            throw new IllegalArgumentException();
        BinaryNumber me = new BinaryNumber(this);
        return (other instanceof BinaryNumber && (me.toString().equals(other.toString())));
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.4 ================================================
    public BinaryNumber add(BinaryNumber addMe) {
        if (addMe == null)
            throw new IllegalArgumentException();
        for (Bit bit : addMe.bits)
            if (bit == null)
                throw new IllegalArgumentException();
        BinaryNumber output = new BinaryNumber(this);
        BitList addMeList = new BitList(addMe.bits);
        BitList myList = new BitList(bits);
        //padding to same size
        myList.padding(addMeList.size());
        addMeList.padding(myList.size());
        //Iterator loop
        Iterator<Bit> iter = myList.iterator();
        Iterator<Bit> iterAdd = addMeList.iterator();
        BitList sumBits = new BitList();
        Bit carry = Bit.ZERO;
        Bit BitSum;
        while (iter.hasNext()) {
            Bit nextIter = iter.next();
            Bit nextIterAdd = iterAdd.next();
            BitSum = Bit.fullAdderSum(carry, nextIter, nextIterAdd);
            sumBits.addLast(BitSum);
            carry = Bit.fullAdderCarry(carry, nextIter, nextIterAdd);
        }
        // condition to add last carry to get reduce to minimum correct
        if ((addMeList.getLast().equals(Bit.ZERO) & myList.getLast().equals(Bit.ZERO) | addMeList.getLast().equals(Bit.ONE) & myList.getLast().equals(Bit.ONE)))
            sumBits.addLast(carry);
        //adding last carry to the end

        // reducing to minimum
        sumBits.reduce();
        output.bits = sumBits;

        return output;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.5 ================================================
    public BinaryNumber negate() {
        BinaryNumber output = new BinaryNumber(this);
        output.bits = bits.complement();
        output = output.add(ONE);
        return output;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.6 ================================================
    public BinaryNumber subtract(BinaryNumber subtractMe) {
        if (subtractMe == null)
            throw new IllegalArgumentException();
        for (Bit bit : subtractMe.bits)
            if (bit == null)
                throw new IllegalArgumentException();
        return (add(subtractMe.negate()));
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.7 ================================================
    public int signum() {
        int output = 0;
        if (length() > 1 && bits.getLast().equals(Bit.ONE))
            output = -1;
        else if (length() > 1 && bits.getLast().equals(Bit.ZERO))
            output = 1;
        return output;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.8 ================================================
    public int compareTo(BinaryNumber other) {
        if (other == null || !(other.isLegal()))
            throw new IllegalArgumentException();
        for (Bit bit : other.bits)
            if (bit == null)
                throw new IllegalArgumentException();
        int output = 0;
        if ((subtract(other).signum()) == 1)
            output = 1;
        else if ((other.subtract(this).signum()) == 1)
            output = -1;
        return output;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.9 ================================================
    public int toInt() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        if (bits.size() > 32)
            throw new RuntimeException("Bigger than 32-bits");
        int output = 0;
        int index = 0;
        int base = 1;
        //Iterator
        Iterator<Bit> iter = bits.iterator();
        while (iter.hasNext()) {
            Bit nextIter = iter.next();
            if (nextIter.equals(Bit.ONE)) {
                output = output + base;
            }
            index = index + 1;
            base = 2 * base;
        }
        // If the number is a negative number
        if (signum() == -1)
            output = output + (-1 * base);
        return output;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.10 ================================================
    // Do not change this method
    public BinaryNumber multiply(BinaryNumber multiplyMe) {
        if (multiplyMe == null)
            throw new IllegalArgumentException();
        for (Bit bit : multiplyMe.bits) {
            if (bit == null)
                throw new IllegalArgumentException();
        }
        BinaryNumber me = new BinaryNumber(this);
        BinaryNumber mult = new BinaryNumber(multiplyMe);
        return multiplyPositive(me, mult);
    }

    private BinaryNumber multiplyPositive(BinaryNumber myNumber, BinaryNumber multiplyBy) {
        //Using algorithm of Booth
        //https://www.youtube.com/watch?v=ReQ8RSBvS3E
        BinaryNumber A = new BinaryNumber(ZERO); //
        BitList leftHalfList = new BitList();
        //padding
        multiplyBy.bits.padding(myNumber.length());
        myNumber.bits.padding(multiplyBy.length());
        A.bits.padding(multiplyBy.bits.size());
        //Less significant that is not part of the Bits
        Bit myNumberLs = Bit.ZERO;
        int sizeA = A.bits.size();
        int index = 0;
        while (index < sizeA) {
            //  myNumber = 1 & Ls = 1 or  myNumber =0 & Ls = 0 ---> no action
            //  myNumber = 1 and Ls = 0 --->subtract
            // myNumber = 0 & Ls = 1  ---> add
            if (myNumberLs.equals(Bit.ZERO) & myNumber.bits.getFirst().equals(Bit.ONE)) {
                A = A.subtract(multiplyBy);
            } else if (myNumberLs.equals(Bit.ONE) & (myNumber.bits.getFirst()).equals(Bit.ZERO)) {
                A = A.add(multiplyBy);
            }//Shift
            leftHalfList.addLast(A.bits.shiftRight());
            A.bits.padding(sizeA);
            myNumberLs = myNumber.bits.shiftRight();
            index = index + 1;
        }
        //Unite two BN and reduce
        BinaryNumber output = new BinaryNumber(0);
        output.bits = leftHalfList;
        for (Bit bit : A.bits) {
            output.bits.addLast(bit);
        }
        output.bits.reduce();
        return output;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.11 ================================================
    // Do not change this method
    public BinaryNumber divide(BinaryNumber divisor) {
        if (divisor == null)
            throw new IllegalArgumentException();
        for (Bit bit : divisor.bits)
            if (bit == null)
                throw new IllegalArgumentException();
        // Do not remove or change the next two lines
        if (divisor.equals(ZERO)) // Do not change this line
            throw new RuntimeException("Cannot divide by zero.");// Do not change this line
        BinaryNumber me = new BinaryNumber(this);
        BinaryNumber div = new BinaryNumber(divisor);
        boolean isNegative = false;
        //checking all cases of negative numbers
        if ((div.signum() == -1) & (me.signum() == 1)) {
            div = div.negate();
            isNegative = true;
        }
        if ((me.signum() == -1) & (div.signum() == 1)) {
            isNegative = true;
            me = me.negate();
        }
        if ((div.signum() == -1) & (me.signum() == -1)) {
            div = div.negate();
            me = me.negate();
            isNegative = false;
        }
        BinaryNumber output = dividePositive(me, div);
        if (isNegative) {
            output = output.negate();
        }
        return output;
    }

    private BinaryNumber dividePositive(BinaryNumber myNumber, BinaryNumber divisor) {
        BinaryNumber A = new BinaryNumber(ZERO);
        Bit lastA = new Bit(0);
        //padding
        myNumber.bits.padding(divisor.length());
        divisor.bits.padding(myNumber.length());
        A.bits.padding(myNumber.length());
        //
        int numOfIterations = myNumber.length() + 1;
        int sizeA = A.length();
        while (numOfIterations != 0) {
            BitList prior = A.bits; //saving in memory instead of adding back again later
            A = A.subtract(divisor);
            if (A.signum() == -1) {
                A.bits = prior;
                myNumber.bits.addFirst(Bit.ZERO);

            } else if (A.signum() == 1 | (A.signum() == 0)) {
                A.bits.padding(sizeA);
                myNumber.bits.addFirst(Bit.ONE);
            }
            //shift left
            A.bits.addFirst(myNumber.bits.removeLast());
            lastA = A.bits.removeLast();
            A.bits.padding(sizeA);
            numOfIterations = numOfIterations - 1;
        }
        myNumber.bits.reduce();
        return myNumber;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.12 ================================================
    public BinaryNumber(String s) {
        //Exceptions checking input
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("Input is null or empty");
        }
        boolean isNegativeE = false;
        if (s.charAt(0) == '-') {
            isNegativeE = true;
            s = s.substring(1);
        }
        for (int i = 0; i < s.length(); i++) {
            int cValue = s.charAt(i) - '0';
            if (cValue < 0 | cValue > 9)
                throw new IllegalArgumentException(s.charAt(i) + " is not a valid digit");
        }
        int i = 0;
        //Adding first char to BN
        BinaryNumber result = new BinaryNumber(s.charAt(i));
        BinaryNumber temp;
        BinaryNumber ten = (new BinaryNumber('9').add(new BinaryNumber('1')));
        i = i + 1;
        while (i < s.length()) {
            temp = new BinaryNumber(s.charAt(i));
            result = result.multiply(ten);
            result = result.add(temp);
            i = i + 1;
        }
        if (isNegativeE) {
            result = result.negate();
        }
        this.bits = result.bits;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.13 ================================================
    public String toIntString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        String output = "";
        BinaryNumber me = new BinaryNumber(this);
        BinaryNumber ten = new BinaryNumber("10");
        BinaryNumber intMaxValue = new BinaryNumber("2147483646"); // less iterations
        boolean isNegative = false;
        if (me.signum() == -1) {
            isNegative = true;
            me = me.negate();
        }
        while (me.compareTo(intMaxValue) > 0) {
            int modulo = (me.modulo(ten)).toInt();
            output = modulo + output;
            me = me.divide(ten);
        }
        output = me.toInt() + output;
        if (isNegative) {
            output = "-" + output;
        }

        return output;

    }
        //using the same algorithm of dividing but returning the remainder
    private BinaryNumber modulo(BinaryNumber divisor) {
        BinaryNumber me = new BinaryNumber(this);
        BinaryNumber div = new BinaryNumber(divisor);
        BinaryNumber output = remainder(me, div);
        return output;
    }
    private BinaryNumber remainder(BinaryNumber Q, BinaryNumber D) {
        BinaryNumber R = new BinaryNumber(ZERO);
        Bit rLast = new Bit(0);
        D.bits.padding(Q.length());
        Q.bits.padding(D.length());
        R.bits.padding(Q.length());
        int iterationSize = Q.bits.size();
        int initialSize = R.bits.size();
        //shift left
        R.bits.addLast(Q.bits.removeLast());
        rLast = R.bits.removeLast();
        Q.bits.shiftLeft();
        while (iterationSize != 0) {
            BitList priorR = R.bits;
            R = R.subtract(D);
            if (R.signum() == -1) {
                R.bits = priorR;
                Q.bits.addFirst(Bit.ZERO);
            } else if (R.signum() == 1 | R.signum() == 0) {
                R.bits.padding(initialSize);
                Q.bits.addFirst(Bit.ONE);
            }
            //shift left
            R.bits.addFirst(Q.bits.removeLast());
            rLast = R.bits.removeLast();
            R.bits.padding(initialSize);
            iterationSize = iterationSize - 1;
        }
        R.bits.shiftRight();
        R.bits.reduce();
        return R;
    }

    // Returns this * 2
    public BinaryNumber multBy2() {
        BinaryNumber output = new BinaryNumber(this);
        output.bits.shiftLeft();
        output.bits.reduce();
        return output;
    }

    // Returns this / 2;
    public BinaryNumber divBy2() {
        BinaryNumber output = new BinaryNumber(this);
        if (!equals(ZERO)) {
            if (signum() == -1) {
                output.negate();
                output.bits.shiftRight();
                output.negate();
            } else output.bits.shiftRight();
        }
        return output;
    }

}
