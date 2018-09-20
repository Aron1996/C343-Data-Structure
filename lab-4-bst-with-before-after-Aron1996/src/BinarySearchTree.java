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

        protected boolean updateHeight() {

            if(height!= Math.max(get_height(left), get_height(right))+1){
                height = Math.max(get_height(left), get_height(right))+1;
                return true;
            }else{
                return false;
            }
        }

        /**
         * TODO
         * <p>
         * Returns the location of the node containing the inorder predecessor
         * of this node.
         */
        public Node getBefore() {

            if(left != null){
                return left.largest();
            }else{
                return smallerAncestor(this);
            }
        }

        /**
         * TODO
         * <p>
         * Returns the location of the node containing the inorder successor
         * of this node.
         */
        public Node getAfter() {

            if(right != null){
                return right.smallest();
            }else{
                return greaterAncestor(this);
            }
        }

        /**
         * TODO
         * <p>
         * This method should return the closest ancestor of node q
         * whose key is less than q's key. It is not necessary to
         * to perform key comparisons to implement this method.
         */
        private Node smallerAncestor(Node q) {
            Node ancestor = q.parent;
            if(q == null) {
                return null;
            }
            if(ancestor == null){
                return null;
            }else if(ancestor.right == q){
                return ancestor;
            }else{
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
            Node ancestor = q.parent;
            if(q == null) {
                return null;
            }
            if(ancestor == null){
                return null;
            }else if(ancestor.left == q){
                return ancestor;
            }else{
                return greaterAncestor(ancestor);
            }
        }

        /*
         * TODO
         * This method should return the node in the subtree rooted at 'this'
         * that has the smallest key.
         */

        public Node smallest_Helper(Node n){
            if(n == null){
                return null;
            }else if(n.right == null){
                return n;
            }else{
                return smallest_Helper(n.right);
            }
        }


        protected Node smallest() {

            return smallest_Helper(this);
        }

        public Node largest_Helper(Node n){
            if(n == null){
                return null;
            }else if(n.right == null){
                return n;
            }else{
                return largest_Helper(n.right);
            }
        }

        /*
         * TODO
         * This method should return the node in the subtree rooted at 'this'
         * that has the largest key.
         */
        private Node largest() {
            return largest_Helper(this);
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
    public Node search_Helper(Node n, K key){
        if(n == null){
            return null;
        }else{
            if(lessThan.test(key, n.data)){
                return search_Helper(n.left, key);
            }else if(lessThan.test(n.data, key)){
                return search_Helper(n.right, key);
            }else{
                return n;
            }
        }

    }
    public Node search(K key) {

        return search_Helper(this.root, key);
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
        if (n == null){
            return -1;
        }else{
            return n.height;
        }
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
    public Node insert_Helper(Node n, K key) {
        Node newNode = new Node(key,null,null);
        if (n == null) {
            return newNode;
        } else if (lessThan.test(key, n.data)) {
            if(n.left == null){
                newNode.parent = n;
                n.left = newNode;
            }else{
                newNode = insert_Helper(n.left, key);
            }n.updateHeight();
        } else if (lessThan.test(n.data, key)) {
            if (n.right == null) {
                newNode.parent = n;
                n.right = newNode;
            } else {
                newNode = insert_Helper(n.right, key);
            }n.updateHeight();
        }else{
            return null;
        }
        return newNode;
    }

    public Node insert(K key) {
        if(root == null){
            root = new Node(key, null, null);
            this.numNodes++;
            return root;
        }
        if(insert_Helper(root, key) != null){
            numNodes++;
        }
        return insert_Helper(root,key);
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
            return n.right;
        } else {
            n.left = delete_min(n.left);
            return n;
        }
    }
    private Node get_min(Node n) {
        if (n.left == null) {
            return n;
        }
        else {
            return get_min(n.left);
        }
    }
    public Node remove_Helper(Node n, K key) {
        if (n == null) {
            return null;
        } else if (lessThan.test(key,n.data)) { // remove in left subtree
            n.left = remove_Helper(n.left, key);
            return n;
        } else if (lessThan.test(n.data,key)) { // remove in right subtree
            n.right = remove_Helper(n.right, key);
            return n;
        } else { // remove this node
            if (n.left == null) {
                return n.right;
            } else if (n.right == null) {
                return n.left;
            } else { // two children, replace this with min of right subtree
                Node min = get_min(n.right);
                n.data = min.data;
                n.right = delete_min(n.right);
                return n;
            }
        }
    }
        /*if(n == null) {
            return null;
        } else if(lessThan.test(n.data, key)){
                n.right = remove_Helper(n.right, key);
                n.updateHeight();
                return n;
        } else if(lessThan.test(key, n.data)){
            n.left = remove_Helper(n.left, key);
            n.updateHeight();
            return n;
        }else {
            if (n.left == null) {
                if (n.right != null) {
                    n.parent.left = n.right;
                    n.right.parent = n.parent;
                    return n.right;
                }else{
                    n = null;
                    return null;
                }
            } else if (n.right == null) {
                    n.left.parent = n.parent;
                    n.parent.right = n.left;
                    return n.left;
            }
            else {
                Node min_children = n.right.smallest();
                n.data = min_children.data;
                n.right = remove_Helper(n.right, min_children.data);
                n.updateHeight();
                return n;
            }
        }*/



    public void remove(K key) {
        if(contains(key)){
            root = remove_Helper(root, key);
            numNodes--;
        }

    }

    /**
     * TODO
     * <p>
     * Returns a sorted list of all the keys in this tree.
     */

    public void keys_Helper(Node n, List<K> ls){
        if(n == null){
            return;
        }
        keys_Helper(n.left, ls);
        ls.add(n.data);
        keys_Helper(n.right,ls);
    }
    public List<K> keys(){
        List<K> array = new ArrayList<>();
        keys_Helper(root, array);
        return array;
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
