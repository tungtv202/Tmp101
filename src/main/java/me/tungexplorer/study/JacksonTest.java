package me.tungexplorer.study;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonTest {


    static class TestDTO extends HashMap<String, List<Integer>> {

    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

        Map<String, List<Integer>> mapping = new HashMap<>();
        mapping.put("Alpha", List.of(1, 2));
        mapping.put("Beta", List.of(4, 5));
        TestDTO testDTO = new TestDTO();
        testDTO.put("Alpha", List.of(1, 2));
        testDTO.put("Beta", List.of(4, 5));

        String s = objectMapper.writeValueAsString(testDTO);
        System.out.println(s);

        System.out.println(123);

    }
}
