package com.mcomp.myrage.controller.payload;

import com.mcomp.myrage.model.enums.DocumentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentListItem {
    private UUID id;
    private String name;
    private String storagePath;
    private Integer size;
    private Instant createdAt;
    private DocumentStatus status;
}
