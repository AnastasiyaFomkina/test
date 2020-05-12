package com.fomkina.test.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.Objects;

/**
 * User: Anastasia Fomkina
 * Time: 2020-05-12 11:53
 */
@Table(name = "item")
@Entity
public class Item {
    @Id
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "contained_in")
    private Long containedIn;

    @Column(name = "color")
    private String color;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContainedIn() {
        return containedIn;
    }

    public void setContainedIn(Long containedIn) {
        this.containedIn = containedIn;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(containedIn, item.containedIn) &&
                Objects.equals(color, item.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, containedIn, color);
    }
}
