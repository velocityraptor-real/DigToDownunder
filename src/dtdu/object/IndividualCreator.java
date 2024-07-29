package dtdu.object;

import dtdu.engine.Registry;
import dtdu.object.base.Individual;

public interface IndividualCreator extends Registry {
	Individual create(float x, float y, byte[] data);
}