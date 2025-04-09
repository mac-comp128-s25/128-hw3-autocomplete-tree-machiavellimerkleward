package autoComplete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.w3c.dom.Node;

/**
 * A prefix tree used for autocompletion. The root of the tree just stores links to child nodes (up to 26, one per letter).
 * Each child node represents a letter. A path from a root's child node down to a node where isWord is true represents the sequence
 * of characters in a word.
 * Written by Macalester MSCS, with the add, contains, getWordsForPrefix, and recursivePrefixHelper methods written by Machiavelli Merkle-Ward.
 */
public class PrefixTree {
    private TreeNode root; 

    // Number of words contained in the tree
    private int size;

    /*
     * Constructor for a new PrefixTree
     */
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
            if(parent.children.containsKey(word.charAt(i))){
                if(i == word.length()-1){ 
                    parent.children.get(word.charAt(i)).isWord = true;
                    return;
                }
                parent = parent.children.get(word.charAt(i));
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
        if(root.children.get(prefix.charAt(0)) == null){
            return words;
        }
        TreeNode parent = root.children.get(prefix.charAt(0)); 
        
        
        for(int i = 1; i<prefix.length(); i++){
            if(parent.children.containsKey(prefix.charAt(i))){
                parent = parent.children.get(prefix.charAt(i));
                }
            else{
                System.out.println((prefix.charAt(i)));
                return new ArrayList<>();
            }
        }

        if(parent.isWord){
            words.add(prefix);
        }
        for(TreeNode child : parent.children.values()){
            recursivePrefixHelper(child, words, prefix);
        }

        return words;
    }

    /**
     * A recursive method to traverse the tree and find all words starting with a given prefix, adding them to a given list of strings
     * @param node The starting node to be passed, a child of the last node of the prefix 
     * @param stringList The list of valid strings containing this prefix for the method to add too
     * @param prefix The initial prefix to be searched
     */
    public void recursivePrefixHelper(TreeNode node, ArrayList<String> stringList, String prefix){
        prefix = prefix + node.letter;
        if(node.isWord){
            stringList.add(prefix);
        }
        for(TreeNode child : node.children.values()){
                recursivePrefixHelper(child, stringList, prefix);
        }
    }

    /**
     * @return the number of words in the tree
     */
    public int size(){
        return size;
    }
    
}
