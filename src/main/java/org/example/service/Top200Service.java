package com.bialy.spotifydatarestapi.service;

import com.bialy.spotifydatarestapi.model.Top200;
import com.bialy.spotifydatarestapi.repository.Top200Repository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class Top200Service {
    private static final int PAGE_SIZE = 50;
    private final Top200Repository top200Repository;

    public List<Top200> getAllTop200() {
        return top200Repository.findAll();
    }

    public Page<Top200> getAllTop200ByRegionSortByDateAndRank(String region, int page, Sort.Direction sortDate, Sort.Direction sortRank) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(sortDate, "date"));
        orders.add(new Sort.Order(sortRank, "rank"));
        return top200Repository.findAllByRegion(region, PageRequest.of(page, PAGE_SIZE, Sort.by(orders)));
    }

    public Page<Top200> getAllTop200ByRegionSortByDate(String region, int page, Sort.Direction sortDate) {
        return top200Repository.findAllByRegion(region, PageRequest.of(page, PAGE_SIZE, Sort.by(sortDate, "date")));
    }

    public Page<Top200> getAllTop200ByRegionSortByRank(String region, int page, Sort.Direction sortRank) {
        return top200Repository.findAllByRegion(region, PageRequest.of(page, PAGE_SIZE, Sort.by(sortRank, "rank")));
    }

    public Page<Top200> getAllTop200ByRegion(String region, int page) {
        return top200Repository.findAllByRegion(region, PageRequest.of(page, PAGE_SIZE));
    }
}
