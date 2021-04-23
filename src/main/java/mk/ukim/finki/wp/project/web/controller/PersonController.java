package mk.ukim.finki.wp.project.web.controller;

import mk.ukim.finki.wp.project.model.Person;
import mk.ukim.finki.wp.project.model.Product;
import mk.ukim.finki.wp.project.service.PersonService;
import mk.ukim.finki.wp.project.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/person")
public class PersonController{
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getPersonPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Person> people = this.personService.findAll();
        model.addAttribute("people", people);
        model.addAttribute("bodyContent", "people");
        return "master-template";
    }

    @GetMapping("/{id}")
    public String getPerson(@RequestParam(required = false) String error, @PathVariable Long id, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        Person person = this.personService.findById(id).orElseThrow();
        model.addAttribute("person", person);
        model.addAttribute("bodyContent", "get-person");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        this.personService.deleteById(id);
        return "redirect:/person";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addPersonPage(Model model) {
        model.addAttribute("bodyContent", "add-person");
        return "master-template";
    }

    @GetMapping("/edit/{id}")
    public String editPersonPage(@PathVariable Long id, Model model) {
        if (this.personService.findById(id).isPresent()) {
            Person person = this.personService.findById(id).get();
            model.addAttribute("person", person);
            model.addAttribute("bodyContent", "add-person");
            return "master-template";
        }
        return "redirect:/person?error=PersonNotFound";
    }


    @PostMapping("/add")
    public String savePerson(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String bio) {
        if (id != null) {
            this.personService.edit(id, name, surname, bio);
        } else {
            this.personService.save(name, surname, bio);
        }
        return "redirect:/person";
    }

}
