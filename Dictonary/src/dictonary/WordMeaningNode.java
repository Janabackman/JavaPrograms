package dictonary;

//class that creates each node of the linked list
public class WordMeaningNode {
	//stores the next node
	WordMeaningNode next;
	
	//instance of WordMeaning
	WordMeaning wm;
	
	WordMeaningNode(WordMeaning wm){
		this.wm = wm;
		next = null;
	}//end constructor
	
	//getter method
	WordMeaning getNode() {
		return wm;
	}//end getNode()
	
}//end class WordMeaningNode
