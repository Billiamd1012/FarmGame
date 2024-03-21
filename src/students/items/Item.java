package students.items;

public abstract class Item {
    //Every item tracks its age, maturation age, death age, and montery value. 
    int age = 0;
    int maturationAge;
    int deathAge;
    float monetaryValue;

    public Item(int age, int maturationAge, int deathAge, float monetaryValue){
        this.age = age;
        this.maturationAge = maturationAge;
        this.deathAge = deathAge;
        this.monetaryValue = monetaryValue;
    }
    //increases the age variable by 1
    public void tick(){
        age += 1;
    }
    //sets the age of an item
    public void setAge(int age){
        this.age = age;
    }
    //returns whether the item’s age is greater than it’s death age
    public boolean died(){
        if (age >= deathAge){
            return true;
        }
        return false;
    }

    //will check this Item against any other object, returning true if both objects have the same age, deathAge, maturation, and monetary value.
    @Override
    public boolean equals(Object other){
        if (other instanceof Item){
            if (this.age == ((Item)other).age && this.deathAge == ((Item)other).deathAge && this.maturationAge == ((Item)other).maturationAge && this.monetaryValue == ((Item)other).monetaryValue){
                return true;
            }
        }
        return false;
    }

    //an abstract function implemented by subclasses returning the string representation of each item.
    @Override
    public abstract String toString();
}
