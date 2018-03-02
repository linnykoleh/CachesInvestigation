package com.investigation.domain;

import java.util.Objects;

public class DomainObject {

	private String key;

	private String prop2;
	private String prop3;
	private String prop4;
	private String prop5;
	private String prop6;
	private String prop7;

	private int prop8;
	private int prop9;
	private int prop10;
	private int prop11;
	private int prop12;
	private int prop13;
	private int prop14;

	private double prop15;
	private double prop16;
	private double prop17;
	private double prop18;
	private double prop19;
	private double prop20;

	public DomainObject(String key, String prop2, String prop3, String prop4, String prop5, String prop6, String prop7, int prop8, int prop9,
			int prop10, int prop11, int prop12, int prop13, int prop14, double prop15, double prop16, double prop17, double prop18,
			double prop19,
			double prop20) {
		this.key = key;
		this.prop2 = prop2;
		this.prop3 = prop3;
		this.prop4 = prop4;
		this.prop5 = prop5;
		this.prop6 = prop6;
		this.prop7 = prop7;
		this.prop8 = prop8;
		this.prop9 = prop9;
		this.prop10 = prop10;
		this.prop11 = prop11;
		this.prop12 = prop12;
		this.prop13 = prop13;
		this.prop14 = prop14;
		this.prop15 = prop15;
		this.prop16 = prop16;
		this.prop17 = prop17;
		this.prop18 = prop18;
		this.prop19 = prop19;
		this.prop20 = prop20;
	}

	public DomainObject() {
	}

	public String getKey() {
		return key;
	}

	public String getProp2() {
		return prop2;
	}

	public String getProp3() {
		return prop3;
	}

	public String getProp4() {
		return prop4;
	}

	public String getProp5() {
		return prop5;
	}

	public String getProp6() {
		return prop6;
	}

	public String getProp7() {
		return prop7;
	}

	public int getProp8() {
		return prop8;
	}

	public int getProp9() {
		return prop9;
	}

	public int getProp10() {
		return prop10;
	}

	public int getProp11() {
		return prop11;
	}

	public int getProp12() {
		return prop12;
	}

	public int getProp13() {
		return prop13;
	}

	public int getProp14() {
		return prop14;
	}

	public double getProp15() {
		return prop15;
	}

	public double getProp16() {
		return prop16;
	}

	public double getProp17() {
		return prop17;
	}

	public double getProp18() {
		return prop18;
	}

	public double getProp19() {
		return prop19;
	}

	public double getProp20() {
		return prop20;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DomainObject that = (DomainObject) o;
		return prop8 == that.prop8 &&
				prop9 == that.prop9 &&
				prop10 == that.prop10 &&
				prop11 == that.prop11 &&
				prop12 == that.prop12 &&
				prop13 == that.prop13 &&
				prop14 == that.prop14 &&
				Double.compare(that.prop15, prop15) == 0 &&
				Double.compare(that.prop16, prop16) == 0 &&
				Double.compare(that.prop17, prop17) == 0 &&
				Double.compare(that.prop18, prop18) == 0 &&
				Double.compare(that.prop19, prop19) == 0 &&
				Double.compare(that.prop20, prop20) == 0 &&
				Objects.equals(key, that.key) &&
				Objects.equals(prop2, that.prop2) &&
				Objects.equals(prop3, that.prop3) &&
				Objects.equals(prop4, that.prop4) &&
				Objects.equals(prop5, that.prop5) &&
				Objects.equals(prop6, that.prop6) &&
				Objects.equals(prop7, that.prop7);
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, prop2, prop3, prop4, prop5, prop6, prop7, prop8, prop9, prop10, prop11, prop12, prop13,
						prop14, prop15, prop16, prop17, prop18, prop19, prop20);
	}

	@Override
	public String toString() {
		return "{" + key + '}';
	}
}
