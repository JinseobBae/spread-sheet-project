package com.js.spreadsheet.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;

@Configuration
class MessageConfig {

    private final MessageSource messageSource;

    MessageConfig(MessageSource messageSource){
        this.messageSource = messageSource;
    }


    @Bean("messageSourceAccessor")
    public MessageSourceAccessor messageSourceAccessor(){
        return new MessageSourceAccessor(messageSource);
    }


}
