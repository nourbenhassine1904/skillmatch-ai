package com.tekup.skillmatchai.service;

import com.tekup.skillmatchai.dto.request.InternshipOfferRequest;
import com.tekup.skillmatchai.entity.Company;
import com.tekup.skillmatchai.entity.InternshipOffer;
import com.tekup.skillmatchai.entity.Skill;
import com.tekup.skillmatchai.exception.BadRequestException;
import com.tekup.skillmatchai.exception.ResourceNotFoundException;
import com.tekup.skillmatchai.repository.CompanyRepository;
import com.tekup.skillmatchai.repository.InternshipOfferRepository;
import com.tekup.skillmatchai.repository.SkillRepository;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InternshipOfferService {

    private final InternshipOfferRepository internshipOfferRepository;
    private final CompanyRepository companyRepository;
    private final SkillRepository skillRepository;

    public InternshipOffer createOffer(InternshipOfferRequest offerRequest) {
        InternshipOffer offer = InternshipOffer.builder()
                .title(offerRequest.getTitle())
                .description(offerRequest.getDescription())
                .domain(offerRequest.getDomain())
                .location(offerRequest.getLocation())
                .duration(offerRequest.getDuration())
                .company(getCompanyById(offerRequest.getCompanyId()))
                .requiredSkills(getSkillsByIds(offerRequest.getRequiredSkillIds()))
                .createdAt(LocalDateTime.now())
                .build();

        return internshipOfferRepository.save(offer);
    }

    public List<InternshipOffer> getAllOffers() {
        return internshipOfferRepository.findAll();
    }

    public InternshipOffer getOfferById(Long id) {
        return internshipOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship offer not found with id: " + id));
    }

    public InternshipOffer updateOffer(Long id, InternshipOfferRequest offerRequest) {
        InternshipOffer existingOffer = getOfferById(id);

        existingOffer.setTitle(offerRequest.getTitle());
        existingOffer.setDescription(offerRequest.getDescription());
        existingOffer.setDomain(offerRequest.getDomain());
        existingOffer.setLocation(offerRequest.getLocation());
        existingOffer.setDuration(offerRequest.getDuration());
        existingOffer.setCompany(getCompanyById(offerRequest.getCompanyId()));
        existingOffer.setRequiredSkills(getSkillsByIds(offerRequest.getRequiredSkillIds()));

        return internshipOfferRepository.save(existingOffer);
    }

    public void deleteOffer(Long id) {
        InternshipOffer offer = getOfferById(id);
        internshipOfferRepository.delete(offer);
    }

    private Company getCompanyById(Long companyId) {
        if (companyId == null) {
            throw new BadRequestException("Company id is required");
        }

        return companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + companyId));
    }

    private Set<Skill> getSkillsByIds(Set<Long> skillIds) {
        if (skillIds == null || skillIds.isEmpty()) {
            return new HashSet<>();
        }

        List<Skill> skills = skillRepository.findAllById(skillIds);
        if (skills.size() != skillIds.size()) {
            throw new ResourceNotFoundException("One or more skills were not found");
        }

        return new HashSet<>(skills);
    }
}
