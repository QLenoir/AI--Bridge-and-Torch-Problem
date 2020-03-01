package uk.ac.rgu.coursework;

import cm3038.search.*;

/**
 * Main class of the program that allows to use the cm3038 search library for the Bridge and Torch problem 
 * @author Quentin
 */
public class RunTorch{
    public static void main(String[] arg){
        
        ///////////////////////////////////////////////////////////// Regular bridge and torch problem /////////////////////////////////////////////// 
        Person[] p1 = {new Person("Quentin",1,Bank.EASTERN),new Person("Lison",2,Bank.EASTERN),new Person("Eva",5,Bank.EASTERN),new Person("Elodie",8,Bank.EASTERN)};   //initial state as an array of Person objects
        Person[] p2 = {new Person("Quentin",1,Bank.WESTERN),new Person("Lison",2,Bank.WESTERN),new Person("Eva",5,Bank.WESTERN),new Person("Elodie",8,Bank.WESTERN)};	//goal state as an array Person objects

        TorchState initialState,goalState;	//The initial and goal states

        int initialCapacity = 2;                //Normal capacity of the bridge
        
        initialState = new TorchState(initialCapacity,p1,Bank.EASTERN);         //Create initial state
        goalState = new TorchState(initialCapacity,p2,Bank.WESTERN);		//Create goal state

        SearchProblem problem = new TorchProblem(initialState,goalState);	//Create problem instance
        System.out.println("Searching...");		
        Path path=problem.search();                                             //Perform search, get result
        System.out.println("Done!");			
        if (path==null)                                                         //If path is null, there is no solution
            System.out.println("No solution");
        else {
            path.print();							//Otherwise print path
            System.out.println("Nodes visited: "+problem.nodeVisited);
            System.out.println("Cost: "+path.cost+"\n");
        }
        
        ///////////////////////////////////////////////////// General problem with customizable parameters /////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\n General Problem :");
        Person[] p3 = {new Person("Quentin",1,Bank.EASTERN),new Person("Lison",2,Bank.EASTERN),new Person("Eva",5,Bank.EASTERN),new Person("Elodie",8,Bank.EASTERN),new Person("Nathan",9,Bank.WESTERN),new Person("Juliette",10,Bank.WESTERN)};//initial state as an array
        Person[] p4 = {new Person("Quentin",1,Bank.WESTERN),new Person("Lison",2,Bank.WESTERN),new Person("Eva",5,Bank.WESTERN),new Person("Elodie",8,Bank.WESTERN),new Person("Nathan",9,Bank.EASTERN),new Person("Juliette",10,Bank.EASTERN)};
        
        TorchState initialState2,goalState2;
        
        int capacity = 3;
        
        initialState2 = new TorchState(capacity,p3,Bank.EASTERN);	
        goalState2 = new TorchState(capacity,p4,Bank.WESTERN);
        
        SearchProblem problem2 = new TorchProblem(initialState2,goalState2);	
        System.out.println("Searching...");		
        Path path2=problem2.search();				
        System.out.println("Done!");			
        if (path2==null)							
            System.out.println("No solution");
        else {
            path2.print();							
            System.out.println("Nodes visited: "+problem2.nodeVisited);
            System.out.println("Cost: "+path2.cost+"\n");
        } 
    } 
} 
