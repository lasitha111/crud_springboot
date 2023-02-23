package com.mycompany.bar_sale_stat.percentage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PercentageController {

    @Autowired
    private PercentageService service;

    @GetMapping("/percentages")
    public String showAuthorPage(Model model){
        List<Percentage> listpercentage = service.listCategories();
        model.addAttribute("listpercentage",listpercentage);
        //model.addAttribute("activePage","authors");
        return "percentage";
    }

    @GetMapping("/percentages/new")
    public String showAuthorForm(Model model){
        model.addAttribute("percentages",new Percentage());
       // model.addAttribute("pageTitle","Add new author");
        return "percentage_form";
    }

    @PostMapping("/percentages/save")
    public String saveAuthor(Percentage percentage, RedirectAttributes ra){
        service.save(percentage);
        ra.addFlashAttribute("message","Successfully added the new percentage") ;
        return "redirect:/percentages";
    }

    @GetMapping("/percentages/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model){
        try{
            Percentage percentage = service.get(id);
            model.addAttribute("percentages", percentage);
           // model.addAttribute("pageTitle","Edit author");
            return "percentage_form";
        }catch(PercentageNotFound e){
            e.printStackTrace();
            return "redirect:/percentages";
        }
    }

    @GetMapping("/percentages/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message","Successfully deleted the percentage " + id) ;
        }catch(PercentageNotFound e){

            ra.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/percentages";
    }
}
