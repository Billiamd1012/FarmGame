package students.items;

public class Food extends Item{

    public Food(int age, int maturationAge, int deathAge, float monetaryValue){
        super(age, maturationAge, deathAge, monetaryValue);
    }
    //for food items, returns their value ONLY if the item’s age is passed it’s maturation age (fully grown and ready for harvest)
	public float getValue(){
        if (age >= maturationAge){
            return monetaryValue;
        }
        return 0f;
    }

    //toString method, will be overriden by each food subclass
    public String toString(){
        return "f";
    }

}
