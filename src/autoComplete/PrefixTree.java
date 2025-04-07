package autoComplete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.w3c.dom.Node;

/**
 * A prefix tree used for autocompletion. The root of the tree just stores links to child nodes (up to 26, one per letter).
 * Each child node represents a letter. A path from a root's child node down to a node where isWord is true represents the sequence
 * of characters in a word.
 */
public class PrefixTree {
    private TreeNode root; 

    // Number of words contained in the tree
    private int size;

    public PrefixTree(){
        root = new TreeNode();
    }

    /**
     * Adds the word to the tree where each letter in sequence is added as a node
     * If the word, is already in the tree, then this has no effect.
     * @param word
     */
    public void add(String word){
        TreeNode parent = root;
        for(int i = 0; i<word.length(); i++){
            if(parent.children.containsKey(word.charAt(i))){ //if the parent has the character 
                if(i == word.length()-1){ //if it's the last letter in the word
                    parent.children.get(word.charAt(i)).isWord = true; //note that the character is the end of a word
                    return;
                }
                parent = parent.children.get(word.charAt(i)); //sets the current to be the node for character i
            }
            else{
                TreeNode charNode = new TreeNode();
                charNode.letter = word.charAt(i);
                parent.children.put(word.charAt(i), charNode);
                if(i == word.length()-1){
                    charNode.isWord = true;
                }
                parent = charNode;
            }
        }
        parent.isWord = true;
        size++;
        
        /* TODO
         * create nodes for each character in the tree (if they don't already exist)
         * link together the nodes at the proper point in the tree and store them in the children maps
         * when the last character of the word is reached, mark it as the end of the word
         * if the word is not in the tree, increment size
         * if the word already exists, don't change anything
         * use the charAt method
         */        
    }

    /**
     * Checks whether the word has been added to the tree
     * @param word
     * @return true if contained in the tree.
     */
    public boolean contains(String word){
        TreeNode parent = root;
        for(int i = 0; i<word.length(); i++){
            if(parent.children.containsKey(word.charAt(i))){
                if(i == word.length()-1){
                    if(parent.children.get(word.charAt(i)).isWord){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                parent = parent.children.get(word.charAt(i));
            }
            else {
                return false;
            }
        }
        /* TODO
         * return true if the word is contained in the tree
         * start at root and iterate down through the characters in the tree
         * if each character is found in the correct order, it passes
         */
        return false;
    }

    /**
     * Finds the words in the tree that start with prefix (including prefix if it is a word itself).
     * The order of the list can be arbitrary.
     * @param prefix
     * @return list of words with prefix
     */
    public ArrayList<String> getWordsForPrefix(String prefix){
        ArrayList<String> words = new ArrayList<>();
        StringBuilder sb = new StringBuilder(); //this stringbuilder is the wrong direction, it'll build nonsense strings
        recursivePrefixHelper(root, sb);

        //use the recursive method to mark the different end nodes?? idk how to keep traversing
        //make copies of the stringbuilders
        //don't use a stringbuilder
        //pass a list of strings and add to the list
        /*
         * pre-order traverse the 
         * pass the parent into the 
         */


        return null;
    }

    public void recursivePrefixHelper(TreeNode node, StringBuilder sb){
        if (node.isWord){
            sb.append(node.toString() + " ");
            return;
        }
        else{
            sb.append(node.toString() + " ");
            for(TreeNode child : node.children.values()){ //for each child node
                recursivePrefixHelper(child, sb);
            }
        }
    }

    /**
     * @return the number of words in the tree
     */
    public int size(){
        return size;
    }
    
}
