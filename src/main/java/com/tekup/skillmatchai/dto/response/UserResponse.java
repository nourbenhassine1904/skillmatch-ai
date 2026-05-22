package com.tekup.skillmatchai.dto.response;

import com.tekup.skillmatchai.enums.Role;

import java.util.Set;

public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Set<SkillResponse> skills;

    public UserResponse() {
    }

    public UserResponse(Long id, String firstName, String lastName, String email, Role role, Set<SkillResponse> skills) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.skills = skills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<SkillResponse> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillResponse> skills) {
        this.skills = skills;
    }
}
