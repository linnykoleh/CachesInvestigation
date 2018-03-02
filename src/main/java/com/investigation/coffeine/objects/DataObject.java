package com.investigation.coffeine.objects;

public class DataObject {

	private final String data;

	public DataObject(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DataObject{" +
				"data='" + data + '\'' +
				'}';
	}

}
