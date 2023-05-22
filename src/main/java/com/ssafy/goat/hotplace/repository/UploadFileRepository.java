package com.ssafy.goat.hotplace.repository;

import com.ssafy.goat.common.domain.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
}
