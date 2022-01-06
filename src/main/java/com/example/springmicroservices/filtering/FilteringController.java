package com.example.springmicroservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter.filterOutAllExcept;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean() {
        UserBean bean = new UserBean("Luke", "Skywalker", "lastJedi");
        MappingJacksonValue mapping = new MappingJacksonValue(bean);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter(
                        "userFilter",
                        filterOutAllExcept("firstName", "lastName")
                );
        mapping.setFilters(filterProvider);
        return mapping;
    }
}