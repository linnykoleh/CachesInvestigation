package com.investigation.caches;

import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.Test;

import com.investigation.domain.DomainObject;
import com.investigation.guava.GuavaCache;

public class TestGuavaCacheGet extends TestCachesParent {

	@Test
	public void testGuavaCacheGet100(){
		final int size = 100;
		final GuavaCache guavaCache = new GuavaCache(size);

		final Stream<DomainObject> pushData = generatePayload(size);
		pushData.forEach(guavaCache::push);

		measure(MEASURE_TIMES, () -> {
			final Stream<DomainObject> getData = generatePayload(size);
			calculateTimeExecuting(getData, (domainObject) -> guavaCache.get(domainObject.getKey()));
		});

		assertTrue("Error GuavaCache size different:", guavaCache.size() == size);
		guavaCache.cleanUp();
	}

	@Test
	public void testGuavaCacheGet10_000(){
		final int size = 10_000;
		final GuavaCache guavaCache = new GuavaCache(size);

		final Stream<DomainObject> pushData = generatePayload(size);
		pushData.forEach(guavaCache::push);

		measure(MEASURE_TIMES, () -> {
			final Stream<DomainObject> getData = generatePayload(size);
			calculateTimeExecuting(getData, (domainObject) -> guavaCache.get(domainObject.getKey()));
		});

		assertTrue("Error GuavaCache size different:", guavaCache.size() == size);
		guavaCache.cleanUp();
	}

	@Test
	public void testGuavaCacheGet1_000_000(){
		final int size = 1_000_000;
		final GuavaCache guavaCache = new GuavaCache(size);

		final Stream<DomainObject> pushData = generatePayload(size);
		pushData.forEach(guavaCache::push);

		measure(MEASURE_TIMES, () -> {
			final Stream<DomainObject> getData = generatePayload(size);
			calculateTimeExecuting(getData, (domainObject) -> guavaCache.get(domainObject.getKey()));
		});

		assertTrue("Error GuavaCache size different:", guavaCache.size() == size);
		guavaCache.cleanUp();
	}


}
