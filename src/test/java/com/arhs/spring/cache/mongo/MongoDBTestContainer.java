package com.arhs.spring.cache.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.MongoDBContainer;

/**
 * @author Якименко Михаил
 */
public class MongoDBTestContainer extends MongoDBContainer {
    public static final String MONGODB_PORT = "__TEST_MONGODB_PORT";

    private static final String IMAGE_VERSION = "mongo:3.6";
    private static MongoDBTestContainer container;
    private static Logger LOGGER = LoggerFactory.getLogger(MongoDBTestContainer.class);

    private MongoDBTestContainer() {
        super(IMAGE_VERSION);
    }

    public static MongoDBTestContainer getInstance() {
        if (container == null) {
            container = new MongoDBTestContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty(MONGODB_PORT, container.getFirstMappedPort().toString());
        LOGGER.info("Started MongoDB");
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
        LOGGER.info("MongoDB was shutdown");
    }
}
