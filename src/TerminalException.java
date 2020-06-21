class TerminalException extends Exception {
    public TerminalException(){

    }

    public TerminalException(String message){
        super(message);
    }
    public TerminalException(String message, Throwable cause){
        super(message, cause);
    }
}

class PinValidateException extends TerminalException{

}

class PinLockException extends TerminalException{
    // Delay timeout
    private final int timeRemaining;

    public PinLockException(int timeRemaining){
        this.timeRemaining = timeRemaining;
    }

    public int timeRemaining(){return timeRemaining;}
}

class ServerConnectionException extends TerminalException{
    public ServerConnectionException(String message){
        super(message);
    }
}

class ServerBalanceException extends TerminalException{
    public ServerBalanceException(String message){
        super(message);
    }
}

class TerminalAmountException extends TerminalException{
    // Required sum
    private final int amount;

    public TerminalAmountException(int amount) {
        this.amount = amount;
    }

    public int amount(){return amount;}
}
