package edu.smcm.util;

/**
 * An exponential weighted moving average.
 * 
 * A method of smoothing changes in a value over time in order to make a
 * prediction about the future. See
 * https://en.wikipedia.org/wiki/Moving_average#Exponential_moving_average
 */
public class ExponentialWeightedMovingAverage {

	/**
	 * The current vale of the average.
	 */
	private double value;

	/**
	 * The weighting used at each step.
	 */
	private double alpha;

	/**
	 * A constructor.
	 * 
	 * @param alpha
	 *            weighting used at each step
	 * @param start
	 *            the initial value
	 */
	public ExponentialWeightedMovingAverage(double alpha, double start) {
		this.value = start;
		this.alpha = alpha;
	}

	/**
	 * A constructor.
	 * 
	 * @param alpha
	 *            weighting used at each step
	 */
	public ExponentialWeightedMovingAverage(double alpha) {
		this.value = 0.0;
		this.alpha = alpha;
	}

	/**
	 * Update the average with a new value.
	 * 
	 * @param value
	 *            latest value
	 * @return prediction of next value
	 */
	public double update(double value) {
		this.value = alpha * value + (1.0 - alpha) * this.value;

		return this.value;
	}

	/**
	 * Get the prediction of the next value.
	 * 
	 * @return prediction of next value
	 */
	public double value() {
		return value;
	}
}
