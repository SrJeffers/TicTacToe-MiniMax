package edu.smcm.ai.experiments;

import java.util.Scanner;

import edu.smcm.ai.search.Action;
import edu.smcm.ai.search.AdversarialState;
import edu.smcm.ai.search.AlphaBetaPruning;
import edu.smcm.ai.search.MiniMax;
import edu.smcm.ai.tictactoe.TicTacToeAction;
import edu.smcm.ai.tictactoe.TicTacToeGame;
import edu.smcm.util.ImplementationException;

/**
 * Driver class for an experiment using Tic Tac Toe as an example.
 * 
 * The class consists only of a main method. This method allows you to play the
 * cross player in TicTacToe. It is designed as a method of testing out the
 * MiniMax and Alpha-Beta pruning search implementations. It compares the two
 * implementations answers and prints out the number of states explored by each
 * method.
 *
 */
public class TicTacToeDemonstration {

	/**
	 * Main method of demonstration.
	 * 
	 * Performs the actual game.
	 * 
	 * @param arguments
	 *            command line arguments
	 */
	@SuppressWarnings("resource")
	public static void main(String[] arguments) {
		Scanner keyboard; // Scanner for keyboard input
		TicTacToeGame tic_tac_toe; // Tic Tac Toe Game object
		AdversarialState state; // Current state of the game, needed for search
		Action mini_max_action; // Action returned by the MiniMax method
		Action alpha_beta_action; // Action returned by Alpha Beta Pruning method
		MiniMax mini_max;  // Minimax search engine
		AlphaBetaPruning alpha_beta; // Alpha Beta Pruning search engine
		int row; // Row entered by user
		int column; // Column entered by user
		int size; // Size of game board, entered by user

		// Create the Scanner for input
		keyboard = new Scanner(System.in);

		// Determine size of board
		System.out.print("Enter size of board: ");
		size = keyboard.nextInt();

		System.out.println("Playing Tic Tac Toe on a " + size + " x " + size + " board.");
		
		// Create the game
		tic_tac_toe = new TicTacToeGame(size);

		// Create the search engines
		mini_max = new MiniMax();
		alpha_beta = new AlphaBetaPruning();

		// Initialise the state
		state = tic_tac_toe.initialState();

		// Play the game by turns
		while (!tic_tac_toe.terminalTest(state)) {
			
			// Search for best action for automatic player
			mini_max_action = mini_max.search(state);
			alpha_beta_action = alpha_beta.search(state);
			
			assert (mini_max_action.equals(alpha_beta_action)) : new ImplementationException(
					"Minimax and Alpha-Beta do not agree.");

			// Output metrics on search
			System.out.println("MiniMax    states evaluated:" + mini_max.statesEvaluated());
			System.out.println("Alpha-Beta states evaluated:" + alpha_beta.statesEvaluated());

			// Play the selected move
			System.out.println("Player " + tic_tac_toe.player(state) + " plays " + alpha_beta_action + ".");
			state = tic_tac_toe.result(state, alpha_beta_action);

			System.out.println(state);

			// Check to see if this was the last move in the game
			if (tic_tac_toe.terminalTest(state)) {
				break;
			}

			// Obtain move from human player
			System.out.print("What do you want to play? (row then column)");
			row = keyboard.nextInt();
			column = keyboard.nextInt();

			// Play the selected move
			state = tic_tac_toe.result(state, new TicTacToeAction(row, column));

			System.out.println(state);

			// Check to see if this was the last move in the game
			if (tic_tac_toe.terminalTest(state)) {
				break;
			}
		}

		// Print out a message indicating the winner of the game
		// NOTE: You should never compare floats for equality!
		if (0.1 > Math.abs(state.utility())) {
			System.out.println("The game was a draw.");
		} else {
			System.out.println("Player " + state.player().nextPlayer() + " wins.");
		}
	}
}
