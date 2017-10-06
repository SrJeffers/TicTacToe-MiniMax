package edu.smcm.ai.search;

import java.util.List;

/**
 * An adversarial game.
 * 
 * It provides the interface described in "Artificial Intelligence: A Modern
 * Approach" (Third Edition) by Russell and Norvig on p. 162.
 *
 */
public abstract class AdversarialGame {

	/**
	 * Obtain the starting state of the game.
	 * 
	 * @return starting state of game
	 */
	abstract public AdversarialState initialState();

	/**
	 * Obtain the player whose turn it is.
	 * 
	 * @param state
	 *            state of interest
	 * @return player whose turn it is
	 */
	abstract public Player player(AdversarialState state);

	/**
	 * Determine if the game has ended.
	 * 
	 * @param state
	 *            state of interest
	 * @return true if game has ended
	 */
	abstract public boolean terminalTest(AdversarialState state);

	/**
	 * Determine the utility of the state.
	 * 
	 * This is only valid if terminalTest(state) is true.
	 * 
	 * @param state
	 *            state of interest
	 * @return utility of state
	 */
	abstract public double utility(AdversarialState state);

	/**
	 * Obtain a list of valid actions in this state.
	 * 
	 * The order of the actions in the list is arbitrary.
	 * 
	 * @param state
	 *            state of interest
	 * @return list of valid actions
	 */
	abstract public List<Action> actions(AdversarialState state);

	/**
	 * Obtain the state that results from taking an action.
	 * 
	 * @param state
	 *            state of interest
	 * @param action
	 *            action taken
	 * @return state resulting from action
	 */
	abstract public AdversarialState result(AdversarialState state, Action action);
}
