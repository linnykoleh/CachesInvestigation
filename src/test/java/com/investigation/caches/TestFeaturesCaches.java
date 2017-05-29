package com.investigation.caches;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.investigation.domain.DomainObject;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

public class TestFeaturesCaches {

	@Test
	public void testFeatureGuavaCacheGenerics(){
		final Cache<String, DomainObject> cache = CacheBuilder
				.newBuilder()
				.maximumSize(500)
				.maximumWeight(1000)
				.build();
	}

	@Test
	public void testFeatureGuavaCacheLoadingCache() throws ExecutionException {
		final LoadingCache<String, String> cache = CacheBuilder
				.newBuilder()
				.build(new CacheLoader<String, String>() {
					@Override
					public String load(String key) {
						return key.toUpperCase();
					}
				});


		System.out.println(cache.getUnchecked("key"));
	}

	@Test
	public void testFeatureEhCache(){
		CacheManager cacheManager = CacheManager.create();
		net.sf.ehcache.Cache cache = new net.sf.ehcache.Cache(
				new CacheConfiguration("TradeEventsCache", 500)
						.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.FIFO)
						.cacheLoaderFactory(new CacheConfiguration.CacheLoaderFactoryConfiguration())
						.cacheExtensionFactory(new CacheConfiguration.CacheExtensionFactoryConfiguration())
						.eternal(true)
				.timeToIdleSeconds(10)
		);
		cacheManager.addCache(cache);
	}


}
