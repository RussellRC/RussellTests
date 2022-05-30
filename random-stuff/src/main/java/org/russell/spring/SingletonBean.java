package org.russell.spring;

import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SingletonBean {
    
    @Autowired
    Provider<PrototypeBean> provider;

    public PrototypeBean getPrototypeBean() {
        return provider.get();
    }
    
}
