package com.lightshoes.springtest.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lightshoes.springtest.product.dto.request.ProductCreate;
import com.lightshoes.springtest.product.dto.response.ProductResponse;
import com.lightshoes.springtest.product.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("MockMvc를 통한 Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception {
        //given
        BDDMockito.given(productService.getProduct(123L))
                .willReturn(new ProductResponse(123L, "pen", 5000, 200));

        String productNumber = "123";

        //expected
        mockMvc.perform(MockMvcRequestBuilders.get("/product?number=" + productNumber))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("pen"))
                .andExpect(jsonPath("$.price").value(5000))
                .andExpect(jsonPath("$.stock").value(200))
                .andDo(print());

        Mockito.verify(productService).getProduct(123L);
    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception {

        BDDMockito.given(productService.saveProduct(new ProductCreate("pen", 5000, 2000)))
                .willReturn(new ProductResponse(12315L, "pen", 5000, 2000));

        ProductCreate request = ProductCreate.builder()
                .name("pen")
                .price(5000)
                .stock(2000)
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(12315L))
                .andExpect(jsonPath("$.name").value("pen"))
                .andExpect(jsonPath("$.price").value(5000))
                .andExpect(jsonPath("$.stock").value(2000))
                .andDo(print());

        BDDMockito.then(productService).should().saveProduct(ArgumentMatchers.any(ProductCreate.class));
    }
}