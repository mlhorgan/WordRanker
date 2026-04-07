import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class WorkRanker2 {

	static String word; // word input
	static char[] letters; // character array of char that make up the inputed word
	static char[] lettersSorted;// an alphabetically sorted version of "letters"
	static long rank = 1; // counter for the rank of a word

	public static void main(String[] args) {
		
		// take in command line argument
		//word = args[0];   
				
		Scanner sc = new Scanner(System.in); 
		  
        // String input 
        String word = sc.nextLine(); 
		
		word = word.toUpperCase();
		letters = word.toCharArray();

		lettersSorted = word.toCharArray();
		Arrays.sort(lettersSorted);
		
		//Calculates the rank of each letter in the word. Example in word 'abcb' 'b' would be ranked 2
		HashMap<Character, Integer> letterRank = new HashMap<Character, Integer>();
		int characterRank = 1;
		for(int i = 0; i < lettersSorted.length ; i++){			
			if(letterRank.containsKey(lettersSorted[i])){
				continue;
			}
			else{
				letterRank.put(lettersSorted[i],characterRank);
				characterRank++;
			}
		}
		
		double answerHelper[] = new double[letters.length]; // Stores calculations that will be used to compute final word rank
		
		
		for(int i = 0; i < letters.length; i++){
			double num = 0;
			double dem = 1;
			
			//Calculates numerator which is then number of letters that have a higher rank of the current letter to the right of that letter in the word
			int currLetterRank = letterRank.get(letters[i]);
			for(int j = i+1; j < letters.length; j++){
				if(currLetterRank > letterRank.get(letters[j])){
					num++;
				}
			}
			
			//Calculate denominator which is the letter counts Factorial of the sub word at and including the current index
			String subWord = word.substring(i);
			Map<Character, Integer> wordCharCount = new HashMap<>();
			for(char c : subWord.toCharArray()){
				wordCharCount.put(c, wordCharCount.getOrDefault(c, 0)+1);
			}
			for (Map.Entry<Character, Integer> entry : wordCharCount.entrySet()) {
				dem*=factorial(entry.getValue());
			}
			
			answerHelper[i] = num/dem;
			
		}
		
		//Calculates the final rank of the word using permutation formula by multiplying letters smaller than x index / letter counts Factorial of the sub word at and including the current index * the factorial of word length - index+1
		// and adding the results for each index x
		for(int x = 0; x < word.length(); x++){
			//System.out.println(answerHelper[x] * factorial(word.length()-(x+1)));
			rank+= answerHelper[x] * factorial(word.length()-(x+1));
		}
		
		//prints output answer
		System.out.println("The entered string has a rank of: " + rank);

		
	}
	
	
	//method for factorial of a number
 	static long factorial(int input){
 		if((input == 1) || (input == 0)){
 			return 1;
 		}else{
 			return input * factorial(input-1);
 		}
 	}//end factorial
 	
 }//end Class
