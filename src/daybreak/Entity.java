package daybreak;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Represents an entity in the game.
 */
public abstract class Entity
{
	//Images to draw for the entity, based off of the direction they are facing
	protected Image img[];

	//Direction the entity is facing. Corresponds to indices in the img array
	protected int direction;

	//Directions the entity can face
	public static final int DIRECTION_DOWN = 0;
	public static final int DIRECTION_LEFT = 1;
	public static final int DIRECTION_UP = 2;
	public static final int DIRECTION_RIGHT = 3;

	//Location of the entity. These correspond to coordinates in a 2D array for the map
	protected int posX;
	protected int posY;

	//Reference to the map. Used for pathfinding and moving the player
	protected Tile[][] map;

	//This entity's health
	protected int health;

	/**
	 * Creates a new entity.
	 * @param map Reference to the map.
	 * @param imgFileName Filename of the sprite sheet to load for the entity
	 */
	public Entity(Tile[][] map, String imgFileName)
	{
		this.map = map;

		//Default to facing down
		direction = DIRECTION_DOWN;

		//Load the images for the entity
		img = new Image[4];
		try
		{
			//Load the sprite 
			SpriteSheet sheet = new SpriteSheet(imgFileName, 64, 64);

			for(int n = 0; n < 4; ++n)
			{
				img[n] = sheet.getSprite(n, 0);
			}
		} 
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * Get the image for this entity in the direction the entity is facing.
	 * @return This entity's image.
	 */
	public Image getImage()
	{
		return img[direction];
	}

	/**
	 * Get's the X coordinate of this entity.
	 * @return X coordinate.
	 */
	public int getPosX()
	{
		return posX;
	}

	/**
	 * Set's the X coordinate of this entity.
	 * @param posX New X coordinate.
	 */
	public void setPosX(int posX)
	{
		setPosition(posX, posY);
	}

	/**
	 * Get's the Y coordinate of this entity.
	 * @return Y coordinate.
	 */
	public int getPosY()
	{
		return posY;
	}

	/**
	 * Sets the Y coordinate of this entity.
	 * @param posY New Y coordinate.
	 */
	public void setPosY(int posY)
	{
		setPosition(posX, posY);
	}

	/**
	 * Sets the location of this entity.
	 * @param x New X coordinate.
	 * @param y New Y coordinate.
	 */
	public void setPosition(int x, int y)
	{
		//Mark that the entity moved from it's previous spot
		map[posY][posX].entity = null;

		//Update the direction the entity is facing
		if(posX < x)
		{
			direction = DIRECTION_RIGHT;
		}
		else if(posX > x)
		{
			direction = DIRECTION_LEFT;
		}
		else if(posY < y)
		{
			direction = DIRECTION_DOWN;
		}
		else if(posY > y)
		{
			direction = DIRECTION_UP;
		}

		this.posX = x;
		this.posY = y;

		//Mark the entity's new location
		map[posY][posX].entity = this;
	}

	/**
	 * Gets the current health of this entity.
	 * @return Current health.
	 */
	public int getHealth()
	{
		return health;
	}

	/**
	 * Updates this entity's health by the specified amount.
	 * @param change Amount to change this entity's health by.
	 */
	public void updateHealth(int change)
	{
		health += change;
	}

	/**
	 * Updates this entity. Perform any logic and calculations here.
	 * @param delta Time since last update in milliseconds
	 */
	public abstract void update(int deltaTime);
}
