package edu.smcm.ai.tictactoe;

import edu.smcm.util.ImplementationException;

/**
 * A Tic Tac Toe Player.
 *
 * The player is either nought or cross. An internal enumerations is used to
 * allow switch statements to be used.
 *
 */
public class TicTacToePlayer extends edu.smcm.ai.search.Player {

	/**
	 * Internal enumeration.
	 * 
	 * Used to allow switch statements to be used.
	 */
	public enum Player {
		Nought, Cross;
	}

	/**
	 * The constant representing the nought player.
	 */
	public static final TicTacToePlayer nought;

	/**
	 * The constant representing the cross player.
	 */
	public static final TicTacToePlayer cross;

	/**
	 * The internal enumeration value.
	 */
	private Player player;

	/**
	 * Constructor.
	 * 
	 * Used in static initialiser block to initialise internal constants.
	 * 
	 * @param player
	 *            player to create
	 */
	private TicTacToePlayer(Player player) {
		this.player = player;
	}

	static {
		nought = new TicTacToePlayer(Player.Nought);
		cross = new TicTacToePlayer(Player.Cross);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.Player#nextPlayer()
	 */
	@Override
	public edu.smcm.ai.search.Player nextPlayer() {
		TicTacToePlayer result;

		switch (player) {
		case Nought:
			result = cross;
			break;
		case Cross:
			result = nought;
			break;
		default:
			assert (false) : new ImplementationException("Bad Player in Next Player");
			result = null;
			break;
		}

		return result;
	}

	/**
	 * Accessor for internal enumeration.
	 * 
	 * @return internal enumeration value
	 */
	public Player value() {
		return this.player;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String result;

		switch (player) {
		case Nought:
			result = "O";
			break;
		case Cross:
			result = "X";
			break;
		default:
			throw new ImplementationException("Illegal Player");
		}

		return result;
	}
}
