package com.investigation.caches;

import com.investigation.domain.DomainObject;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

public class EhCache {

	private static final int CACHE_SIZE_FACTOR = 2;
	private static final int DESIRED_SIZE = 200; //must be configured

	private CacheManager cacheManager;
	private Cache cache;

	public EhCache(int size) {
		init(size);
	}

	private void init(int size) {
		this.cacheManager = CacheManager.create();
		this.cache = new Cache(
				new CacheConfiguration("TradeEventsCache", size * CACHE_SIZE_FACTOR)
						.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.FIFO)
						.eternal(true)
		);
		this.cacheManager.addCache(this.cache);
	}


	public void push(DomainObject event) {
		this.cache.put(new Element(event.getKey(), event));
	}

	public DomainObject get(String key) {
		Element cachedValue = cache.get(key);
		return (DomainObject) cachedValue.getObjectValue();
	}

	public void cleanUp(){
		cache.removeAll();
		cache = null;
		cacheManager.clearAll();
		cacheManager.removeAllCaches();
		cacheManager.shutdown();
	}

	public int size(){
		return cache.getSize();
	}
}
