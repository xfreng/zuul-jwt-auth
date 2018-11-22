package com.fui.cloud.engine;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.nio.charset.Charset;

public class DatabaseEngine {

    private boolean databaseSchemaUpdate;
    private String databaseType;
    private DataSource dataSource;

    public DatabaseEngine(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() throws Exception {
        if (databaseSchemaUpdate) {
            ScriptRunner runner = new ScriptRunner(dataSource.getConnection());
            Resources.setCharset(Charset.forName("utf-8"));
            runner.runScript(Resources.getResourceAsReader("sql/" + databaseType + "/jwt.sql"));
            runner.runScript(Resources.getResourceAsReader("sql/" + databaseType + "/jwt-data.sql"));
            runner.closeConnection();
        }
    }

    public void setDatabaseSchemaUpdate(boolean databaseSchemaUpdate) {
        this.databaseSchemaUpdate = databaseSchemaUpdate;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }
}
