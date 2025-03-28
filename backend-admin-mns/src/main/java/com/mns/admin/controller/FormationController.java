package com.mns.admin.controller;

import com.mns.admin.model.Formation;
import com.mns.admin.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/formations")
@CrossOrigin(origins = "http://admin-mns")
public class FormationController {

    @Autowired
    private FormationRepository formationRepository;

    @GetMapping("/formations")
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }
}