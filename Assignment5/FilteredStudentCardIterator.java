/*
I, <Niv Dan> (<311598031>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilteredStudentCardIterator implements Iterator<StudentCard> {
    private BinaryTreeInOrderIterator<StudentCard> iterator;
    private StudentCard current;
    private Filter<StudentCard> filter;

    public FilteredStudentCardIterator(StudentCardBinarySearchTree StudentCardsTree, Filter<StudentCard> filter) {
        this.filter = filter;
        this.iterator = new BinaryTreeInOrderIterator<>(StudentCardsTree.root);
        this.current = null;
        // initializing the first filtered student when calling first to iterator by filter
        boolean foundFirst = false;
        while (iterator.hasNext() & !foundFirst) {
            StudentCard next = iterator.next();
            if (filter.accept(next)) {
                current = next;
                foundFirst = true;
            }
        }
    }

    public boolean hasNext() {
        // if current is null then next() didn't find another student card that is filtered
        return current != null;
    }

    public StudentCard next() {
        if (!hasNext()) // Exception
            throw new NoSuchElementException();
        StudentCard tmp = current; // saving the current
        current = null;
        boolean found = false;
        // finding the next Student card that implements the filter
        while (iterator.hasNext() & !found) {
            StudentCard next = iterator.next();
            if (filter.accept(next)) {
                current = next;
                found = true;
            }
        }
        return tmp; // returning the last current and updating current to be next student card that implements the filter
    }

    // Do not delete or change or implement this method
    public void remove() {
        throw new UnsupportedOperationException("Do not change this line");
    }
}
