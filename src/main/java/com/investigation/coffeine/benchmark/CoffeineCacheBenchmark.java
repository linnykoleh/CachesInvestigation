package com.investigation.coffeine.benchmark;

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

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.investigation.domain.DomainObject;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5)
@Fork(2)
public class CoffeineCacheBenchmark {

	private Cache<String, DomainObject> cache;

	private DomainObject domainObject = new DomainObject("key",
			"prop2", "prop3","prop4","prop5","prop6","prop7",
			8,9,10,11,12,13,14,
			15,16,17,18,19,20);

	@Setup
	public void setup() {
		cache = Caffeine.newBuilder()
				.expireAfterWrite(1, TimeUnit.MINUTES)
				.maximumSize(100)
				.build();
	}

	@Benchmark
	@Threads(5)
	@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.NANOSECONDS)
	public void add() {
		cache.put("key1", domainObject);
	}

	@Benchmark
	@Threads(5)
	@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.NANOSECONDS)
	public DomainObject get() {
		return cache.get("key", s -> domainObject);
	}

	@Benchmark
	@Threads(5)
	@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.NANOSECONDS)
	public DomainObject getAdd() {
		cache.put("key", domainObject);
		return cache.get("key", s -> domainObject);
	}

}
