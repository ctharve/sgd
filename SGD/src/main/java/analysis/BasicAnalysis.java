package src.main.java.analysis;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BasicAnalysis {
	/**
	 * Return the uniqe tokens in the dataset.
	 * 
	 * @param dataset
	 * @return
	 */
	public Set<Integer> uniqTokens(DataSet dataset) {	
	       int count = 0;
	       Set<Integer> ids = new HashSet<Integer>();
	       
	       while(dataset.hasNext()) {
	         DataInstance instance = dataset.nextInstance();
		 int[] tokens = instance.tokens;
		 
		 for (int ii=0; ii<tokens.length; ii++) {
		     ids.add(tokens[ii]);
		 }

		 count++;
	         
		 if (count % 100000 == 0) {
	             System.err.println("Loaded " + count + " lines");
	         }
	       }

	       // Important: remember to reset the dataset everytime
	       dataset.reset();	
	       
	       return ids;
	}
	
	/**
	 * Return a mapping of age group to unique users in the dataset in that
	 * age group.
	 * 
	 * @param dataset
	 * @return
	 */
	public Map<Integer, Set<Integer>> uniqUsersPerAgeGroup(DataSet dataset) {
		// Fill in your code here
	        // scan entire dataset and for each observation index the Map with 
	        // the age group and insert the userid if unique
		return null;
	}

	/**
	 * @return the average CTR for the training set.
	 */
	public double averageCtr(DataSet dataset) {
	       int clickSum = 0;	
	       int numExamples = 0;
	       while(dataset.hasNext()) {
		   DataInstance instance = dataset.nextInstance();
		   clickSum += instance.clicked;
		   numExamples++;
		   if (numExamples % 100000 == 0) {
		       System.err.println("Loaded " + numExamples + " lines");
		   }
	       }

	       double average = ((double) clickSum) / numExamples;
	       // Important: remember to reset the dataset everytime
	       dataset.reset();	
	       return average;
	}

	public static void main(String args[]) throws Exception {
		// Fill in your code here
	}
}
