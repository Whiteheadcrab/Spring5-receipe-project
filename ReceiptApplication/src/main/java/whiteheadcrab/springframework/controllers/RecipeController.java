package whiteheadcrab.springframework.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import whiteheadcrab.springframework.commands.RecipeCommand;
import whiteheadcrab.springframework.domain.Difficulty;
import whiteheadcrab.springframework.exceptions.NotFoundException;
import whiteheadcrab.springframework.services.RecipeService;

@Slf4j
@Controller
public class RecipeController
{
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService)
    {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model)
    {
        model.addAttribute("recipe",recipeService.findById(new Long(id)));
        Difficulty.values();
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model)
    {
        model.addAttribute("recipe",new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand)
    {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @PostMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model)
    {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id)
    {
        log.debug("Deleting id: " + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound()
    {

        log.error("Handling not found exception");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");

        return modelAndView;
    }
}
