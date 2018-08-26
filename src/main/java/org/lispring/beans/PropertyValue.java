package org.lispring.beans;

public class PropertyValue {
	private final String name;
	private final Object value;
	private Object convertValue;
	
	private boolean isConverted = false;
	
	public PropertyValue(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}


	public Object getConvertValue() {
		return convertValue;
	}

	public void setConvertValue(Object convertValue) {
		this.convertValue = convertValue;
	}

	public boolean isConverted() {
		return isConverted;
	}

	public void setConverted(boolean isConverted) {
		this.isConverted = isConverted;
	}
	
	

	
	
	

}
