package com.studymongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.halt;

public class HelloWorldSparkFreeMarkerStyle {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreeMarkerStyle.class, "/");
        get("/", (req, res)-> {
            StringWriter stringWriter = new StringWriter();
            try {
                Template helloTemplate = configuration.getTemplate("hello.ftl");

                Map<String, Object> helloMap = new HashMap<>();
                helloMap.put("name","Freemarker");

                helloTemplate.process(helloMap, stringWriter);

            } catch (Exception e) {
                halt(500);
                e.printStackTrace();
            }
            return stringWriter;
        });
    }
}
