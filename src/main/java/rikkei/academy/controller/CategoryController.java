package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.model.Blog;
import rikkei.academy.model.Category;
import rikkei.academy.service.blog.IBlogService;
import rikkei.academy.service.category.ICategoryService;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String showList(Model model, @PageableDefault(size = 3) Pageable pageable) {
        model.addAttribute("categories", categoryService.findAll(pageable));
        return "/category/list";
    }

    @GetMapping("/create")
    public String formCreate(Model model) {
        model.addAttribute("category", new Category());
        return "/category/create";
    }

    @PostMapping("/create")
    public String create(Category category) {
        categoryService.save(category);
        return "redirect:/category/create";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView formEdit(@PathVariable("id") Optional<Category> category) {
        if (!category.isPresent()) {
            return new ModelAndView("/error.404");
        }
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category", category.get());
        return modelAndView;
    }

    @PostMapping("/update")
    public String update(Category category) {
        categoryService.save(category);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView formDelete(@PathVariable("id") Optional<Category> category) {
        if (!category.isPresent()) {
            return new ModelAndView("/error.404");
        }
        ModelAndView modelAndView = new ModelAndView("/category/delete");
        modelAndView.addObject("category", category.get());
        return modelAndView;
    }

    @PostMapping("/remove")
    public String remove(Category category) {

        categoryService.remove(category.getId());
        return "redirect:/category";
    }
}
