package com.employeework.config;

//import org.modelmapper.ModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//configuration should run before the project run they are run automatically
//   what information present inside the configuration classes that information   given to spring boot
@Configuration
public class ConfigClass {

    @Bean
    public ModelMapper getModelMapper(){
        return  new ModelMapper();
    }
}
