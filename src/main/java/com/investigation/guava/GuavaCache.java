package com.investigation.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.investigation.domain.DomainObject;

public class GuavaCache {

	private static final long CACHE_SIZE_FACTOR = 2;
	private static final long DESIRED_SIZE = 200; //must be configured

	private Cache<String, DomainObject> cache;

	public GuavaCache(long size) {
		init(size);
	}

	private void init(long size){
		cache = CacheBuilder
				.newBuilder()
				.maximumSize(size * CACHE_SIZE_FACTOR)
				.build();
	}

	public void push(DomainObject event) {
		this.cache.put(event.getKey(), event);
	}

	public DomainObject get(String key) {
		return cache.getIfPresent(key);
	}


	public void cleanUp(){
		cache.cleanUp();
	}

	public long size(){
		return cache.size();
	}
}
