package com.cakefactory.controllers;

import com.cakefactory.service.CatalogService;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;
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

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(CatalogController.class)
@Slf4j
class CatalogServiceControllerTest {

//    private static final Logger log = LoggerFactory.getLogger(CatalogServiceControllerTest.class);
    public static final String LOCALHOST_8080 = "http://localhost:8080";

    @Autowired
    private MockMvc mockMvc;

    WebClient webClient;

    @MockBean
    CatalogService catalogService;

    @BeforeEach
    void init(){
        webClient = MockMvcWebClientBuilder.mockMvcSetup(mockMvc).build();
    }

//    @Test
    @DisplayName("Catalog page contains the button to add to cart")
    public void checkAddToCartButton() throws IOException {
        final HtmlPage page = webClient.getPage(LOCALHOST_8080);
        final HtmlForm htmlForm = (HtmlForm) page.getElementsByName("add-to-cart-form").get(0);
        final String actionAttribute = htmlForm.getActionAttribute();
        assertThat(actionAttribute).contains("basket");
    }

//    @Test
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