package com.mycompany.bar_sale_stat.brands;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepositary brandrepo;

    public List<Brand> listBrands(){
        return (List<Brand>) brandrepo.findAll();
    }
    public void save(Brand brand){
        brandrepo.save(brand);
    }

    public Brand get(Integer id) throws BrandNotFound {
        Optional<Brand> result = brandrepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw  new BrandNotFound("Could not find a brand for this id.");

    }

    public void delete(Integer id) throws BrandNotFound {

        if(brandrepo.count()==0){
            throw  new BrandNotFound("Could not find brand id" + id);
        }
        brandrepo.deleteById(id);
    }

}
