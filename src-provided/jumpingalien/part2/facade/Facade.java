package jumpingalien.part2.facade;

import java.util.Collection;

import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Facade implements IFacadePart2 {

	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		return new Mazub(pixelLeftX, pixelBottomY, sprites);
	}

	@Override
	public int[] getLocation(Mazub alien) {
		int[] location = {(int) alien.getX(), (int) alien.getY()};
		return location;
	}

	@Override
	public double[] getVelocity(Mazub alien) {
		double[] velocity = {alien.getVx(), alien.getVy()};
		return velocity;
	}

	@Override
	public double[] getAcceleration(Mazub alien) {
		double[] acceleration = {alien.getAx(), alien.getAy()};
		return acceleration;
	}

	@Override
	public int[] getSize(Mazub alien) {
		int[] size = {alien.getWidth(), alien.getHeight()};
		return size;
	}

	@Override
	public Sprite getCurrentSprite(Mazub alien) {
		return alien.getCurrentSprite();
	}

	@Override
	public void startJump(Mazub alien) {
		alien.startJump();
	}

	@Override
	public void endJump(Mazub alien) {
		alien.endJump();
	}

	@Override
	public void startMoveLeft(Mazub alien) {
		alien.startMove("left");
	}

	@Override
	public void endMoveLeft(Mazub alien) {
		alien.endMove("left");
	}

	@Override
	public void startMoveRight(Mazub alien) {
		alien.startMove("right");
	}

	@Override
	public void endMoveRight(Mazub alien) {
		alien.endMove("right");
	}

	@Override
	public void startDuck(Mazub alien) {
		alien.startDuck();
	}

	@Override
	public void endDuck(Mazub alien) {
		alien.endDuck();
	}

	@Override
	public int getNbHitPoints(Mazub alien) {
		return alien.getHitpoints();
	}

	@Override
	public World createWorld(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) {
		return new World(tileSize, nbTilesX, nbTilesY, visibleWindowWidth, visibleWindowHeight, targetTileX, targetTileY);
	}

	@Override
	public int[] getWorldSizeInPixels(World world) {
		int[] size = {(int) world.getWorldWidth(), (int) world.getWorldHeight()};
		return size;
	}

	@Override
	public int getTileLength(World world) {
		return world.getTileSize();
	}

	@Override
	public void startGame(World world) {
		boolean slimetest = false;
		boolean planttest = false;
		boolean outofboundstest = false;
		if (slimetest) {
			School school1 = new School();
			world.addSchool(school1);
			School school2 = new School();
			world.addSchool(school2);
			Sprite[] sprites = {jumpingalien.part2.internal.Resources.SLIME_SPRITE_LEFT,
					jumpingalien.part2.internal.Resources.SLIME_SPRITE_RIGHT};
			new Slime(150, 500, sprites, school1);
			new Slime(390, 500, sprites, school1);
			new Slime(530, 500, sprites, school2);
		}
		if (planttest) {
			Sprite[] sprites = {jumpingalien.part2.internal.Resources.PLANT_SPRITE_LEFT,
					jumpingalien.part2.internal.Resources.PLANT_SPRITE_RIGHT};
			Plant plant = new Plant(650, 200, sprites);
			world.addPlant(plant);
		}
		if (outofboundstest) {
			world.setFeature(0, 1, 0);
			world.setFeature(0, 2, 0);
		}
	}

	@Override
	public boolean isGameOver(World world) {
		return world.isGameOver();
	}

	@Override
	public boolean didPlayerWin(World world) {
		return world.touchesTarget();
	}

	@Override
	public void advanceTime(World world, double dt) {
		try {
			world.advanceTime(dt);
		} catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public int[] getVisibleWindow(World world) {
		world.updateWindow();
		int[] window = {world.getXWindow(), world.getYWindow(), world.getXWindow() + world.getWindowWidth() - 1
						, world.getYWindow() + world.getWindowHeight() - 1};
		return window;
	}

	@Override
	public int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY) {
		int[] location = {tileX*world.getTileSize(), tileY*world.getTileSize()};
		return location;
	}

	@Override
	public int[][] getTilePositionsIn(World world, int pixelLeft,
			int pixelBottom, int pixelRight, int pixelTop) {
		return world.getTilePositionsIn(pixelLeft, pixelBottom, pixelRight, pixelTop);
	}

	@Override
	public int getGeologicalFeature(World world, int pixelX, int pixelY) throws ModelException {
		if ((pixelX % world.getTileSize() != 0) || (pixelY % world.getTileSize() != 0)) {
			throw new ModelException("Coordinates don't correspond with any tile's bottomleft pixel.");
		}
		return world.getFeature(world.getTilePos(pixelX), world.getTilePos(pixelY)).getNumber();
	}

	@Override
	public void setGeologicalFeature(World world, int tileX, int tileY, int tileType) {
		world.setFeature(tileX, tileY, tileType);
	}

	@Override
	public void setMazub(World world, Mazub alien) {
		world.setMazub(alien);
	}

	@Override
	public boolean isImmune(Mazub alien) {
		return (alien.getTimeInvincible() > 0);
	}

	@Override
	public Plant createPlant(int x, int y, Sprite[] sprites) {
		return new Plant(x, y, sprites);
	}

	@Override
	public void addPlant(World world, Plant plant) {
		world.addPlant(plant);
	}

	@Override
	public Collection<Plant> getPlants(World world) {
		return world.getPlants();
	}

	@Override
	public int[] getLocation(Plant plant) {
		int[] location = {(int) plant.getX(), (int) plant.getY()};
		return location;
	}

	@Override
	public Sprite getCurrentSprite(Plant plant) {
		return plant.getCurrentSprite();
	}

	@Override
	public Shark createShark(int x, int y, Sprite[] sprites) {
		return new Shark(x, y, sprites);
	}

	@Override
	public void addShark(World world, Shark shark) {
		world.addShark(shark);
	}

	@Override
	public Collection<Shark> getSharks(World world) {
		return world.getSharks();
	}

	@Override
	public int[] getLocation(Shark shark) {
		int[] location = {(int) shark.getX(), (int) shark.getY()};
		return location;
	}

	@Override
	public Sprite getCurrentSprite(Shark shark) {
		return shark.getCurrentSprite();
	}

	@Override
	public School createSchool() {
		return new School();
	}

	@Override
	public Slime createSlime(int x, int y, Sprite[] sprites, School school) {
		return new Slime(x, y, sprites, school);
	}

	@Override
	public void addSlime(World world, Slime slime) {
		slime.getSchool().setWorld(world);
	}

	@Override
	public Collection<Slime> getSlimes(World world) {
		return world.getSlimes();
	}

	@Override
	public int[] getLocation(Slime slime) {
		int[] location = {(int) slime.getX(), (int) slime.getY()};
		return location;
	}

	@Override
	public Sprite getCurrentSprite(Slime slime) {
		return slime.getCurrentSprite();
	}

	@Override
	public School getSchool(Slime slime) {
		return slime.getSchool();
	}

}