package cracking.code.fb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TimeMap {
	private Map<String, List<Data>> map;

	public TimeMap() {
	}

	public void set(String key, String value, int timestamp) {
		if (!map.containsKey(key)) {
			map.put(key, new ArrayList<>());
		}
		map.get(key).add(new Data(value, timestamp));
	}

	public String get(String key, int timestamp) {
		if (!map.containsKey(key)) {
			return "";
		}
		List<Data> data = map.get(key);
		return findCloset(data, timestamp);
	}

	private String findCloset(List<Data> data, int timestamp) {
		int l = 0;
		int r = data.size() - 1;
		while (l < r) {
			int mid = (l + r + 1) / 2;
			if (data.get(mid).timestamp <= timestamp)
				l = mid;
			else
				r = mid - 1;
		}
		Data closetData = data.get(l);
		return closetData.timestamp > timestamp ? "" : closetData.value;
	}

	static class Data {
		String value;
		int timestamp;

		public Data(String value, int timestamp) {
			this.value = value;
			this.timestamp = timestamp;
		}
	}
}
