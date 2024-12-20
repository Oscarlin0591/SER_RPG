package Level;

import GameObject.SpriteSheet;
import Screens.PlayLevelScreen;

public class Enemy extends NPC {
    //instance variables
    protected float health;
    protected float strength;
    protected float critChance;
    protected float dodgeChance;
    protected float maxHealth;
    
    //constructor
    public Enemy (int id, float x, float y, SpriteSheet spriteSheet, String startingAnimation, float health, float strength, float critChance, float dodgeChance) {
        //call NPC constructor
        super(id, x, y, spriteSheet, startingAnimation);

        //initialize health and strength
        this.health = health;
        this.strength = strength;
        this.critChance = critChance;
        this.dodgeChance = dodgeChance;

        //initialize maxHealth
        this.maxHealth = health;
    }

    // getters and setters
    public float getHealth() {
        return this.health;
    }

    public void setHealth(float newHealth) {
        this.health = newHealth;
    }

    public float getMaxHealth () {
        return this.maxHealth;
    }

    public float getStrength() {
        return this.strength;
    }

    public void setStrength(float newStrength) {
        this.strength = newStrength;
    }

    public float getCritChance() {
        return critChance;
    }

    public void setCritChance(float newCritChance) {
        critChance = newCritChance;
    }

    public float getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(float newDodgeChance) {
        dodgeChance = newDodgeChance;
    }

    public void attack(int damage) {
        //if not dodge, deal damage
        if ((dodgeChance * Math.random()) < 0.9) {
            setHealth(this.health - damage);
        } else {
            PlayLevelScreen.flagManager.setFlag("attackDodged");
        }
    }
    
}
