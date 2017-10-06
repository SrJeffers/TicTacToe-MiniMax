package edu.smcm.ai.search;

/**
 * A search engine that implements the Alpha Beta Pruning algorithm.
 * 
 * This class is implemented from the pseudocode that appears in "Artificial
 * Intelligence: A Modern Approach" (Third Edition) by Russell and Norvig on p.
 * 166.
 * 
 */
public class MiniMax extends AdversarialSearch {
	
	/**
	 * Find the value of this state for the Max player.
	 * 
	 * @param state state of game
	 * @return worst outcome for Max player
	 */
	private double maxValue(AdversarialState state) {
		// TODO Implement Minimax.maxValue()
		
		//if the the state is complete, then it's utility is returned
		if (state.terminalTest()){
			return state.utility();
		}
		
		
		double v = Double.NEGATIVE_INFINITY;
		
		//moves through each action available
		for (int i = 0; i < state.actions().size(); i++) {
			states_evaluated++;
			
			//choses the greater value of v or the value of the action
			v = Math.max(v, minValue(state.result(state.actions().get(i))));
		}
		
		return v;
	}
	
	/**
	 * Find the value of this state for the Min player.
	 * 
	 * @param state state of game
	 * @return worst outcome for Min player
	 */
	private double minValue(AdversarialState state) {
		// TODO Implement MiniMax.minValue()
		
		//if the the state is complete, then it's utility is returned
		if (state.terminalTest()){
			return state.utility();
		}
		
		double v = Double.POSITIVE_INFINITY;
		
		//moves through each action available
		for (int i = 0; i < state.actions().size(); i++) {
			states_evaluated++;
			
			//choses the lesser value of v or the value of the action
			v = Math.min(v, maxValue(state.result(state.actions().get(i))));
		}
		
		return v;
	}
	
	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.AdversarialSearch#search(edu.smcm.ai.search.AdversarialState)
	 */
	@Override
	public Action search(AdversarialState state) {
		// TODO Implement MiniMax.search()

		
		int maxIndex = 0;
		double max = Double.NEGATIVE_INFINITY;
		
		//moves through each of the actions within the current state
		for (int i = 0; i < state.actions().size(); i++) {
			//temp is used to find the best possible solution to the current state
			double temp = minValue(state.result(state.actions().get(i)));
			states_evaluated++;
			//the max value is switched if temp is greater than it. maxIndex is changed to the corresponding action's location
			if(temp>max){
				max = temp;
				maxIndex = i;
			}
		}
		//the optimal action is returned
		return state.actions().get(maxIndex);
	}
}
