package com.betuiasi.server.controller;

import com.betuiasi.server.persistence.model.AreaModel;
import com.betuiasi.server.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/areas")
public class AreaController {

    private final AreaService areaService;

    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping("")
    public ResponseEntity<?> findArea(@RequestParam Optional<String> nume, @RequestParam Optional<String> code){
        if(nume.isPresent()){
            Optional<AreaModel> foundArea = areaService.findAreaByName(nume.get());
            return foundArea.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else if(code.isPresent()){
            Optional<AreaModel> foundArea = areaService.findAreaByCode(code.get());
            return foundArea.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        List<AreaModel> foundAreas = areaService.findAreas();
        return foundAreas.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(foundAreas, HttpStatus.OK);
    }

}
