package students.items;

public class Soil extends Item{
    //Soil are items that have an infinite maturation and death age, and -1 value. They can never die.
    public Soil(){
        super(0, Integer.MAX_VALUE, Integer.MAX_VALUE, -1);
    }
    //and are represented by the string “#”.
    public String toString(){
        return ".";
    }

}
