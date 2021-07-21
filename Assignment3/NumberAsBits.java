import java.math.BigInteger;

/*
I, <Niv Dan> (<311598031>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/
public class NumberAsBits {

    private Bit[] bits;


    public NumberAsBits(Bit[] bits) {
        //Task 3.4
        if (bits == null | bits.length == 0)
            throw new IllegalArgumentException();
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] == null)
                throw new IllegalArgumentException();
        }
        this.bits = new Bit[bits.length];
        for (int i = 0; i < bits.length; i++) {
            this.bits[i] = bits[i];
        }

    }

    public String toString() {
        //Task 3.5
        String ans;
        BigInteger base = new BigInteger("2");
        BigInteger finalNumber = BigInteger.ZERO;
        for (int i = bits.length - 1; i >= 0; i--) {
            if ((bits[i].toInt()) == 1) {
                //using method of BigInteger - 2^(array.length -1)-index) because we are calculating backwards
                BigInteger t = base.pow((bits.length - 1) - i);
                finalNumber = finalNumber.add(t);
            }
        }
        ans = finalNumber.toString();

        return ans;
    }

    public String base2() {
        //Task 3.6
        String ans = "";
        for (int i = 0; i < bits.length; i++) {
            ans = ans + bits[i].toInt(); //toInt method we built in Bit class
        }
        return ans;
    }
}

