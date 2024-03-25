package students;

import students.items.Item;
import students.items.Soil;
import students.items.UntilledSoil;
import students.items.Weed;

public class Field {
	private Item[][] field;
	private Item current_tile;

	public Field(int height, int width)
	{
		//create 2 dimensional array with height and width variables, using soil item.
		field = new Item[height][width];
		for (int y = 0; y < field.length; y++){
			for (int x = 0; x < field[y].length; x++){
				field[y][x] = new Soil();
			}
		}
	}
	//•	tick() – each time this is called, every Item in the field must have it’s tick() function called to increase the age of each item. 
	public void tick(){
		for (int y = 0; y < field.length; y++){
			for (int x = 0; x < field[y].length; x++){
				current_tile = field[y][x];
				current_tile.tick();
				//If an Item is Soil, 20% of the time that location will turn into a new Weed. 
				if(current_tile.toString() == ".") {
					if (Math.random() >= 0.8){
						field[y][x] = new Weed();
					}
				}
				//If an item in the field has died after ageing, it turns into UntilledSoil.
				if (current_tile.died()) {
					field[y][x] = new UntilledSoil();
				}
			}
		}
	}
}
