package com.berktas.blogApi.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SavePostRequest {
    private String description;
    private String title;
    private Long userId;
    private Long categoryId;

//    private List<String> tags;

//    public List<String> getTags() {
//
//        return tags == null ? Collections.emptyList() : new ArrayList<>(tags);
//    }
//
//    public void setTags(List<String> tags) {
//
//        if (tags == null) {
//            this.tags = null;
//        } else {
//            this.tags = Collections.unmodifiableList(tags);
//        }
//    }
}
