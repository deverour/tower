package deverour.tower.controller;

import deverour.tower.service.impl.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/switch")
public class SwitchController {

    @Autowired
    private AdministratorService administratorService;

    @RequestMapping("/on")
    public void turnOn(){

        administratorService.turnOn();

    }

    @RequestMapping("/off")
    public void turnOff(){

        administratorService.turnOff();

    }
}
