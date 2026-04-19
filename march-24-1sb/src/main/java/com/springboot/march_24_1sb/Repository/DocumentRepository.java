package com.springboot.march_24_1sb.Repository;

import com.springboot.march_24_1sb.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,Long> {
}
