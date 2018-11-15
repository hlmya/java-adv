package pr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/*
 * This class count words in files
 */
public class WordCountsInFiles {

	public static void main(String[] args) throws FileNotFoundException {
		// Create a new set (no duplicate)
		Set<String> words = new HashSet<String>();
//		words = Collections.synchronizedSet(words); // way 3 to synchronized ~ same syn block
		
		// starting time
        long startTime = System.currentTimeMillis();
        
        String[] fileNames = {"bb2","bb3"};
        for (String name : fileNames) {
            readFile(name, words);
        }
        // ending time
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        System.out.println("There are " + words.size()
                           + " unique words.");
        System.out.println("Took " + elapsed + " ms.");
	}
	
	/*
	 * The program essentially becomes sequential; 
	 * no two threads are able to read their files at the same time.
	 * But use synchronized is better than original program
	 */
	// way 1 to synchronized
//	public synchronized static void readFile(String fileName, Set<String> words)
	public static void readFile(String fileName, Set<String> words)
            throws FileNotFoundException {
        System.out.println("Starting to read " + fileName + " ...");
        
        //Opend file 
        Scanner input = new Scanner(new File(fileName + ".txt"));
        
        //Read file
        while (input.hasNext()) {
            String word = input.next();
//            words.add(word);
            //way 2 to synchronized
            synchronized (words) {
                words.add(word);
            }
        }
        System.out.println("Done reading " + fileName + ".");
    }

}
