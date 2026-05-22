package com.tekup.skillmatchai.service;

import com.tekup.skillmatchai.dto.request.UserRequest;
import com.tekup.skillmatchai.dto.response.UserResponse;
import com.tekup.skillmatchai.entity.Skill;
import com.tekup.skillmatchai.entity.User;
import com.tekup.skillmatchai.exception.BadRequestException;
import com.tekup.skillmatchai.exception.ResourceNotFoundException;
import com.tekup.skillmatchai.mapper.UserMapper;
import com.tekup.skillmatchai.repository.SkillRepository;
import com.tekup.skillmatchai.repository.UserRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new BadRequestException("User already exists with email: " + userRequest.getEmail());
        }

        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .role(userRequest.getRole())
                .skills(getSkillsByIds(userRequest.getSkillIds()))
                .build();

        return UserMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        return UserMapper.toUserResponse(getUserEntityById(id));
    }

    public void deleteUser(Long id) {
        User user = getUserEntityById(id);
        userRepository.delete(user);
    }

    private User getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
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
