
/*
 I, <Niv Dan> (<311598031>), assert that the work I submitted is entirely my own.
 I have not received any part from any other student in the class, nor did I give parts of it for use to others.
 I realize that if my work is found to contain code that is not originally my own, a
 formal case will be opened against me with the BGU disciplinary committee.
*/

// Last Update: 04/12/19

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;

public class Assignment2 {
	/*--------------------------------------------------------
	   Part a: instance representation & Solution verification 
	  -------------------------------------------------------
	 */

    // Task 1
    public static boolean hasFlight(int[][] flights, int i, int j) {
        // Add your code here
        boolean hasFlight = false;
        for (int index = 0; index < flights[i].length & !hasFlight; index = index + 1) {
            if (flights[i][index] == j)
                hasFlight = true;
        }
        return hasFlight;
    }

    // Task 2
    public static boolean isLegalInstance(int[][] flights) {
        // Add your code here
        boolean sameFlight = false;
        boolean haveAFlightBack = true;
        boolean flightIsNull = false;
        boolean isLegalInstance = false;
        boolean differentCity = false;
        //FIRST CONDITION
        if ((flights != null) && (flights.length > 1)) {
            for (int i = 0; (i < flights.length) & haveAFlightBack & !(sameFlight) & !flightIsNull & !differentCity; i = i + 1) {
                if (flights[i] == null) {
                    flightIsNull = true;
                }
                //SECOND CONDITION
                for (int j = 0; (!flightIsNull && !differentCity & (j < flights[i].length) & !(sameFlight) & haveAFlightBack); j = j + 1) {
                    if ((flights[i][j] <= flights.length) & (flights[i][j] >= 0)) {
                        if (flights[i][j] == i) { //FOURTH CONDITION
                            sameFlight = true;
                        }
                        //Check flight back
                        int origin = flights[i][j];
                        if (!(hasFlight(flights, origin, i))) { //THIRD CONDITION
                            haveAFlightBack = false;
                        }// SECOND CONDITION
                    } else differentCity = true;
                }
            }
            if (!(sameFlight) & haveAFlightBack & !flightIsNull & !differentCity) {
                isLegalInstance = true;
            }
        }
        return isLegalInstance;
    }

    // Task 3
    public static boolean isSolution(int[][] flights, int[] tour) {
        // Add your code here
        //EXCEPTIONS
        if (tour == null || tour.length != flights.length) {
            throw new IllegalArgumentException("null OR tour length bigger/noEqual  flights");
        }
        for (int i = 0; i < tour.length; i = i + 1) {
            if (tour[i] >= flights.length | tour[i] < 0) {
                throw new IllegalArgumentException("Illegal argument in tour array");
            }
        } //Tour conditions
        boolean firstCondition = false;
        boolean secondCondition = false;
        boolean appearOnce = true;
        boolean isSolution = false;
        for (int i = 0; i < tour.length - 1 & appearOnce & !firstCondition & !secondCondition; i = i + 1) {
            // Every num appears once
            for (int j = 1; j < tour.length - 1; j = j + 1) {
                if (tour[i] == tour[j]) {
                    appearOnce = false;
                }
            }
            if (appearOnce) {
                if (hasFlight(flights, tour[i], tour[i + 1])) {
                    firstCondition = true;
                }
                if ((hasFlight(flights, (tour[(tour.length) - 1]), tour[0]))) {
                    secondCondition = true;
                }
            }
        }
        if (firstCondition & secondCondition & appearOnce) {
            isSolution = true;
        }
        return isSolution;
    }
	/*------------------------------------------------------
	  Part b: Express the problem as a CNF formula, solve 
	  it with a SAT solver, and decode the solution
	  from the satisfying assignment
	  -----------------------------------------------------
	 */

    // Task 4
    public static int[][] atLeastOne(int[] vars) {
        // Add your code here
        int[][] cnf = new int[1][vars.length];
        for (int i = 0; i < vars.length; i = i + 1) {
            cnf[0][i] = vars[i];
        }
        return cnf;
    }

    // Task 5
    public static int[][] atMostOne(int[] vars) {
        // Add your code here
        int n = vars.length;
        int cnfSize = (n * (n - 1)) / 2;
        int currIndex = 0;
        int[][] cnf = new int[cnfSize][2];
        for (int i = 0; i < n; i = i + 1) {
            for (int j = i + 1; j < n; j = j + 1, currIndex = currIndex + 1) {
                int[] clause = {-vars[i], -vars[j]};
                cnf[currIndex] = clause;
            }
        }
        return cnf;
    }

    // Task 6
    public static int[][] append(int[][] arr1, int[][] arr2) {
        // Add your code here
        int totalArrayLength = arr1.length + arr2.length;
        int[][] arrTotal = new int[totalArrayLength][];
        int counterIndex = 0;
        for (int i = 0; i < arr1.length; i = i + 1) {
            arrTotal[i] = arr1[i];
            counterIndex = counterIndex + 1;
        }
        for (int i = 0; i < arr2.length; i = i + 1) {
            arrTotal[counterIndex] = arr2[i];
            counterIndex = counterIndex + 1;
        }
        return arrTotal;
    }


    // Task 7
    public static int[][] exactlyOne(int[] vars) {
        // Add your code here
        int[][] exactlyOne;
        int[][] atLeastOne = atLeastOne(vars);
        int[][] atMostOne = atMostOne(vars);
        exactlyOne = append(atLeastOne, atMostOne);
        return exactlyOne;
    }

    // Task 8
    public static int[][] diff(int[] I1, int[] I2) {
        // Add your code here
        int[][] cnf = new int[I1.length][];
        int index = 0;
        for (int i = 0; i < I1.length; i = i + 1) {
            int[] clause = {-I1[i], -I2[i]};
            cnf[index] = clause;
            index++;
        }
        return cnf;
    }

    // Task 9
    public static int[][] createVarsMap(int n) {
        // Add your code here
        int[][] map = new int[n][n];
        int valueIn = 1;
        for (int i = 0; i < n; i = i + 1) {
            for (int j = 0; j < n; j = j + 1) {
                map[i][j] = valueIn;
                valueIn++;
            }
        }
        return map;
    }

    // Task 10
    public static int[][] declareInts(int[][] map) {
        // Add your code here
        int n = map.length;
        int[][] cnf = new int[0][];
        for (int index = 0; index < n; index = index + 1) {
            cnf = append(cnf, exactlyOne(map[index]));
        }
        return cnf;
    }

    // Task 11
    public static int[][] allDiff(int[][] map) {
        // Add your code here
        int[][] cnf = new int[0][];
        for (int i = 0; i < map.length - 1; i = i + 1) {
            for (int j = i + 1; j < map.length; j = j + 1) {
                int[][] different = diff(map[i], map[j]);
                cnf = append(cnf, different);
            }
        }
        return cnf;
    }

    // Task 12
    public static int[][] allStepsAreLegal(int[][] flights, int[][] map) {
        // Add your code here
        int[][] cnf = new int[0][];
        int n = map.length;
        for (int j = 0; j < n; j = j + 1) {
            for (int k = 0; k < n; k = k + 1) {
                if (!(hasFlight(flights, j, k))) {
                    for (int i = 0; i < n - 1; i = i + 1) {
                        int[][] tmpCnf = {{-map[i][j], -map[i + 1][k]}};
                        cnf = append(cnf, tmpCnf);
                    }
                    int[][] tmpCnf = {{-map[n - 1][j], -map[0][k]}};
                    cnf = append(cnf, tmpCnf);
                }
            }
        }
        return cnf;
    }

    // Task 13
    public static void encode(int[][] flights, int[][] map) {
        // Add your code here
        //EXCEPTIONS ON INPUT
        if (flights == null | map == null)
            throw new IllegalArgumentException("Invalid input");
        for (int i = 0; i < flights.length; i++) {
            if (flights[i] == null)
                throw new IllegalArgumentException("Null argument");
        }
        //EXCEPTIONS ON ILLEGAL FLIGHTS/MAP
        if (!(isLegalInstance(flights)))
            throw new IllegalArgumentException("Flight is illegal");
        for (int i = 0; i < map.length; i++) {
            if (map.length != map[i].length) {
                throw new IllegalArgumentException("Map is illegal");
            }
        }
        //CONDITION CLAUSES INPUT
        int[][] cnf1 = allStepsAreLegal(flights, map);
        SATSolver.addClauses(cnf1);
        int[][] cnf2 = allDiff(map);
        SATSolver.addClauses(cnf2);
        int[][] cnf3 = declareInts(map);
        SATSolver.addClauses(cnf3);
    }

    // Task 14
    public static int[] decode(boolean[] assignment, int[][] map) {
        // Add your code here
        int n = map.length;
        //EXCEPTION
        if (assignment.length != ((n * n) + 1))
            throw new IllegalArgumentException();
        int[] solution = new int[n];
        int booleanIndex;
        int solutionIndex = 0;
        //Running all index of assignment
        for (booleanIndex = 1; booleanIndex < assignment.length; booleanIndex = booleanIndex + 1) {
            if (assignment[booleanIndex]) {
                for (int i = 0; i < map.length; i = i + 1) {
                    for (int j = 0; j < map[i].length; j = j + 1) {
                        if (map[i][j] == booleanIndex) {
                            solution[solutionIndex] = j;
                            solutionIndex++;
                        }
                    }
                }
            }
        }
        return solution;
    }

    // Task 15
    public static int[] solve(int[][] flights) {
        // Add your code here
        //EXCEPTION ILLEGAL FLIGHT
        if (!(isLegalInstance(flights))) //NULL CHECK in: isLegalInstance method.
            throw new IllegalArgumentException("Flight is null or illegal");
        int[][] map = createVarsMap(flights.length);
        int[] s;
        int nVars = (map.length * map.length);
        //Initialize SATSolver
        SATSolver.init(nVars);
        encode(flights, map); //All conditions
        boolean[] assignment = SATSolver.getSolution();
        if (assignment == null) {
            throw new IllegalArgumentException("TIMEOUT");
        } else if (assignment.length == nVars + 1) {
            int[] tourSolution = decode(assignment, map);
            boolean isSolution = isSolution(flights, tourSolution);
            if ((isSolution))
                s = tourSolution;
            else throw new IllegalArgumentException("No flight solution");
        } else {
            s = null;
        }
        return s;
    }

    // Task 16
    public static boolean solve2(int[][] flights, int s, int t) {
        if (!(isLegalInstance(flights)))
            throw new IllegalArgumentException("Flight is null or illegal");
        if (!((s <= flights.length && s >= 0) && (t <= flights.length && t >= 0)))
            throw new IllegalArgumentException();
        int[][] map = createVarsMap(flights.length);
        boolean solve2 = false;
        int n = map.length;
        int nVars = flights.length * flights.length;
        //Initialize SATSolver
        SATSolver.init(nVars);
        //All clauses same like Task15 + NEW.
        encode(flights, map);
        int[] clauseAdd1 = {map[0][s]}; // Start in map from city s
        SATSolver.addClause(clauseAdd1);
        int[] clauseAdd2 = {map[flights.length - 1][t]}; // END at city t
        SATSolver.addClause(clauseAdd2);
        boolean[] assignment = SATSolver.getSolution();
        if (assignment == null) {
            throw new IllegalArgumentException("TIMEOUT");
        } else if (assignment.length == nVars + 1) {
            int[] solution = decode(assignment, map);
            if (isSolution(flights, solution)) { //Find Second solution
                SATSolver.init(nVars);
                //SAME CLAUSES + NEW
                encode(flights, map);
                int[] clauseAdd4 = {map[0][s]};
                SATSolver.addClause(clauseAdd4);
                int[] clauseAdd5 = {map[flights.length - 1][t]};
                SATSolver.addClause(clauseAdd5);
                int[] clauseAdd3 = {-solution[1]}; // NOT SAME SOLUTION LIKE PREVIOUS
                SATSolver.addClause(clauseAdd3);
                boolean[] assignment2 = SATSolver.getSolution();
                if (assignment2 == null)
                    throw new IllegalArgumentException("TIMEOUT");
                else if (assignment2.length == nVars + 1) {
                    int[] solution2 = decode(assignment2, map);
                    if (isSolution(flights, solution2)) {
                        solve2 = true;
                    }
                }
            } else
                throw new IllegalArgumentException("No flight solution");
        }
        return solve2;
    }
}
