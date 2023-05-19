package com.ssafy.goat.attraction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ContentType {

    @Id
    @Column(name = "content_type_id")
    private int id;
    @Column(name = "content_type_name", unique = true, nullable = false, length = 30)
    public String name;

    @Builder
    public ContentType(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
