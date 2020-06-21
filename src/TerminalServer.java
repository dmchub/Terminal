import java.util.Random;

public class TerminalServer {
    // Balance checking
    private int balance;
    // Random to generate random shutdowns
    private static final Random random = new Random();

    public TerminalServer(){
        // Put a random sum
        balance = random.nextInt(100000)+1000;
    }

    public int getBalance() throws ServerConnectionException{
        randomError();
        return balance;
    }

    public void toDeposit(int amount) throws ServerConnectionException{
        randomError();
        balance += amount;
    }

    public void withdraw(int amount) throws ServerConnectionException, ServerBalanceException{
        randomError();

        if(amount > balance){
            throw new ServerBalanceException("Lack of goods on balance");
        }

        balance -= amount;
    }

    private void randomError() throws ServerConnectionException {
        if(random.nextInt(100)<5)
            throw new ServerConnectionException("Server connection error. Please try later.");
    }
}
