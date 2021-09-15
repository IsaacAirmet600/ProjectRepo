//Isaac Airmet Assn9
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

// SpellChecker class
public class SpellChecker {
    public static void main(String[] args) {
        // Step 1: Demonstrate tree capabilities
        testTree();

        // Step 2: Read the dictionary and report the tree statistics
        BinarySearchTree<String> dictionary = readDictionary();
        System.out.println("--- Tree Stats for Dictionary Tree ---\n");
        reportTreeStats(dictionary);

        // Step 3: Perform spell checking
        checkLetter(dictionary);
    }
    // Method to read through the letter
    public static void checkLetter(BinarySearchTree<String> tree){
        // Array list to hold the final words
        ArrayList<String> letWords = new ArrayList<>();
        File file = new File("letter.txt");
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                // Get each line, split it by each word, then add words to a new array
                String line = input.nextLine().strip();
                String[] words = line.split(" ");
                ArrayList<String> wordArray = new ArrayList<>();
                for (String word: words){
                    if (!word.strip().equals("")){
                        wordArray.add(word);
                    }
                }
                // Send array to have the words trimmed
                cleanWords(wordArray);
                // Add all words in the line to the final array
                letWords.addAll(wordArray);
            }
        }
        // Catch any errors
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the file: " + ex);
        }
        // Send the words of the letter and the BST to start spell checking
        verifyWords(letWords, tree);
    }
    // Method to trim each word
    public static void cleanWords(ArrayList<String> words){
        // For each word, get the word and it's position
        for (int i = 0; i < words.size(); i ++){
            String word = words.get(i);
            int wordIndex = words.indexOf(word);
            // Remove the word, remove punctuation, then re-add as a lowercase word
            words.remove(word);
            word = word.replaceAll("[,:.!?()]", "");
            word = word.toLowerCase();
            words.add(wordIndex, word);
        }
    }
    // Method to spell check the words
    public static void verifyWords(ArrayList<String> words, BinarySearchTree<String> tree){
        System.out.println("\n--- Misspelled words: ---\n");
        // If the word isn't in the BST, print it out
        for (String word: words){
            if (!tree.search(word)){
                System.out.println(word + "\n");
            }
        }
    }
    /**
     * This method is used to help develop the BST, also for the grader to
     * evaluate if the BST is performing correctly.
     */
    public static void testTree() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        //
        // Add a bunch of values to the tree
        tree.insert("Olga");
        tree.insert("Tomeka");
        tree.insert("Benjamin");
        tree.insert("Ulysses");
        tree.insert("Tanesha");
        tree.insert("Judie");
        tree.insert("Tisa");
        tree.insert("Santiago");
        tree.insert("Chia");
        tree.insert("Arden");
        //
        // Make sure it displays in sorted order
        tree.display("--- Initial Tree State ---");
        reportTreeStats(tree);
        System.out.println();
        //
        // Try to add a duplicate
        if (tree.insert("Tomeka")) {
            System.out.println("oops, shouldn't have returned true from the insert");
        }
        tree.display("--- After Adding Duplicate ---");
        reportTreeStats(tree);
        System.out.println();
        //
        // Remove some existing values from the tree
        tree.remove("Olga");    // Root node
        tree.remove("Arden");   // a leaf node
        tree.display("--- Removing Existing Values ---");
        reportTreeStats(tree);
        System.out.println();
        //
        // Remove a value that was never in the tree, hope it doesn't crash!
        tree.remove("Karl");
        tree.display("--- Removing A Non-Existent Value ---");
        reportTreeStats(tree);
        System.out.println();
    }
    /**
     * This method is used to report on some stats about the BST
     */
    public static void reportTreeStats(BinarySearchTree<String> tree) {
        System.out.println("-- Tree Stats --");
        System.out.printf("Total Nodes : %d\n", tree.numberNodes());
        System.out.printf("Leaf Nodes  : %d\n", tree.numberLeafNodes());
        System.out.printf("Tree Height : %d\n", tree.height());
    }
    /**
     * This method reads the dictionary and constructs the BST to be
     * used for the spell checking.
     */
    public static BinarySearchTree<String> readDictionary() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        ArrayList<String> wordList = new ArrayList<>();
        File file = new File("Dictionary.txt");
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String word = input.nextLine().strip();
                wordList.add(word);
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the file: " + ex);
        }
        java.util.Collections.shuffle(wordList, new java.util.Random(System.currentTimeMillis()));
        for (String word: wordList){
            tree.insert(word);
        }
        return tree;
    }
}
