package com.shut.savify.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Table(name = "texts")
@Entity
public class Text {

    @Id
    private String key;

    @NotNull
    private String text;
}
