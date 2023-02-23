package com.mycompany.bar_sale_stat.percentage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PercentageService {

    @Autowired
    private PercentageInterface percentageRepo;

    public List<Percentage> listCategories(){
        return (List<Percentage>) percentageRepo.findAll();
    }

    public void save(Percentage percentage){
        percentageRepo.save(percentage);
    }

    public Percentage get(Integer id) throws PercentageNotFound {
        Optional<Percentage> result = percentageRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw  new PercentageNotFound("Could not find percentage with this ID.");

    }

    public void delete(Integer id) throws PercentageNotFound {
//        Long count = authorRepo.countById(id);
        if(percentageRepo.count()==0){
            throw  new PercentageNotFound("Could not find a percentage." + id);
        }
        percentageRepo.deleteById(id);
    }


}
