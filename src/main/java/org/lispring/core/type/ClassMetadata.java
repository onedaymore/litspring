package org.lispring.core.type;

public interface ClassMetadata {

	boolean isAbstract();
	
	boolean isInterface();
	
	boolean isFinal();
	
	String getClassName();
}
