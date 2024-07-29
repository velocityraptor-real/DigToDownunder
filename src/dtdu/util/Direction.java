package dtdu.util;

public enum Direction {
	NONE(0, 0),
	UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0),
	UP_LEFT(-1, -1), UP_RIGHT(1, -1), DOWN_LEFT(-1, 1), DOWN_RIGHT(1, 1);
	public final boolean isUp, isDown, isLeft, isRight, isVertical, isHorizontal;
	public final int verticalMultiplier, horizontalMultiplier;
	private Direction(int dx, int dy) {
		this.verticalMultiplier = dy;
		this.horizontalMultiplier = dx;
		this.isDown = dy > 0;
		this.isUp = dy < 0;
		isVertical = isUp || isDown;
		this.isLeft = dx < 0;
		this.isRight = dx > 0;
		isHorizontal = isRight || isLeft;
	}
	public static Direction fromDXY(float dx, float dy) {
		return dx > 0F ? (dy > 0F ? DOWN_RIGHT : (dy < 0F ? UP_RIGHT : RIGHT))
			: (dx < 0F ? (dy > 0F ? DOWN_LEFT : (dy < 0F ? UP_LEFT : LEFT))
			: (dy > 0F ? DOWN : (dy < 0F ? UP : NONE)));
	}
	public static Direction fromDXY4(float dx, float dy) {
		return Math.abs(dy) > Math.abs(dx) ?
				dy > 0F ? UP : (dy < 0F ? DOWN : (dx > 0F ? RIGHT : (dx < 0F ? LEFT : NONE))):
				dx > 0F ? RIGHT : (dx < 0F ? LEFT : (dy > 0F ? UP : (dy < 0F ? DOWN : NONE)));
	}
	public static Direction blockAxis(Direction dir, Direction blockDir) {
		return fromDXY(blockDir.isHorizontal ? 0F : dir.horizontalMultiplier, blockDir.isVertical ? 0F : dir.verticalMultiplier);
	}
	public static Direction block(Direction dir, Direction blockDir) {
		return fromDXY(dir.isHorizontal ? dir.horizontalMultiplier + blockDir.horizontalMultiplier : 0F, dir.isVertical ? dir.verticalMultiplier + blockDir.verticalMultiplier : 0F);
	}
	public static Direction add(Direction dir, Direction add) {
		return fromDXY(dir.horizontalMultiplier + add.horizontalMultiplier, dir.verticalMultiplier + add.verticalMultiplier);
	}
}