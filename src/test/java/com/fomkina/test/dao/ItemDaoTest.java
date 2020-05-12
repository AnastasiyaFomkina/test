package com.fomkina.test.dao;

import com.fomkina.test.JpaTest;
import com.fomkina.test.domain.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * User: Anastasia Fomkina
 * Time: 2020-05-12 12:23
 */
@ExtendWith(SpringExtension.class)
@JpaTest
@Transactional
public class ItemDaoTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void expectCreate() {
        Item item = new Item();
        item.setId(1L);
        item.setContainedIn(9L);
        item.setColor("black");

        testEntityManager.persist(item);

        assertThat(item.getId(), notNullValue());
    }

    @Test
    public void expectCreateWithoutColor() {
        Item item = new Item();
        item.setId(1L);
        item.setContainedIn(10L);

        testEntityManager.persist(item);

        assertThat(item.getId(), notNullValue());
    }
}