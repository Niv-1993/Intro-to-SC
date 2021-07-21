/*
I, <Niv Dan> (<311598031>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
a formal case will be opened against me with the BGU disciplinary committee.
*/


import java.util.Iterator;

public class StudentCard {
    private static final int DEFAULT_NOT_COMPLETED_GRADE = -100;
    private String name;
    private int id;
    private double average;
    private int numOfCompletedCourses;
    private List<SimpleIntPair> courses;

    public StudentCard(String name, int id) {
        this.name = name;
        this.id = id;
        this.numOfCompletedCourses = 0;
        this.average = -50;
        this.courses = new DynamicArray<>();
    }

    /**
     * A getter of the field name.
     *
     * @return the field name..
     */
    public String getName() {
        return name;
    }

    /**
     * A getter of the field ID.
     *
     * @return the field ID..
     */
    public int getId() {
        return id;
    }


    /**
     * A getter of the field average.
     *
     * @return the field average.
     */
    public double getAverage() {
        return average;
    }


    /**
     * This method searches for a course in the list of courses.
     *
     * @return the index of the course if found, -1 otherwise.
     */
    public int hasCourse(int course) {
        int output = -1;
        boolean found = false;
        for (int i = 0; i < courses.size() & !found; i++) {
            if (courses.get(i).getFirst() == course) {
                output = i;
                found = true;
            }
        }
        return output;
    }

    /**
     * This method checks if the student has a course in progress.
     *
     * @return true if the student has a course in progress.
     */
    public boolean isActive() {
        boolean isActive = false;
        for (int i = 0; i < courses.size() & !isActive; i++) {
            isActive = courses.get(i).getSecond() == DEFAULT_NOT_COMPLETED_GRADE;
        }
        return isActive;
    }

    /**
     * This method returns the grade of the student in the requested course.
     *
     * @return the grade if the course appears in the courses list, -50 otherwise.
     */
    public int courseGrade(int course) {
        int courseIndex = hasCourse(course);
        if (courseIndex != -1)
            return courses.get(courseIndex).getSecond();
        else throw new IllegalArgumentException();
    }

    /**
     * This method adds a course with the default non completed grade.
     *
     * @return true if the course was not in the list and false otherwise.
     */
    public boolean registerCourse(int course) {
        boolean added = false;
        SimpleIntPair toAdd = new SimpleIntPair(course, DEFAULT_NOT_COMPLETED_GRADE);
        int i = 0;
        boolean courseExists = false;
        if (courses.isEmpty() || course > courses.get((courses.size() - 1)).getFirst()) {
            courses.add(toAdd);
            added = true;
        }
        while ((i < courses.size()) & !added & !courseExists) {
            if (courses.get(i).getFirst() == course) {
                courseExists = true;
            }
            else if ((course < courses.get(i).getFirst())) {
                courses.add(i, toAdd);
                added = true;
            }
            i++;
        }
        return added;
    }

    /**
     * This method completes a course by updating the grade.
     *
     * @return true if the student was registered to the course and it was in progress.
     */
    public boolean completeCourse(int course, int grade) {
        if (grade < 0 | grade > 100)
            throw new IllegalArgumentException();
        boolean isComplete = false;
        for (int i = 0; i < courses.size() & !(isComplete); i = i + 1) {
            if (courses.get(i).getFirst() == course && courses.get(i).getSecond() == DEFAULT_NOT_COMPLETED_GRADE) {
                courses.get(i).setSecond(grade);
                isComplete = true;
                this.numOfCompletedCourses = this.numOfCompletedCourses + 1;
                this.average = ((this.average*(numOfCompletedCourses-1)) + grade) / this.numOfCompletedCourses;
            }
        }
        return isComplete;
    }


    @Override
    public String toString() {
        return getName() + " " + getId();
    }

}

