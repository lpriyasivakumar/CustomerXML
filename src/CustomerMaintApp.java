import java.util.Scanner;

public class CustomerMaintApp {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String choice = showMenu();
		CustomerXMLFile customerxml = new CustomerXMLFile();
		while (!choice.equalsIgnoreCase("exit")) {
			switch (choice) {
			case "list":
				customerxml.listCustomers();
				break;
			case "add":
				String firstName = validate(sc, "Enter first name:");
				String lastName = validate(sc, "Enter last name:");
				String email = validate(sc, "Enter email address:");
				customerxml.addCustomer(email, firstName, lastName);
				break;
			case "del":
				String delete = validate(sc, "Enter the Email of Customer to delete:");
				customerxml.deleteCustomer(delete);
				break;
			case "help":
				break;
			default:
				System.out.println("Invalid Choice!");
				break;
			}
			System.out.println();
			choice = showMenu();
		}
		System.out.println("Buh Bye!");

	}

	private static String validate(Scanner sc, String prompt) {
		String input = null;
		do {
			System.out.println(prompt);			
			input = sc.nextLine();
			sc.nextLine();
		} while (input.length() < 1);

		return input;
	}

	public static String showMenu() {

		System.out.println("Customer Maintenance Application");
		System.out.println("Make your choice:");
		System.out.println("list add del help exit");
		return sc.next();
	}
}
