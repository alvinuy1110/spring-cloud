package com.myproject.cloud.controller;

import com.myproject.cloud.domain.Features;
import com.myproject.cloud.domain.User;
import com.myproject.cloud.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by user on 12/06/17.
 */

@RefreshScope
@RestController
public class MessageBundleController {

    @Autowired
    private MessageSource messageSource;

    private static final String UI_DISPLAY_TEXT = "ui.displayText";
    private static final String UI_LABEL = "ui.label";
    private static final String UI_SUBMIT_BUTTON_TEXT = "ui.submit.button.text";
    private static final String UI_EDIT_BUTTON_TEXT = "ui.edit.button.text";


    @RequestMapping(
            value = "/messages",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,String>> user(@RequestParam(value = "lang", required = false) String lang) {
        Locale locale = Locale.CANADA;

        if ("fr".equals(lang)) {
            locale = Locale.CANADA_FRENCH;
        }

        Map<String,String> map = new HashMap<>();
        List<String> messageCodes = getUIMessageCodes();

        for (String messageCode : messageCodes) {
          String message=  messageSource.getMessage(messageCode, null,
                    locale);

            map.put(messageCode,message);

        }

        return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);


    }


    private List<String> getUIMessageCodes() {
        List list = new ArrayList();

        list.add(UI_DISPLAY_TEXT);
        list.add(UI_LABEL);
        list.add(UI_SUBMIT_BUTTON_TEXT);
        list.add(UI_EDIT_BUTTON_TEXT);
        return list;
    }
}
