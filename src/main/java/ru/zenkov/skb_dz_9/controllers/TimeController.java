package ru.zenkov.skb_dz_9.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class TimeController {

    @GetMapping("/get-server-time")
    public ResponseEntity<String> getServerTime() {
        return new ResponseEntity<>("Server time is - " +
                new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime()),
                HttpStatus.OK);
    }
}
