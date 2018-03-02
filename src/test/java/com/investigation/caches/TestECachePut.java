package com.investigation.caches;

import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.Test;

import com.investigation.domain.DomainObject;
import com.investigation.ehcache.EhCache;

public class TestECachePut extends TestCachesParent {

	@Test
	public void testECachePut100(){
		measure(MEASURE_TIMES, () -> {
			final int size = 100;
			final EhCache ehCache = new EhCache(size);

			final Stream<DomainObject> domainObjects = generatePayload(size);
			calculateTimeExecuting(domainObjects, ehCache::push);

			assertTrue("Error EhCache size different:", ehCache.size() == size);
			ehCache.cleanUp();
		});
	}

	@Test
	public void testECachePut10_000(){
		measure(MEASURE_TIMES, () -> {
			final int size = 10_000;
			final EhCache ehCache = new EhCache(size);

			final Stream<DomainObject> domainObjects = generatePayload(size);
			calculateTimeExecuting(domainObjects, ehCache::push);

			assertTrue("Error EhCache size different:", ehCache.size() == size);
			ehCache.cleanUp();
		});
	}

	@Test
	public void testECachePut1_000_000(){
		measure(MEASURE_TIMES, () -> {
			final int size = 1_000_000;
			final EhCache ehCache = new EhCache(size);

			final Stream<DomainObject> domainObjects = generatePayload(size);
			calculateTimeExecuting(domainObjects, ehCache::push);

			assertTrue("Error EhCache size different:", ehCache.size() == size);
			ehCache.cleanUp();
		});
	}


}
