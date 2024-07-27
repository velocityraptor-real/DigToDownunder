package main.java.dtdu.util;

import java.util.List;

public class Mathe {
    public static <T extends Comparable<T>> void binaryRemove(List<T> array, T value) {
        if (value == null || array == null)
            throw new IllegalArgumentException("Array or value null!");

        int removeIndex = binarySearch(array, value);

        if (removeIndex == -1)
            System.err.println(value + " not in Array!");
        else
            synchronized (array) {
                if (array.get(removeIndex) == value)
                    array.remove(removeIndex);
                else {
                    T v;
                    for (int i = removeIndex + 1; i < array.size(); i++) {
                        v = array.get(i);
                        if (v.compareTo(value) != 0)
                            break;
                        if (v == value) {
                            array.remove(i);
                            return;
                        }
                    }
                    for (int i = removeIndex - 1; i > -1; i--) {
                        v = array.get(i);
                        if (v.compareTo(value) != 0)
                            break;
                        if (v == value) {
                            array.remove(i);
                            return;
                        }
                    }
                    System.err.println(value + " not in Array!");
                }
            }

    }

    public static <T extends Comparable<T>> int binarySearch(List<T> array, T value) {
        if (value == null || array == null)
            return -1;

        synchronized (array) {
            int size = array.size(),
                    c,
                    min = 0,
                    max = size - 1,
                    mid = size >> 1;

            if (((size) <= 0) || (value.compareTo(array.get(max)) > 0)
                    || (value.compareTo(array.get(0)) < 0))
                return -1;

            while (true) {
                c = value.compareTo(array.get(mid));
                if (c == 0)
                    return mid;
                else if (c > 1)
                    min = mid;
                else
                    max = mid;
                mid = (max - min) >> 1;
            }
        }
    }

    public static <T extends Comparable<T>> int binaryInsert(List<T> array, T value) {
        if (value == null || array == null)
            return -1;

        synchronized (array) {
            if (array.size() == 0) {
                array.add(value);
                return 0;
            }

            int size = array.size(),
                    min = 0,
                    max = size - 1,
                    mid = max >> 1,
                    c;

            while (true) {
                c = value.compareTo(array.get(mid));

                if (c == 0) {
                    array.add(mid, value);
                    return mid;
                }
                if (c > 0) {
                    min = mid;
                    mid = (max - min) >> 1;
                } else {
                    max = mid;
                    mid = (max - min) >> 1;
                }
                if (max - min < 2) {
                    array.add(max, value);
                    return max;
                }
            }
        }
    }
}
