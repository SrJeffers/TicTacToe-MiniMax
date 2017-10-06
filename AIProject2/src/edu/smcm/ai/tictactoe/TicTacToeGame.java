package edu.smcm.ai.tictactoe;

import java.util.List;

import edu.smcm.ai.search.Action;
import edu.smcm.ai.search.AdversarialGame;
import edu.smcm.ai.search.AdversarialState;
import edu.smcm.ai.search.Player;

/**
 * Implementation of the Tic Tac Toe game.
 * 
 * The game is implemented so that is can be played on a board of any size. The
 * number of marks that need to be in a line is the same as the size of the
 * board. Most of the game is actually implemented in @see TicTacToeState. The
 * size of the board is a pass-through.
 *
 */
public class TicTacToeGame extends AdversarialGame {

	/**
	 * Size of the board
	 */
	private int size;

	/**
	 * All-arguments constructor.
	 * 
	 * @param size
	 *            size of board
	 */
	public TicTacToeGame(int size) {
		this.size = size;
	}

	/**
	 * Default constructor.
	 * 
	 * Make the board of the traditional 3 by 3 size.
	 */
	public TicTacToeGame() {
		this(3);
	}

	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.AdversarialGame#initialState()
	 */
	@Override
	public AdversarialState initialState() {
		return new TicTacToeState(size);
	}

	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.AdversarialGame#player(edu.smcm.ai.search.AdversarialState)
	 */
	@Override
	public Player player(AdversarialState state) {
		return state.player();
	}

	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.AdversarialGame#terminalTest(edu.smcm.ai.search.AdversarialState)
	 */
	@Override
	public boolean terminalTest(AdversarialState state) {
		return state.terminalTest();
	}

	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.AdversarialGame#utility(edu.smcm.ai.search.AdversarialState)
	 */
	@Override
	public double utility(AdversarialState state) {
		return state.utility();
	}

	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.AdversarialGame#actions(edu.smcm.ai.search.AdversarialState)
	 */
	@Override
	public List<Action> actions(AdversarialState state) {
		return state.actions();
	}

	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.AdversarialGame#result(edu.smcm.ai.search.AdversarialState, edu.smcm.ai.search.Action)
	 */
	@Override
	public AdversarialState result(AdversarialState state, Action action) {
		return state.result(action);
	}
}
