package edu.smcm.ai.search;

/**
 * Representation of a player in an adversarial game.
 * 
 * The intention is to be able to represent players in any game, not just a
 * two-player game. Implementations should supply a toString() method. In
 * addition, implementations should probably supply a value() method that
 * returns a value of some type of enum so that this can be used in a switch.
 * 
 * TODO Find a way to enforce the supplying of a value() method.
 *
 */
public abstract class Player {

	/**
	 * Obtain the next player in turn.
	 * 
	 * @return the next player in turn
	 */
	public abstract Player nextPlayer();
}
