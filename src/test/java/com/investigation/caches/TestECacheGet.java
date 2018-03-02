package com.investigation.caches;

import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.Test;

import com.investigation.domain.DomainObject;
import com.investigation.ehcache.EhCache;

public class TestECacheGet extends TestCachesParent {

	@Test
	public void testECacheGet100(){
		final int size = 100;
		final EhCache ehCache = new EhCache(size);

		final Stream<DomainObject> pushData = generatePayload(size);
		pushData.forEach(ehCache::push);

		measure(MEASURE_TIMES, () -> {
			final Stream<DomainObject> getData = generatePayload(size);
			calculateTimeExecuting(getData, (domainObject) -> ehCache.get(domainObject.getKey()));
		});

		assertTrue("Error EhCache size different:", ehCache.size() == size);
		ehCache.cleanUp();
	}

	@Test
	public void testECacheGet10_000(){
		final int size = 10_000;
		final EhCache ehCache = new EhCache(size);

		final Stream<DomainObject> pushData = generatePayload(size);
		pushData.forEach(ehCache::push);

		measure(MEASURE_TIMES, () -> {
			final Stream<DomainObject> getData = generatePayload(size);
			calculateTimeExecuting(getData, (domainObject) -> ehCache.get(domainObject.getKey()));
		});

		assertTrue("Error EhCache size different:", ehCache.size() == size);
		ehCache.cleanUp();
	}

	@Test
	public void testECacheGet1_000_000(){
		final int size = 1_000_000;
		final EhCache ehCache = new EhCache(size);

		final Stream<DomainObject> pushData = generatePayload(size);
		pushData.forEach(ehCache::push);

		measure(MEASURE_TIMES, () -> {
			final Stream<DomainObject> getData = generatePayload(size);
			calculateTimeExecuting(getData, (domainObject) -> ehCache.get(domainObject.getKey()));
		});

		assertTrue("Error EhCache size different:", ehCache.size() == size);
		ehCache.cleanUp();
	}


}
