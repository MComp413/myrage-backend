package com.mcomp.myrage.controller.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentCreationData {
    private String name;
    // TODO: remove and use owner id from auth
    private String ownerEmail;
    private MultipartFile file;
}
