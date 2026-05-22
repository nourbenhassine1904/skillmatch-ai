package com.tekup.skillmatchai.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternshipOfferRequest {

    @NotBlank(message = "Offer title is required")
    private String title;

    @NotBlank(message = "Offer description is required")
    private String description;

    @NotBlank(message = "Offer domain is required")
    private String domain;

    private String location;

    private String duration;

    private Long companyId;

    private Set<Long> requiredSkillIds;
}
