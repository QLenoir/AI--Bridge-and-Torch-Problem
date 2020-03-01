package uk.ac.rgu.coursework.test;

import uk.ac.rgu.coursework.Bank;
import uk.ac.rgu.coursework.Person;
import uk.ac.rgu.coursework.TorchAction;
import uk.ac.rgu.coursework.TorchState;


/**
 * Test class to verify if the apply Action method of a TorchState is working with 2 examples
 * @author Quentin
 */
public class TestApplyAction {

    public static void main(String[] arg){

        Person[] persons = {new Person("Quentin",5,Bank.EASTERN),new Person("Lison",3,Bank.EASTERN),new Person("Nathan",4,Bank.WESTERN),new Person("Eva",6,Bank.EASTERN),new Person("Elodie",(5),Bank.EASTERN),new Person("Juliette",2,Bank.WESTERN)};	
        TorchState before = new TorchState(2,persons,Bank.EASTERN);
        
        //Single person action
        TorchAction action = new TorchAction(new Person[]{persons[4]},Bank.WESTERN,before.costEstimated(new Person[]{persons[4]}));	
        TorchState after = before.applyAction(action);
        
        //Mulitple person action
        TorchAction action2 = new TorchAction(new Person[]{persons[4],persons[1]},Bank.WESTERN,before.costEstimated(new Person[]{persons[4],persons[1]}));	
        TorchState after2 = before.applyAction(action2);

        System.out.println("Before:\n"+before.toString());
        System.out.println("Action:\n"+action.toString()+"\n");
        System.out.println("After:\n"+after.toString());
        
        System.out.println("Before:\n"+before.toString());
        System.out.println("Action:\n"+action2.toString()+"\n");
        System.out.println("After:\n"+after2.toString());
    }
}
