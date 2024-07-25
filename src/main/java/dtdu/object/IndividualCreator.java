package main.java.dtdu.object;

import main.java.dtdu.engine.Registry;
import main.java.dtdu.object.base.Individual;

public interface IndividualCreator extends Registry {
	Individual create(float x, float y, byte[] data);
}