package uk.ac.rgu.coursework;

import cm3038.search.ActionStatePair;
import cm3038.search.State;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to represent a state in the Torch problem
 * It contains an array of persons to move, the position of the torch and the capacity of the bridge. The possible
 * moves are also held in class attributes
 * @author Quentin
 */
public class TorchState implements State {

    private static final String LEFT_BRIDGE = "|======("; //Constant String of the left part of the bridge
    private static final String RIGHT_BRIDGE = ")======| "; //Constant String of the right part of the bridge
    
    private final int bridgeCapacity;                       //Capacity of persons that the bridge can support
    private final Person[] persons;                         //An array of Person objects in the State
    private Bank torch;                                     //A Bank symbolizing the Torch position
    private final ArrayList<Person> possibleMovesList;      //An Arraylist containing the different moves to have a dynamic size array
    private Person[] possibleMoves;                         //Array containing the possibles moves once they have been searched
    
    /**
     * Constructor for the TorchState class
     * @param capacity int that represent the capacity of the bridge
     * @param persons array of Person in the problem containing their positions
     * @param torch Bank Enum holding the position of the torch
     */
    public TorchState(int capacity, Person[] persons,Bank torch){
        this.bridgeCapacity = capacity;
        this.persons = persons;
        this.torch = torch;
        this.possibleMovesList = new ArrayList<>();
    }
    
    /**
     * Successor function used to determine the list of possible moves available given the current state
     * @return A list of State and Actions that can be reached from the current State
     */
    @Override
    public List<ActionStatePair> successor() { 
        
        List<ActionStatePair> results = new ArrayList<>();	
        
        //One person only moves
        for (Person i : this.persons) {
            if (i.getBank() == this.torch) { //To move the person should be on the same bank as the torch
                TorchAction action = new TorchAction(new Person[]{i}, this.bankSwitch(torch), this.costEstimated(new Person[]{i}));
                TorchState nextState = this.applyAction(action);
                ActionStatePair actionStatePair = new ActionStatePair(action,nextState);
                results.add(actionStatePair);
            }
        }
        
        //2 or more person moves
        for(int c = this.bridgeCapacity; c >= 2; c-- ){
            this.printCombination(this.persons, this.persons.length, c); //Get all combinations of a number c of Person
        
            this.possibleMoves = new Person[this.possibleMovesList.size()]; //conversion from the ArrayList to array
            for (int i = 0; i < this.possibleMovesList.size(); i++) {
                this.possibleMoves[i] = this.possibleMovesList.get(i);   
            }

            for (int i = 0; i < this.possibleMoves.length; i++) {
                if(i % c == c-1){                                               //To separate correctly the combinations we have to split the array at each c-1 
                    int j = 0;
                    boolean delete = false;
                    while(j < c && !delete){
                        if(this.possibleMoves[i-j].getBank() != this.torch){    //If one person is not on the same bank as the torch the combination is ignored
                            delete = true;
                        }
                        j++;
                    }
                    if(!delete){    

                        Person[] personsToMove = new Person[c];                 //Otherwise the combination is out in an array
                        for (int m = 0; m < c; m++) {
                            personsToMove[m] = this.possibleMoves[i-m];
                        }
                        TorchAction action = new TorchAction(personsToMove,this.bankSwitch(this.torch),this.costEstimated(personsToMove));
                        TorchState nextState = this.applyAction(action); //The action is created
                        ActionStatePair actionStatePair = new ActionStatePair(action,nextState);
                        results.add(actionStatePair);  //Then the possible State is added to the list of possible moves
                    }
                } 
            }     
        }
        
	return results; //Return the list of possible moves
    }
    
    /**
     * Method called when the State is displayed as a String
     * @return A string showing the positions of every persons and their time, the bridge capacity and the torch 
     */
    @Override
    public String toString(){
        String eastern = "";
        String western = "";
      
        for (Person i : this.persons) { //Loop to create a string for each bank
            switch(i.getBank()){
                case EASTERN:
                    eastern += i.getName() + "(" + i.getTime() + ") ";
                    break;
                case WESTERN:
                    western += i.getName() + "(" + i.getTime() + ") ";
                    break;
                default: throw new IllegalArgumentException("Error : Bank must be either WESTERN or EASTERN"); //If the bank is not WESTERN or EASTERN throws an error
            }
        }
        switch(this.torch){ //Returns the correct
            case EASTERN : return "Torch " + eastern + LEFT_BRIDGE + this.bridgeCapacity + RIGHT_BRIDGE + western;
            case WESTERN : return eastern + LEFT_BRIDGE + this.bridgeCapacity + RIGHT_BRIDGE + "Torch " + western;
            default: throw new IllegalArgumentException("Error : Bank must be either WESTERN or EASTERN"); //If the bank is not WESTERN or EASTERN throws an error
        }
    }
    
    /**
     * Method called to verify if 2 States are equal.
     * 2 States are equal if the torch and every person are on the same bank in both States
     * @param obj Object to be compared with this TorchState object
     * @return true if the States are equal and false if they're not
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof TorchState){                          //First verify if the object given in paramter is a TorchState
            if(this.torch != ((TorchState) obj).getTorch()){    //Verify if the torch is on the same bank 
                return false;
            }
            
            for (int i = 0;i<this.persons.length;i++){
                if(this.persons[i].getBank() != ((TorchState) obj).persons[i].getBank()){ //Verify if each person is on the same side in both States
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to hash every state in a unique int
     * Here I add or substract the time of the persons according to their side, and same for the torch 
     * @return A unique code that represent the current TorchState
     */
    @Override
    public int hashCode() {
        int code = 0;
        for(Person person : this.persons){
            if(person.getBank()==Bank.EASTERN){
                code += person.getTime();
            } else {
                code -= person.getTime();
            }
        }
        if(this.torch == Bank.EASTERN){
            code += 42;
        } else {
            code -= 42;
        }
        return code;
    }

    /**
     * Method that will create a new State with an Action given in parameter
     * @param action Action object to apply to the current TorchState
     * @return TorchState after the Action has been applied to it
     */
    public TorchState applyAction(TorchAction action) {
        
        TorchState after = this.clone();
        
        for(int i = 0 ; i < persons.length ; i++){
            for (Person j : action.getPersonsToMove()) {
                if (after.persons[i].equals(j)) {
                    after.persons[i].setBank(action.getDestination());
                }
            }
        }
        after.torch = action.getDestination();
        return after; 
    }
    
    /**
     * Method used to switch from one bank to another
     * @param bank The original bank
     * @return The opposite bank from the one given in parameter
     */
    public Bank bankSwitch(Bank bank){
        switch(bank){
            case EASTERN: return Bank.WESTERN;
            case WESTERN: return Bank.EASTERN;
            default: throw new IllegalArgumentException("Error : Bank must be either WESTERN or EASTERN"); //Throws an error if Bank isn't WESTERN or EASTERN
        }
    }
    
    //Algorithm to find all combinations of a given array taken and adapted from :
    //https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
    /**
     * @param arr Input Array of Persons
     * @param data Temporary array to store current combination 
     * @param start Staring and Ending indexes in arr[] 
     * @param end Ending indexes in arr[]
     * @param index Current index in data[] 
     * @param r Size of a combination to be printed 
     */
    public void combinationUtil(Person arr[], Person data[], int start, 
                                int end, int index, int r) 
    { 
        // Current combination if inserted into the possiblemoves ArrayList
        if (index == r) 
        { 
            for (int j=0; j<r; j++) 
                this.possibleMovesList.add(data[j]);
            return;
        } 
  
        // replace index with all possible elements. The condition 
        // "end-i+1 >= r-index" makes sure that including one element 
        // at index will make a combination with remaining elements 
        // at remaining positions 
        for (int i=start; i<=end && end-i+1 >= r-index; i++) 
        { 
            data[index] = arr[i]; 
            combinationUtil(arr, data, i+1, end, index+1, r); 
        } 
    } 
  
    //Taken and adapted from : https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
    // The main function that adds all combinations of size r 
    // in arr[] of size n. This function mainly uses combinationUtil() 
    public void printCombination(Person arr[], int n, int r) 
    { 
        // A temporary array to store all combination one by one 
        Person data[] = new Person[r]; 
  
        // Print all combination using temprary array 'data[]' 
        combinationUtil(arr, data, 0, n-1, 0, r); 
    }
    
    /**
     * Method used to find the highest cost in time when moving several people 
     * @param personsToMove array of Person objects that will be moving  
     * @return The highest time from the array given in parameters
     */
    public double costEstimated(Person[] personsToMove){
        double costCalculated = 0;
        for (Person i : personsToMove) {
            if (costCalculated < i.getTime()) {
                costCalculated = i.getTime();
            } 
        }
        return costCalculated;
    }
    
    /**
     * Method to clone the current State
     * @return a clone of this TorchState
     */
    public TorchState clone() {
        Person[] newPersons = new Person[persons.length];
        for(int i = 0; i < persons.length; i++){
            newPersons[i] = new Person(persons[i].getName(),persons[i].getTime(),persons[i].getBank());
        }
        return new TorchState(this.bridgeCapacity,newPersons,this.torch);
} //end method
    
    //Getter methods
    public int getBridgeCapacity() {
        return bridgeCapacity;
    }

    public Person[] getPersons() {
        return persons;
    }

    public Bank getTorch() {
        return torch;
    }
}
