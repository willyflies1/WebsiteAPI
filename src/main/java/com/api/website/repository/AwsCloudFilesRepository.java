package com.api.website.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class AwsCloudFilesRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AwsCloudFilesRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String getRoles() {
        String result = jdbcTemplate.queryForObject(
                "SELECT * FROM roles", new RowMapper<String>() {

                    @Override
                    public String mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        return rs.getString(1);
                    }
                });
        return result;
    }

}
