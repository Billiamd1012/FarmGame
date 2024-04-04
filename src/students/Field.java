package students;

import students.items.Apples;
import students.items.Grain;
import students.items.Food;
import students.items.Item;
import students.items.Soil;
import students.items.UntilledSoil;
import students.items.Weed;

public class Field {
	private Item[][] field;
	private Item current_tile;
	private Food current_food;
	private String current_row;
	private String all_rows = "";
	private int height;
	private int width;

	public Field(int height, int width)
	{
		this.height = height;
		this.width = width;
		//create 2 dimensional array with height and width variables, using soil item.
		field = new Item[height][width];
		for (int y = 0; y < field.length; y++){
			for (int x = 0; x < field[y].length; x++){
				field[y][x] = new Soil();
			}
		}
	}

	//returns the height of the field.
	public int getHeight(){
		return height;
	}
	//returns the width of the field.
	public int getWidth(){
		return width;
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

	//•	toString() – overridden function prints out a numbered grid with the contents of each location. 
	@Override
	public String toString(){
		all_rows = "";
		//print out each row of items
		for (int y = 0; y <= field.length; y++){
			current_row = "";
			//for first row print out spacer and then col count
			if (y == 0){
				current_row += "  ";
				for (int x = 1; x <= field[1].length; x++){
					current_row += x + " ";
				}
			}
			// else print out row number and contents of row
			else{
				if (y >= 10){
					current_row += y;
				}
				else{
					current_row += y + " ";
				}
		
				for (int x = 0; x < field[y-1].length; x++){
					current_tile = field[y-1][x];
					current_row += current_tile.toString() + " ";
				}
			}
			all_rows += current_row + "\n";
		}
		return all_rows;
	}

	//	till(int, int) – takes in the location in the field to till and turn into new Soil, regardless of what’s there currently.
	public void till(int x, int y){
		field[y][x] = new Soil();
	}

	//	get(int, int) – returns a copy of the item at that location.
	public Item get(int x, int y){
		return field[y][x];
	}

	//	plant(int, int, Item) – stores a given Item at a given location
	public void plant(int x, int y, Item item){
		field[y][x] = item;
	}
	//	getValue() – returns the total monetary value of each item in the field.
	public float getValue(){
		float total_value = 0;
		for (int y = 0; y < field.length; y++){
			for (int x = 0; x < field[y].length; x++){
				current_tile = field[y][x];
				//if current_tile of subclass food then get value and add to total_value
				if (current_tile instanceof Food){
					current_food = (Food) current_tile;
					total_value += current_food.getValue();
				}
			}
		}
		return total_value;
	}
	//⦁	getSummary() – returns a string representing the quantities and overall value of the field, as shown below. Alignment is important, and remember the Food items only have value if they’re past their maturation age.
	public String getSummary(){
		int apple_count = 0;
		int grain_count = 0;
		int weed_count = 0;
		int untilled_soil_count = 0;
		int soil_count = 0;
		int total_value = 0;
		int apple_generations = Apples.getGenerationCount();
		int grain_generations = Grain.getGenerationCount();

		for (int y = 0; y < field.length; y++){
			for (int x = 0; x < field[y].length; x++){
				current_tile = field[y][x];
				if (current_tile instanceof Food){
					current_food = (Food) current_tile;
					if (current_food instanceof Apples){
						apple_count++;
					}
					else{
						grain_count++;
					}
					total_value += current_food.getValue();
				}
				else if (current_tile instanceof Weed){
					weed_count++;
				}
				else if (current_tile instanceof UntilledSoil){
					untilled_soil_count++;
				}
				else if (current_tile instanceof Soil){
					soil_count++;
				}
			}
		}

		return 
		"Apples:        " + apple_count + 
		"\nGrain          " + grain_count + 
		"\nWeeds:         " + weed_count + 
		"\nUntilled:      " + untilled_soil_count + 
		"\nSoil:          " + soil_count + 
		"\nFor a total of " + total_value +
		"\nTotal apples created: " + apple_generations + 
		"\nTotal grain created: " + grain_generations;
	}
}
