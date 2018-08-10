package com.studymongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;

public class SparkFormHandling {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

        get("/", (req, res)-> {

            try {
                Template fruitPickerTemplate = configuration.getTemplate("fruitPicker.ftl");

                Map<String, Object> fruitsMap = new HashMap<>();
                fruitsMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));
                StringWriter stringWriter = new StringWriter();
                fruitPickerTemplate.process(fruitsMap, stringWriter);
                return stringWriter;
            } catch (Exception e) {
                halt(500);
                return null;
            }

        });

        post("/favorite_fruit", (req, res)->{
            final String fruit = req.queryParams("fruit");
            if(fruit == null){
                return "Why don't you pick any?";
            }else{
                return "Your favorite fruit is " + fruit;
            }
        });
    }
}
