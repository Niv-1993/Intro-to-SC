/*
I, <Niv Dan> (<311598031>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
a formal case will be opened against me with the BGU disciplinary committee.
*/


public class FilterByActive implements Filter<StudentCard> {
    @Override
    public boolean accept(StudentCard student) {
       return student.isActive();
    }
}