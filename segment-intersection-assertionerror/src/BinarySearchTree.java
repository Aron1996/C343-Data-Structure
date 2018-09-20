import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * TODO: This is your first major task.
 * <p>
 * This class implements a generic unbalanced binary search tree (BST).
 */

public class BinarySearchTree<K> implements Tree<K> {

    /**
     * A Node is a Location (defined in Tree.java), which means that it can be the return value
     * of a search on the tree.
     */

    class Node implements Location<K> {

        protected K data;
        protected Node left, right;
        protected Node parent;
        protected int height;

        /**
         * Constructs a leaf node with the given key.
         */
        public Node(K key) {
            this(key, null, null);
        }

        /**
         * TODO
         * <p>
         * Constructs a new node with the given values for fields.
         */
        public Node(K data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        /*
         * Provide the get() method required by the Location interface.
         */
        @Override
        public K get() {
            return data;
        }

        /**
         * Return true iff this node is a leaf in the tree.
         */
        protected boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * TODO
         * <p>
         * Performs a local update on the height of this node. Assumes that the
         * heights in the child nodes are correct. Returns true iff the height
         * actually changed. This function *must* run in O(1) time.
         */
        private int height_helper(Node n){
            if (n == null) return -1;
            else return n.height;
        }
        protected boolean updateHeight() {
            if (this.height !=
                    Math.max(height_helper(this.left),height_helper(this.right)) + 1){
                this.height =
                        Math.max(height_helper(this.left),height_helper(this.right)) + 1;
                return true;
            }
            return false;
        }

        /**
         * TODO
         * <p>
         * Returns the location of the node containing the inorder predecessor
         * of this node.
         */
        public Node getBefore(){
            Node result;
            if (this.left != null){
                result = this.left.largest();
                }
            else result = smallerAncestor(this);
            return result;
        }


        /**
         * TODO
         * <p>
         * Returns the location of the node containing the inorder successor
         * of this node.
         */
        public Node getAfter() {
            Node result;
            if (this.right != null){
                result = this.right.smallest();
                }
            else {
                result = greaterAncestor(this);
            }
            return result;
        }

        /**
         * TODO
         * <p>
         * This method should return the closest ancestor of node q
         * whose key is less than q's key. It is not necessary to
         * to perform key comparisons to implement this method.
         */
        private Node smallerAncestor(Node q) {
            if (q == null) return null;
            Node ancestor = q.parent;
            //Node q2 = ancestor;
            if (ancestor == null) return null;
            else if (ancestor.right == q) return ancestor;
            else { //node q is the right child of parent
                        //I tried a non-recursion version, then I found recursion is easier to understand and read
                /*do{
                    ancestor = ancestor.parent;
                }
                while (ancestor != null){
                    if (ancestor.right == q2) return ancestor;
                }
                return ancestor;
            }*/
                return smallerAncestor(ancestor);
            }
        }

        /**
         * TODO
         * <p>
         * This method should return the closest ancestor of node q
         * whose key is greater than q's key. It is not necessary to
         * to perform key comparisons to implement this method.
         */
        private Node greaterAncestor(Node q) {
            if (q == null) return null;
            Node ancestor = q.parent;
            if (ancestor == null) return null;
            else if (ancestor.left == q) return ancestor;
            else return greaterAncestor(ancestor);
        }

        /*
         * TODO
         * This method should return the node in the subtree rooted at 'this'
         * that has the smallest key.
         */
        private Node smallest(Node n) {
            if (n == null) return null;
            else if (n.left == null) return n;
            else return smallest(n.left);
        }
        protected Node smallest() {
            return smallest(this);
        }

        /*
         * TODO
         * This method should return the node in the subtree rooted at 'this'
         * that has the largest key.
         */
        private Node largest(Node n) {
            if (n == null) return null;
            else if (n.right == null) return n;
            else return (largest(n.right));
        }
       private Node largest() {
            return largest(this);
        }

        public String toString() {
            return toStringPreorder(this);
        }

    }

    protected Node root;
    protected int numNodes;
    protected BiPredicate<K, K> lessThan;

    /**
     * Constructs an empty BST, where the data is to be organized according to
     * the lessThan relation.
     */
    public BinarySearchTree(BiPredicate<K, K> lessThan) {
        this.lessThan = lessThan;
    }

    /**
     * TODO
     * <p>
     * Looks up the key in this tree and, if found, returns the
     * location containing the key.
     */
    private Node search(Node n,K key){
        if (n == null) return null;
        else {
            boolean small = lessThan.test(key, n.data);
            boolean large = lessThan.test(n.data, key);
            if (small) return search(n.left,key);
            else if (large) return search(n.right,key);
            else return n;
        }
    }
    public Node search(K key) {
        return search(this.root,key);
    }

    /**
     * Returns the height of this tree. Runs in O(1) time!
     */
    public int height() {
        return get_height(root);
    }

    /**
     * TODO
     * The get_height method returns the height of the Node n, which may be null.
     */
    protected int get_height(Node n) {
        if (n == null) return -1;
        else return n.height;
    }

    /**
     * TODO
     * <p>
     * Clears all the keys from this tree. Runs in O(1) time!
     */
    public void clear() {
        this.root.left = null;
        this.root.right = null;
        this.root = null;
        this.numNodes = 0;
    }

    /**
     * Returns the number of keys in this tree.
     */
    public int size() {
        return numNodes;
    }

    /**
     * TODO
     * <p>
     * Inserts the given key into this BST, as a leaf, where the path
     * to the leaf is determined by the predicate provided to the tree
     * at construction time. The parent pointer of the new node and
     * the heights in all node along the path to the root are adjusted
     * accordingly.
     * <p>
     * Note: we assume that all keys are unique. Thus, if the given
     * key is already present in the tree, nothing happens.
     * <p>
     * Returns the location where the insert occurred (i.e., the leaf
     * node containing the key), or null if the key is already present.
     */
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
    private Node insert(Node n, K key){
        Node newNode;
        boolean small = lessThan.test(key, n.data);
        boolean large = lessThan.test(n.data, key);
        if (small) {
            if (n.left == null) {
                newNode = new Node(key, null, null);
                setLeftChild(n, newNode);
            } else {
                newNode = insert(n.left, key);
            }
        }
        else if (large) {
            if (n.right == null) {
                newNode = new Node(key, null, null);
                setRightChild(n, newNode);
            }
            else {
                newNode = insert(n.right, key);
            }
            }
        else newNode = null;
        if (newNode != null && n!=null) {
                n.updateHeight();
            }
        return newNode;
        }

    public Node insert(K key) {
        if (this.root == null){
            root = new Node(key,null,null);
            this.numNodes++;
            return root;
        }
        Node newNode = insert(this.root,key);
        if (newNode != null) numNodes++;
        return newNode;
    }

        /**
         * Returns true iff the given key is in this BST.
         */
    public boolean contains(K key) {
        Node p = search(key);
        return p != null;
    }

    /**
     * TODO
     * <p>
     * Removes the key from this BST. If the key is not in the tree,
     * nothing happens.
     */
    private Node delete_min(Node n) {
        if (n.left == null) {
            if (n.parent != null) {
                setLeftChild(n.parent,n.right);
                n.parent.updateHeight();
            }
            else{
                if (n.right !=null) n.right.parent = null;
            }
            return n.right;
        } else {
            n.left = delete_min(n.left);
            n.updateHeight();
            return n;
        }
    }

    private Node remove_helper(Node n, K key) {
        if (n == null) {
            return null;
        } else if (lessThan.test(key,n.data)) { // remove in left subtree
            n.left = remove_helper(n.left, key);
            n.updateHeight();
            return n;
        } else if (lessThan.test(n.data,key)) { // remove in right subtree
            n.right = remove_helper(n.right, key);
            n.updateHeight();
            return n;
        } else { // remove this node
            if (n.left == null) {
                if (n.parent!=null && n.parent.left == n){
                    setLeftChild(n.parent,n.right);
                    n.parent.updateHeight();
                }
                else if(n.parent!=null && n.parent.right == n){
                    setRightChild(n.parent,n.right);
                    n.parent.updateHeight();
                }
                else{
                    if (n.right != null) n.right.parent = null;
                }
                return n.right;
            } else if (n.right == null) {
                if (n.parent!=null && n.parent.left == n){
                    setLeftChild(n.parent,n.left);
                }
                else if(n.parent!=null && n.parent.right == n){
                    setRightChild(n.parent,n.left);
                }
                else{
                    n.left.parent = null;
                }
                return n.left;
            } else { // two children, replace this with min of right subtree
                Node min = n.right.smallest();
                n.data = min.data;
                n.right = delete_min(n.right);
                n.updateHeight();
                return n;
            }
        }

    }
    public void remove(K key) {
        if (this.contains(key)) numNodes--;
        root = remove_helper(root,key);
    }

    /**
     * TODO
     * <p>
     * Returns a sorted list of all the keys in this tree.
     */
    private void keys(Node n,List<K> ls){
        if (n == null){
            return;
        }
        keys(n.left,ls);
        ls.add(n.data);
        keys(n.right,ls);
    }
    public List<K> keys() {
        List<K> result = new ArrayList<>();
        keys(this.root,result);
        return result;
    }

    private String toStringInorder(Node p) {
        if (p == null)
            return ".";
        String left = toStringInorder(p.left);
        if (left.length() != 0) left = left + " ";
        String right = toStringInorder(p.right);
        if (right.length() != 0) right = " " + right;
        String data = p.data.toString();
        return "(" + left + data + right + ")";
    }

    private String toStringPreorder(Node p) {
        if (p == null)
            return ".";
        String left = toStringPreorder(p.left);
        if (left.length() != 0) left = " " + left;
        String right = toStringPreorder(p.right);
        if (right.length() != 0) right = " " + right;
        String data = p.data.toString();
        return "(" + data + "[" + p.height + "]" + left + right + ")";
    }

    /**
     * Returns a textual representation of this BST.
     */
    public String toString() {
        return toStringPreorder(root);
    }
}
