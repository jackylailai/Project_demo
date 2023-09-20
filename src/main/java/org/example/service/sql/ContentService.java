package org.example.service.sql;

import org.example.entity.Content;
import org.example.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {
    private final ContentRepository contentRepository;

    @Autowired
    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }

    public Optional<Content> getContentById(Long id) {
        return contentRepository.findById(id);
    }


    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }

    public Content saveContent(Content content) {
        return contentRepository.save(content);
    }
}

