package com.linbet.spring.controllers;


import com.linbet.spring.models.Blog;
import com.linbet.spring.models.BlogMockedData;
import com.linbet.spring.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BlogController {

    @Autowired
    BlogRepository blogRepository;
//    BlogMockedData mockedData = BlogMockedData.getInstance();

    @GetMapping("/blog")
    public List<Blog> index() {
//       return mockedData.fetchBlogs();
        return blogRepository.findAll();
    }


    @GetMapping("/blog/{id}")
    public Blog show(@PathVariable String id) {
        int blogId = Integer.parseInt(id);

//        return mockedData.getBlogById(blogId);
        return blogRepository.findOne(blogId);
    }

    @PostMapping(value = "/blog/search")
    public List<Blog> search(@RequestBody Map<String, String> body) {
        String keywords = body.get("text");
//        return mockedData.searchBlogs(keywords);

//        return blogRepository.findTitleContainingOrContentContaining(keywords);
       return new ArrayList<Blog>();
    }

    @PostMapping(value = "/blog")
    public Blog create(@RequestBody Map<String, String> body) {
        String title = body.get("title");
        String content = body.get("content");

//        return mockedData.createBlog(title, content);
        return blogRepository.save(new Blog(title, content));
    }

    @PutMapping("/blog/{id}")
    public Blog update(@PathVariable String id, @RequestBody Map<String, String> body) {
        int blogId = Integer.parseInt(id);

        Blog blog = blogRepository.findOne(blogId);
        String title = body.get("title");
        String content = body.get("content");

        blog.setTitle(title);
        blog.setContent(title);


        return blogRepository.save(blog);
    }

    @DeleteMapping("/blog/{id}")
    public boolean delete(@PathVariable String id) {
        int blogId = Integer.parseInt(id);
        blogRepository.delete(blogId);

        return true;
    }
}
