package com.tpe.repository.business;

import com.tpe.entity.business.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>
{

    boolean existsByName(String name);
}
