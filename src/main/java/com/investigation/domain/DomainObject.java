package com.investigation.domain;


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

		if (prop8 != that.prop8) {
			return false;
		}
		if (prop9 != that.prop9) {
			return false;
		}
		if (prop10 != that.prop10) {
			return false;
		}
		if (prop11 != that.prop11) {
			return false;
		}
		if (prop12 != that.prop12) {
			return false;
		}
		if (prop13 != that.prop13) {
			return false;
		}
		if (prop14 != that.prop14) {
			return false;
		}
		if (Double.compare(that.prop15, prop15) != 0) {
			return false;
		}
		if (Double.compare(that.prop16, prop16) != 0) {
			return false;
		}
		if (Double.compare(that.prop17, prop17) != 0) {
			return false;
		}
		if (Double.compare(that.prop18, prop18) != 0) {
			return false;
		}
		if (Double.compare(that.prop19, prop19) != 0) {
			return false;
		}
		if (Double.compare(that.prop20, prop20) != 0) {
			return false;
		}
		if (key != null ? !key.equals(that.key) : that.key != null) {
			return false;
		}
		if (prop2 != null ? !prop2.equals(that.prop2) : that.prop2 != null) {
			return false;
		}
		if (prop3 != null ? !prop3.equals(that.prop3) : that.prop3 != null) {
			return false;
		}
		if (prop4 != null ? !prop4.equals(that.prop4) : that.prop4 != null) {
			return false;
		}
		if (prop5 != null ? !prop5.equals(that.prop5) : that.prop5 != null) {
			return false;
		}
		if (prop6 != null ? !prop6.equals(that.prop6) : that.prop6 != null) {
			return false;
		}
		return prop7 != null ? prop7.equals(that.prop7) : that.prop7 == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = key != null ? key.hashCode() : 0;
		result = 31 * result + (prop2 != null ? prop2.hashCode() : 0);
		result = 31 * result + (prop3 != null ? prop3.hashCode() : 0);
		result = 31 * result + (prop4 != null ? prop4.hashCode() : 0);
		result = 31 * result + (prop5 != null ? prop5.hashCode() : 0);
		result = 31 * result + (prop6 != null ? prop6.hashCode() : 0);
		result = 31 * result + (prop7 != null ? prop7.hashCode() : 0);
		result = 31 * result + prop8;
		result = 31 * result + prop9;
		result = 31 * result + prop10;
		result = 31 * result + prop11;
		result = 31 * result + prop12;
		result = 31 * result + prop13;
		result = 31 * result + prop14;
		temp = Double.doubleToLongBits(prop15);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(prop16);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(prop17);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(prop18);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(prop19);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(prop20);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "{" + key + '}';
	}
}
