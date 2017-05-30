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
	public void push() {
		this.cache.put("key", new DomainObject());
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
	public DomainObject getPut() {
		this.cache.put("key", new DomainObject());
		return cache.getIfPresent("key");
	}

}
