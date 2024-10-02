package Level;

import GameObject.SpriteSheet;

public class Enemy extends NPC {
    //instance variables
    protected float health;
    protected float strength;

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

    public Enemy (int id, float x, float y, SpriteSheet spriteSheet, String startingAnimation, float health, float strength) {
        //call NPC constructor
        super(id, x, y, spriteSheet, startingAnimation);

        //initialize health and strength
        this.health = health;
        this.strength = strength;
    }
}
