package com.example.demo;

import com.example.work.controller.ProductController;
import com.example.work.model.Product;
import com.example.work.repository.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
class DemoApplicationTests {


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mock;

    @MockBean
    Repository repository;


    @Test
    void getById() throws Exception {
        Product product = new Product("6", "iphone", "us", 200.0);
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        mock.perform(MockMvcRequestBuilders.get("/api/products/?id=1")).
                andExpect(status().is(200)).
                andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(jsonPath("$.name", is("6")));
    }

    @Test
    void getAll() throws Exception {
        Product product1 = new Product("6", "iphone", "us", 200.0);
        Product product2 = new Product("7", "iphone", "us", 300.0);
        Product product3 = new Product("2210", "nokia", "china", 150.0);
        Product product4 = new Product("s10+", "samsung", "england", 500.0);
        List<Product> products = new ArrayList<>(Arrays.asList(product1, product2, product3, product4));

        when(repository.findAllByOrderByIdAsc()).thenReturn(products);
        MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.get("/api/products")).
                andExpect(status().is(200)).
                andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(jsonPath("$.size()", is(4))).
                andExpect(jsonPath("$[0].name", is("6"))).
                andExpect(jsonPath("$[1].name", is("7"))).
                andExpect(jsonPath("$[2].name", is("2210"))).
                andExpect(jsonPath("$[3].name", is("s10+"))).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        String expectedResponse = objectMapper.writeValueAsString(products);
        assertThat(expectedResponse).isEqualToIgnoringWhitespace(contentAsString);


    }

    @Test
    void addProduct() throws Exception {
        Product product = new Product("6", "iphone", "us", 200.0);
        when(repository.save(any(Product.class))).thenReturn(product);
        mock.perform(post("/api/products")
                .content(objectMapper.writeValueAsString(product))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("6")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand", Matchers.is("iphone")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.madeIn", Matchers.is("us")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is(200.0)));
        verify(repository, times(1)).save(any(Product.class));
    }

    @Test
    void delete() throws Exception {
        Product product = new Product("6", "iphone", "us", 200.0);
//        product.setId(1L);
        doNothing().when(repository).delete(product);
        mock.perform(MockMvcRequestBuilders.delete("/api/products/?id=1").content(objectMapper.writeValueAsString(product)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1L)));
    }


}
