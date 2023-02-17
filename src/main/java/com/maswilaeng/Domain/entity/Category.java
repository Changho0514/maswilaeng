package com.maswilaeng.Domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Post> postList = new ArrayList<>();

    @Builder
    public Category(Long categoryId, String categoryName, List<Post> postList) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.postList = postList;
    }
}
