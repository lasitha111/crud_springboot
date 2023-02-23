package com.mycompany.bar_sale_stat.brands;


import com.mycompany.bar_sale_stat.category.Category;
import com.mycompany.bar_sale_stat.percentage.Percentage;
import com.mycompany.bar_sale_stat.percentage.PercentageInterface;
import com.mycompany.bar_sale_stat.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private CategoryRepository publisherRepo;

    @Autowired
    private PercentageInterface authorRepo;

    @Autowired
    private BrandService service;

    @GetMapping("")
    public String showHomePage(Model model){
        List<Brand> brands = service.listBrands();
        model.addAttribute("listBrands", brands);
        //model.addAttribute("activePage","books");
        return "index";
    }

    @GetMapping("/brand/new")
    public  String showNewBookForm(Model model){
        List<Category> listCategories = (List<Category>) publisherRepo.findAll();
        List<Percentage> listPercentages = authorRepo.findAll();
        model.addAttribute("brand",new Brand());
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("listpercentage", listPercentages);
        //model.addAttribute("pageTitle","Add Brand");
        return "brand_form";
    }

    @PostMapping("/brand/save")
    public String saveBook(Brand brand, RedirectAttributes ra){
        service.save(brand);
        ra.addFlashAttribute("message","Successfully added the brand");
        return "redirect:/";
    }

    @GetMapping("/brand/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model){
        try{
            List<Category> listCategories = (List<Category>) publisherRepo.findAll();
            List<Percentage> listPercentages = authorRepo.findAll();
            Brand brand = service.get(id);
            model.addAttribute("brand", brand);
            model.addAttribute("listCategories", listCategories);
            model.addAttribute("listpercentage", listPercentages);
            //model.addAttribute("pageTitle","Edit Book");
            return "brand_form";
        }catch(BrandNotFound e){
            e.printStackTrace();
            return "redirect:/";
        }
    }

    @GetMapping("/brand/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message","Successfully deleted brand " + id) ;
        }catch(BrandNotFound e){

            ra.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/";
    }
}
