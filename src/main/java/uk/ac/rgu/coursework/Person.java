package uk.ac.rgu.coursework;

/**
 * Class to represent the persons trying to cross the bridge.
 * Each person has a name, a time to cross and a bank where it stands
 * @author Quentin
 */
public class Person {
    
    private String name;                                    //Name of the person
    private int time;                                       //Time to cross the bridge 
    private Bank bank;                                      //Current Bank where the person is standing
    
    /**
     * Constructor of the class Person
     * @param name Name of the person
     * @param time Time to cross the bridge 
     * @param bank Current Bank where the person is standing
     */
    public Person(String name,int time, Bank bank){
        this.name = name;
        this.time = time;
        this.bank = bank;
    }

    /**
     * Method used to know if 2 Person objects are equal. They are if their name and time are the same
     * @param p A Person object to compare with the current one 
     * @return true if name and time are the same and false if not
     */
    public boolean equals(Person p){
        return p.name == this.name && p.time == this.time; 
    }
    
    /**
     * Method to display a Person object as a String
     * @return A String containing the attributes of the Person
     */
    public String toString(){
        return this.name + " " + this.time + " " + this.bank;
    }
    
    //Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
    
}
