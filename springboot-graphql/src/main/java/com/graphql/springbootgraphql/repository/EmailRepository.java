package com.graphql.springbootgraphql.repository;

import com.graphql.springbootgraphql.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
