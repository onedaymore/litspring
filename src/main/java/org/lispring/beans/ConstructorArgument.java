package org.lispring.beans;

import java.util.LinkedList;
import java.util.List;

import org.lispring.beans.ConstructorArgument.ValueHolder;

public class ConstructorArgument {

	private final List<ValueHolder> holders = new LinkedList<>();

	public ConstructorArgument() {
		
	}
	
	public void addArgumens(ValueHolder vh) {
		holders.add(vh);
	}
	
	
	public List<ValueHolder> getArgument() {
		return holders;
	}
	
	public int getSise() {
		return holders.size();
	}
	
	public static class ValueHolder {
		private Object value;
		
		public ValueHolder(Object value) {
			this.value = value;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}
		
		
	}

	public List<ValueHolder> getValueHolders() {
		// TODO Auto-generated method stub
		return holders;
	}
}
