package src.main.java.analysis;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import src.main.java.util.EvalUtil;

public class LogisticRegressionWithHashing {
	public class Weights {
		double w0;
		double wPosition;
		double wDepth;
		double wAge;
		double wGender;
		double[] wHashedFeature;
		Map<Integer, Integer> accessTime; // keep track of the access timestamp of feature weights.
																			// Using this to do delayed regularization.
		int featuredim;
		
		public Weights(int featuredim) {
			this.featuredim = featuredim;
			w0 = wAge = wGender = wDepth = wPosition = 0.0;
			wHashedFeature = new double[featuredim];
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
			builder.append("HashedFeature: " );
			for (double w: wHashedFeature)
				builder.append(w + " ");
			builder.append("\n");				
			return builder.toString();
		}

		/**
		 * @return the l2 norm of this weight vector.
		 */
		public double l2norm() {
			double l2 = w0 * w0 + wAge * wAge + wGender * wGender
					 				+ wDepth*wDepth + wPosition*wPosition;
			for (double w : wHashedFeature)
				l2 += w * w;
			return Math.sqrt(l2);
		}
	} // end of weight class

	
	/**
	 * Helper function to compute inner product w^Tx.
	 * 
	 * @param weights
	 * @param instance
	 * @return
	 */
	private double computeWeightFeatureProduct(Weights weights,
			HashedDataInstance instance) {
		// Fill in your code ehre
		return 0.0;
	}
	
	/**
   * Apply delayed regularization to the weights corresponding to the given tokens.
	 * @param featureids
	 * @param weights
	 * @param now
	 * @param step
	 * @param lambda
	 */
	private void performDelayedRegularization(Set<Integer> featureids,
			Weights weights,
			int now, double step, double lambda) {
		// Fill in your code here
	}

	/**
	 * Train the logistic regression model using the training data and the
	 * hyperparameters. Return the weights, and record the cumulative loss.
	 * 
	 * @param dataset
	 * @param lambda
	 * @param step
	 * @return the weights for the model.
	 */
	public Weights train(DataSet dataset, int dim, double lambda, double step, ArrayList<Double> AvgLoss,
			boolean personalized) {
		// Fill in your code here
		return null;
	}
	
	/**
	 * Using the weights to predict CTR in for the test dataset.
	 * 
	 * @param weights
	 * @param dataset
	 * @return An array storing the CTR for each datapoint in the test data.
	 */
	public ArrayList<Double> predict(Weights weights, DataSet dataset,
			boolean personalized) {
		// Fill in your code here
		return null;
	}


	public static void main(String args[]) throws IOException {
		// Fill in your code here
	}
}
