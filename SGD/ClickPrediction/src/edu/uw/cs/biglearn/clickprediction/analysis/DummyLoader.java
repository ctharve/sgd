package edu.uw.cs.biglearn.clickprediction.analysis;

import java.io.FileNotFoundException;
import java.io.IOException;

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
		System.err.println("Loading data from " + dataset.path + " ... ");
		while (dataset.hasNext()) {
			DataInstance instance = dataset.nextInstance();
			/**
			 * Here we printout the instance. But your code for processing each
			 * instance will come here. For example: tracking the max clicks,
			 * update token frequency table, or compute gradient for logistic
			 * regression...
			 */
			System.out.println(instance.toString());
			count++;
			if (count % 100000 == 0) {
				System.err.println("Loaded " + count + " lines");
			}
		}
		if (count < dataset.size) {
			System.err
					.println("Warning: the real size of the data is less than the input size: "
							+ dataset.size + "<" + count);
		}
		System.err.println("Done. Total processed instances: " + count);
		dataset.reset();// Important: remember to reset the dataset everytime
						// you are done with processing.
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		DummyLoader loader = new DummyLoader();
		int size = 10;

		// Creates a dataset from the trainingdata with size = 10;
		DataSet training = new DataSet(
				"/Users/haijieg/workspace/kdd2012/simpledata/train.txt",
				true, size);
		loader.scanAndPrint(training);

		// Creates a dataset from the testdata with size = 10;
		DataSet testing = new DataSet(
				"/Users/haijieg/workspace/kdd2012/simpledata/test.txt",
				false, size);
		loader.scanAndPrint(testing);
	}
}
