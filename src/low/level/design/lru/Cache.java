package low.level.design.lru;

import java.util.Optional;

public interface Cache<K, V> {
	
	boolean set(K key, V value);

	Optional<V> get(K key);

	int size();

	boolean isEmpty();

	void clear();
	
}