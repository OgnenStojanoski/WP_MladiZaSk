package mk.ukim.finki.wp.project.web.controller;

import mk.ukim.finki.wp.project.model.Person;
import mk.ukim.finki.wp.project.model.Project;
import mk.ukim.finki.wp.project.model.enumerations.Genre;
import mk.ukim.finki.wp.project.service.PersonService;
import mk.ukim.finki.wp.project.service.ProjectService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController{
    private final ProjectService projectService;
    private final PersonService personService;

    public ProjectController(ProjectService projectService, PersonService personService) {
        this.projectService = projectService;
        this.personService = personService;
    }

    @GetMapping
    public String getProjectsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Project> projects = this.projectService.findAll();
        model.addAttribute("projects", projects);
        model.addAttribute("bodyContent", "projects");
        return "master-template";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProjectPage(Model model) {
        model.addAttribute("bodyContent", "add-project");
        return "master-template";
    }

    @PostMapping("/add-project-type")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProjectTypePage(@RequestParam String type,
                                     Model model){
        if(type.equals("BAND"))
            model.addAttribute("bodyContent", "add-band-project");
        else if(type.equals("VISUAL_ART"))
            model.addAttribute("bodyContent", "add-visual-project");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProject(
            @RequestParam(required = false) Long type_id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Genre genre,
            @RequestParam(required = false) String technique) {
        if(!type_id.equals(1L))
            this.projectService.save(name, technique);
        else
            this.projectService.save(name, genre);
        return "redirect:/project";
    }

    @GetMapping("/add-members/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.projectService.findById(id).isPresent()) {
            Project project = this.projectService.findById(id).get();
            List<Person> people = this.personService.findAll();
            model.addAttribute("people", people);
            model.addAttribute("project", project);
            model.addAttribute("bodyContent", "add-member");//
            return "master-template";
        }
        return "redirect:/project?error=ProjectNotFound";
    }

    @PostMapping("/add-member")
    public String saveMember(
            @RequestParam(required = false) Long project_id,
            @RequestParam(required = false) Long member_id)
    {
        this.projectService.saveMember(project_id, member_id);
        return "redirect:/project";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        this.projectService.deleteById(id);
        return "redirect:/project";
    }
}
