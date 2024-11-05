package Level;


import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import GameObject.GameObject;
import GameObject.Rectangle;
import GameObject.SpriteSheet;
import Screens.PlayLevelScreen;
import Utils.Direction;
import java.awt.image.BufferedImage;

public abstract class Player extends GameObject {
    // values that affect player movement
    // these should be set in a subclass
    protected float walkSpeed = 0;
    protected float defaultWalkSpeed = 2.3f;   
    protected int interactionRange = 1;
    protected Direction currentWalkingXDirection;
    protected Direction currentWalkingYDirection;
    protected Direction lastWalkingXDirection;
    protected Direction lastWalkingYDirection;
    protected float width;
    protected float height;

    // values used to handle player movement
    protected float moveAmountX, moveAmountY;
    protected float lastAmountMovedX, lastAmountMovedY;

    // values used to keep track of player's current state
    protected PlayerState playerState;
    protected PlayerState previousPlayerState;
    protected Direction facingDirection;
    protected Direction lastMovementDirection;

    // define keys
    protected KeyLocker keyLocker = new KeyLocker();
    protected Key MOVE_LEFT_KEY = Key.A;
    protected Key MOVE_RIGHT_KEY = Key.D;
    protected Key MOVE_UP_KEY = Key.W;
    protected Key MOVE_DOWN_KEY = Key.S;
    protected Key INTERACT_KEY = Key.E;
    protected Key SPRINT_KEY = Key.SHIFT;

    // values for stats
    protected static float max_health;
    protected static float health;
    protected static float strength;
    protected static float critChance;
    protected static float dodgeChance;

    protected boolean isLocked = false;
    protected boolean isSprinting;

    public Player(SpriteSheet spriteSheet, float x, float y, String startingAnimationName) {
        super(spriteSheet, x, y, startingAnimationName);
        facingDirection = Direction.RIGHT;
        playerState = PlayerState.STANDING;
        previousPlayerState = playerState;
        this.affectedByTriggers = true;
    }

    public Player(SpriteSheet spriteSheet, float x, float y, String startingAnimationName, float newHealth, float newStrength, float newCritChance, float newDodgeChance) {
        super(spriteSheet, x, y, startingAnimationName);

        facingDirection = Direction.RIGHT;
        playerState = PlayerState.STANDING;
        previousPlayerState = playerState;
        this.affectedByTriggers = true;

        max_health = newHealth;
        setHealth(max_health);

        strength = newStrength;

        critChance = newCritChance;
        dodgeChance = newDodgeChance;
    }

    public void update() {
        if (!isLocked) {
            moveAmountX = 0;
            moveAmountY = 0;

            // if player is currently playing through level (has not won or lost)
            // update player's state and current actions, which includes things like determining how much it should move each frame and if its walking or jumping
            do {
                previousPlayerState = playerState;
                handlePlayerState();
            } while (previousPlayerState != playerState);

            // move player with respect to map collisions based on how much player needs to move this frame
            lastAmountMovedY = super.moveYHandleCollision(moveAmountY);
            lastAmountMovedX = super.moveXHandleCollision(moveAmountX);
        }

        handlePlayerAnimation();

        updateLockedKeys();

        // update player's animation
        super.update();
    }

    // based on player's current state, call appropriate player state handling method
    protected void handlePlayerState() {
        switch (playerState) {
            case STANDING:
                playerStanding();
                break;
            case WALKING:
                playerWalking();
                break;
        }
    }

    // player STANDING state logic
    protected void playerStanding() {
        if (!keyLocker.isKeyLocked(INTERACT_KEY) && Keyboard.isKeyDown(INTERACT_KEY)) {
            keyLocker.lockKey(INTERACT_KEY);
            map.entityInteract(this);
        }

        // if a walk key is pressed, player enters WALKING state
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(MOVE_RIGHT_KEY) || Keyboard.isKeyDown(MOVE_UP_KEY) || Keyboard.isKeyDown(MOVE_DOWN_KEY)) {
            playerState = PlayerState.WALKING;
        }
    }

    // player WALKING state logic
    protected void playerWalking() {
        if (!keyLocker.isKeyLocked(INTERACT_KEY) && Keyboard.isKeyDown(INTERACT_KEY)) {
            keyLocker.lockKey(INTERACT_KEY);
            map.entityInteract(this);
        }

        // if walk left key is pressed, move player to the left
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY) && this.x > 0) {
            moveAmountX -= walkSpeed;
            facingDirection = Direction.LEFT;
            currentWalkingXDirection = Direction.LEFT;
            lastWalkingXDirection = Direction.LEFT;
        }

        // if walk right key is pressed, move player to the right
        else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY) && this.x < map.endBoundX - this.getWidth()) {
            moveAmountX += walkSpeed;
            facingDirection = Direction.RIGHT;
            currentWalkingXDirection = Direction.RIGHT;
            lastWalkingXDirection = Direction.RIGHT;
        }
        else {
            currentWalkingXDirection = Direction.NONE;
        }

        if (Keyboard.isKeyDown(MOVE_UP_KEY) && this.y > 0) {
            moveAmountY -= walkSpeed;
            currentWalkingYDirection = Direction.UP;
            lastWalkingYDirection = Direction.UP;
        }
        else if (Keyboard.isKeyDown(MOVE_DOWN_KEY) && this.y < map.endBoundY - this.getHeight()) {
            moveAmountY += walkSpeed;
            currentWalkingYDirection = Direction.DOWN;
            lastWalkingYDirection = Direction.DOWN;
        }
        else {
            currentWalkingYDirection = Direction.NONE;
        }

        if ((currentWalkingXDirection == Direction.RIGHT || currentWalkingXDirection == Direction.LEFT) && currentWalkingYDirection == Direction.NONE) {
            lastWalkingYDirection = Direction.NONE;
        }

        if ((currentWalkingYDirection == Direction.UP || currentWalkingYDirection == Direction.DOWN) && currentWalkingXDirection == Direction.NONE) {
            lastWalkingXDirection = Direction.NONE;
        }

        if (Keyboard.isKeyUp(MOVE_LEFT_KEY) && Keyboard.isKeyUp(MOVE_RIGHT_KEY) && Keyboard.isKeyUp(MOVE_UP_KEY) && Keyboard.isKeyUp(MOVE_DOWN_KEY)) {
            playerState = PlayerState.STANDING;
        }
        
        if (Keyboard.isKeyDown(SPRINT_KEY) && !isSprinting) {
            isSprinting = true;
            walkSpeed *= 1.75; 
        }
    
        if (Keyboard.isKeyUp(SPRINT_KEY) && isSprinting) {
            isSprinting = false;
            walkSpeed = defaultWalkSpeed;
        }
    }

    protected void updateLockedKeys() {
        if (Keyboard.isKeyUp(INTERACT_KEY) && !isLocked) {
            keyLocker.unlockKey(INTERACT_KEY);
        }
    }

    // anything extra the player should do based on interactions can be handled here
    protected void handlePlayerAnimation() {
        if (playerState == PlayerState.STANDING) {
            // sets animation to a STAND animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";
        }
        else if (playerState == PlayerState.WALKING) {
            // sets animation to a WALK animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_RIGHT" : "WALK_LEFT";
        }
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, GameObject entityCollidedWith) { }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, GameObject entityCollidedWith) { }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }

    public Rectangle getInteractionRange() {
        return new Rectangle(
                getBounds().getX1() - interactionRange,
                getBounds().getY1() - interactionRange,
                getBounds().getWidth() + (interactionRange * 2),
                getBounds().getHeight() + (interactionRange * 2));
    }

    public Key getInteractKey() { return INTERACT_KEY; }
    public Direction getCurrentWalkingXDirection() { return currentWalkingXDirection; }
    public Direction getCurrentWalkingYDirection() { return currentWalkingYDirection; }
    public Direction getLastWalkingXDirection() { return lastWalkingXDirection; }
    public Direction getLastWalkingYDirection() { return lastWalkingYDirection; }

    
    public void lock() {
        isLocked = true;
        playerState = PlayerState.STANDING;
        this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";
    }

    public void unlock() {
        isLocked = false;
        playerState = PlayerState.STANDING;
        this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";
    }

    public boolean getIsLocked(){
        return isLocked;
    }

    // used by other files or scripts to force player to stand
    public void stand(Direction direction) {
        playerState = PlayerState.STANDING;
        facingDirection = direction;
        if (direction == Direction.RIGHT) {
            this.currentAnimationName = "STAND_RIGHT";
        }
        else if (direction == Direction.LEFT) {
            this.currentAnimationName = "STAND_LEFT";
        }
    }

    // used by other files or scripts to force player to walk
    public void walk(Direction direction, float speed) {
        playerState = PlayerState.WALKING;
        facingDirection = direction;
        if (direction == Direction.RIGHT) {
            this.currentAnimationName = "WALK_RIGHT";
        }
        else if (direction == Direction.LEFT) {
            this.currentAnimationName = "WALK_LEFT";
        }
        if (direction == Direction.UP) {
            moveY(-speed);
        }
        else if (direction == Direction.DOWN) {
            moveY(speed);
        }
        else if (direction == Direction.LEFT) {
            moveX(-speed);
        }
        else if (direction == Direction.RIGHT) {
            moveX(speed);
        }
    }
/* 
    public void sprint() {
        walkSpeed = getWalkSpeed() * 1.75f;
        walkSpeed = 2.3f;
    }   

    public void stopSprint() {
        
    }

*/
    // getters and setters for Player class
    public float getHealth() {
        return health;
    }

    public static void setHealth(float newHealth) {
        health = newHealth;
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float newStrength) {
        strength = newStrength;
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

    // damage method
    public void damage(int damage) {
        //if not dodge, deal damage
        if ((dodgeChance * Math.random()) < 0.9) {
            if (health - damage == 0) {
                setHealth(0);
            } else {
                setHealth(health - damage);
            }
        } else
            PlayLevelScreen.flagManager.setFlag("attackDodged");
    }

    public void heal() {
        if ((float)(health + max_health*.4) <= max_health) {
        setHealth((float)(health + max_health*.4));
        } else if ((float)(health + max_health*.4) > max_health) {
            setHealth(max_health);
        }

        System.out.println("Healed: " + (health + max_health*.4));
    }

    public void setMaxHealth(float maxHealth) {
        max_health = maxHealth;
        setHealth(max_health);
    }

    public float getMaxHealth() {
        return max_health;
    }

    public void fullHealth() {
        setHealth(max_health);
    }
    
    // Uncomment this to have game draw player's bounds to make it easier to visualize
    /*
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    }
    */
}
