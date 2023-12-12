package com.bialy.spotifydatarestapi.repository;

import com.bialy.spotifydatarestapi.model.Viral50;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Viral50Respository extends MongoRepository<Viral50, String> {
    Page<Viral50> findAllByRegion(String region, Pageable page);
}
