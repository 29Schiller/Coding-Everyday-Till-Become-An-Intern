package PvZ;

public class Zombie {
    private int health;
    private int damage;
    private int limit;
    private long lastAttack;
    private double attackWaitingTime;
    private int lane;


    public Zombie(int x, int y, int speed,int health, int damage, long lastAttack, double attackWaitingTime, int lane) {
        this.health = health;
        this.damage = damage;
        this.lastAttack = 0;
        limit=150;
        this.attackWaitingTime = attackWaitingTime;
        this.lane = lane;
    }

    public void act() {
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public long getLastattack() {
        return lastAttack;
    }
    
    public void setLastattack(long lastattack) {
        this.lastAttack = lastattack;
    }

    public double getAttackWaitingTime(){
        return attackWaitingTime;
    }
    public void setAttackWaitingTime(double wai){
        attackWaitingTime = wai;
    }
}
