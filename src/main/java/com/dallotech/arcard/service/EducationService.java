package com.dallotech.arcard.service;

import com.dallotech.arcard.model.db.Education;
import com.dallotech.arcard.model.db.User;
import com.dallotech.arcard.model.dto.EducationDto;
import com.dallotech.arcard.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("educationService")
@Transactional
public class EducationService {

    @Autowired
    EducationRepository educationRepository;

    public List<Education> addEducation(List<EducationDto> educationDtoList, User user){
        educationRepository.deleteByUser_Id(user.getId());
        List<Education> educationList=new ArrayList<>();
        for(int i=0;i<educationDtoList.size();i++){
            educationList.add(educationRepository.save(Education.getEducationFromDto(educationDtoList.get(i),user)));
        }
        return educationList;
    }
}
