package com.investigation.guava.benchmark;

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

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.investigation.domain.DomainObject;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5)
@Fork(2)
public class GuavaCacheBenchmark {


	private static final long CACHE_SIZE_FACTOR = 2;
	private static final long DESIRED_SIZE = 200;

	private Cache<String, DomainObject> cache;

	private DomainObject exampleObject = new DomainObject("key",
			"prop2", "prop3","prop4","prop5","prop6","prop7",
			8,9,10,11,12,13,14,
			15,16,17,18,19,20);

	@Setup
	public void setup() {
		cache = CacheBuilder
				.newBuilder()
				.maximumSize(DESIRED_SIZE * CACHE_SIZE_FACTOR)
				.build();
	}

	@Benchmark
	@Threads(5)
	@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.NANOSECONDS)
	public void add() {
		this.cache.put("key", exampleObject);
	}

	@Benchmark
	@Threads(5)
	@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.NANOSECONDS)
	public DomainObject get() {
		return cache.getIfPresent("key");
	}

	@Benchmark
	@Threads(5)
	@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.NANOSECONDS)
	public DomainObject getAdd() {
		this.cache.put("key", exampleObject);
		return cache.getIfPresent("key");
	}

}
