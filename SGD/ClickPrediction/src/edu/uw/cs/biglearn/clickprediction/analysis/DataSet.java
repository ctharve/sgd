package edu.uw.cs.biglearn.clickprediction.analysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * This class represents a dataset object.
 * 
 * @author haijieg
 * 
 */
public class DataSet {
	public static final int TRAININGSIZE = 2335859;
	public static final int TESTINGSIZE = 1016552;
	public String path;
	public boolean hasLabel;
	public int size;
	public int counter;
	private Scanner sc;

	/**
	 * Creates a dataset from the given path.
	 * 
	 * @param path
	 *            Path to the data file living on the disk.
	 * @param isTraining
	 *            True if the input is training data.
	 * @param size
	 *            The size of the dataset, can be SMALLER than the size of the
	 *            input.
	 * @throws FileNotFoundException
	 */
	public DataSet(String path, boolean isTraining, int size)
			throws FileNotFoundException {
		this.path = path;
		this.hasLabel = isTraining;
		this.size = size;
		sc = new Scanner(new BufferedReader(new FileReader(path)));
	}

	/**
	 * @return True if the dataset has more data.
	 */
	public boolean hasNext() {
		return (counter < size) && sc.hasNextLine();
	}

	/**
	 * @return the next data instance.
	 */
	public DataInstance nextInstance() {
		counter++;
		return new DataInstance(sc.nextLine(), hasLabel);
	}

	/**
	 * @return the next data instance with hashed feature.
	 */
	public HashedDataInstance nextHashedInstance(int featuredim,
			boolean personal) {
		counter++;
		return new HashedDataInstance(sc.nextLine(), hasLabel, featuredim,
				personal);
	}

	/**
	 * Reset the dataset. Must be called when ever the same dataset need to be
	 * reused.
	 */
	public void reset() {
		counter = 0;
		sc.close();
		try {
			sc = new Scanner(new BufferedReader(new FileReader(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}