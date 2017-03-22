package com.genesis.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.genesis.domain.User;

@Component
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    // lets test creation of an entity via manual insert
    public void createUser(User user) {
        jdbcTemplate.update("insert into user (id, name, age) values(?,?,?)", new Object[]{user.getId(), user.getName(), user.getAge()});
    }
    
    // lets test a list retrieval for all
    public List<User> findAll() {
        return jdbcTemplate.query("select id, name, age from user", new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                
                return user;
            }
        });
    }
    
    // how about calculation with sum and count then get average via jdbc on crate
    public Integer averageAge() {
        int totalNumberOfUsers = jdbcTemplate.queryForObject("select count(id) from user", Integer.class);
        int totalAge = jdbcTemplate.queryForObject("select sum(age) from user", Integer.class);
        return totalAge / totalNumberOfUsers;
    }
    
}
