package com.mycompany.bar_sale_stat.category;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryrepo;

    public List<Category> listCategory(){
        return (List<Category>) categoryrepo.findAll();
    }
    public void save(Category category){
        categoryrepo.save(category);
    }

    public Category get(Integer id) throws CategoryNotFound {
        Optional<Category> result = categoryrepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw  new CategoryNotFound("Could not find a Category for this id");

    }

    public void delete(Integer id) throws CategoryNotFound {

        if(categoryrepo.count()==0){
            throw  new CategoryNotFound("Could not find a category for id." + id);
        }
        categoryrepo.deleteById(id);
    }
}
