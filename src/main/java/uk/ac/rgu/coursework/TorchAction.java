package uk.ac.rgu.coursework;

import cm3038.search.Action;

/**
 * 
 * @author Quentin
 */
public class TorchAction extends Action {

    private final Person[] personsToMove;           //Array of Person that will cross the bridge
    private final Bank destination;                 //Bank where the Persons are crossing

    /**
     * Constructor for the TorchAction class
     * @param personsToMove An array of Person that are crossing
     * @param destination The Bank where the Persons are heading
     * @param cost The cost of moving the Persons (the highest from the array)
     */
    public TorchAction(Person[] personsToMove, Bank destination,double cost) {
        super.cost = cost;                                          //We give our action the cost of time to cross as its cost
        this.personsToMove = personsToMove;                         //The array of Person to move
        this.destination = destination;                             //The Bank where the Persons are moving
    }
    
    /**
     * Method used to describe the action as a String
     * @return a String containing the names of the Persons that have been moved and the total cost
     */
    @Override
    public String toString() {
        String names = "";
            for(int i = 0; i < this.personsToMove.length;i++){
                names += this.personsToMove[i].getName() + ", ";                //Adding the persons name to the String
            }
            names = names.substring(0, names.length() - 2);                     //To remove the last coma
                
        switch(destination){
            case WESTERN:
                return "Move torch, " + names + " from EAST to WEST (cost : " + super.cost + ")";
            case EASTERN:
                return "Move torch, " + names + " from WEST to EAST (cost : " + super.cost + ")";
            default: throw new IllegalArgumentException("Error : Bank must be either WESTERN or EASTERN"); //If Destination is not a Bank, throws an error
        }        
    }

    //Getter methods
    public Bank getDestination() {
        return this.destination;
    }
    
    public Person[] getPersonsToMove(){
        return this.personsToMove;
    }
}
