package students.items;

public class Apples extends Food{

    //Its maturation time is 3, death age is 5, and monetary value is 3. Apple is a type of Food. Apples cost $2 to buy as an item to plant. A function called getGenerationCount returns the total number of apple objects that have been instantiated.
    public Apples(){
        super(0, 3, 5, 3f);
    }

    //returns a string representation of this class, A if it mature and a if not
    public String toString(){
        if (age < maturationAge){
            return "a";
        }
        return "A";
    }
}
