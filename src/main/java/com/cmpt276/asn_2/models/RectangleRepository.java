package com.cmpt276.asn_2.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RectangleRepository extends JpaRepository<Rectangle, Integer> {
}
