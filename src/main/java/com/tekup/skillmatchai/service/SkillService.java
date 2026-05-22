package com.tekup.skillmatchai.service;

import com.tekup.skillmatchai.entity.Skill;
import com.tekup.skillmatchai.exception.BadRequestException;
import com.tekup.skillmatchai.exception.ResourceNotFoundException;
import com.tekup.skillmatchai.repository.SkillRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public Skill createSkill(Skill skill) {
        if (skillRepository.existsByNameIgnoreCase(skill.getName())) {
            throw new BadRequestException("Skill already exists");
        }

        return skillRepository.save(skill);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
    }

    public Skill updateSkill(Long id, Skill updatedSkill) {
        Skill existingSkill = getSkillById(id);

        if (!existingSkill.getName().equalsIgnoreCase(updatedSkill.getName())
                && skillRepository.existsByNameIgnoreCase(updatedSkill.getName())) {
            throw new BadRequestException("Skill already exists");
        }

        existingSkill.setName(updatedSkill.getName());
        existingSkill.setCategory(updatedSkill.getCategory());

        return skillRepository.save(existingSkill);
    }

    public void deleteSkill(Long id) {
        Skill skill = getSkillById(id);
        skillRepository.delete(skill);
    }
}
