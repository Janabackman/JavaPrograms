/*Assignment 4, COP 3804, Fall 2021
 * by Sophiane Cineus, Tiago Caselli, and Jana Backman*/
package preprocessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class  BasicFile{
	File f; //File Variable     
    JFileChooser choose; //JFileChooser Variable
    
	
    //Class constructor. Prompts user to choose file and assign chosen file to File object instance
    BasicFile()
    {            
        //FileChooser to allow user to choose a File FileNotFoundException or IOException.
        choose = new JFileChooser(".");
        choose.setDialogTitle("Select or Type File Name");
        int status = choose.showOpenDialog(null);//Open the JFileChooser	

	    try //Try - Catch Begins for any 
	    {
	        if (status != JFileChooser.APPROVE_OPTION)
	        {
	            throw new IOException(); //Exception for Selection
	        }//end if
	
	        f = choose.getSelectedFile(); //File Selection
	        
	        
	        if (!f.exists()) //Exception if there is no file.
	        {
	            throw new FileNotFoundException();
	        }//end if
	    }//end try
    
        catch (FileNotFoundException e) //Catching Exceptions. Feedback the user with error message.
        {
            JOptionPane.showMessageDialog(null, "File Not Found. \nSelect existing file or CANCEL", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } //end catch
        catch (IOException e) //if exception occurs, exit the program
        {
             	System.exit(0);  
        } //end catch    
        
	}//end constructor
    
    File getSelectedFile() {
    	return f;
    }
}
