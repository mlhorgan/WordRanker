import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class WorkRanker2 {

	static String word; // word input
	static char[] letters; // character array of char that make up the inputed word
	static char[] lettersSorted;// an alphabetically sorted version of "letters"
	static int rank = 1; // counter for the rank of a word

	public static void main(String[] args) {

		//word = args[0];   // take in command line argument
		
		
		Scanner sc = new Scanner(System.in); 
		  
        // String input 
        String word = sc.nextLine(); 
		
		word = word.toUpperCase();
		letters = word.toCharArray();

		lettersSorted = word.toCharArray();
		Arrays.sort(lettersSorted);
		
		int index = 0;
		int next = 1;
		
		while(!letters.equals(lettersSorted)){
			
			//test print
			//for(char c : lettersSorted){System.out.print(c);}System.out.println();
			//System.out.println(rank);
			
			if(index>=lettersSorted.length){break;}

			if(lettersSorted[index]==letters[index]){//charcters match no permutations ruled out
				index++;
				next = index + 1;
			}
			else
			{
				
				String temp = new String(lettersSorted);
				char[] permIn = temp.substring(index+1, lettersSorted.length).toCharArray();
				rank += numOfPermutations(permIn); //add number of ruled out permutaions to the rank counter	
				
				//Swap indexes making sure equal chars are not swapped
				swapLettersSorted(index,next);

				next++;
	
			}
			
		}
		
		//prints output answer
		System.out.println("The entered string has a rank of: " + rank);

		
	}
	
	
	//method for factorial of a number
 	static int factorial(int input){
 		if(input == 1){
 			return 1;
 		}else{
 			return input * factorial(input-1);
 		}
 	}//end factorial
 	
 	//method for calculating the count of each character in a Character array
 	static ArrayList charCount(char Word[]){
 		 ArrayList<Integer> ret = new ArrayList<Integer>(Word.length);
 		HashMap<Character, Integer> characterCount = new HashMap<Character, Integer>();
 		
 		for(int i = 0 ; i < Word.length ; i++){
 			
 			if(characterCount.containsKey(Word[i])){
 				characterCount.put(Word[i],characterCount.get(Word[i])+1);
 			}
 			else
 			{
 				characterCount.put(Word[i], 1);
 			}
 		}
 		for(Entry<Character, Integer> entry : characterCount.entrySet()){
 			ret.add(entry.getValue());
 		}
 		
 		return ret;
 		
 	}//end charCount
 	
 	//Calculates the number of permutations of a word
 	static int numOfPermutations(char Word[]){
 		int numerator = factorial(Word.length);
 		
 		int denominator = 1;
 		
 		ArrayList<Integer> charCounts = charCount(Word);
 		
 		for(Integer i : charCounts){
 			denominator *= factorial(i);
 		}
 		
 		return numerator/denominator;
 		
 	}//end numOfPermutations
 	
 	//Swaps the the 2 indexes of lettersSoreted char array making sure equal chars are not swapped
 	static void swapLettersSorted(int index, int index2){
 		
 		while(lettersSorted[index]==lettersSorted[index2]){
 			if(index2 >= lettersSorted.length){break;}
 			index2++;		
 		}
 		
 		char temp  = lettersSorted[index];
 		lettersSorted[index] = lettersSorted[index2];
 		lettersSorted[index2] = temp;
 		
 	}//end calculatedSwap
 	
 	
 }//end Class