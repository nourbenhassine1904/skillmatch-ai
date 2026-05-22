package com.tekup.skillmatchai.controller;

import com.tekup.skillmatchai.dto.request.InternshipOfferRequest;
import com.tekup.skillmatchai.entity.InternshipOffer;
import com.tekup.skillmatchai.service.InternshipOfferService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/offers")
@RequiredArgsConstructor
public class InternshipOfferController {

    private final InternshipOfferService internshipOfferService;

    @PostMapping
    public ResponseEntity<InternshipOffer> createOffer(@Valid @RequestBody InternshipOfferRequest offerRequest) {
        return ResponseEntity.ok(internshipOfferService.createOffer(offerRequest));
    }

    @GetMapping
    public ResponseEntity<List<InternshipOffer>> getAllOffers() {
        return ResponseEntity.ok(internshipOfferService.getAllOffers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternshipOffer> getOfferById(@PathVariable Long id) {
        return ResponseEntity.ok(internshipOfferService.getOfferById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InternshipOffer> updateOffer(
            @PathVariable Long id,
            @Valid @RequestBody InternshipOfferRequest offerRequest
    ) {
        return ResponseEntity.ok(internshipOfferService.updateOffer(id, offerRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        internshipOfferService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }
}
