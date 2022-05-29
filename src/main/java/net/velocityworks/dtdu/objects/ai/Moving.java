package net.velocityworks.dtdu.objects.ai;

import static net.velocityworks.dtdu.util.Mathe.*;

import net.velocityworks.dtdu.util.Direction;

public interface Moving {
	int getY();
	int getX();
	default void move(Direction direction) {
		if(direction != Direction.NONE) moveTo(getX(), getY(), getX() + getHorizontalM(direction), getY() + getVerticalM(direction));
	}
	void moveTo(int fromX, int fromY, int toX, int toY);
}