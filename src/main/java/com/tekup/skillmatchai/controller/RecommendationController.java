package com.tekup.skillmatchai.controller;

import com.tekup.skillmatchai.dto.response.RecommendationResponse;
import com.tekup.skillmatchai.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/users/{userId}/missing-skills")
    public ResponseEntity<RecommendationResponse> getMissingSkillsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(recommendationService.getMissingSkillsForUser(userId));
    }
}
