package com.springboot.march_24_1sb.Repository;

import com.springboot.march_24_1sb.enums.JobTitle;
import com.springboot.march_24_1sb.model.Executive;
import org.hibernate.annotations.DialectOverride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExecutiveRepository extends JpaRepository<Executive,Long> {

    @Query("""
            select e from Executive e
            where e.jobTitle  = ?1
            """)
    List<Executive> FindByTitle(JobTitle title);


    @Query("""
            select e from Executive e
            where (?1 IS NULL OR e.jobTitle = ?1)
            """)
    List<Executive> getExecutiveByFilter(JobTitle jobTitle1);
}
