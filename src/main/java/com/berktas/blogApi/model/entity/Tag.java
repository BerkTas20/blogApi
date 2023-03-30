package com.berktas.blogApi.model.entity;

import com.berktas.blogApi.core.entity.AbstractTimestampEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Tag extends AbstractTimestampEntity {
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"))
    private List<Post> posts;

    public Tag(String name) {
        super();
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts == null ? null : new ArrayList<>(posts);
    }

    public void setPosts(List<Post> posts) {
        if (posts == null) {
            this.posts = null;
        } else {
            this.posts = Collections.unmodifiableList(posts);
        }
    }
}
