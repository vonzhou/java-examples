package com.vonzhou.learn.jakson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * http://www.davismol.net/2015/03/10/jackson-json-difference-between-jsonignore-and-jsonignoreproperties-annotations/
 * @version 2016/9/22.
 */
public class JsonPropertyTest {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        MyTestClass mtc = new MyTestClass();
        mtc.setId(1);
        mtc.setName("Test program");
        mtc.setNotInterstingMember("Don't care about this");
        mtc.setAnotherMember(100);
        mtc.setForgetThisField(-1);


        String s = null;
        try {
            s = mapper.writeValueAsString(mtc);
        }
        catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(s);

        MyTestClass mtc2 = null;
        try {
            mtc2 = mapper.readValue(s, MyTestClass.class);
        }
        catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(mtc2.toString());

    }
}
