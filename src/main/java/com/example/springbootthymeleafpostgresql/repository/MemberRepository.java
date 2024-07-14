package com.example.springbootthymeleafpostgresql.repository;

import com.example.springbootthymeleafpostgresql.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    List<Member> findByNameContainingIgnoreCase(String name);
}
