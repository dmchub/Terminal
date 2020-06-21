public interface Terminal {
    // Pin code authentication
    void authorize(int pin) throws TerminalException;

    // Complete session
    void endSession();

    // Account checking
    int checkAccount() throws TerminalException;

    // Put sum
    void toDeposit(int amount) throws TerminalException;

    // Withdraw required sum
    void withdraw(int amount) throws TerminalException;
}
