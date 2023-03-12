package com.example.demo.repository;

import com.example.demo.model.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersRepo extends JpaRepository<Members , Long> {
}
