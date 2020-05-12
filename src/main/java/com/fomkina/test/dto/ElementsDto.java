package com.fomkina.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * User: Anastasia Fomkina
 * Time: 2020-05-12 14:30
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElementsDto {
    @JsonProperty("box")
    Long boxId;
    String color;

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementsDto that = (ElementsDto) o;
        return Objects.equals(boxId, that.boxId) &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boxId, color);
    }

    @Override
    public String toString() {
        return "ElementsDto{" +
                "box=" + boxId +
                ", color='" + color + '\'' +
                '}';
    }
}
