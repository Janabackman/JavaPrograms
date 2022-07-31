/*Assignment 4, COP 3804, Fall 2021
 * by Sophiane Cineus, Tiago Caselli, and Jana Backman*/
package preprocessor;

import java.util.EmptyStackException;
import java.util.Stack;

public class Preprocessor {
	
	private Stack <Character> stk; 
	private String expression;
	private int length;
	
	//constructor that takes one String parameter
	Preprocessor(String expression) {
		//initiating the Stack to use for validation
		stk = new Stack<Character>();
		this.expression = expression;
		this.length = expression.length();
	}

	// Determine if parentheses are balanced
	boolean isBalance(){
	
		int index = 0;
		boolean fail = false;
	
		
			try{
				//loop through each character in the file selected
				while (index < length && !fail) {
					// Read to the end of the list
					char ch =expression.charAt(index);
			             switch (ch) {
			             //pushing into the stack open delimiters
			               case Constants.LEFT_BRAK:stk.push(ch);
			                   break;
			               case Constants.LEFT_BRAC:stk.push(ch);
			                   break;
			               case Constants.LEFT_PAR:stk.push(ch);
			                   break;
			               case Constants.ASTERISC:
			            	   //try - catch if the index is out of bounds
			            	   try{
			            		   //looking for the char before the '*' to see if it's part of a comments delimiter
			            		   char t = expression.charAt(index - 1);
				            	   //if the char before the '*' is '/' then push both to the stack
				                   if (t == Constants.F_SLASH) {
				                       stk.push(ch);
				                       stk.push(t); 
				                       }//end if
			            	   }//end try
			            	   catch (StringIndexOutOfBoundsException e){
									System.out.println(e.toString());
								}//end catch
			                   break;
			                   
			                   
			            //removing the delimiters from the stack
			           case Constants.RIGHT_BRAK:if(stk.peek() == Constants.LEFT_BRAK) stk.pop();
	                       break;
	                   case Constants.RIGHT_BRAC:if(stk.peek() == Constants.LEFT_BRAC) stk.pop();
	                       break;
	                   case Constants.RIGHT_PAR:if(stk.peek() == Constants.LEFT_PAR) stk.pop(); 
	                       break;
	                   case Constants.F_SLASH:
	        
	                	   try{
		            		   //looking for the char before the '/' to see if it's part of a comments delimiter
		            		   char t = expression.charAt(index - 1);
		            		   	   //if the char before the '/' is '*' then remove both from stack
			            		   if (t == Constants.ASTERISC) {
			           
			            			   if(stk.peek() == Constants.F_SLASH){	  
			            				   stk.pop();
			            				   stk.pop(); 
			            			   }//end if
			            		   }//end if
		            	   	}//end try
		            	   catch (StringIndexOutOfBoundsException e){
								System.out.println(e.toString());
							}//end catch
							break;
 

			               default:break;
			          }//end switch(ch)

					index++;
					}//end of while
				}//end of try
			catch (EmptyStackException e){
				System.out.println(e.toString());
				fail = true;
			}
		return (stk.empty() && !fail);
		} // end isBalance()
	
	
}//end class Prepocessor
