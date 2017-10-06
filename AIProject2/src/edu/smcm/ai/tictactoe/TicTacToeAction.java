package edu.smcm.ai.tictactoe;

import edu.smcm.ai.search.Action;

/**
 * Action in the Tic Tac Toe game.
 *
 */
public class TicTacToeAction extends Action {

	/**
	 * The row at which a mark will be placed. 
	 */
	private int row;
	
	/**
	 * The column at which a mark will be placed.
	 */
	private int column;
	
	/**
	 * The all-arguments constructor.
	 * 
	 * @param row row at which mark will be placed
	 * @param column column at which mark will be placed
	 */
	public TicTacToeAction(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Obtain the column at which the mark will be placed.
	 * 
	 * @return column at which mark will be placed
	 */
	public int column() {
		return this.column;
	}
	
	/**
	 * Obtain the row at which the mark will be placed.
	 * 
	 * @return row at which mark will be placed
	 */
	public int row() {
		return this.row;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		TicTacToeAction actual;
		
		actual = (TicTacToeAction) other;
		
		return this.row == actual.row && this.column == actual.column;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "(" + row + ", " + column + ")";
	}
}
