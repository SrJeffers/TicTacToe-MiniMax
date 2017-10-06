package edu.smcm.ai.tictactoe;

import java.util.ArrayList;
import java.util.List;

import edu.smcm.ai.search.Action;
import edu.smcm.ai.search.AdversarialState;
import edu.smcm.ai.search.IllegalAction;
import edu.smcm.ai.search.Player;
import edu.smcm.util.ImplementationException;
import edu.smcm.util.Util;

/**
 * State of a TicTacToe board.
 * 
 * Implementation of the actual state of a Tic Tac Toe board. The board can be
 * of any (square) size. Many of the methods are pass-throughs from @see
 * TicTacToeGame. This is done for modularity reasons. The rules of the game are
 * described in "Artificial Intelligence: A Modern Approach" (Third Edition) by
 * Russell and Norvig on p. 162.
 *
 */
public class TicTacToeState extends AdversarialState {

	/**
	 * The marks on the board. No mark is represented by null.
	 */
	private TicTacToePlayer[][] board;

	/**
	 * The player who is about to make a mark.
	 */
	private TicTacToePlayer player;

	/**
	 * The all-argument constructor.
	 * 
	 * @param size
	 *            size of board to create
	 */
	public TicTacToeState(int size) {
		this.board = new TicTacToePlayer[size][size];
		this.player = TicTacToePlayer.nought;
		for (int row = 0; row < this.board.length; row++) {
			for (int column = 0; column < this.board[row].length; column++) {
				this.board[row][column] = null;
			}
		}
	}

	/**
	 * Copy constructor.
	 * 
	 * A deep copy is made of the object passed as a parameter so that mutator
	 * methods, such as result() can be called on it.
	 * 
	 * @param state
	 *            state to be copied
	 */
	private TicTacToeState(TicTacToeState state) {
		this.board = new TicTacToePlayer[state.board.length][state.board.length];
		this.player = state.player;
		for (int row = 0; row < this.board.length; row++) {
			for (int column = 0; column < this.board[row].length; column++) {
				this.board[row][column] = state.board[row][column];
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.AdversarialState#player()
	 */
	public Player player() {
		return player;
	}

	/**
	 * Check for a line on the board.
	 * 
	 * This method can be used to check rows, columns and diagonals by setting
	 * the parameters appropriately.
	 * 
	 * 
	 * 
	 * @param row
	 *            starting row
	 * @param column
	 *            starting column
	 * @param delta_row
	 *            amount to step row
	 * @param delta_column
	 *            amount to step column
	 * @return true if all cells traversed are the same player
	 */
	private boolean check(int row, int column, int delta_row, int delta_column) {
		boolean result;
		TicTacToePlayer expect;

		// All of the marks should be the same as the first mark
		expect = board[row][column];

		// If the first space is unmarked then there can't be a line!
		if (null != expect) {
			result = true;
			for (int count = 0; count < board.length; count++) {
				result = result && (expect == board[row][column]);
				row = row + delta_row;
				column = column + delta_column;
			}
		} else {
			result = false;
		}

		return result;
	}

	/**
	 * Check if the board is full of marks.
	 *
	 * 
	 * 
	 * @return true is all squares are marked.
	 */
	private boolean board_full() {
		boolean result;

		result = true;
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				result = result && (null != board[row][column]);
			}
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.AdversarialState#terminalTest()
	 */
	public boolean terminalTest() {
		boolean result;

		result = false;

		// Check each row in turn
		for (int row = 0; row < board.length; row++) {
			result = result || check(row, 0, 0, +1);
		}

		// Check each column in turn
		for (int column = 0; column < board.length; column++) {
			result = result || check(0, column, +1, 0);
		}

		// Check the forward diagonal
		result = result || check(0, 0, +1, +1);

		// Check the reverse diagonal
		result = result || check(board.length - 1, 0, -1, +1);

		// Have to check the board is full last!
		result = result || board_full();

		return result;
	}

	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.AdversarialState#utility()
	 */
	public double utility() {
		assert (terminalTest()) : new ImplementationException(
				"Cannot call utility() except when terminalTest() is true");

		double result;

		if (board_full()) {
			result = 0.0;
		} else {
			switch (player.value()) {
			case Cross:
				result = +1.0;
				break;
			case Nought:
				result = -1.0;
				break;
			default:
				assert (false) : new ImplementationException("Unknown player " + player + " in utility()");
				result = 0.0;
				break;
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.AdversarialState#actions()
	 */
	@Override
	public List<Action> actions() {
		List<Action> result;

		result = new ArrayList<Action>();

		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				if (null == board[row][column]) {
					result.add(new TicTacToeAction(row, column));
				}
			}
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.AdversarialState#result(edu.smcm.ai.search.Action)
	 */
	@Override
	public AdversarialState result(Action action) throws IllegalAction {
		TicTacToeState result;
		TicTacToeAction actual;

		result = new TicTacToeState(this);
		actual = (TicTacToeAction) action;

		// Check that the mark will be on the board and in an empty space
		if (!Util.between(0, actual.row(), board.length) || !Util.between(0, actual.row(), board.length)
				|| (null != board[actual.row()][actual.column()])) {
			throw new IllegalAction();
		}

		// Make the mark
		result.board[actual.row()][actual.column()] = (TicTacToePlayer) player();

		// Move to the next player in turn
		result.player = (TicTacToePlayer) player.nextPlayer();

		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String result;

		result = "";
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				if (null == board[row][column]) {
					result = result + ".";
				} else {
					result = result + board[row][column];
				}
			}
			if (board.length != row) {
				result = result + "\n";
			}
		}

		return result;
	}
}
