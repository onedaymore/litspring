package org.lispring.core.type.classreading;

import org.lispring.core.type.ClassMetadata;
import org.lispring.util.ClassUtils;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Opcodes;
import org.springframework.asm.SpringAsmInfo;

public class ClassMetadataReadingVistor extends ClassVisitor implements ClassMetadata {
	
	private String className;
	private boolean isInterface;
	private boolean isFinal;
	private boolean isAbstract;
	private String superClassName;
	private String [] interfaces;
	
	public ClassMetadataReadingVistor() {
		super(SpringAsmInfo.ASM_VERSION);
	}

	public ClassMetadataReadingVistor(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visit(int version, int access, String name, String signature, String supername, String[] interfaces) {
		this.className = ClassUtils.convertResourcePathToClassName(name);
		this.isInterface = ((access & Opcodes.ACC_INTERFACE) != 0);
		this.isAbstract = ((access & Opcodes.ACC_ABSTRACT) != 0);
		this.isFinal = ((access & Opcodes.ACC_FINAL) != 0);
		
		if (supername != null) {
			this.superClassName = ClassUtils.convertResourcePathToClassName(supername);
		}
		
		this.interfaces = new String[interfaces.length];
		for (int i = 0; i < interfaces.length; i++) {
			this.interfaces[i] = ClassUtils.convertResourcePathToClassName(interfaces[i]);
		}
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public boolean isInterface() {
		return isInterface;
	}

	public void setInterface(boolean isInterface) {
		this.isInterface = isInterface;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public String getSuperClassName() {
		return superClassName;
	}

	public void setSuperClassName(String superClassName) {
		this.superClassName = superClassName;
	}

	public String[] getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(String[] interfaces) {
		this.interfaces = interfaces;
	}

	
	
}
