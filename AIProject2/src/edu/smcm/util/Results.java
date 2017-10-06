package edu.smcm.util;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a list of lists of integers. This is useful for
 * storing the results of experiments. Some useful statistical methods are
 * provided for each list of numbers.
 * 
 * TODO Put this class in Utils?
 
 */
public class Results {

	// TODO Provide standard deviation 
	// TODO Make the class Iterable
	
	/**
	 * The data structure for storing the results
	 */
	private List<List<Integer>> results;

	/**
	 * A constructor.
	 * 
	 * We can't use an array of List&lt;&gt; because Java won't let us. In any
	 * case "Effective Java" recommends not to.
	 * 
	 * TODO Find reference in Effective Java
	 * 
	 * @param size
	 *            number of result sets to store
	 */
	public Results(int size) {
		results = new ArrayList<List<Integer>>();

		for (int index = 0; index < size; index++) {
			results.add(new ArrayList<Integer>());
		}
	}

	/**
	 * The number of results sets.
	 * 
	 * @return number of result sets.
	 */
	public int size() {
		return results.size();
	}

	/**
	 * Insert a result into a result set.
	 * 
	 * @param index
	 *            result set to insert into
	 * @param value
	 *            result to insert
	 */
	public void add(int index, int value) {
		results.get(index).add(value);
	}

	/**
	 * Get size of result set.
	 * 
	 * @param index
	 *            result set to get the size of
	 * @return number of results in set
	 */
	public int size(int index) {
		return results.get(index).size();
	}

	/**
	 * Get the mean of the results in a set.
	 * 
	 * @param index
	 *            result set to mean
	 * @return mean of result set
	 */
	public double mean(int index) {
		int sum;

		sum = 0;
		for (int value : results.get(index)) {
			sum = sum + value;
		}

		return (double) sum / results.get(index).size();
	}

	/**
	 * Clear result set.
	 * 
	 * Remove all results from all results sets. This allows the results data
	 * structure to be reused.
	 */
	public void clear() {
		for (int index = 0; index < results.size(); index++) {
			results.get(index).clear();
		}
	}
}
