package basicFile_a3;

import java.io.IOException;

import javax.swing.JOptionPane;

public class TestFile {

	public static void main(String[] args) throws IOException 
        {
            //while loop control
			boolean done = false;
            
			//Creating BasicFile Object
            BasicFile f = new BasicFile();
            
            //if file doesn't exist, let the user choose file again by creating a new BasicFile instance 
            if (!f.fileExist()) {
            	f = new BasicFile();
            }//end if
            
            //if file exists let the user choose from the menu option what to do with the file until the user exist the program.
            //Menu Options
	         String menu = "Enter Option\n1. Copy File "                   
	                    + "\n2. Append or Overwrite File \n3. View File Attributes \n4. View File Content \n"
	                    + "5. Search File for String \n6. Tokenize the file to recognizes all printable characters\n7. Exit ";
            
         
            while (!done)
            {
                       
                String s = JOptionPane.showInputDialog(menu); //Menu Display
                
                try
                {
                    int i = Integer.parseInt(s);
                    
                    switch(i)
                    {
                                                  
                        case 1: //Copy File
        
                            f.backupFile();
    
                        break;
                         
                        case 2: //Append or Overwrite File
                            
                        	int submenu = GetData.getInt("Would you like to \n1. Override to file \n2. Append to File");                  
            	                   
                        	switch (submenu) {
                        		case 1://overwrite file
                        
                        			f.overwriteFile();
                        			
                        			break;
                        		
                        		case 2://append to file
                        			f.appendFile();
                        			
                        		}//end switch(submenu);
                        	
                        	break;
                            
                        case 3: //File Attributes
                            
                         
                                f.display(f.displayFileAtr(), "File Attributes");
                       
                            break;
                         
                        case 4: //File Contents
                                           
                                f.display(f.displayFileContent(), "File Contents");
              
                              break;
                         
                        case 5: //Search File for String
                            
                        	f.findWord();
   
                        break;
                        
                        case 6: //Tokenize file
                            
                        	f.tokenizeFile();
   
                        break;
                         
                        case 7: //Exit                            
                        
                            done = true;

                        break;
                        
                        default:
                        	f.displayShort("Not a menu option", "ERROR",
             	                    JOptionPane.ERROR_MESSAGE);
                        }//end switch(i);
               }//end try

               catch (NumberFormatException e) 
               {
            		   
            	   if (s == null)
            		   System.exit(0);
            	   else
            		   f.displayShort("Not a menu option", "ERROR",
         	                    JOptionPane.ERROR_MESSAGE); 	   
            		   
               } //end catch
               catch (NullPointerException e)

               {
            	   System.exit(0);
               }//end catch

          }//end while(!done);

     }//end main();
     
}//end TestFile class
