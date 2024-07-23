package com.devteria.identity_.repository;
import com.devteria.identity_.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}