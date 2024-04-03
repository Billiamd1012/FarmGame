package students;
import java.util.Scanner;

import students.items.Food;
import students.items.Item;


public class Farm {
	private Field field;
	private int funds;
	private boolean running = true;

	public Farm(int fieldWidth, int fieldHeight, int startingFunds)
	{
		field = new Field(fieldWidth, fieldHeight);
		funds = startingFunds;
	}
	
	public void run()
	{
		Scanner scanner = new Scanner(System.in);

		while (running) {			

			//print a summary of the farm
			System.out.print("\n\n\n");
			System.out.println(field);
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

	private void tillAction(String action) {
		String[] tillStrings = action.split(" ");
		int tx = Integer.parseInt(tillStrings[1]);
		int ty = Integer.parseInt(tillStrings[2]);
		field.till(tx-1, ty-1);
	}

	private void plantAction(String action) {
		String[] plantStrings = action.split(" ");
		int px = Integer.parseInt(plantStrings[1]);
		int py = Integer.parseInt(plantStrings[2]);

		Scanner scanner = new Scanner(System.in);

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
		scanner.close();
	}

	private void harvestAction(String action) {
		String[] harvestStrings = action.split(" ");
		int hx = Integer.parseInt(harvestStrings[1]);
		int hy = Integer.parseInt(harvestStrings[2]);
		Item currentTile = field.get(hx-1, hy-1);
		if (currentTile instanceof Food) {
			Food food = (Food) currentTile;
			funds += food.getValue();
		}
		field.till(hx-1, hy-1);
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
