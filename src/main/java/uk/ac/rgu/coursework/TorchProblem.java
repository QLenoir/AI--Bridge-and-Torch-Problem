package uk.ac.rgu.coursework;

import cm3038.search.Node;
import cm3038.search.State;
import cm3038.search.informed.BestFirstSearchProblem;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class to represent the Bridge and Torch problem, with an initial State and a goal State to reach.
 * The evaluation method and heuristic used are declared here
 * @author Quentin
 */
public class TorchProblem extends BestFirstSearchProblem {
    
    /**
     * Constructor for the TorchProblem class, which take an initial State and a goal State as parameters 
     * @param start initial TorchState
     * @param goal  goal TorchState to reach
     */
    public TorchProblem(State start,State goal) {
        super(start,goal);
    }

    /**
     * Method to determine if a given state is a goal state
     * @param state TorchState that verify
     * @return true if the given parameter is a goal State and false if not
     */
    @Override
    public boolean isGoal(State state)
    {
        return state.equals(this.goalState);
    }

    /**
     * The evaluation function is used to evaluate which node is the best to explore next. 
     * It requires a heuristic function and the cost of the path up to the node. 
     * @param node The node to evaluate 
     * @return The score of the node. The lower the score is, the better the node.
     */
    @Override
    public double evaluation(Node node) {
        return this.heuristic((TorchState)node.state) + node.getCost();
    }
    
    /**
     * The heuristic function is used to estimate the distance between a given node and the goal state.
     * It must be admissible (never overestimating the cost) if implementing the A* algorithm. 
     * It is important to note that this heuristic is relaxing the torch rule of the problem, it doesn't take into account the Bank of the torch.
     * @param currentState The State of which the nodes needs to be evaluated
     * @return An estimated distance(cost) to the goal State 
     */
    public double heuristic(TorchState currentState){
        double costGoal = 0.0;                                                  //The cost to reach the goal State
        ArrayList<Person> misplacedPersonsEast = new ArrayList<>();             //An array containing the misplaced Persons on the EASTERN Bank
        ArrayList<Person> misplacedPersonsWest = new ArrayList<>();             //An array containing the misplaced Persons on the WESTERN Bank
        int j;                                                                  //counter
        
        for (Person p : currentState.getPersons()) {
            j = 0;
            while (j < ((TorchState)super.goalState).getPersons().length && !p.equals(((TorchState)super.goalState).getPersons()[j])) {
                //This is done to find which to which index the Persons should be compared
                j++;
            }
            if (p.getBank() != ((TorchState)super.goalState).getPersons()[j].getBank()) {
                //If the Person is not on the Bank he is supposed to be
                switch (p.getBank()) {
                //Adds the misplaced Person to the correct array
                    case WESTERN:
                        misplacedPersonsWest.add(p);
                        break;
                    case EASTERN:
                        misplacedPersonsEast.add(p);
                        break;
                    default : throw new IllegalArgumentException("Error : Bank must be either WESTERN or EASTERN"); //Throws an error if Bank isn't EASTERN or WESTERN
                }
            }
        }
        
        Comparator<? super Person> c = (Person p1, Person p2) -> { //This comparator will sort the arrays from the highest cost to the lowest
            if(p1.getTime() < p2.getTime()){
                return 1;
            } else {
                if(p1.getTime() == p2.getTime()){
                    return 0;
                } else {
                    return -1;
                }
            }
        };
        
        
        misplacedPersonsEast.sort(c); //Sorting the arrays
        misplacedPersonsWest.sort(c);
              
        costGoal += this.processBank(currentState,misplacedPersonsEast); //We estimate the cost for each bank
        costGoal += this.processBank(currentState,misplacedPersonsWest);
              
        return costGoal; //Returning the overall cost
    }

    /**
     * This private method is calculating the minimal cost of a given ArrayList of Person to be moved to the correct Bank.
     * @param currentState The current State where the Persons are
     * @param misplacedPersons The ArrayList of misplaced Persons of the same Bank
     * @return The minimal cost to move all the persons to the correct Bank
     */
    private double processBank(TorchState currentState, ArrayList<Person> misplacedPersons) {
        double costTemp = 0.0;
        double costGoal = 0.0;
        if(misplacedPersons.size()==1){
            return misplacedPersons.get(0).getTime();                           //If there is only one person on the Bank it directly returns the cost of that person
        }
        for(int j = 0; j < misplacedPersons.size(); j++){
            if(j % currentState.getBridgeCapacity() == currentState.getBridgeCapacity()-1 ){    //When calculating the cost we must not forget that the 
                                                                                                //cost will be the highest person crossing the bridge, 
                                                                                                //and that several persons may cross at the same time.
                                                                                                //It has been demonstrated that the optimal solution is 
                                                                                                //to make the highest costs cross together
                for(int k = 0; k<currentState.getBridgeCapacity();k++){
                    if(costTemp < misplacedPersons.get(j-k).getTime()){
                        costTemp = misplacedPersons.get(j-k).getTime();         //Temporary cost to hold the highest cost of the persons crossing at the same time
                    }
                }
                costGoal += costTemp;                                           //Adding the cost to the total coast of the ArrayList
                costTemp = 0.0;                                                 //reseting the temporary cost
            }
        }
        return costGoal;
    }
    
}
