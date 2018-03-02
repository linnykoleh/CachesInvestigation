package com.investigation.coffeine.example;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.investigation.coffeine.objects.DataObject;

public class CoffeineExampleMain {

	/*http://www.baeldung.com/java-caching-caffeine*/

	public static void main(String[] args) throws InterruptedException {
		Cache<String, DataObject> cache = Caffeine.newBuilder()
				.expireAfter(new Expiry<String, DataObject>() {
					@Override
					public long expireAfterCreate(String key, DataObject value, long currentTime) {
						System.out.println(key + " " + currentTime);
						return key.equals("A") ? 1_500_000_000 : currentTime;
					}
					@Override
					public long expireAfterUpdate(String key, DataObject value, long currentTime, long currentDuration) {
						return currentDuration;
					}
					@Override
					public long expireAfterRead(String key, DataObject value, long currentTime, long currentDuration) {
						return currentDuration;
					}
				})
				.maximumSize(100)
				.build();


		cache.put("A", new DataObject("A"));
		cache.put("B", new DataObject("B"));
		cache.put("C", new DataObject("C"));
		cache.put("D", new DataObject("D"));
		cache.put("D", new DataObject("DD")); //update
		cache.invalidate("C"); //remove

		Thread.sleep(1498);

		DataObject a = cache.getIfPresent("A");
		System.out.println(a);

		cache.asMap().values().forEach(System.out::println);
	}

}
