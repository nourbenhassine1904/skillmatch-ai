package com.tekup.skillmatchai.dto.response;

import java.util.Set;

public class RecommendationResponse {

    private Long userId;
    private String fullName;
    private Set<SkillResponse> currentSkills;
    private Set<SkillResponse> recommendedSkills;
    private String message;

    public RecommendationResponse() {
    }

    public RecommendationResponse(Long userId, String fullName, Set<SkillResponse> currentSkills,
                                  Set<SkillResponse> recommendedSkills, String message) {
        this.userId = userId;
        this.fullName = fullName;
        this.currentSkills = currentSkills;
        this.recommendedSkills = recommendedSkills;
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<SkillResponse> getCurrentSkills() {
        return currentSkills;
    }

    public void setCurrentSkills(Set<SkillResponse> currentSkills) {
        this.currentSkills = currentSkills;
    }

    public Set<SkillResponse> getRecommendedSkills() {
        return recommendedSkills;
    }

    public void setRecommendedSkills(Set<SkillResponse> recommendedSkills) {
        this.recommendedSkills = recommendedSkills;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
