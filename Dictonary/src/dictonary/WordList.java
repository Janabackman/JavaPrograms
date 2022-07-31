package dictonary;

public class WordList {
	
	WordMeaningNode list;//would be use for the first node in the link
	WordMeaningNode wordInUse;//Set to the WordMeaningNode being searched
	WordMeaningNode current;
	WordMeaningNode back;
	WordMeaningNode rear;//set as placeholder (for the append method) to go through the linked list
	
	WordList(){
		list = null;	
	}//end constructor
	
	//get the word in use after being searched
	WordMeaningNode getWordInUse() {
		return wordInUse;
	}// end getWordInUse
	
	// Inserting in the list
	void add(WordMeaning wm) 
	{
		//create an instance of WordMeningNode and initiate it with the WorkMeaning set as parameter
		WordMeaningNode temp = new WordMeaningNode(wm);  
		
		//if the list is empty (first link is null)
	 	if (list == null)
	 		//temp would be link - the first link
	 		list = temp;
	 	else// if link is not null
	 	{
	 		current = list;//creating one WordMeaningNode assigning to list. This would be used for iterating through the linked list  
	 		back = null; //creating WordMeaningNode to store the previous WordMeaning in the link
	 	
	 		boolean found = false; 
			
	 	 	while(current != null && !found ){//Iterate through the likedlist
	 	 		
	 	 	 	if(temp.wm.getWord().compareTo(current.wm.getWord()) < 0) //compares to see if lexicographically lower
					found = true;																
				else
				{
					back = current;//set the previous word to current
					current = current.next;	//set current to next in link
					
				}//end else
	 	 	}//end while
			
	 	 	//set the next link in temp to current		
			temp.next = current;
			//if back was never initiated, it would be the first in the link so list (first link) is set to temp
			if (back == null)
				list = temp;
			else//else back.next set to temp so it is placed between back and current
				back.next = temp;
	 	}//end else
	}//end add()
	
	
	//a method that searches for a word within the dictionary
	//returns true if the word was found
	boolean searchWord(String key) {
		
		boolean found = false;
		current = list;
		//iterates through the linked list
		while (current != null && !found)
		{
			if (current.getNode().getWord().equalsIgnoreCase(key)) {
				found = true;
				wordInUse = current;//set word in use to found word to be user by other method, classes
			}//end if
			current = current.next;
			
		}//end while
		return found;
	}//end searchWord()
	
	//method that displays one word using the word found
	//returns string with word and meaning
	String displyWord() {
		String s = wordInUse.getNode().getWord()+" - " +wordInUse.getNode().getMeaning();
		return s;
	}//end displayWord()
	
	//appending into the linked list
	//used to append deleted words
	void apend(WordMeaning wm)
	{
		WordMeaningNode temp = new WordMeaningNode(wm); 
		
		//if list is null = empty likedList, set list to temp and rear to list
		if (list == null) {
			list = temp;
			rear = list;
			}//end if
		else
		{		
			rear.next = temp;
			rear = temp;
		}//end else
	}//end apend()
	
	//deletes one node from the liked List
	 WordMeaningNode deleteNode(String key) {
		 
		 current = list;
		 back = list;
		 
		 //iterate through the list until it finds the word the user wants to delete
		 while (!current.wm.getWord().equalsIgnoreCase(key)) {
			 
			 //if gets to end return null
			 if (current.next == null) 		 
				 return null;
			 
			 //set back - the previous link to current and current to the next one 
			 else {
				 back = current;
				 current = current.next;
			 }//end else
		 }//end while 
		 
		 //if current is the fist link, set list to list next and deletes the first link
		 if (current == list) 
			 list = list.next;
		 
		 //else, set back.next (which is current) to current.next and deletes current
		 else 
			 back.next = current.next;
		 
		 //returns the deleted link
		 return current;
	 }// end deleteNode()
		
	
	//prints liked list
	//returns a string with all words and their meaning in dictionary 
	public String toString() {
		
		String result = "";
		current = list;
	
	
		while (current != null)
		{
			result += current.getNode().getWord()+ " - "+ current.getNode().getMeaning()+"\n";
			current = current.next;	
		}//end while
		return result;		
	}//end toString()
	
	//prints word without meaning
	//returns a string with list of words
	 public String toStringWord(){
		String result = "";
		current = list;

		while (current != null)
		{
			result += current.getNode().getWord()+"\n";
			current = current.next;	
		}//end while 
		return result;
	}//end toStringWord()
}//end class WordList
