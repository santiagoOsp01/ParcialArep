package edu.eci.co.calculator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class calculator {

    public static Map<String, service> serivices = new HashMap<>();

    public static void main(String[] args) throws IOException, URISyntaxException {
        get("/abs", sqr -> Math.abs(Double.parseDouble(sqr)) + "");
        get("/cos", sqr -> Math.cos(Double.parseDouble(sqr)) + "");
        get("/sin", sqr -> Math.sin(Double.parseDouble(sqr)) + "");
        get("/sqrt", sqr -> Math.sqrt(Double.parseDouble(sqr)) + "");
        get("/abs", sqr -> ack(sqr) + "");
        calculatorServer.start();
    }

    private static String ack(String arreglo) {
        return "ok";
    }

    public static service Search(String accion){
        if (serivices.containsKey(accion)){
            return serivices.get(accion);
        }
        return null;
    }

    public static void get(String name, service servicio){
        serivices.put(name,servicio);
    }




}
