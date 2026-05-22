package com.tekup.skillmatchai.service;

import com.tekup.skillmatchai.dto.response.RecommendationResponse;
import com.tekup.skillmatchai.dto.response.SkillResponse;
import com.tekup.skillmatchai.entity.Skill;
import com.tekup.skillmatchai.entity.User;
import com.tekup.skillmatchai.exception.ResourceNotFoundException;
import com.tekup.skillmatchai.mapper.UserMapper;
import com.tekup.skillmatchai.repository.SkillRepository;
import com.tekup.skillmatchai.repository.UserRepository;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    @Transactional(readOnly = true)
    public RecommendationResponse getMissingSkillsForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Set<Skill> userSkills = user.getSkills() == null ? Collections.emptySet() : user.getSkills();
        Set<Long> userSkillIds = userSkills.stream()
                .map(Skill::getId)
                .collect(Collectors.toSet());

        Set<SkillResponse> currentSkills = userSkills.stream()
                .map(UserMapper::toSkillResponse)
                .collect(Collectors.toSet());

        Set<SkillResponse> recommendedSkills = skillRepository.findAll()
                .stream()
                .filter(skill -> !userSkillIds.contains(skill.getId()))
                .map(UserMapper::toSkillResponse)
                .collect(Collectors.toSet());

        String fullName = user.getFirstName() + " " + user.getLastName();
        String message = "Recommended " + recommendedSkills.size() + " missing skills for user.";

        return new RecommendationResponse(
                user.getId(),
                fullName,
                currentSkills,
                recommendedSkills,
                message
        );
    }
}
