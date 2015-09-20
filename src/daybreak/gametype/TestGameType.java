package daybreak.gametype;

import daybreak.Enemy;
import daybreak.Tile;

public class TestGameType extends GameType
{
	public TestGameType()
	{
		super(20, 20);
	}

	@Override
	public void init()
	{		
		for(int n = 0; n < map.length; ++n)
		{
			for(int i = 0; i < map[0].length; ++i)
			{
				map[n][i] = new Tile(Tile.FLOOR);
			}
		}
		
		for(int n = 0; n < map.length; ++n)
		{
			map[n][0] = new Tile(Tile.WALL);
			
			map[n][map[0].length - 1] = new Tile(Tile.WALL);
		}
		
		for(int i = 1; i < map[0].length; ++i)
		{
			map[0][i] = new Tile(Tile.WALL);
			
			map[map.length - 1][i] = new Tile(Tile.WALL);
		}
		
		player.setPosition(5, 5);
		
		Enemy enemy = new Enemy(map, player);
		enemy.setPosition(10, 10);
		entities.add(enemy);
	}

	@Override
	public void update(int deltaTime)
	{
	}

	@Override
	public int getID()
	{
		return 0;
	}
}
