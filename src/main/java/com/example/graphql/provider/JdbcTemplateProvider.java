package com.example.graphql.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static java.util.Objects.requireNonNull;
import static org.springframework.jdbc.datasource.DataSourceUtils.getConnection;
import static org.springframework.jdbc.datasource.init.ScriptUtils.executeSqlScript;

/**
 * @author Uladik
 */
@Component
public class JdbcTemplateProvider {

    private JdbcTemplate template;
    private final String initScriptFileName;

    @Autowired
    public JdbcTemplateProvider(DataSource source, @Qualifier("initScriptFileName") String initScriptFileName) {
        this.template = new JdbcTemplate(source);
        this.initScriptFileName = initScriptFileName;
        initSchema();
    }

    public JdbcTemplate getTemplate() {
        return this.template;
    }

    private void initSchema() {
        Resource initScript = new ClassPathResource(initScriptFileName);
        executeSqlScript(getConnection(requireNonNull(template.getDataSource())), initScript);
    }
}
