package dev.rmmarquini.repository;

import org.hashids.Hashids;

import java.util.Random;

public abstract class AbstractRepository {

	abstract void load();

	protected String generateId() {
		Hashids hashids = new Hashids("Dora-Stafford", 8);
		return hashids.encode(new Random().nextInt(1000));
	}

}
