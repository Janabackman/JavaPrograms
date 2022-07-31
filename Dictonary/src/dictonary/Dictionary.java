package dictonary;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Dictionary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean done = false;
		
		WordList wl = new WordList();//creating WordList instance and initiating
		WordList wlDeleted = new WordList();//WordList for the deleted items
		WordMeaning wm;//wordMeaning instance to use throughout the code
		String s;
		String word;//use word to store words from and to the dictionary
		
		//populating the dictionary before user starts the program although the user can add words as well
		WordMeaning wmp = new WordMeaning("Cup", "A container from which we drink");
		WordMeaning wmc = new WordMeaning("Library", "A collection of books");
		WordMeaning wmr = new WordMeaning("School", "A place for learning");
		//adding the words to the linkedList
		wl.add(wmp);
		wl.add(wmc);
		wl.add(wmr);
		
		//loop until user exits
		while(!done) {
			String menu = "Enter Option\n1. Add new word "                   
                    + "\n2. Add meaning to existing word \n3. Search for word \n4. Delete word \n5. View dictionary \n"
                    + "6. View deleted words \n7. Exit ";
			
			String str = GetData.getWord(menu);
			//try in case it throws a NumberFormatException
			try {
				int i = Integer.parseInt(str);
				//Handling the user menu
				switch(i){
				//case 1 - add new word	
				case 1:
						word = GetData.getWord("Type the word to add to dictionary");//ask user to enter the word
						
						if (!wl.searchWord(word)) {	//if word not in dictionary already
							
							String meaning = GetData.getWord("Type meaning");//ask user to enter meaning
							wm = new WordMeaning(word, meaning);
							wl.add(wm);//adding the new word
							
							//feedback user
							displayShort("The word has been added succesfully", "SUCCESS",
		    	                    JOptionPane.INFORMATION_MESSAGE);		
						}//end if
						
						else {//if user enters an already existing word
							wm = wl.getWordInUse().getNode();
							String menu2 = "The word "+wm.getWord()+ " already in dictionary\n\n"+
									wm.getWord()+ " - "+wm.getMeaning()+"\n\nWould you like to add a meaning to the word?"
									+"\n1. Yes \n2. No";
							int choice = GetData.getInt(menu2);
							//offer user to enter additional meaning
							switch(choice){
							
								case 1: 
									
									s = GetData.getWord("Type additional meaning");
									if (s != null) {
										wm.addMeaning(s);
										displayShort("The meaning has been added succesfully to the word "+wm.getWord(), "SUCCESS",
				    	                    JOptionPane.INFORMATION_MESSAGE);
									}
									break;
								case 2: break;
							}
						}
						break;
				//look for a word and add additional meaning 	
				case 2:
						word = GetData.getWord("Enter the word you would like to add meaning to");
						
						
						if (wl.searchWord(word)) {	
							
							wm= wl.getWordInUse().getNode();
							s = GetData.getWord(wl.displyWord()+"\nType additional meaning");
							if(s !=null) {
								wm.addMeaning(s);
								
								displayShort("The meaning has been added succesfully to the word "+wm.getWord(), "SUCCESS",
			    	                    JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else {
							displayShort("The word "+word+" was not found", "ERROR",
		    	                    JOptionPane.ERROR_MESSAGE);
						}
						
						break;
					//search for a word and display word + meaning
					case 3:
						word = GetData.getWord("Enter the word you like to search");
						 
						
						if (wl.searchWord(word)) {	
							
							wm= wl.getWordInUse().getNode();	
							displayShort( wl.displyWord(), wm.getWord(),
		    	                    JOptionPane.INFORMATION_MESSAGE);
							
						}
						else {
							displayShort("The word "+ word +" was not found", "ERROR",
		    	                    JOptionPane.ERROR_MESSAGE);
						}
						break;
					//delete a word and add it to the deleted list
					case 4:
						word = GetData.getWord("Enter the word you like to Delete");
							if (wl.searchWord(word)) {	
								
								wm= wl.getWordInUse().getNode();	
								
								WordMeaningNode wmn = new WordMeaningNode(wm);
								wmn = wl.deleteNode(wm.getWord());
								wlDeleted.apend(wmn.getNode());	
								displayShort("The word "+wm.getWord()+" was deleted", "SUCCESS",
			    	                    JOptionPane.INFORMATION_MESSAGE);
							}
						else {
							displayShort("The word "+ word +" was not found", "ERROR",
		    	                    JOptionPane.ERROR_MESSAGE);
						}
						break;
					//display dictionary
					case 5:	displayScroll(wl.toString(), "Dictionary");
						break;
					//display deleted words
					case 6:	displayScroll(wlDeleted.toStringWord(), "Deleted Words");
						break;
					case 7: 
						done = true;
						
					break;
				    default:
				    	displayShort("Not a menu option", "ERROR",
	    	                    JOptionPane.ERROR_MESSAGE);
	               }//end switch(i);
				}
			
			//deals with exceptions if and when they occur.  
			catch (NumberFormatException e) 
            {

         	   if (str == null)
         		   System.exit(0);
         	   else
         		   displayShort("Not a menu option", "ERROR",
      	                    JOptionPane.ERROR_MESSAGE); 	   
         		   
            } //end catch(NumberFormatException e) 
            catch (NullPointerException e)

            {
         	   System.exit(0);
			
            }//end catch(NullPointerException e) 
		}//end while(!done)
		
	
	}
		//displaying short messages to use
	   static void displayShort(String s, String heading,int MESSAGE_TYPE) {
		   
				JOptionPane.showMessageDialog(null, s, heading,
						MESSAGE_TYPE);
		   }//end displayShort();
	   
	   //displaying long messages to user with a scrolling page
	   static void displayScroll(String s, String heading)
	   {
	       JTextArea text = new JTextArea(s, 20, 40);
	       JScrollPane pane = new  JScrollPane(text);
	       JOptionPane.showMessageDialog(null, pane, heading, JOptionPane.INFORMATION_MESSAGE);
	   }//end display();
}