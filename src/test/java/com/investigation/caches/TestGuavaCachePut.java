package com.investigation.caches;

import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.investigation.domain.DomainObject;
import com.investigation.guava.GuavaCache;

public class TestGuavaCachePut extends TestCachesParent {

	@Test
	public void testGuavaCachePut100(){
		measure(MEASURE_TIMES, () -> {
			final int size = 100;
			final GuavaCache guavaCache = new GuavaCache(size);

			final Stream<DomainObject> domainObjects = generatePayload(size);
			calculateTimeExecuting(domainObjects, guavaCache::push);

			Assert.assertTrue("Error GuavaCache size different:", guavaCache.size() == size);
			guavaCache.cleanUp();
		});
	}

	@Test
	public void testGuavaCachePut10_000(){
		measure(MEASURE_TIMES, () -> {
			final int size = 10_000;
			final GuavaCache guavaCache = new GuavaCache(size);

			final Stream<DomainObject> domainObjects = generatePayload(size);
			calculateTimeExecuting(domainObjects, guavaCache::push);

			Assert.assertTrue("Error GuavaCache size different:", guavaCache.size() == size);
			guavaCache.cleanUp();
		});
	}

	@Test
	public void testGuavaCachePut1_000_000(){
		measure(MEASURE_TIMES, () -> {
			final int size = 1_000_000;
			final GuavaCache guavaCache = new GuavaCache(size);

			final Stream<DomainObject> domainObjects = generatePayload(size);
			calculateTimeExecuting(domainObjects, guavaCache::push);

			Assert.assertTrue("Error GuavaCache size different:", guavaCache.size() == size);
			guavaCache.cleanUp();
		});
	}

}
