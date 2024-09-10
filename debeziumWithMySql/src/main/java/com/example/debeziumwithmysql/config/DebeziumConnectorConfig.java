package com.example.debeziumwithmysql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class DebeziumConnectorConfig {

    // PostgreSQL database connection details
    String databaseHostname = "localhost";
    String databasePort = "5433";
    String databaseUser = "postgres";
    String databasePassword = "postgres";
    String databaseName = "debezium-postgres";


    @Bean
    public io.debezium.config.Configuration customerConnector() throws IOException {
        var offsetStorageTempFile = File.createTempFile("offsets_", ".dat");
        return io.debezium.config.Configuration.create()
                .with("name", "customer_postgres_connector")
                .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", offsetStorageTempFile.getAbsolutePath())
                .with("offset.flush.interval.ms", "60000")
                .with("database.hostname", databaseHostname)
                .with("database.port", databasePort)
                .with("database.user", databaseUser)
                .with("database.password", databasePassword)
                .with("database.dbname", databaseName)
                .with("database.server.id", "10181")
                .with("database.server.name", "customer-postgres-db-server")
                .with("topic.prefix", "slave-postgresql")
                .with("database.history", "io.debezium.relational.history.MemoryDatabaseHistory")
                .with("publication.autocreate.mode", "all_tables")
                .with("plugin.name", "pgoutput")
                .with("slot.name", "dbz_customerdb_listener")
                .build();
    }
}
