package com.mcomp.myrage.controller.payload;

import com.mcomp.myrage.model.enums.DocumentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentListItem {
    private Integer id;
    private String name;
    private String storagePath;
    private Integer size;
    private DocumentStatus status;
}
