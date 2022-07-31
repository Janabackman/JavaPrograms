package basicFile_a3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BasicFile {
	
	File f; //File Variable     
    JFileChooser choose; //JFileChooser Variable
    File f2 = new File(".", "BackupFile"); //File Object, uses file (creates if does not exist yet) to copy content from selected file
	
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
	
    //Method checking if File exists to be used out of the class.
	boolean fileExist(){
		return f.exists();
	}//end fileExist()
	
	//backup file
	void backupFile() throws  IOException{
		
		//using DIS and DOS so all kind of files can be copied (image too)
        DataInputStream dis = null; 
        DataOutputStream dos = null;
		
        try {
		        dis = new DataInputStream(new FileInputStream(f)); //In Object
                dos = new DataOutputStream(new FileOutputStream(f2)); //Out Object
                int length = dis.available();
                
                byte[] buf = new byte[length];//array to hold file content in bytes
                dis.readFully(buf);//in object reads content from array 
                dos.write(buf, 0, buf.length); //Writing to File in bytes to out object
                
                //user feedback of task competition
                displayShort("File has been copied succesfully", "SUCCESS",
                JOptionPane.INFORMATION_MESSAGE);
        	}// end try
        	
        	//Catching Exceptions
			catch(IOException e) {
				displayErrorMessage(e);
			}//end catch
			//finally clause to close all opened file in and out whether or not an exception was thrown.
        	finally {
				dos.flush();
				dos.close(); 
				dis.close();
			}//end finally
		 }//end backupFile()
		
		//overwrite file 
		void overwriteFile() throws FileNotFoundException{
		
		   PrintWriter print = null;//writer object
	            
	       try {
	    	 
				print = new PrintWriter(f); 
				String info = GetData.getWord("Write desired info to File");//ask user to enter text to write into file
					
					
				print.print(info); //Write Data into file
				print.println();
					
				//user feedback of task competition
				displayShort( "File was overwritten succesfully", "SUCCESS",                        
		                        JOptionPane.INFORMATION_MESSAGE);
				
	        }//end try
	       
	       //Exception Catching
	       catch(IOException e) {
	                    displayErrorMessage(e);
			}//end catch
	     
	       //finally clause to close all opened file writer whether or not an exception was thrown.
	       finally{
	             print.flush();
	             print.close();
			}//end finally
			
		}//end overwriteFile();
	
	//append to file
	void appendFile() throws IOException{
		
		//fileWriter to write into file
        FileWriter print = null;
      
        try {
	        print = new FileWriter(f, true);//fileWriter append set to true so it does not overwrite file content
	        
	        //Message Prompt
	        String info = GetData.getWord("Write desired info to File");
	        print.write(" "); //Write Data
	        print.write(info);
	        
	        //user feedback of task competition
	        displayShort( "File was appended succesfully", "SUCCESS",
                    JOptionPane.INFORMATION_MESSAGE);
        }//end try
        //Exception Catch
        catch(IOException e) {
        	displayErrorMessage(e);
		}//end catch
        
        //finally clause to close all opened file writer whether or not an exception was thrown.
        finally {
            print.flush();
	        print.close();
        }//end finally
	}//end appendFile();
	
	//displaying file attributes
	String displayFileAtr() throws IOException {
		
            String s = "";
            
            s += "Absolute Path: "+ f.getAbsolutePath();//1. absolute path 
            s += "\nFiles and Directories in path: \n"; //2. files and directories in file location
            File f3 = new File (f.getParent());//get parent directory to get the files in the chosen file location
            File [] r = f3.listFiles();//array to hold files and directories in directory
            
            for (File i: r) //display each file/directory
            {
            	s +=  ">"+(i.getName())+"\n";
            }//end for
		
            double kb = f.length()/1024.00; //3. Length(Bytes)/KB
            s+= String.format("Size in kb: %.5f", kb)+"\n"; //Displays Size in KB
		
            int numberLines = countLines(); //4. File Lines - calling countLines() method that counts lines in file
            s +="Number of line in file: "+ numberLines+"\n"; //Display of Result Count
		
            return s;	
		}//end displayFileAtr();
	
	//countLines in file
	int countLines() throws IOException{
		LineNumberReader lnr = null;//using line reader object
		int numLines= 0;
		
		try {	
			lnr = new LineNumberReader(new FileReader(f));
			//count lines in file
			while ((lnr.readLine())!=null)
				numLines +=1;	
		}//end try
		catch(IOException e) {//Exception Catch
		  displayErrorMessage(e);
		} //end catch
		//finally clause to close all opened file reader whether or not an exception was thrown.
		finally {
			lnr.close();
		}//end finally
		return numLines;
	}//end countLines();
	
	//displaying the file content into the screen
	String displayFileContent() throws IOException{
		
		DataInputStream dis = null;
		String str ="";
			
		try {
			//data input to read file content
			dis = new DataInputStream(new FileInputStream(f));
			int length = dis.available();
			byte[] buf = new byte[length];//byte array to store file content
			
			dis.readFully(buf);//read file content
			//writing content file from byte array
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < buf.length; ++i)
					sb.append((char)buf[i]);
			str += sb.toString();
          }//end try
		//Exception Catch
		catch(IOException e) {
			displayErrorMessage(e);
		}//end catch
		//finally clause to close all opened file input whether or not an exception was thrown.
		finally {	
			dis.close();
		}
		return str;
	}
	//find word line in file
    void findWord() throws FileNotFoundException, IOException{
    		//Ask user for lookup word
    		String key = GetData.getWord("What word would you like to look for");
    		String strLine = "";
	   		BufferedReader br = null;
	   		
	   		try {
		   		br = new BufferedReader(new FileReader(f));//bufferReader to read content from file
	 
	            int lcount = 1;
	            StringTokenizer st;
	            String line;
	            boolean bl = false;
	           
	            //loop to go through each line in file
	            while ((line = br.readLine()) != null)
	            {
	            	//tokenize each line
	                st = new StringTokenizer(line);
	                
	                //call boolean method that looks for the key word in line. Return true if found
	                if(searchWord(st, key))
	                {   
	                	//append each line where key found with line number
	                    strLine += lcount+": "+line+"\n"; 
	                }
	                
	                lcount++;       
	               }
	            
	            if (!(strLine == ""))
	            	display(strLine, "Word Found");
	            //if no key was found, feedback user
	            else 
	            	JOptionPane.showMessageDialog(null, "Word not found", "NOT FOUND",
		                    JOptionPane.ERROR_MESSAGE);
	   			} //end while
	   		
	   		catch(IOException e) {
	   			displayErrorMessage(e);
	   			}//end catch
	   		//finally clause to close all opened buffered reader whether or not an exception was thrown.
	   		finally {
	   			br.close();
	   			}//end finally

   			}//end findWord();
   
  
	   //search for word in each line word if it's the key word, if not it calls recursively to look for next word in line
	   boolean searchWord(StringTokenizer t, String key)
	    {
	        if (!t.hasMoreTokens())
	        {
	            return false;
	        }//end if
	        //return true if found
	        else if (t.nextToken().equalsIgnoreCase(key))
	        {
	            return true;
	        }//end else if
	        //if not match, call recursively for next word
	        else
	        {
	        	return searchWord(t, key);
	        }//end else
	    }//end searchWord();
	   
	   //tokenize the input file
	   void tokenizeFile() throws FileNotFoundException, IOException{

		   FileReader fr = null;//fileReader to read file content
		   StreamTokenizer st = null;
		   
		   try {
			   fr =  new FileReader(f);
			   st  = new StreamTokenizer(fr);
			   st.eolIsSignificant(true);
			   //making all characters recognizable using code numbers found in ASCII table
			   st.wordChars(32, 126);
			   //feedback user that task has been completed
			   JOptionPane.showMessageDialog(null, "File has been tokenized", "SUCCESS",
	                    JOptionPane.INFORMATION_MESSAGE);
			   
		   }//end try
		   
		   catch(IOException e) {
			  displayErrorMessage(e);
	   			}//end catch
		   //finally clause to close all opened file reader whether or not an exception was thrown.
		   finally {
			   fr.close(); 
		   }//end finally
		   
	   }//end tokenizeFile();
	   
	   //display error message passing in IOException e
	   void displayErrorMessage(IOException e) {
		   String s = "SORRY, AN ERROR HAS OCCURED!\n"+ e.toString();
			displayShort(s, "Error",
               JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
	   }//end displayErrorMessage();
	   
	   //display short message to user
	   void displayShort(String s, String heading,int MESSAGE_TYPE) {
		   
			JOptionPane.showMessageDialog(null, s, heading,
					MESSAGE_TYPE);
	   }//end displayShort();
	   
	   //display in scrolling pane
	   void display(String s, String heading)
	   {
	       JTextArea text = new JTextArea(s, 20, 40);
	       JScrollPane pane = new  JScrollPane(text);
	       JOptionPane.showMessageDialog(null, pane, heading, JOptionPane.INFORMATION_MESSAGE);
	   }//end display();
	
}//end class BasicFile;

