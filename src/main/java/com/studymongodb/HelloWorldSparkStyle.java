package com.studymongodb;

import spark.Request;

import static spark.Spark.get;

public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        get("/", (req, res)-> "Hello World");

        get("/test/:number", (req, res)-> {
            return "This is a test: " + req.params(":number");
        });
    }
}
