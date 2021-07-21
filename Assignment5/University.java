/*
I, <Niv Dan> (<311598031>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.NoSuchElementException;

public class University {
    private StudentCardBinarySearchTree idTree;
    private StudentCardBinarySearchTree avgTree;

    public University() {
        idTree = new StudentCardBinarySearchTree(new StudentComparatorById());
        avgTree = new StudentCardBinarySearchTree(new StudentComparatorByAverage());
    }

    public StudentCard lookUp(int idNumber) {
        StudentCard lookFor = new StudentCard("", idNumber);
        return idTree.findData(lookFor);
    }

    public void balance() {
        idTree.balance();
        avgTree.balance();
    }

    public boolean add(StudentCard newStudent) {
        boolean added = false;
        StudentCard lookedUp = lookUp(newStudent.getId()); // if the id is not in tree then it is null
        if (lookedUp == null) {
            // adding to both trees
            idTree.insert(newStudent);
            avgTree.insert(newStudent);
            added = true;
        }
        return added;
    }


    public boolean delete(StudentCard student) {
        boolean deleted = false;
        StudentCard lookedUp = lookUp(student.getId()); // if the id is not in tree then it is null
        if (lookedUp != null){
            // adding to both trees
            idTree.remove(student);
            avgTree.remove(student);
            deleted = true;
        }
        return deleted;
    }


    public boolean register(int id, int course) {
       boolean registered = false;
       StudentCard lookedUp = lookUp(id);
       if (lookedUp != null){
           //registerCourse returns boolean answer if the process was success
            registered = lookedUp.registerCourse(course);
       }
       return registered;
    }

    public boolean complete(int id, int course, int grade) {
        boolean completed = false;
        StudentCard lookedUp = lookUp(id); // looking up for student
        if (lookedUp != null){
            // completeCourse returns boolean answer if the process was success
            completed = lookedUp.completeCourse(course,grade);
        }
        if (completed){ // if the process was success then balance the trees
            balance();
        }
        return completed;
    }

    public double courseAverage(int course) {
        // building filter iterator for avgTree
        FilterByCourse filter = new FilterByCourse(course);
        FilteredStudentCardIterator iterator = new FilteredStudentCardIterator(avgTree, filter);
        // counting the number of completed courses in the tree
        int numberOfCompleted = 0;
        double totalAvg = 0;
        while (iterator.hasNext()){
            StudentCard next = iterator.next();
            totalAvg = totalAvg + next.courseGrade(course); // adding each iteration to the totalAvg
            numberOfCompleted = numberOfCompleted + 1;
        }
        if (numberOfCompleted == 0){ // if non of the students completed the course
            throw new NoSuchElementException();
        }
        totalAvg = totalAvg / numberOfCompleted; // calculate average
        return totalAvg;
    }

    public void activeStudentsByAverage() {
        // building filter iterator
        FilterByActive filter = new FilterByActive();
        FilteredStudentCardIterator iterator = new FilteredStudentCardIterator(avgTree, filter);
        // saving in the stack all the students that are in-order from the iterator
        StackAsDynamicArray<StudentCard> stack = new StackAsDynamicArray<>();
        while (iterator.hasNext()){
            StudentCard next = iterator.next();
            stack.push(next); // pushing in the stack each filtered student
        }
        while (!stack.isEmpty()){ // printing all students from highest average to lowest
            System.out.println(stack.pop());
        }
    }
}
