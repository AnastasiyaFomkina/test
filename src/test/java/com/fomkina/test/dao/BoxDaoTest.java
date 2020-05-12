package com.fomkina.test.dao;

import com.fomkina.test.JpaTest;
import com.fomkina.test.domain.Box;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;

/**
 * User: Anastasia Fomkina
 * Time: 2020-05-12 12:14
 */
@ExtendWith(SpringExtension.class)
@JpaTest
@Transactional
class BoxDaoTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;

    private BoxDao boxDao;

    @BeforeEach
    public void setUp() {
        boxDao = new BoxDao(entityManager);
    }


    @Test
    public void expectCreate() {
        Box box = new Box();
        box.setId(1L);
        box.setContainedIn(null);

        testEntityManager.persist(box);

        assertThat(box.getId(), notNullValue());
    }

    @Test
    public void expectGetAll() {
        Box box = new Box();
        box.setId(1L);
        box.setContainedIn(null);

        boxDao.create(box);

        assertThat(boxDao.getAll(), notNullValue());
    }

    @Test
    public void expectGetByContainedIn() {
        Box box = new Box();
        box.setId(1L);
        box.setContainedIn(156L);

        boxDao.create(box);
        Collection<Box> boxesByContainedIn = boxDao.getBoxesByContainedIn(156L);

        assertThat(boxesByContainedIn, hasItem(box));
    }
}