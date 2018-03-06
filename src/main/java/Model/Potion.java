package Model;


import java.io.Serializable;

public class Potion extends Item implements Serializable{
    private static final long serialVersionUID = 1L;
    private int recoveryAmount;

    public Potion(String name, String description, int cost, int recoveryAmount) {
        super(name, description,cost);
        this.setRecoveryAmount(recoveryAmount);
    }

    public void setRecoveryAmount(int recoveryAmount) {
        this.recoveryAmount = recoveryAmount;
    }

    public void useItem(Creature creature) {
        if(creature instanceof Hero){
            ((Hero) creature).setCurHealth(recoveryAmount);
        }

    }
}
