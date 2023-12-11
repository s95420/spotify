package com.bialy.spotifydatarestapi.repository;

import com.bialy.spotifydatarestapi.model.Top200;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Top200Repository extends MongoRepository<Top200, String> {
    Page<Top200> findAllByRegion(String region, Pageable page);
}
