package uk.ac.rgu.coursework.test;

import uk.ac.rgu.coursework.Bank;
import uk.ac.rgu.coursework.Person;
import uk.ac.rgu.coursework.TorchState;

public class TestSuccessor {
    public static void main(String[] arg){

        Person[] persons = {new Person("Quentin",5,Bank.EASTERN),new Person("Lison",3,Bank.EASTERN),new Person("Nathan",4,Bank.WESTERN),new Person("Eva",6,Bank.EASTERN),new Person("Elodie",(5),Bank.EASTERN),new Person("Juliette",2,Bank.WESTERN)};	       
        TorchState s = new TorchState(3,persons,Bank.EASTERN);			//create state

        System.out.println("State:\n"+s.toString());
        System.out.println("All action-state:\n"+s.successor().toString());

        Person[] persons2 = {new Person("Quentin",5,Bank.EASTERN),new Person("Lison",3,Bank.WESTERN),new Person("Nathan",4,Bank.WESTERN),new Person("Eva",6,Bank.EASTERN),new Person("Elodie",(5),Bank.EASTERN),new Person("Juliette",2,Bank.WESTERN)};	       
        TorchState s2 = new TorchState(3,persons,Bank.WESTERN);					

        System.out.println("State:\n"+s2.toString());
        System.out.println("All action-state:\n"+s2.successor().toString());
    } //end method
} //end class