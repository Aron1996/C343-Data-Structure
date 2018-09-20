import java.util.function.BiPredicate;

/**
 * TODO: This is your second major task.
 * <p>
 * This class implements a generic height-balanced binary search tree,
 * using the AVL algorithm. Beyond the constructor, only the insert()
 * and remove() methods need to be implemented. All other methods are unchanged.
 */

public class AVLTree<K> extends BinarySearchTree<K> {

    /**
     * Creates an empty AVL tree as a BST organized according to the
     * lessThan predicate.
     */
    public AVLTree(BiPredicate<K, K> lessThan) {
        super(lessThan);
    }

    protected void setLeftChild(Node n1,Node n2){
        if (n1 == null) n2.parent = n1;
        else if (n2 == null) n1.left = n2;
        else {
            n1.left = n2;
            n2.parent = n1;
        }
    }

    protected void setRightChild(Node n1,Node n2){
        if (n1 == null) n2.parent = n1;
        else if (n2 == null) n1.right = n2;
        else {
            n1.right = n2;
            n2.parent = n1;
        }
    }

    /**
     * TODO
     * Inserts the given key into this AVL tree such that the ordering
     * property for a BST and the balancing property for an AVL tree are
     * maintained.
     */
    private void rotate_left(Node n){
        Node ns_parent = n.parent;
        Node n_rights_leftchild = n.right.left;
        setLeftChild(ns_parent,n.right);
        if (ns_parent == null) this.root = n.right;
        setLeftChild(n.right,n);
        setRightChild(n,n_rights_leftchild);
        n.updateHeight();
        n.parent.updateHeight();
        if (ns_parent != null) ns_parent.updateHeight();
    }

    private void rotate_right(Node n){
        Node ns_parent = n.parent;
        Node n_lefts_rightchild = n.left.right;
        setRightChild(ns_parent,n.left);
        if (ns_parent == null) this.root = n.left;
        setRightChild(n.left,n);
        setLeftChild(n,n_lefts_rightchild);
        n.updateHeight();
        n.parent.updateHeight();
        if (ns_parent!=null) ns_parent.updateHeight();
    }

    private int height_helper(Node n){
        if (n == null) return -1;
        else return n.height;
    }
    private int balance(Node n){
        if (n == null){
            return 0;
        }
        return (height_helper(n.left) - height_helper(n.right));
    }

    private void rebalance(Node n){
        if (balance(n) > 1) {
            if (balance(n.left) < 0) {//left right case
                rotate_left(n.left);
            }
            //if (balance(n) > 1) {
                rotate_right(n); //left left case
            //}
        } else if (balance(n) < -1) {
            if (balance(n.right) > 0) {//right left case
                rotate_right(n.right);
            }
            //if (balance(n) < -1) {
                rotate_left(n);
            //}
        }
    }

    public Node insert(K key) {
        super.insert(key);
        rebalance(this.root);
        return search(key);
    }

    public void remove(K key) {
        super.remove(key);
        rebalance(this.root);
    }

    public static void main(String[] args){
        AVLTree<Integer> a1 = new AVLTree<>((Integer x, Integer y) -> x < y);
        int[] datas = new int[]{1,2,3};
        for (int d:datas){
            a1.insert(d);
        }
        System.out.println(a1.root.data);
        System.out.println(a1.root.left.data);
        System.out.println(a1.root.right.data);
        System.out.println(a1.root.parent);
        System.out.println(a1.root.left.left);
        System.out.println(a1.root.right.right);
        System.out.println(a1.root.left.height);
    }


}
