package com.example.theoenterprise2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.theoenterprise2.entities.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
}
