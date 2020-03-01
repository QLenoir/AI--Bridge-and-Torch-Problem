package uk.ac.rgu.coursework.test;

import uk.ac.rgu.coursework.Bank;
import uk.ac.rgu.coursework.TorchState;
import uk.ac.rgu.coursework.Person;

/**
 * Test class to verify how a TorchState is displayed as a String
 * @author Quentin
 */
public class TestToString {
    public static void main(String[] arg)
    {
        
    Person[] persons = {new Person("Quentin",5,Bank.EASTERN),new Person("Lison",3,Bank.EASTERN),new Person("Nathan",4,Bank.WESTERN),new Person("Eva",6,Bank.EASTERN),new Person("Elodie",(5),Bank.EASTERN),new Person("Juliette",2,Bank.WESTERN)};			//print out Sq8State as a String
    
    TorchState s = new TorchState(2, persons, Bank.EASTERN);
    
    System.out.println("Initial :\n"+s.toString());
    } 
} 

