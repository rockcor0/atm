package com.acmesoft.atm.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class IndexController {

    public final String index(@RequestParam(name = "accountId", required = true) String accountId,
                              Model model) {
        model.addAttribute("accountId", accountId);

        return "index";
    }
}
