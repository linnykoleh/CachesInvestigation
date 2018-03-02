package com.investigation.cqengine.example;

import static com.googlecode.cqengine.codegen.AttributeBytecodeGenerator.createAttributes;

import java.util.Arrays;

import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.googlecode.cqengine.IndexedCollection;
import com.googlecode.cqengine.query.Query;
import com.googlecode.cqengine.query.QueryFactory;
import com.googlecode.cqengine.query.parser.cqn.CQNParser;
import com.googlecode.cqengine.query.parser.sql.SQLParser;
import com.googlecode.cqengine.resultset.ResultSet;
import com.investigation.cqengine.objects.Car;
import com.investigation.cqengine.objects.CarCQN;

public class CQEngineExampleMain {

	public static void main(String[] args) {
		final IndexedCollection<Car> cars = new ConcurrentIndexedCollection<>();

		System.out.println("----------------------------------------------------------------------");
		System.out.println("Simply add, get, remove");

		cars.add(new Car(1, "ford focus", "great condition, low mileage", Arrays.asList("spare tyre", "sunroof")));
		cars.add(new Car(2, "ford taurus", "dirty and unreliable, flat tyre", Arrays.asList("spare tyre", "radio")));
		cars.add(new Car(3, "honda civic", "has a flat tyre and high mileage", Arrays.asList("radio")));
		cars.add(new Car(4, "seat cordoba", "has a flat tyre and high mileage", Arrays.asList("safe", "wheels", "doors")));
		cars.add(new Car(5, "kia stortage", "has a flat tyre and high mileage", Arrays.asList("condition", "wheels")));

		System.out.println("Cars whose name ends with 'vic' or whose id is less than 2:");
		final Query<Car> query1 = QueryFactory.or(QueryFactory.endsWith(Car.NAME, "vic"), QueryFactory.lessThan(Car.CAR_ID, 2));
		for (Car car : cars.retrieve(query1)) {
			System.out.println(car);
		}

		System.out.println("\nCars whose flat tyre can be replaced:");
		final Query<Car> query2 = QueryFactory.and(QueryFactory.contains(Car.DESCRIPTION, "flat tyre"), QueryFactory.equal(Car.FEATURES, "spare tyre"));
		for (Car car : cars.retrieve(query2)) {
			System.out.println(car);
		}

		System.out.println("\nCars which have a sunroof or a radio but are not dirty:");
		Query<Car> query3 = QueryFactory.and(QueryFactory.in(Car.FEATURES, "sunroof", "radio"), QueryFactory.not(QueryFactory.contains(Car.DESCRIPTION, "dirty")));
		for (Car car : cars.retrieve(query3)) {
			System.out.println(car);
		}

		System.out.println("\nRemoving");
		final Query<Car> queryDelete = QueryFactory.equal(Car.CAR_ID, 5);
		final Car carToDel = cars.retrieve(queryDelete).uniqueResult();
		cars.remove(carToDel);

		System.out.println("cars size " + cars.size());

		System.out.println("\nUpdating");
		cars.add(new Car(4, "seat cordoba new", "has a flat tyre and high mileage", Arrays.asList("safe", "wheels", "doors")));

		System.out.println("All cars");
		cars.forEach(System.out::println);

		System.out.println("----------------------------------------------------------------------");
		System.out.println("SQL dialect");

		final SQLParser<Car> parser = SQLParser.forPojoWithAttributes(Car.class, createAttributes(Car.class));

		ResultSet<Car> results = parser.retrieve(cars, "SELECT * FROM cars WHERE (carId <= 5 and features NOT IN ('radio')) ORDER BY carId ASC");
		for (Car car : results) {
			System.out.println(car);
		}

		System.out.println("----------------------------------------------------------------------");
		System.out.println("CQN dialect");

		final IndexedCollection<CarCQN> carsCQN = new ConcurrentIndexedCollection<>();

		carsCQN.add(new CarCQN(1, "ford focus", "great condition, low mileage", Arrays.asList("spare tyre", "sunroof")));
		carsCQN.add(new CarCQN(2, "ford taurus", "dirty and unreliable, flat tyre", Arrays.asList("spare tyre", "radio")));
		carsCQN.add(new CarCQN(3, "honda civic", "has a flat tyre and high mileage", Arrays.asList("radio")));
		carsCQN.add(new CarCQN(4, "seat cordoba", "has a flat tyre and high mileage", Arrays.asList("safe", "wheels", "doors")));
		carsCQN.add(new CarCQN(5, "kia stortage", "has a flat tyre and high mileage", Arrays.asList("condition", "wheels")));

		final CQNParser<CarCQN> parserCQN = CQNParser.forPojoWithAttributes(CarCQN.class, createAttributes(CarCQN.class));

		ResultSet<CarCQN> resultsCQN = parserCQN.retrieve(carsCQN, "and(" +
				"or(equal(\"carId\", 1), equal(\"carId\", 2)), " +
				"not(in(\"features\", radio))" +
				")");
		for (CarCQN car : resultsCQN) {
			System.out.println(car);
		}

		System.out.println("----------------------------------------------------------------------");

	}
}
