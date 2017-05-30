package com.investigation.benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import com.investigation.domain.DomainObject;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5)
@Fork(2)
public class EhCacheBenchmark {

	private static final int CACHE_SIZE_FACTOR = 2;
	private static final int DESIRED_SIZE = 200;

	private Cache cache;
	private CacheManager cacheManager = CacheManager.create();

	@Setup
	public void setup() {
		cacheManager.removeAllCaches();
		cacheManager.clearAll();
		this.cache = new Cache(
				new CacheConfiguration("TradeEventsCache " + Thread.currentThread().getId(), DESIRED_SIZE * CACHE_SIZE_FACTOR)
						.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.FIFO)
						.eternal(true)
		);
		cacheManager.addCache(this.cache);
	}


	@Benchmark
	@Threads(5)
	@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.NANOSECONDS)
	public void push() {
		this.cache.put(new Element("key", new DomainObject()));
	}

	@Benchmark
	@Threads(5)
	@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.NANOSECONDS)
	public DomainObject get() {
		Element cachedValue = cache.get("key");
		return cachedValue == null ? null :(DomainObject) cachedValue.getObjectValue();
	}

	@Benchmark
	@Threads(5)
	@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.NANOSECONDS)
	public DomainObject getPut() {
		this.cache.put(new Element("key", new DomainObject()));
		Element cachedValue = cache.get("key");
		return cachedValue == null ? null :(DomainObject) cachedValue.getObjectValue();
	}

}
