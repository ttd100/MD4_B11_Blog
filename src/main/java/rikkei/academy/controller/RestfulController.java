package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rikkei.academy.model.Blog;
import rikkei.academy.model.Category;
import rikkei.academy.service.blog.IBlogService;
import rikkei.academy.service.category.ICategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RestfulController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<Iterable<Category>> findAllCategory() {
        List<Category> categoryList = (List<Category>) categoryService.findAll();
        for (Category category : categoryList) {
            category.setBlogs(null);
        }
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/blog")
    public ResponseEntity<Iterable<Blog>> findAllBlog() {
        List<Blog> blogList = (List<Blog>) blogService.findAll();
        if (blogList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Iterable<Blog>> findCategoryById(@PathVariable("id") Optional<Category> categoryOptional) {
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryOptional.get().getBlogs(), HttpStatus.OK);
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<Blog> detailBlog(@PathVariable("id") Optional<Blog> blog) {
        if (!blog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blog.get().getCategory().setBlogs(null);
        return new ResponseEntity<>(blog.get(), HttpStatus.OK);
    }

}
