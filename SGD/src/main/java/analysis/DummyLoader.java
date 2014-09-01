package src.main.java.analysis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class DummyLoader {
	/**
	 * Scan the data and print out to the stdout.
	 * 
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void scanAndPrint(DataSet dataset) {
		int count = 0;
		//int clickSum = 0;
		System.err.println("Loading data from " + dataset.path + " ... ");
		while (dataset.hasNext()) {
			DataInstance instance = dataset.nextInstance();
			/**
			 * Here we printout the instance. But your code for processing each
			 * instance will come here. For example: tracking the max clicks,
			 * update token frequency table, or compute gradient for logistic
			 * regression...
			 * or not. implement in a method of the class BasicAnalysis
			 */      	
			//System.out.println(instance.toString());
			//clickSum += instance.clicked;
			count++;
			
			if (count % 100000 == 0) {
				System.err.println("Loaded " + count + " lines");
			}
		}
		if (count < dataset.size) {
			System.err.println("Warning: the real size of the data is less than the input size: "
							+ dataset.size + "<" + count);
		}
		System.err.println("Done. Total processed instances: " + count);
		//System.err.println("Number CTR: " + clickSum);
		// Important: remember to reset the dataset after each pass through
		dataset.reset();
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		DummyLoader loader = new DummyLoader();
		//int size = 10;
		int trainSize = 2335859;
		int testSize = 1016552;

		// useful for printing new lines . . . duh
		String seperator = System.getProperty("line.separator");
		
		// process training data
		DataSet training = new DataSet(
				"/Users/cbboipdx/data/clickprediction/data/train.txt",
				true, trainSize);
		//loader.scanAndPrint(training);
		BasicAnalysis basic = new BasicAnalysis();
		Set<Integer> ids = basic.uniqTokens(training);
		int numTokens = ids.size();
		System.out.printf("Training data: unique words =  " + numTokens + seperator);
		//double ctr = basic.averageCtr(training);
		//System.out.printf("Test data: CTR =  %.4f" + seperator, ctr);

		// process test data
		DataSet testing = new DataSet(
				"/Users/cbboipdx/data/clickprediction/data/test.txt",
				false, testSize);
		//loader.scanAndPrint(testing);
		BasicAnalysis basicTest = new BasicAnalysis();
		Set<Integer> idsTesting = basic.uniqTokens(testing);
		int numTokensTest = idsTesting.size();
		System.out.printf("Test data: unique words =  " + numTokensTest + seperator);

	}
}
