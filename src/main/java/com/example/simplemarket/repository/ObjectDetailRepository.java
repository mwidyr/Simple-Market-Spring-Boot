package com.example.simplemarket.repository;

import com.example.simplemarket.model.ObjectDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectDetailRepository extends JpaRepository<ObjectDetail, Integer> {
}
