package com.example.demo.blog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo.utility.Utils;
import com.example.demo.responses.BlogResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public final class BlogPage {
    private static final List<BlogPost> BLOG_POSTS = new ArrayList<>();

    @GetMapping("/")
    public String mainPage() {
        log.info("Someone entered the page");

        StringBuilder stringBuilder = new StringBuilder("Welcome to MyBlog!\n");
        String postTemplate = Utils.readFile("html/blog-post-template.html");
        log.info("Loading blog posts");
        for (BlogPost post : BLOG_POSTS) {
            Map<String, String> postMap = post.getAsMap();
            StringSubstitutor stringSubstitutor = new StringSubstitutor(postMap);
            stringBuilder.append(stringSubstitutor.replace(postTemplate));
        }
        return stringBuilder.toString();
    }

    /**
     * Method used for writing new blog posts.
     */
    @PostMapping("/createNewBlogPost")
    public BlogResponse createNewBlogPost(@RequestBody final String body) {
        log.info("New blog post request registered");

        ObjectMapper objectMapper = new ObjectMapper();
        BlogPost newPost;
        try {
            newPost = objectMapper.readValue(body, BlogPost.class);
            BLOG_POSTS.add(newPost);
            log.info("New blog post successfully created");
        } catch (Exception e) {
            log.warn("Request body: {}", body);
            log.warn("Unable to process createNewBlogPost request");
            log.warn(e.getLocalizedMessage());

            BlogResponse blogResponse = new BlogResponse();
            blogResponse.setStatus(BlogResponse.STATUS_FAIL);
            blogResponse.setDescription(BlogResponse.INCORRECT_JSON);
            return blogResponse;
        }

        BlogResponse blogResponse = new BlogResponse();
        blogResponse.setStatus(BlogResponse.STATUS_SUCCESS);
        blogResponse.setDescription("");
        return blogResponse;
    }
}
