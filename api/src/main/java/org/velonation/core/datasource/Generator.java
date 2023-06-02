package org.velonation.core.datasource;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.factory.spi.StandardGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import java.util.Properties;
import java.util.Random;


public class Generator implements StandardGenerator {
    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        StandardGenerator.super.configure(type, params, serviceRegistry);
    }

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return new Random().nextInt(8999) + 1000;
    }
}
