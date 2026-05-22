package com.tekup.skillmatchai.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Entity
@Table(name = "internship_offers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternshipOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Offer title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Offer description is required")
    @Column(nullable = false)
    private String description;

    @NotBlank(message = "Offer domain is required")
    @Column(nullable = false)
    private String domain;

    private String location;

    private String duration;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
    @JoinTable(
            name = "internship_offer_required_skills",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @Builder.Default
    private Set<Skill> requiredSkills = new HashSet<>();

    private LocalDateTime createdAt;
}
