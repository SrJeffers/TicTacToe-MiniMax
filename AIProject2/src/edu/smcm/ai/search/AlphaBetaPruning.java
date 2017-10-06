package edu.smcm.ai.search;

/**
 * A search engine that implements the Alpha Beta Pruning algorithm.
 * 
 * This class is implemented from the pseudocode that appears in "Artificial
 * Intelligence: A Modern Approach" (Third Edition) by Russell and Norvig on p.
 * 170. The search() method does not follow the Russell and Norvig pseudocode for
 * this algorithm because it is not clear how this should be implemented.
 * Instead, the pseudocode from the MiniMax algorithm (p. 166) is used.
 * 
 */
public class AlphaBetaPruning extends AdversarialSearch {

	/**
	 * Find the value of this state for the Max player.
	 * 
	 * @param state state of game
	 * @param alpha best value so far for Max player
	 * @param beta best value so far for Min player
	 * @return worst outcome for Max player
	 */
	private double maxValue(AdversarialState state, double alpha, double beta) {
		// TODO Implement AlphaBetaPruning.maxValue()
		
		//if the the state is complete, then it's utility is returned
		if (state.terminalTest()){
			return state.utility();
		}
		
		
		double v = Double.NEGATIVE_INFINITY;
		
		//moves through each action available
		for (int i = 0; i < state.actions().size(); i++) {
			states_evaluated++;
			
			//choses the greater value of v or the value of the action
			v = Math.max(v, minValue(state.result(state.actions().get(i)), alpha, beta));
			
			//v is returned sooner if its value is greater than beta
			if (v >= beta){
				return v;
			}
			
			//the value of beta is changed to v if v is lesser
			alpha = Math.max(alpha, v);
		}
		
		return v;
	}

	/**
	 * Find the value of this state for the Min player.
	 * 
	 * @param state state of game
	 * @param alpha best value so far for Max player
	 * @param beta best value so far for Min player
	 * @return worst outcome for Min player
	 */
	private double minValue(AdversarialState state, double alpha, double beta) {
		// TODO Implement AlphaBetaPruning.minValue()
		
		//if the the state is complete, then it's utility is returned
		if (state.terminalTest()){
			return state.utility();
		}
		
		double v = Double.POSITIVE_INFINITY;
		
		//moves through each action available
		for (int i = 0; i < state.actions().size(); i++) {
			states_evaluated++;
			
			//choses the lesser value of v or the value of the action
			v = Math.min(v, maxValue(state.result(state.actions().get(i)), alpha, beta));
			//v is returned sooner if its value is less than alpha
			if (v <= alpha){
				return v;
			}
			//the value of beta is changed to v if v is lesser 
			beta = Math.min(beta, v);
			
		}
		
		return v;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.AdversarialSearch#search(edu.smcm.ai.search.
	 * AdversarialState)
	 */
	@Override
	public Action search(AdversarialState state) {
		// TODO Implement AlphaBetaPruning.search()
		
		//calls the maxValue method giving negative infinity to alpha and positive infinity to beta
		double v = maxValue(state,Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
		
		//the for loop goes through each of the actions available
		for (int i = 0; i < state.actions().size(); i++) {
			
			//returns the action that has the same value as v
			if(v == minValue(state.result(state.actions().get(i)), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)){
				
				return state.actions().get(i);
			}
		}
		
		return null;
		
	}
}
