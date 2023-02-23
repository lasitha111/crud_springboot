package com.mycompany.bar_sale_stat.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryController {


    @Autowired
    private CategoryService service;

    @GetMapping("/category")
    public String showPublisherPage(Model model){
        List<Category> categories = service.listCategory();
        model.addAttribute("listCategories", categories);
        //model.addAttribute("activePage","publishers");
        return "category";
    }

    @GetMapping("/category/new")
    public String showPublisherForm(Model model){

        model.addAttribute("category",new Category());
        //model.addAttribute("pageTitle","Add publisher");
        return "category_form";
    }

    @PostMapping("/category/save")
    public String savePublisher(Category category, RedirectAttributes ra){
        service.save(category);
        ra.addFlashAttribute("message","Successfully added the category");
        return "redirect:/category";
    }

    @GetMapping("/category/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model){
        try{

            Category category = service.get(id);
            model.addAttribute("category", category);
            //model.addAttribute("pageTitle","Edit publisher");
            return "category_form";
        }catch(CategoryNotFound e){
            e.printStackTrace();
            return "redirect:/category";
        }
    }

    @GetMapping("/category/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message","Successfully deleted the category" + id) ;
        }catch(CategoryNotFound e){

            ra.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/category";
    }
}
