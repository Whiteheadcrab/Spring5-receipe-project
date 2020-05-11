package whiteheadcrab.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import whiteheadcrab.springframework.domain.Category;
import whiteheadcrab.springframework.domain.UnitOfMeasure;
import whiteheadcrab.springframework.repositories.CategoryRepository;
import whiteheadcrab.springframework.repositories.UnitofMeasureRepository;

import java.util.Optional;

@Controller
public class IndexController
{
    private CategoryRepository categoryRepository;
    private UnitofMeasureRepository unitofMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitofMeasureRepository unitofMeasureRepository)
    {
        this.categoryRepository = categoryRepository;
        this.unitofMeasureRepository = unitofMeasureRepository;
    }

    @RequestMapping({"/","","/index"})
    public String getIndexPage()
    {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitofMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Cat id is : "+categoryOptional.get().getId());
        System.out.println("Dog Id is :" +unitOfMeasureOptional.get().getId());

        return "index";
    }
}
