package src.main.java.analysis;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import src.main.java.util.EvalUtil;

public class LogisticRegression {
	/**
	 * This class represents the weights in the logistic regression model.
	 * 
	 * @author haijieg
	 * 
	 */
	public class Weights {
		double w0;
		/*
		 * query.get("123") will return the weight for the feature:
		 * "token 123 in the query field".
		 */
		Map<Integer, Double> wTokens;
		double wPosition;
		double wDepth;
		double wAge;
		double wGender;
		
		Map<Integer, Integer> accessTime; // keep track of the access timestamp of feature weights.
																			// Using this to do delayed regularization.
		
		public Weights() {
			w0 = wAge = wGender = wDepth = wPosition = 0.0;
			wTokens = new HashMap<Integer, Double>();
			accessTime = new HashMap<Integer, Integer>();
		}

		@Override
		public String toString() {
			DecimalFormat myFormatter = new DecimalFormat("###.##");
			StringBuilder builder = new StringBuilder();
			builder.append("Intercept: " + myFormatter.format(w0) + "\n");
			builder.append("Depth: " + myFormatter.format(wDepth) + "\n");
			builder.append("Position: " + myFormatter.format(wPosition) + "\n");
			builder.append("Gender: " + myFormatter.format(wGender) + "\n");
			builder.append("Age: " + myFormatter.format(wAge) + "\n");
			builder.append("Tokens: " + wTokens.toString() + "\n");
			return builder.toString();
		}

		/**
		 * @return the l2 norm of this weight vector.
		 */
		public double l2norm() {
			double l2 = w0 * w0 + wAge * wAge + wGender * wGender
					 				+ wDepth*wDepth + wPosition*wPosition;
			for (double w : wTokens.values())
				l2 += w * w;
			return Math.sqrt(l2);
		}

		/**
		 * @return the l0 norm of this weight vector.
		 */
		public int l0norm() {
			return 4 + wTokens.size();
		}
	}
	


	/**
	 * Helper function to compute inner product w^Tx.
	 *
         * @author charvey
	 *
	 * @param weights
	 * @param instance
	 * @return
	 */
	private double computeWeightFeatureProduct(Weights weights,
			DataInstance instance) {
	    // Fill in your code here
	    // w0 | wAge | wGender | wDepth | wPosition | wTokens;
	    double tokenSum = 0;
	    int[] instTokens = instance.tokens;
            Map<Integer, Double> allTokens = weights.wTokens;

	    for (int ii=0; ii<instTokens.length; ii++) {
		int thisInst = instTokens[ii];
		if(allTokens.containsKey(thisInst)){
		    tokenSum += allTokens.get(thisInst);
		
		}
		// TODO: throw an error if token is not found
	    }

	    Double innerP  = 1  + weights.wAge*instance.age 
		                + weights.wGender*instance.gender 
		                + weights.wDepth*instance.depth 
		                + weights.wPosition*instance.position
		                + tokenSum;
	    return innerP;
	}

	/**
	 * Apply delayed regularization to the weights corresponding to the given tokens.
	 * @param tokens
	 * @param weights
	 * @param now
	 * @param step
	 * @param lambda
	 */
	private void performDelayedRegularization(int[] tokens,
			Weights weights,
			int now, double step, double lambda) {
		// Fill in your code here.
	}
	
	
	/**
	 * Train the logistic regression model using the training data and the
	 * hyperparameters. Return the weights, and record the cumulative loss.
	 *
	 * @author charvey
	 *
	 * @param dataset
	 * @param lambda
	 * @param step
	 * @return the weights for the model.
	 */
	public Weights train(DataSet dataset, double lambda, double step, ArrayList<Double> AvgLoss) {
	    int count = 0;
	    Weights myWeights = new  Weights();
	    double loss = 0;
	    double avLoss; 

	    while (dataset.hasNext()) {
		count++;
		DataInstance instance = dataset.nextInstance();
		//performDelayedRegularization(instance.tokens, myWeights, 9, step, lambda);
		double ip = Math.exp(computeWeightFeatureProduct(myWeights, instance));
		double y_hat = ip / (1 + ip);
		double y = instance.clicked;
		loss += Math.pow((y_hat - y), 2);
		
		if(count % 100 == 0) {
		    avLoss = loss / count; 
		    AvgLoss.add(loss);
		}

		double diff = y-y_hat;
		myWeights.w0 += step * (1*diff);;
		myWeights.wAge += step * (instance.age*diff);
		myWeights.wPosition += step * (instance.position*diff);
	        myWeights.wGender += step * (instance.gender*diff);
		myWeights.wDepth += step * (instance.depth*diff);
		// need to update wTokens
	    }
	    
	    myWeights.toString();
	    dataset.reset();
	    return myWeights;
	
		// Fill in your code here. The structure should look like:
		// For each data point: {
    		// Your code: perform delayed regularization
      			// Your code: predict the label, record the loss	  
  			// Your code: compute w0 + <w, x>, and gradient
  			// Your code: update weights along the negative gradient
		// }
	}

	/**
	 * Using the weights to predict CTR in for the test dataset.
	 * 
	 * @param weights
	 * @param dataset
	 * @return An array storing the CTR for each datapoint in the test data.
	 */
	public ArrayList<Double> predict(Weights weights, DataSet dataset) {
		// Fill in your code here
		return null;
	}

	public static void main(String args[]) throws IOException {
		// Fill in your code here
	}
}
