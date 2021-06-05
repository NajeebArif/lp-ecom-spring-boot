package com.cakefactory.controllers;

import com.cakefactory.service.CatalogService;
import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(CatalogController.class)
class CatalogServiceControllerTest {

    private static final Logger log = LoggerFactory.getLogger(CatalogServiceControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    WebClient webClient;

    @MockBean
    CatalogService catalogService;

    @BeforeEach
    void init(){
        webClient = MockMvcWebClientBuilder.mockMvcSetup(mockMvc).build();
    }

    @Test
    @DisplayName("Fetch Catalog Page")
    void displayCatalog() throws Exception {
        BDDMockito.given(catalogService.getItems()).willReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/catalog"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        final var page = webClient.getPage("http://localhost");
        page.initialize();
        final var contentAsString = page.getWebResponse().getContentAsString();
        log.info(contentAsString);
        final var items_catalog = contentAsString.contains("Items Catalog");
//        assertThat(items_catalog).isTrue();
        final var loadTime = page.getWebResponse().getLoadTime();
        log.info("Load Time is: {}",loadTime);
    }
}