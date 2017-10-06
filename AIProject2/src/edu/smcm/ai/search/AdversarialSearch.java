package edu.smcm.ai.search;

/**
 * An abstraction of a two-player adversarial game search engine.
 *
 */
public abstract class AdversarialSearch {

	/**
	 * The number of game states that were examined in the search.
	 */
	protected int states_evaluated;

	/**
	 * Find the best action to take in a state.
	 * 
	 * This is only valid for the Max player.
	 * 
	 * @param state
	 *            state of interest
	 * @return best move
	 */
	public abstract Action search(AdversarialState state);

	/**
	 * Obtain the number of states evaluated during the search.
	 * 
	 * @return number of states evaluated
	 */
	public int statesEvaluated() {
		return states_evaluated;
	}
}
