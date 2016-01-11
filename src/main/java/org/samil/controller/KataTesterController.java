package org.samil.controller;

import org.samil.junit.JUnitThread;
import org.samil.Katas;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kata")
public class KataTesterController {

    @RequestMapping(value = "/test/{kata}", method = RequestMethod.POST)
    public String greeting(@PathVariable("kata") final Katas kata, @RequestBody final String code) {

        JUnitThread runJUnitTests = new JUnitThread(kata, code);
        Thread t = new Thread(runJUnitTests);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return runJUnitTests.getMessage();
    }

}