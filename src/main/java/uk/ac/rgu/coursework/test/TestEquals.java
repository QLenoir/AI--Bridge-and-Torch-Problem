package uk.ac.rgu.coursework.test;

import uk.ac.rgu.coursework.Bank;
import uk.ac.rgu.coursework.Person;
import uk.ac.rgu.coursework.TorchState;


/**
 * Test class to verify that the method to compare 2 TorchState is working
 * @author Quentin
 */
public class TestEquals {
    public static void main(String[] arg){

        Person[] p1 = {new Person("Quentin",5,Bank.EASTERN),new Person("Lison",3,Bank.EASTERN),new Person("Nathan",4,Bank.WESTERN),new Person("Eva",6,Bank.EASTERN),new Person("Elodie",(5),Bank.EASTERN),new Person("Juliette",2,Bank.WESTERN)};
        Person[] p2 = {new Person("Quentin",5,Bank.EASTERN),new Person("Lison",3,Bank.EASTERN),new Person("Nathan",4,Bank.WESTERN),new Person("Eva",6,Bank.EASTERN),new Person("Elodie",(5),Bank.EASTERN),new Person("Juliette",2,Bank.WESTERN)};
        Person[] p3 = {new Person("Quentin",5,Bank.WESTERN),new Person("Lison",3,Bank.EASTERN),new Person("Nathan",4,Bank.WESTERN),new Person("Eva",6,Bank.EASTERN),new Person("Elodie",(5),Bank.EASTERN),new Person("Juliette",2,Bank.WESTERN)};

        TorchState s1 = new TorchState(2, p1, Bank.EASTERN); //Original state
        TorchState s2 = new TorchState(2, p2, Bank.EASTERN); //Same State
        TorchState s3 = new TorchState(2, p2, Bank.WESTERN); //Same Array but different Torch
        TorchState s4 = new TorchState(2, p3, Bank.EASTERN); //Same Torch but different Arrays
        

        System.out.println("State 1:\n"+s1.toString());
        System.out.println("State 2:\n"+s2.toString());
        System.out.println("State 3:\n"+s3.toString());
        System.out.println("State 4:\n"+s4.toString());

        System.out.println("State 1 equals State 2: "+s1.equals(s2));		//should be true (Arrays have the same values)
        System.out.println("State 1 equals State 3: "+s1.equals(s3));           //should be false (Torch isn't on the same bank)
        System.out.println("State 1 equals State 4: "+s1.equals(s4));		//should be false (Arrays have different values)
    }
}
