package org.lispring.beans.factory.support;

import org.lispring.beans.factory.config.RuntimeBeanRefrence;
import org.lispring.beans.factory.config.TypeStringValue;

public class BeanDefineValueResolver {
	
	private DefaultBeanFactory facorty;

	public BeanDefineValueResolver(DefaultBeanFactory facorty) {
		this.facorty = facorty;
	}

	public Object resolveValueIfNecessary(Object obj) {
		
		if (obj instanceof RuntimeBeanRefrence) {
			RuntimeBeanRefrence ref = (RuntimeBeanRefrence) obj;
			return facorty.getBean(ref.getBeanName());
		} else if (obj instanceof TypeStringValue) {
			TypeStringValue tsv = (TypeStringValue) obj;
			return tsv.getValue();
		}
		return null;
	}
	

}
