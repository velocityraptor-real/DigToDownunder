package main.java.dtdu.util;

import java.util.List;

public class Mathe {
	public static <T extends Comparable<T>> void binaryRemove(List<T> array, T value) {
		int i = binarySearch(array, value);
		if(i != -1) synchronized(array) {
			if(array.get(i) == value) array.remove(i);
			else {
				T v; int j = i;
				while(j > 0 && (v = array.get(--i)).compareTo(value) == 0) if(v == value) {
					array.remove(i);
					return;
				} i = array.size() - 1;
				while(j < i && (v = array.get(++j)).compareTo(value) == 0) if(v == value) {
					array.remove(j);
					return;
				} System.err.println(value + " not in Array!");
			}
		} else System.err.println(value + " not in Array!");
	}
	public static <T extends Comparable<T>> int binarySearch(List<T> array, T value) {
		synchronized(array) {if(array.size() > 0) {
			int min = 0, max = array.size() - 1, mid, c;
			while(true) {
				c = value.compareTo(array.get(max));
				if(c == 0) return max;
				if(c > 0) return -1;
				c = value.compareTo(array.get(min));
				if(c == 0) return min;
				if(c < 0 || max - min < 2) return -1;
				c = value.compareTo(array.get(mid = (min + max) >> 1));
				if(c == 0) return mid;
				if(c < 0) max = mid - 1;
				else min = mid + 1;
			}
		}} return -1;
	}
	public static <T extends Comparable<T>> int binaryInsert(List<T> array, T value) {
		if(value != null) {
			synchronized(array) {
				if(array.size() == 0) {
					array.add(value);
					return 0;
				} int min = 0, max = array.size() - 1, mid, c;
				while(true) {
					if(value.compareTo(array.get(max)) > -1) {
						array.add(max + 1, value);
						return max + 1;
					} if(value.compareTo(array.get(min)) < 1) {
						array.add(min, value);
						return min;
					} if(max - min < 2) {
						array.add(max, value);
						return max;
					} c = value.compareTo(array.get(mid = (min + max) >> 1));
					if(c == 0) {
						array.add(mid, value);
						return mid;
					} if(c < 0) max = mid - 1;
					else min = mid + 1;
				}
			}
		} return -1;
	}
}