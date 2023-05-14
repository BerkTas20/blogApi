package com.berktas.blogApi.controller;

import com.berktas.blogApi.controller.requests.SaveAndUpdateTagRequest;
import com.berktas.blogApi.dto.TagDto;
import com.berktas.blogApi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
@io.swagger.v3.oas.annotations.tags.Tag(name = "Tag")
public class TagController {
    private final TagService tagService;
    @PostMapping
    public ResponseEntity<TagDto> save(@RequestBody SaveAndUpdateTagRequest tag) {
        return ResponseEntity.ok(tagService.save(tag));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDto> update(@PathVariable Long id, @RequestBody SaveAndUpdateTagRequest saveAndUpdateTagRequest) {
        return ResponseEntity.ok(tagService.updateTag(id, saveAndUpdateTagRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.getTag(id));
    }

}
