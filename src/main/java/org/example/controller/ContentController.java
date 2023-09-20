package org.example.controller;

import org.example.entity.Content;
import org.example.service.sql.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping("/insert")
    public Content insertContent(@RequestBody Content content) {
        return contentService.saveContent(content);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteContentById(@PathVariable Long id) {
        contentService.deleteContent(id);
    }

    @GetMapping("/contents")
    public List<Content> getAllContents() {
        List<Content> contents = contentService.getAllContents();
        return contents;
    }
}