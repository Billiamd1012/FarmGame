package students;
import java.util.Scanner;

import students.items.Food;
import students.items.Item;


public class Farm {
	private Field field;
	private int funds;
	private boolean running = true;
	private Scanner scanner = new Scanner(System.in);


	public Farm(int fieldWidth, int fieldHeight, int startingFunds)
	{
		field = new Field(fieldWidth, fieldHeight);
		funds = startingFunds;
	}
	
	public void run()
	{
		while (running) {			

			//print a summary of the farm
			System.out.println("\n\n\n" + field);
			System.out.print("Bank balance: $" + funds +"\n\n");
			//list next actions
			printNextActions();

			//read in the next action
			String action = scanner.nextLine();
			
			//process the action
			switch (action.charAt(0)) {
				case 't':
					tillAction(action);
					break;
				case 'h':
					harvestAction(action);
					break;
				case 'p':
					plantAction(action);
					break;
				case 's':
					//personal note: I think the user should be able to check the summary without the game progressing so I have chosen to not call field.tick() here
					System.out.println(field.getSummary());
					break;
				case 'w':
					field.tick();
					break;
				case 'q':
					//print out a summary and exit run loop 
					System.out.println("Finished the game with a balance of $" + funds);
					running = false;
					break;
				default:
					System.out.println("Invalid action. Please enter a valid action.");
					break;
			}
		}
		scanner.close();
	}

	private boolean checkCoordinates(String[] coordinates) {
		try {
			int x = Integer.parseInt(coordinates[1]);
			int y = Integer.parseInt(coordinates[2]);
	
			if (x < 1 || x > field.getWidth() || y < 1 || y > field.getHeight()) {
				System.out.println("Invalid coordinates. Please enter coordinates within the field's width and height.");
				return false;
			}
	
			return true;
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter integers for the coordinates.");
			return false;
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid input. Please enter coordinates.");
			return false;
		}

	}
	
	private void tillAction(String action) {
		String[] tillStrings = action.split(" ");
		if (checkCoordinates(tillStrings)) {
			int tx = Integer.parseInt(tillStrings[1]);
			int ty = Integer.parseInt(tillStrings[2]);
			field.till(tx-1, ty-1);
			field.tick();
		}
	}
	
	private void plantAction(String action) {
		String[] plantStrings = action.split(" ");
		if (checkCoordinates(plantStrings)) {
			int px = Integer.parseInt(plantStrings[1]);
			int py = Integer.parseInt(plantStrings[2]);
		
			System.out.println("Enter  ");
			System.out.println("- 'a' to buy an apple for $2");
			System.out.println("- 'g' to buy a grain for $1");
			String plant = scanner.nextLine();
	
			switch (plant.charAt(0)) {
				case 'a':
					if (funds >= 2) {
						funds -= 2;
						field.plant(px-1, py-1, new students.items.Apples());
					}
					break;
				case 'g':
					if (funds >= 1) {
						funds -= 1;
						field.plant(px-1, py-1, new students.items.Grain());
					}
					break;
				default:
					System.out.println("Invalid action. Please enter a valid action.");
					break;
			}
			field.tick();
		}
	}
	
	private void harvestAction(String action) {
		String[] harvestStrings = action.split(" ");
		if (checkCoordinates(harvestStrings)) {
			int hx = Integer.parseInt(harvestStrings[1]);
			int hy = Integer.parseInt(harvestStrings[2]);
	
			Item currentTile = field.get(hx-1, hy-1);
			if (currentTile instanceof Food) {
				Food food = (Food) currentTile;
				funds += food.getValue();
			}
			field.till(hx-1, hy-1);
			field.tick();
		}
	}

	private void printNextActions() {
		System.out.println("Enter your next action: ");
		System.out.println("t x y: till");
		System.out.println("h x y: harvest");
		System.out.println("p x y: plant");
		System.out.println("s: field summary");
		System.out.println("w: wait");
		System.out.println("q: quit");
	}
}
