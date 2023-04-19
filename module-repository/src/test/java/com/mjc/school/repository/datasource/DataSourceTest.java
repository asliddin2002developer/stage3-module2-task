package com.mjc.school.repository.datasource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataSourceTest {
    @Test
    void givenDataSource_whenInstanceCreated_thenShouldNotBeEmpty(){
        DataSource dataSource = new DataSource();
        Assertions.assertNotNull(dataSource);
    }
}
