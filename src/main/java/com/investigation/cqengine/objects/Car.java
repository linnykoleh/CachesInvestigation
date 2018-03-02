package com.investigation.cqengine.objects;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import com.googlecode.cqengine.attribute.Attribute;
import com.googlecode.cqengine.attribute.MultiValueAttribute;
import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.query.option.QueryOptions;

public class Car {

	static final AtomicLong VERSION_GENERATOR = new AtomicLong();

	// Version field should have a value such that if two objects with the same carId are created,
	// their version numbers will be different.
	// We cheat on this point, by simply assigning a unique version number to every car...
	final long version = VERSION_GENERATOR.incrementAndGet();

	public final int carId;
	public final String name;
	public final String description;
	public final List<String> features;

	public Car(int carId, String name, String description, List<String> features) {
		this.carId = carId;
		this.name = name;
		this.description = description;
		this.features = features;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Car car = (Car) o;
		return carId == car.carId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(carId);
	}

	// -------------------------- Attributes --------------------------
	public static final Attribute<Car, Integer> CAR_ID = new SimpleAttribute<Car, Integer>("carId") {
		public Integer getValue(Car car, QueryOptions queryOptions) { return car.carId; }
	};

	public static final Attribute<Car, String> NAME = new SimpleAttribute<Car, String>("name") {
		public String getValue(Car car, QueryOptions queryOptions) { return car.name; }
	};

	public static final Attribute<Car, String> DESCRIPTION = new SimpleAttribute<Car, String>("description") {
		public String getValue(Car car, QueryOptions queryOptions) { return car.description; }
	};

	public static final Attribute<Car, String> FEATURES = new MultiValueAttribute<Car, String>("features") {
		public List<String> getValues(Car car, QueryOptions queryOptions) { return car.features; }
	};

	@Override
	public String toString() {
		return "Car{carId=" + carId + ", name='" + name + "', description='" + description + "', features=" + features + "}";
	}
}