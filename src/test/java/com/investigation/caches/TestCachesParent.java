package com.investigation.caches;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.apache.commons.lang.RandomStringUtils;

import com.google.common.base.Stopwatch;
import com.investigation.domain.DomainObject;

class TestCachesParent {

	static final int MEASURE_TIMES = 20;

	private final Stopwatch timer = Stopwatch.createUnstarted();
	private Random r = new Random();

	Stream<DomainObject> generatePayload(final int size){
		final List<DomainObject> domainObjects = new ArrayList<>();
		for(int key = 0; key < size; key++) {
			final DomainObject object = new DomainObject(
					String.valueOf(key),

					RandomStringUtils.randomAlphanumeric(10),
					RandomStringUtils.randomAlphanumeric(10),
					RandomStringUtils.randomAlphanumeric(10),
					RandomStringUtils.randomAlphanumeric(10),
					RandomStringUtils.randomAlphanumeric(10),
					RandomStringUtils.randomAlphanumeric(10),

					r.nextInt(),
					r.nextInt(),
					r.nextInt(),
					r.nextInt(),
					r.nextInt(),
					r.nextInt(),
					r.nextInt(),

					r.nextDouble(),
					r.nextDouble(),
					r.nextDouble(),
					r.nextDouble(),
					r.nextDouble(),
					r.nextDouble()
			);

			domainObjects.add(object);
		}
		return domainObjects.stream();
	}

	<T> void calculateTimeExecuting(Stream<T> stream, Consumer<T> consumer){
		timer.reset();
		timer.start();
		stream.forEach(consumer);
		System.out.println(timer.stop());
		timer.reset();
	}

	public void measure(int times, Runnable testSuite){
		for(int i = 0; i < times; i++){
			testSuite.run();
		}
	}

}
