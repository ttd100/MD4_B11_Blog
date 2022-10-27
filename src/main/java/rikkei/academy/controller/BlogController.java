package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.model.Blog;
import rikkei.academy.model.Category;
import rikkei.academy.service.blog.IBlogService;
import rikkei.academy.service.category.ICategoryService;

import java.util.Optional;


@Controller
@RequestMapping({"/blog", "/"})
public class BlogController {

    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("allCategories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping
    public String index(Model model, @PageableDefault(size = 2) Pageable pageable) {
        model.addAttribute("blogList", blogService.findAll(pageable));
        model.addAttribute("blogForm", new Blog());
        return "/blog/index";
    }

    @PostMapping("/create")
    public String create(Blog blog) {
        blogService.save(blog);
        return "redirect:/blog";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Optional<Blog> blog, Model model) {
        if (!blog.isPresent()) {
            return "/error.404";
        }
        model.addAttribute("blogEdit", blog.get());
        return "/blog/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Optional<Blog> blog, Model model) {
        if (!blog.isPresent()) {
            return "/error.404";
        }
        model.addAttribute("blogDelete", blog.get());
        return "/blog/delete";
    }

    @PostMapping("/update")
    public String update(Blog blog) {
        blogService.save(blog);
        return "redirect:/blog";
    }

    @PostMapping("/remove")
    public String remove(Blog blog) {
        blogService.remove(blog.getId());
        return "redirect:/blog";
    }
}
