package com.mcomp.myrage.converter;

import com.mcomp.myrage.controller.payload.DocumentCreationData;
import com.mcomp.myrage.controller.payload.DocumentListItem;
import com.mcomp.myrage.model.Document;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class DocumentConverter {

    public DocumentListItem entityToListItem(@NotNull Document document) {
        return DocumentListItem.builder()
                .id(document.getId())
                .name(document.getName())
                .size(document.getSize())
                .status(document.getStatus())
                .storagePath(document.getStoragePath())
                .build();
    }

    public Document dataToEntity(@NotNull DocumentCreationData documentCreationData) {
        return Document.builder()
                .name(documentCreationData.getName())
                .size(documentCreationData.getSize())
                .build();
    }
}
