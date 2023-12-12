package com.bialy.spotifydatarestapi.service;

import com.bialy.spotifydatarestapi.model.Viral50;
import com.bialy.spotifydatarestapi.repository.Viral50Respository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class Viral50Service {
    private static final int PAGE_SIZE = 50;
    private final Viral50Respository viral50Respository;
    public List<Viral50> getAllViral50() {
        return viral50Respository.findAll();
    }

    public Page<Viral50> getAllViral50ByRegionSortByDateAndRank(String region, int page, Sort.Direction sortDate, Sort.Direction sortRank) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(sortDate, "date"));
        orders.add(new Sort.Order(sortRank, "rank"));
        return viral50Respository.findAllByRegion(region, PageRequest.of(page, PAGE_SIZE, Sort.by(orders)));
    }

    public Page<Viral50> getAllViral50ByRegionSortByDate(String region, int page, Sort.Direction sortDate) {
        return viral50Respository.findAllByRegion(region, PageRequest.of(page, PAGE_SIZE, Sort.by(sortDate, "date")));
    }

    public Page<Viral50> getAllViral50ByRegionSortByRank(String region, int page, Sort.Direction sortRank) {
        return viral50Respository.findAllByRegion(region, PageRequest.of(page, PAGE_SIZE, Sort.by(sortRank, "rank")));
    }

    public Page<Viral50> getAllViral50ByRegion(String region, int page) {
        return viral50Respository.findAllByRegion(region, PageRequest.of(page, PAGE_SIZE));
    }

}
