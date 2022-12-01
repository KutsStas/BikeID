package com.kytc.bikeID.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AliveController.
 */
@RestController
@RequestMapping("/")
public class AliveController {
    /**
     * GetMapping.
     */
    @GetMapping
    public static Boolean isAlive() {
        return true;
    }
}
