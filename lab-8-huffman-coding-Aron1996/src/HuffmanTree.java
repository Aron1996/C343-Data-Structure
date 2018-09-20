import java.util.Comparator;
import java.util.*;

/**
 * TODO: Complete the implementation of this class.
 * 
 * A HuffmanTree represents a variable-length code such that the shorter the
 * bit pattern associated with a character, the more frequently that character
 * appears in the text to be encoded.
 */

public class HuffmanTree {
  
  class Node {
    protected char key;
    protected int priority;
    protected Node left, right;
    
    public Node(int priority, char key) {
      this(priority, key, null, null);
    }
    
    public Node(int priority, Node left, Node right) {
      this(priority, '\0', left, right);
    }
    
    public Node(int priority, char key, Node left, Node right) {
      this.key = key;
      this.priority = priority;
      this.left = left;
      this.right = right;
    }
    
    public boolean isLeaf() {
      return left == null && right == null;
    }
    
    public String toString() {
      return String.format("%s:%f", key, priority);
    }
  }
  
  protected Node root;
  
  /**
   * TODO
   * 
   * Creates a HuffmanTree from the given frequencies of letters in the
   * alphabet using the algorithm described in lecture.
       */
      public HuffmanTree(FrequencyTable charFreqs) {
          Comparator<Node> comparator = (x, y) -> {
              /**
               *  TODO: x and y are Nodes
               *  x comes before y if x's count is less than y's count
               */
              return Integer.compare(x.priority, y.priority);
//            Comparator.comparingInt(x -> x.priority);
          };
          PriorityQueue<Node> forest = new Heap<Node>(comparator);

          /**
           * TODO: Complete the implementation of Huffman's Algorithm.
           * Start by populating forest with leaves.
           */
          for (Character x: charFreqs.keySet()){
              forest.insert(new Node(charFreqs.get(x),x,null,null));
          }
          while (forest.size() > 1) {
              Node left = forest.delete();
              Node right = forest.delete();
              Node head = new Node(left.priority + right.priority, left, right);
              forest.insert(head);
          }
          root = forest.peek();
      }


    /**
       * TODO
       *
       * Returns the character associated with the prefix of bits.
       *
       * @throws DecodeException if bits does not match a character in the tree.
     */

        public char decodeChar(String bits) {
            return decode(bits, 0);
        }

        public char decode(String bits, int index) {
            Node p = this.root;
            while (index < bits.length()) {
                if (p.isLeaf()) {
                    return p.key;
                }
                char temp = bits.charAt(index);
                if (temp == '0') {
                    p = p.left;
                }
                else if (temp == '1') {
                    p = p.right;
                }
                else {
                    throw new DecodeException(bits);
                }
                index += 1;
            }
            if (p.isLeaf()) {
                return p.key;
            }
            else {throw new DecodeException(bits);}
        }


    /**
       * TODO
       *
       * Returns the bit string associated with the given character. Must
       * search the tree for a leaf containing the character. Every left
       * turn corresponds to a 0 in the code. Every right turn corresponds
       * to a 1. This function is used by CodeBook to populate the map.
       *
       * @throws EncodeException if the character does not appear in the tree.
           */
        public String lookup(char ch) {
            List<String> ls = new LinkedList<>();
           find(this.root, ch, "", ls);
           if (ls.isEmpty()) {
                throw new EncodeException(ch);
            }
            return ls.get(0);
        }
        private void find(Node p, char ch, String result, List<String> ls) {
            if (p != null) {
                if (p.key == ch) {
                    ls.add(result);
                }
                else {
                    find(p.left, ch, result + "0", ls);
                    find(p.right, ch, result + "1", ls);
                }
            }
        }
    }



