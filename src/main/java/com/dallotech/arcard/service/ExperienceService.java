package com.dallotech.arcard.service;

import com.dallotech.arcard.model.db.Experience;
import com.dallotech.arcard.model.db.User;
import com.dallotech.arcard.model.dto.ExperienceDto;
import com.dallotech.arcard.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("experienceService")
@Transactional
public class ExperienceService {

    @Autowired
    ExperienceRepository experienceRepository;

    public List<Experience> addExperience(List<ExperienceDto> experienceDtoList, User user){
        experienceRepository.deleteByUser_Id(user.getId());
        List<Experience> experienceList=new ArrayList<>();
        for(int i=0;i<experienceDtoList.size();i++){
            experienceList.add(experienceRepository.save(Experience.getExperienceFromDto(experienceDtoList.get(i),user)));
        }
        return experienceList;
    }


}
