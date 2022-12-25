package com.leonardus.kasadalu.services;

import com.leonardus.kasadalu.dtos.FrenchToastDTO;
import com.leonardus.kasadalu.repositories.FrenchToastRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrenchToastService {
    @Autowired
    ModelMapper mapper;
    @Autowired
    FrenchToastRepository toastRepository;

    public List<FrenchToastDTO> findAll(){
        return toastRepository.findAll().stream()
                .map(frenchToast -> mapper.map(frenchToast, FrenchToastDTO.class))
                .toList();
    }
}
