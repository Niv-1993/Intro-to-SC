/*
I, <Niv Dan> (<311598031>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.LinkedList;

public class BitList extends LinkedList<Bit> {
    private int numberOfOnes;

    // Do not change the constructor
    public BitList() {
        numberOfOnes = 0;
    }

    // Do not change the method
    public int getNumberOfOnes() {
        return numberOfOnes;
    }


//=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.1 ================================================

    public void addLast(Bit element) {
        if (element == null)
            throw new IllegalArgumentException("Null is not allowed");
        if (element.equals(Bit.ONE))
            numberOfOnes = numberOfOnes + 1;
        super.addLast(element);
    }

    public void addFirst(Bit element) {
        if (element == null)
            throw new IllegalArgumentException("Null is not allowed");
        if (element.equals(Bit.ONE))
            numberOfOnes = numberOfOnes + 1;
        super.addFirst(element);
    }

    public Bit removeLast() {
        if (getLast().equals(Bit.ONE))
            numberOfOnes = numberOfOnes - 1;
        return super.removeLast();
    }

    public Bit removeFirst() {
        if (getFirst().equals(Bit.ONE))
            numberOfOnes = numberOfOnes - 1;
        return super.removeFirst();
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.2 ================================================
    public String toString() {
        String output = ">";
        for (Bit bit : this)
            output = bit + output;
        output = "<" + output;
        return output;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.3 ================================================
    public BitList(BitList other) {
        super(other); //LinkedList already checks if other = null
        for (Bit bit : this) {
            if (bit == null)// check each bit in list not null
                throw new IllegalArgumentException();
        }
        this.numberOfOnes = other.getNumberOfOnes();
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.4 ================================================
    public boolean isNumber() {
        return (size() >= 1 && (numberOfOnes > 1 | getLast().equals(Bit.ZERO)));
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.5 ================================================
    public boolean isReduced() {
        boolean isReduced = false;
        if (isNumber()) { //first condition
            //condition 2 first part
            if ((size() == 1 && getFirst().equals(Bit.ZERO)) || (size() == 2 && (getFirst().equals(Bit.ONE) && getLast().equals(Bit.ZERO))) ||
                    (size() == 2 && (getFirst().equals(Bit.ONE) && getLast().equals(Bit.ONE)))) {
                isReduced = true;
            }
            //condition 2 - second and third part
            else if ((size() >= 3 && ((getLast().equals(Bit.ONE) && get(size() - 2).equals(Bit.ZERO)) || (getLast().equals(Bit.ZERO) && get(size() - 2).equals(Bit.ONE)))) ||
                    ((numberOfOnes == 2) && size() >= 3 && (getLast().equals(Bit.ONE) && get(size() - 2).equals(Bit.ONE)))) {
                isReduced = true;
            }
        }
        return isReduced;
    }

    public void reduce() {
        while (!(isReduced()) & size() >=2)
            removeLast();
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.6 ================================================
    public BitList complement() {
        BitList output = new BitList();
        for (Bit bit : this)
            output.addLast(bit.negate());
        return output;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.7 ================================================
    public Bit shiftRight() {
        return removeFirst();
    }

    public void shiftLeft() {
        addFirst(Bit.ZERO);
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.8 ================================================
    public void padding(int newLength) {
        while (newLength > size()) {
            addLast(getLast());
        }
    }


    //----------------------------------------------------------------------------------------------------------
    // The following overriding methods must not be changed.
    //----------------------------------------------------------------------------------------------------------
    public boolean add(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public void add(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit remove(int index) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offer(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerFirst(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerLast(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit set(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Do not use this method!");
    }
}
