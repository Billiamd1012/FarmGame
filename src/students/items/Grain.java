package students.items;

//Grain is a type of Food.
// Grain cost $1 to buy as an item to plant. 
public class Grain extends Food{
    //count to keep track of the number of grain generations that have been
    private static int generationCount = 0;


    //Its maturation time is 2, death age is 6, and monetary value is 2. 
    public Grain(){
        super(0, 2, 6, 2);
        generationCount += 1;
    }
	
    //returns a string representation of this class, G if it mature and g if not
    public String toString(){
        if (age < maturationAge){
            return "g";
        }
        return "G";
    }

    //getGenerationCount returns the total number of grain objects that have been instantiated.
    public static int getGenerationCount(){
        return generationCount;
    }
}
