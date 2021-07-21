/*
I, <Niv Dan> (<311598031>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Comparator;

public class StudentComparatorByAverage implements Comparator<StudentCard> {
    @Override
    public int compare(StudentCard student1, StudentCard student2) {
        if (student1 == null || student2 == null) {
            throw new IllegalArgumentException();
        }
        int output;
        double avrg1 = student1.getAverage();
        double avrg2 = student2.getAverage();
        if (avrg1 > avrg2)
            output = 1;
        else if (avrg1 < avrg2)
            output = -1;
        else
            output = new StudentComparatorById().compare(student1, student2);
        return output;
    }

}
