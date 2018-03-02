package com.investigation.cqengine.benchmark;

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

import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.googlecode.cqengine.IndexedCollection;
import com.googlecode.cqengine.query.Query;
import com.googlecode.cqengine.query.QueryFactory;
import com.googlecode.cqengine.resultset.ResultSet;
import com.investigation.cqengine.objects.Data;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5)
@Fork(2)
public class CQEngineCacheBenchmark {

	private IndexedCollection<Data> cache;

	private Data data = new Data("key",
			"prop2", "prop3","prop4","prop5","prop6","prop7",
			8,9,10,11,12,13,14,
			15,16,17,18,19,20);

	@Setup
	public void setup() {
		cache = new ConcurrentIndexedCollection<>();
	}

	@Benchmark
	@Threads(5)
	@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.NANOSECONDS)
	public void add() {
		cache.add(data);
	}

	@Benchmark
	@Threads(5)
	@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.NANOSECONDS)
	public Data get() {
		final Query<Data> query1 = QueryFactory.equal(Data.PROP10, 10);
		ResultSet<Data> retrieve = cache.retrieve(query1);
		return retrieve.isEmpty() ? data : retrieve.uniqueResult();
	}

	@Benchmark
	@Threads(5)
	@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.NANOSECONDS)
	public Data getAdd() {
		cache.add(data);
		final Query<Data> query1 = QueryFactory.equal(Data.PROP10, 10);
		ResultSet<Data> retrieve = cache.retrieve(query1);
		return retrieve.isEmpty() ? data : retrieve.uniqueResult();
	}
}
