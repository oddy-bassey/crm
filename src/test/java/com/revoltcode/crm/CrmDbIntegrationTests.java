package com.revoltcode.crm;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class CrmDbIntegrationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Inserting data into the database before each test
    @BeforeEach
    public void populateDatabase(){
        jdbcTemplate.execute("insert into customer (CREATED_DATE, LAST_UPDATED_DATE, DATE_OF_BIRTH, EMAIL, FIRST_NAME, GENDER, LAST_NAME, ID) " +
                "values ('2022-06-11 20:09:14', '2021-08-11 20:09:14', '2021-09-12', 'dami@gmail.com', 'dami', 'female', 'adams', 'ef3b95f1-01b1-4dcf-9a65-0bb798e159c1')");
    }

    // Removing all the data from the database after each test
    @AfterEach
    public void cleanDatabase(){
        jdbcTemplate.execute("delete from customer");
    }

    @DisplayName("Querying the database to get total count of data stored.")
    @Test
    public void dbContainsData(){
        int numberOfCustomersSaved = jdbcTemplate.queryForObject("select count(*) from customer", Integer.class);
        assertTrue(numberOfCustomersSaved>0);
    }
}
