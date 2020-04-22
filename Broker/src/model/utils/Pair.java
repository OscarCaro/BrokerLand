package model.utils;

public class Pair<T1, T2> {
	private T1 _key;
	private T2 _value;

	public Pair(T1 key, T2 value) {
		_key = key;
		_value = value;
	}

	public T1 getKey() {
		return _key;
	}

	public T2 getValue() {
		return _value;
	}

}

