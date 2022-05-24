import java.util.Scanner;

public class loginMenu {

    public static void main(String[] args) {

        System.out.println("Welcome to the login menu");
        System.out.println("1. Login");
        System.out.println("2. Create an account");
        System.out.println("3. Exit");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Please enter your username");
                String uname = sc.next();
                System.out.println("Please enter your password");
                String pword = sc.next();
                break;
            case 2:
                System.out.println("Please enter your first name");
                String firstName = sc.next();
                System.out.println("Please enter your last name");
                String lastName = sc.next();
                System.out.println("Please enter your email");
                String email = sc.next();
                System.out.println("Please enter your username");
                String username = sc.next();
                System.out.println("Please enter your password");
                String password = sc.next();
                System.out.println("Please enter your profile picture");
                String profilepic = sc.next();
                System.out.println("Welcome to the appUsers page");
                break;
            case 3:
                System.out.println("Thank you for using our application");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}
