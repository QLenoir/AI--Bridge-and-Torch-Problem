package uk.ac.rgu.coursework.test;

import cm3038.search.SearchProblem;
import uk.ac.rgu.coursework.Bank;
import uk.ac.rgu.coursework.Person;
import uk.ac.rgu.coursework.TorchProblem;
import uk.ac.rgu.coursework.TorchState;

/**
 * Test class to verify the isGoal method is working
 * @author Quentin
 */
public class TestIsGoal {
    
    public static void main(String[] arg)
    {
    
    Person[] p1 = {new Person("Quentin",5,Bank.EASTERN),new Person("Lison",3,Bank.EASTERN),new Person("Nathan",4,Bank.WESTERN),new Person("Eva",6,Bank.EASTERN),new Person("Elodie",(5),Bank.EASTERN),new Person("Juliette",2,Bank.WESTERN)};
    Person[] p2 = {new Person("Quentin",5,Bank.WESTERN),new Person("Lison",3,Bank.WESTERN),new Person("Nathan",4,Bank.EASTERN),new Person("Eva",6,Bank.WESTERN),new Person("Elodie",(5),Bank.WESTERN),new Person("Juliette",2,Bank.EASTERN)};
    Person[] p3 = {new Person("Quentin",5,Bank.WESTERN),new Person("Lison",3,Bank.WESTERN),new Person("Nathan",4,Bank.EASTERN),new Person("Eva",6,Bank.WESTERN),new Person("Elodie",(5),Bank.WESTERN),new Person("Juliette",2,Bank.EASTERN)};


    TorchState initialState,goalState,anotherState;	

    initialState = new TorchState(2,p1,Bank.EASTERN);       //Initial State
    goalState = new TorchState(2,p2,Bank.WESTERN);          //Goal State
    anotherState = new TorchState(2,p3,Bank.WESTERN);       //Another random State

    System.out.println("Goal state:\n"+goalState.toString());

    SearchProblem problem = new TorchProblem(initialState,goalState);	

    System.out.println(initialState.toString());
    System.out.println(problem.isGoal(initialState) ? "is a goal state\n" : "is not a goal state\n"); //Should return false

    System.out.println(anotherState.toString());
    System.out.println(problem.isGoal(anotherState) ? "is a goal state\n" : "is not a goal state\n"); //Should return true

    System.out.println(anotherState.toString());
    System.out.println(problem.isGoal(anotherState) ? "is a goal state\n" : "is not a goal state\n"); //Should return true
    } 
}
