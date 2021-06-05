package com.cakefactory.service.impl;

import com.cakefactory.model.entity.Item;
import com.cakefactory.repository.ItemsRepo;
import com.cakefactory.service.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Slf4j
class JpaCatalogServiceTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    ItemsRepo itemsRepo;

    CatalogService catalogService;

    @BeforeEach
    public void init(){
        catalogService = new JpaCatalogService(itemsRepo);
    }

    @Test
    @DisplayName("Fetch data via Catalog Service")
    public void serviceFetchALl(){
        final var item = new Item("testSku", "TestTitle", BigDecimal.ZERO);
        testEntityManager.persistAndFlush(item);

        final var items = catalogService.getItems();
        System.out.println(items.size());
        assertThat(items).isNotEmpty().hasSize(1).anyMatch(i->i.getTitle().equalsIgnoreCase("TestTitle"));
    }

    @Test
    @DisplayName("Fetch Data from test h2 db.")
    public void testFetchAll(){
        final var items = itemsRepo.findAll();
        assertThat(items).isNotNull().isEmpty();
    }
}