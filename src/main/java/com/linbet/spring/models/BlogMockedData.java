package com.linbet.spring.models;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class BlogMockedData {
   private List<Blog> blogs;

   private static BlogMockedData instance = null;
   public static BlogMockedData getInstance() {
      if (instance == null) {
         instance = new BlogMockedData();
      }

      return instance;
   }


   public BlogMockedData() {
      blogs = new ArrayList<Blog>();
      Faker faker = new Faker();

      for (int i=1; i<=5; i++) {
         Blog blog = new Blog(i, faker.lorem().sentence(3), faker.lorem().paragraph(2));
         blogs.add(blog);
      }
   }

   public List<Blog> fetchBlogs() {
      return blogs;
   }

   public Blog getBlogById(int id) {
      for (Blog blog: blogs) {
        if (blog.getId() == id) {
           return blog;
        }
      }

      return null;
   }

   public Blog createBlog(String title, String content) {
      int blogId = blogs.size() + 1;
      Blog newBlog = new Blog(blogId, title, content);

      blogs.add(newBlog);

      return newBlog;
   }

   public List<Blog> searchBlogs(String keywords) {
      List<Blog> result = new ArrayList<>();

      for (Blog blog: blogs) {
         if (blog.getTitle().toLowerCase().contains(keywords)||
                 blog.getContent().toLowerCase().contains(keywords)) {
             result.add(blog);
         }
      }

      return result;
   }


   public Blog updateBlog(int id, String newTitle, String newContent) {

      for (Blog blog: blogs) {
        if (blog.getId() == id) {
           int index = blogs.indexOf(blog);
           blog.setTitle(newTitle);
           blog.setContent(newContent);

           blogs.set(index, blog);

           return blog;
        }

      }

      return null;
   }


   public boolean delete(int id) {
      int index = -1;

      for (Blog blog: blogs) {
        if (blog.getId() == id) {
           index = blogs.indexOf(blog);

           break;
        }
      }

      if (index != -1) {
         blogs.remove(index);

         return true;
      }

      return false;
   }
}


