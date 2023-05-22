package com.ssafy.goat.common.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UploadFile {

    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long fileId;
    @Column(nullable = false)
    private String uploadFileName;
    @Column(nullable = false)
    private String storeFileName;

    @Builder
    public UploadFile(Long fileId, String uploadFileName, String storeFileName) {
        this.fileId = fileId;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
