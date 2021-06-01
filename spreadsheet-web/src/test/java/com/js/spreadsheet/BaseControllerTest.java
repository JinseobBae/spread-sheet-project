package com.js.spreadsheet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
public class BaseControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private final String MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;
    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper;

    @BeforeAll
    void testSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }


    /**
     * Get request without param
     * @param url
     * @return
     * @throws Exception
     */
    protected ResultActions requestGet(String url) throws Exception{
        return requestGet(url, new LinkedMultiValueMap<>());
    }

    /**
     * Get request with params
      * @param url
     * @param params
     * @return
     * @throws Exception
     */
    protected ResultActions requestGet(String url, MultiValueMap<String, String> params) throws Exception{
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url)
                .params(params)
                .contentType(MEDIA_TYPE)
                .accept(MEDIA_TYPE);
        return this.mockMvc.perform(builder);
    }

    /**
     * Post request
      * @param url
     * @param param
     * @return
     * @throws Exception
     */
    protected ResultActions requestPost(String url, Object param) throws Exception{
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url)
                .content(objectMapper.writeValueAsString(param))
                .contentType(MEDIA_TYPE)
                .accept(MEDIA_TYPE);
        return this.mockMvc.perform(builder);
    }

}
