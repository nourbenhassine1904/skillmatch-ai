package com.tekup.skillmatchai.mapper;

import com.tekup.skillmatchai.dto.response.SkillResponse;
import com.tekup.skillmatchai.dto.response.UserResponse;
import com.tekup.skillmatchai.entity.Skill;
import com.tekup.skillmatchai.entity.User;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    private UserMapper() {
    }

    public static UserResponse toUserResponse(User user) {
        if (user == null) {
            return null;
        }

        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                toSkillResponses(user.getSkills())
        );
    }

    public static SkillResponse toSkillResponse(Skill skill) {
        if (skill == null) {
            return null;
        }

        return new SkillResponse(
                skill.getId(),
                skill.getName(),
                skill.getCategory()
        );
    }

    private static Set<SkillResponse> toSkillResponses(Set<Skill> skills) {
        if (skills == null) {
            return Collections.emptySet();
        }

        return skills.stream()
                .map(UserMapper::toSkillResponse)
                .collect(Collectors.toSet());
    }
}
