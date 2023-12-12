package com.bialy.spotifydatarestapi.controller;

import com.bialy.spotifydatarestapi.model.Top200;
import com.bialy.spotifydatarestapi.service.Top200Service;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/top200")
@AllArgsConstructor
public class Top200Controller {
    private final Top200Service top200Service;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Top200> fetchAllTop200() {
        return top200Service.getAllTop200();
    }

    @GetMapping("/{region}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Page<Top200> fetchAllTop200ByRegion(@PathVariable String region,
                                               @RequestParam(required = false) Integer page,
                                               @RequestParam(required = false) Sort.Direction sortDate,
                                               @RequestParam(required = false) Sort.Direction sortRank) {
        int pageNumber = page != null && page > 0 ? page : 1;
        if (sortDate != null && sortRank != null) {
            return top200Service.getAllTop200ByRegionSortByDateAndRank(region, pageNumber - 1, sortDate, sortRank);
        } else if (sortDate != null && sortRank == null) {
            return top200Service.getAllTop200ByRegionSortByDate(region, pageNumber - 1, sortDate);
        } else if (sortDate == null && sortRank != null) {
            return top200Service.getAllTop200ByRegionSortByRank(region, pageNumber - 1, sortRank);
        } else
            return top200Service.getAllTop200ByRegion(region, pageNumber - 1);
    }
}
