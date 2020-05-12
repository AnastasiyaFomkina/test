package com.fomkina.test;

import com.fomkina.test.parser.XmlParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * User: Anastasia Fomkina
 * Time: 2020-05-12 11:37
 */

@SpringBootApplication
public class Application {
    protected Application() {
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Application.class, args);
        XmlParser xmlParser = configurableApplicationContext.getBean(XmlParser.class);
        if (args.length == 1) {
            xmlParser.parseXml(args[0]);
        } else
            throw new IllegalArgumentException("Set the file path as the parameter");
    }
}
