package edu.smcm.ai.search;

import java.util.List;

/**
 * State in an Adversarial Game.
 * 
 * We are using the schema described on p.162 of Artificial Intelligence: A
 * Modern Approach by Russell and Norvig. However, we have chosen to creat an
 * additional class AdversarialGame that includes the initial state. This is to
 * make the structure of the classes more like that for classical search (@see
 * Search).
 * 
 * 
 * 
 */
abstract public class AdversarialState extends State {

	/**
	 * Determine the current player.
	 * 
	 * @return current player
	 */
	abstract public Player player();

	/**
	 * Determine if the game is complete.
	 * 
	 * @return true if the game is complete
	 */
	abstract public boolean terminalTest();

	/**
	 * Determine the utility of this state.
	 * 
	 * This is only valid if the game is complete.
	 * 
	 * @return utility of state
	 */
	abstract public double utility();

	/**
	 * Determine valid actions in this state.
	 * 
	 * The actions are in some arbitrary order.
	 * 
	 * @return valid actions in this state
	 */
	abstract public List<Action> actions();

	/**
	 * Determine next state given action.
	 * 
	 * Computes the state resulting from applying some legal action to a given
	 * state.
	 * 
	 * @param action
	 *            action to be taken
	 * @return next state
	 */
	abstract public AdversarialState result(Action action);
}
