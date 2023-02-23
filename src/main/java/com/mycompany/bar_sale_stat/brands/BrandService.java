package com.mycompany.bar_sale_stat.brands;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.text.CaseUtils;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    private static final String BLANK_SPACE=" ";
    @Autowired
    private BrandRepositary brandrepo;

    public List<Brand> listBrands(){
        List<Brand> brands= (List<Brand>) brandrepo.findAll();
        String brandName;
        for(int i = 0; i< brands.size();i++){
            brandName = makeCamelCase(brands.get(i).getBrand_name()) ;
            brands.get(i).setBrand_name(brandName);
        }
        return brands;
    }

    private String makeCamelCase(String s){
        String[] words = s.split("\\b");
        StringBuilder sb = new StringBuilder(words[0].toLowerCase());
        for (int i = 1; i < words.length; i++) {
            sb.append(Character.toUpperCase(words[i].charAt(0)));
            sb.append(words[i].substring(1).toLowerCase());
            if (i < words.length - 1) {
                sb.append(" ");
            }
        }
        s = sb.toString();
        s = Character.toUpperCase(s.charAt(0)) + s.substring(1);
        return s;
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
