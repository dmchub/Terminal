public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;

    public TerminalImpl(){
        this.server = new TerminalServer();
        this.pinValidator = new PinValidator();
    }

    @Override
    public void authorize(int pin) throws TerminalException{
        try {
            this.pinValidator.validate(pin);
        }
        catch (PinValidateException e){
            throw new TerminalException("Wrong pin code. Please try again");
        }
        catch (PinLockException e){
            throw new TerminalException("The system is blocked. Next attempt will be available in " + e.timeRemaining()+" secs");
        }
    }

    @Override
    public void endSession(){
        pinValidator.reset();
    }

    @Override
    public int checkAccount() throws TerminalException{
        try {
            pinValidator.check();
            return server.getBalance();
        }catch (PinValidateException e){
            throw e;
        }
    }

    @Override
    public void toDeposit(int amount) throws TerminalException{
        try {
            pinValidator.check();
            checkAmount(amount);

            putMoney(amount);
            server.toDeposit(amount);
        }catch (ServerConnectionException e){
            throw new TerminalException("Server connection error. Please try to connect later ", e);
        }catch (TerminalAmountException e){
            throw new TerminalException("Sum should be divided by 100", e);
        }
    }

    @Override
    public void withdraw(int amount) throws TerminalException{
        try {
            pinValidator.check();
            checkAmount(amount);

            givMoney(amount);
            server.withdraw(amount);
        }catch (ServerConnectionException e){
            throw new TerminalException("Server connection error. Please try to connect later ", e);
        }catch (TerminalAmountException e){
            throw new TerminalException("Sum should be divided by 100", e);
        }
    }

    // Give back money
    private void givMoney(int amount) {
    }

    // Get back money
    private void putMoney(int amount) {
    }

    private void checkAmount(int amount) throws TerminalAmountException {
        if(amount % 100 > 0)
            throw new TerminalAmountException(amount);
    }
}
