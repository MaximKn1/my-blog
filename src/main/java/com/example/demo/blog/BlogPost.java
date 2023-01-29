package com.example.demo.blog;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used for storing blog post data.
 */
@Data
public final class BlogPost {
    private String author;
    private String text;

    public Map<String, String> getAsMap() {
        Map<String, String> map = new HashMap<>();
        map.put("author", author);
        map.put("text", text);

        return map;
    }
}
