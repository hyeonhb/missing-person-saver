package com.example.controller;

import com.example.service.MissingPersonService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/missing-persons")
public class MissingPersonController {

    @Autowired
    private MissingPersonService missingPersonService;

    @GetMapping("/persons")
    public String getTest(@RequestParam("test") String test) {
        return test;
    }

    @PostMapping("/list")
    public String getMissingPersonList(/*
            @RequestParam(required = false) String sexdstnDscd,
            @RequestParam(required = false) String nm,
            @RequestParam(required = false) String detailDate1,
            @RequestParam(required = false) String detailDate2,
            @RequestParam(required = false) String age1,
            @RequestParam(required = false) String age2,*/
            @RequestParam(required = false) String occrAdres/*,
            @RequestParam(required = false) String xmlUseYN*/) {

        Map<String, String> queryParams = new HashMap<>();/*
        queryParams.put("sexdstnDscd", sexdstnDscd);
        queryParams.put("nm", nm);
        queryParams.put("detailDate1", detailDate1);
        queryParams.put("detailDate2", detailDate2);
        queryParams.put("age1", age1);
        queryParams.put("age2", age2);*/
        queryParams.put("occrAdres", occrAdres);
//        queryParams.put("xmlUseYN", xmlUseYN);

        return missingPersonService.getMissingPersonList(queryParams);
    }

    @PostMapping("/find")
    public String getMissingPersonInfo(
            @RequestParam(required = false) String nowPage,
            @RequestParam(required = false) String sexdstnDscd,
            @RequestParam(required = false) String nm,
            @RequestParam(required = false) String detailDate1,
            @RequestParam(required = false) String detailDate2,
            @RequestParam(required = false) String age1,
            @RequestParam(required = false) String age2,
            @RequestParam(required = false) String etcSpfeatr,
            @RequestParam(required = false) String occrAdres,
            @RequestParam(required = false) String[] writngTrgetDscds) {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("nowPage", nowPage);
        queryParams.put("sexdstnDscd", sexdstnDscd);
        queryParams.put("nm", nm);
        queryParams.put("detailDate1", detailDate1);
        queryParams.put("detailDate2", detailDate2);
        queryParams.put("age1", age1);
        queryParams.put("age2", age2);
        queryParams.put("etcSpfeatr", etcSpfeatr);
        queryParams.put("occrAdres", occrAdres);

        if (writngTrgetDscds != null) {
            for (String writngTrgetDscd : writngTrgetDscds) {
                queryParams.put("writngTrgetDscds", writngTrgetDscd);
            }
        }

        return missingPersonService.getMissingPersonInfo(queryParams);
    }
}
