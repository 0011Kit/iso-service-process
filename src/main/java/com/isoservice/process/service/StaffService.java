package com.isoservice.process.service;

import com.isoservice.process.entity.StaffEntity;
import com.isoservice.process.exception.ResourceNotFoundException;
import com.isoservice.process.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository){
        this.staffRepository = staffRepository;
    }

    public StaffEntity getStaffInfoById(String id){
        return staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff Info Not found with id : " + id ));
    }

    public List<StaffEntity> getStuffInfoByName (String name){
        List<StaffEntity> result = staffRepository.findAllByStaffName(name);
        if(result.isEmpty()){
            throw new ResourceNotFoundException("Staff Info Not found with name : " + name);
        }
        return result;
    }
}
