import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Terminal terminal = new TerminalImpl();
        TerminalConsoleInterface consoleInterface = new TerminalConsoleInterface(terminal);
        Scanner scanner = new Scanner(System.in);

        while (true){

            consoleInterface.authorize();

            boolean session = true;
            while (session){
                System.out.println();
                System.out.println("Please select operation:");
                System.out.println("1 - check the balance");
                System.out.println("2 - put the money on deposit");
                System.out.println("3 - withdraw the money from deposit");
                System.out.println("0 - finish the service");
                System.out.println();

                int num = scanner.nextInt();

                switch (num){
                    case 1:
                        consoleInterface.checkAccount();
                        break;
                    case 2:
                        consoleInterface.toDeposit();
                        break;
                    case 3:
                        consoleInterface.withdraw();
                        break;
                    case 0:
                        session = false;
                        break;
                }
            }
            terminal.endSession();
            System.out.println("Good bye!");
            System.out.println();
        }
    }
}
