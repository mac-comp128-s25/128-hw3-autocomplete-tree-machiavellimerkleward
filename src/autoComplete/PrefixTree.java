package autoComplete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

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
        for(int i = 0; i<word.length()-1; i++){
            if(parent.children.containsKey(word.charAt(i))){ // not accounting for 
                if(i == word.length()-1){
                    parent.children.get(word.charAt(i)).isWord = true;
                }
                parent = root.children.get(word.charAt(i)); //sets the current to be the node for character i
                if(i == word.length()-1){
                    
                }
                continue;
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
        //make a recursive function!!
        //base case: the letter is the final letter -- add it and unwind the recursion
        // for each character, check if it's in that node's map
            //if yes, add(char)
            //if not, make a new node and add it as the parent's child, then add it to the parent's map of children, then add(next char)
            //TODO NOT RECURSIVE
        //TODO: complete me
        /*
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
        //TODO: complete me
        /*
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
        //TODO: complete me
        /*
         * 
         */
        return null;
    }

    /**
     * @return the number of words in the tree
     */
    public int size(){
        return size;
    }
    
}
