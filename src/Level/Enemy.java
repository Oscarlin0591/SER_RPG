package Level;

import GameObject.SpriteSheet;

public class Enemy extends NPC {
    //instance variables
    protected float health;
    protected float strength;
    protected float critChance;
    protected float dodgeChance;
    
    //constructor
    public Enemy (int id, float x, float y, SpriteSheet spriteSheet, String startingAnimation, float health, float strength, float critChance, float dodgeChance) {
        //call NPC constructor
        super(id, x, y, spriteSheet, startingAnimation);

        //initialize health and strength
        this.health = health;
        this.strength = strength;
        this.critChance = critChance;
        this.dodgeChance = dodgeChance;
    }

    // getters and setters
    public float getHealth() {
        return this.health;
    }

    public void setHealth(float newHealth) {
        this.health = newHealth;
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

    public void attack(float damage) {
        //if not dodge, deal damage
        if ((dodgeChance * Math.random()) < 0.9) {
            setHealth(this.health - damage);
        } else {
            //hypothetical dodge flag tripped to display message
        }
    }
    
}
