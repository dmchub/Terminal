import java.util.*;

public class PinValidator {
    // Max attempt number
    private static final int MAX_ATTEMPTS = 3;
    // Timeout after wrong max attempts
    private static final int LOCK_TIME = 5;
    // PIN code
    private int pin = 1234;

    private int attemptCounter = 0;
    private boolean valid = false;
    private boolean lock = false;

    private final Timer timer;

    public PinValidator(){
        this.timer = new Timer();
    }

    public void validate(int pin) throws PinValidateException, PinLockException{
        if(lock)
            throw new PinLockException(1);

        if(pin == this.pin){
            this.attemptCounter=0;
            this.valid=true;
        }else {
            this.valid=false;
            if(++attemptCounter==MAX_ATTEMPTS){
                this.lock=true;
                this.timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        lock=false;
                        attemptCounter=0;
                    }
                }, LOCK_TIME * 1000);
            }
            throw new PinValidateException();
        }
    }

    public void check() throws PinValidateException{
        if(!this.valid)
            throw new PinValidateException();
    }

    public void reset(){
        this.timer.cancel();
        this.valid=false;
        this.lock=false;
        this.attemptCounter=0;
    }
}
