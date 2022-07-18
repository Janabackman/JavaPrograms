/*Assignment 4, COP 3804, Fall 2021
 * by Sophiane Cineus, Tiago Caselli, and Jana Backman*/
package preprocessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class MyPreprocessor {

	public static void main(String[] args) throws IOException {
	
		//creates an instance of BasicFile that allows the user to choose a file
		BasicFile f = new BasicFile();
		//new BufferedReader to read the file
		BufferedReader br = null;
		String str = "";
   		try {
	   		br = new BufferedReader(new FileReader(f.getSelectedFile()));//bufferReader to read content from file
    
            String line;//to store each line from the file    
            String s = "";//to put all the lines of the file into one string
           
            //loop to go through each line in file
            while ((line = br.readLine()) != null)
            {
            	s += line+"\n";
  
            }
            int index =1;
            //Splitting the file so it analyzes each statement separately
            String part [] = s.split("---");
            for (String i: part) {
    			//creating an instance of Preprocessor object
	            Preprocessor p = new Preprocessor(i);
				if (p.isBalance())
					str +=("Statement "+index +":\n"+i+"\nThis statement is VALID\n");
				else
					str+=("Statement "+index +":\n"+i+"\nThis statement is INVALID\n");
				str+=("----------------------------------------------------------------------------------\n");
				index ++;
            }
   		}
   		
   		catch(IOException e) {
   			System.out.println(e.toString());
   			
   			}//end catch
   		//finally clause to close all opened buffered reader whether or not an exception was thrown.
   		finally {
   			br.close();
   			}//end finally
   		//Feedback the user when allowing them to know if the statements are valid
   		display(str, "Code Validation");
		
   		
	}//end main
	
		static void display(String s, String heading){
	       JTextArea text = new JTextArea(s, 20, 30);
	       JScrollPane pane = new  JScrollPane(text);
	       JOptionPane.showMessageDialog(null, pane, heading, JOptionPane.INFORMATION_MESSAGE);
		}

}
