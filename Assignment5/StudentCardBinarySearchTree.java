/*
I, <Niv Dan> (<311598031>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Comparator;

public class StudentCardBinarySearchTree extends BinarySearchTree<StudentCard> {


    public StudentCardBinarySearchTree(Comparator<StudentCard> myComparator) {
        super(myComparator);
    }

    public void balance() {
        // Building DynamicArrayList
        List<StudentCard> list = new DynamicArray<>();
        BinaryTreeInOrderIterator<StudentCard> inOrderIter = new BinaryTreeInOrderIterator<>(this.root);
        while (inOrderIter.hasNext()) {
            //Each iteration add to DynamicArrayList the tree node and at the same time remove the node from tree
            StudentCard nextCard = inOrderIter.next();
            list.add(nextCard);
            this.remove(nextCard);
        }
        // this tree is empty now so we can send it empty
        buildBalancedTree(this, list, 0, (list.size() - 1));
    }

    private void buildBalancedTree(StudentCardBinarySearchTree tree, List<StudentCard> list, int low, int high) {
        if (high >= low) {
            buildBalancedTree(tree, list, low + 1, high - 1);
            tree.insert(list.get(low));
        }
        tree.insert(list.get(high)); // base case gets here every recursion and adds
    }
}


