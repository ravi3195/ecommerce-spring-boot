package com.ravicoding.ecommerce.config;

import com.ravicoding.ecommerce.entity.Country;
import com.ravicoding.ecommerce.entity.Product;
import com.ravicoding.ecommerce.entity.ProductCategory;
import com.ravicoding.ecommerce.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer
{
    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager)
    {
        entityManager=theEntityManager;
    }


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config)
    {
        HttpMethod[] theUnsupportedActions= {HttpMethod.POST,HttpMethod.PUT, HttpMethod.DELETE};

        //Disable http method for product category, Product, Country and State Entity: POST, PUT, DELETE
        disableHttpMethods(ProductCategory.class,config, theUnsupportedActions);
        disableHttpMethods(Product.class,config, theUnsupportedActions);
        disableHttpMethods(Country.class,config, theUnsupportedActions);
        disableHttpMethods(State.class,config, theUnsupportedActions);

        // Call an internal helper method
        exposeIds(config);
    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config)
    {
        //expose entity ids

        // Get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities= entityManager.getMetamodel().getEntities();

        // Create the array of entity type
        List<Class> entityClasses = new ArrayList<>();

        // Get the entity types for the entity
        for(EntityType tempEntityType: entities)
        {
            entityClasses.add(tempEntityType.getJavaType());
        }
        //expose the entity ids for the list of entity/domain type
        Class[] domainTypes= entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
