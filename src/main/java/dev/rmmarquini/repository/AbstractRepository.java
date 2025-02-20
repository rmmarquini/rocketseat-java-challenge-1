package dev.rmmarquini.repository;

import org.hashids.Hashids;

import java.util.Random;
import java.util.Scanner;

public abstract class AbstractRepository {

	abstract void load();
	abstract void manage(Scanner scanner);

	protected String generateId() {
		Hashids hashids = new Hashids("Dora-Stafford", 8);
		return hashids.encode(new Random().nextInt(1000));
	}

}
