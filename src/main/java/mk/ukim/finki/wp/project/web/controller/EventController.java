package mk.ukim.finki.wp.project.web.controller;

import mk.ukim.finki.wp.project.model.Event;
import mk.ukim.finki.wp.project.model.FileUploadUtil;
import mk.ukim.finki.wp.project.model.Person;
import mk.ukim.finki.wp.project.model.Product;
import mk.ukim.finki.wp.project.model.Projects.MusicBand;
import mk.ukim.finki.wp.project.model.Projects.VisualArtist;
import mk.ukim.finki.wp.project.service.EventService;
import mk.ukim.finki.wp.project.service.ProductService;
import mk.ukim.finki.wp.project.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController{
    private final EventService eventService;
    private final ProjectService projectService;
    private final ProductService productService;

    public EventController(EventService eventService, ProjectService projectService, ProductService productService) {
        this.eventService = eventService;
        this.projectService = projectService;
        this.productService = productService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Event> events = this.eventService.findAll();
        model.addAttribute("events", events);
        model.addAttribute("bodyContent", "events");
        return "master-template";
    }

    @GetMapping("/test2")
    public String getEventsPageWithPagination(Pageable pageable, Model model){
        List<Event> events = this.eventService.findAll(pageable).getContent();
        model.addAttribute("events", events);
        model.addAttribute("bodyContent", "events");
        return "master-template";
    }

    @GetMapping("/{id}")
    public String getEvent(@RequestParam(required = false) String error, @PathVariable Long id, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        Event event = this.eventService.findById(id).orElseThrow();
        model.addAttribute("event", event);
        model.addAttribute("bodyContent", "get-event");
        return "master-template";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addEventPage(Model model) {
        List<MusicBand> bands = this.projectService.getBands();
        List<VisualArtist> artists = this.projectService.getArtists();
        model.addAttribute("bands", bands);
        model.addAttribute("artists", artists);
        model.addAttribute("bodyContent", "add-event");
        return "master-template";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addEvent(@RequestParam("image") MultipartFile multipartFile,
                           @RequestParam(required = false) Long id,
                           @RequestParam Long band_id,
                           @RequestParam Long artist_id,
                           RedirectAttributes ra,
                           @RequestParam
                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                         LocalDateTime localDateTime) throws IOException {
//        if (id != null) {
//            //this.eventService.edit(id, name, surname, bio);
//        } else {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        //user.setPhotos(fileName);
        this.eventService.save(id, band_id, artist_id, localDateTime, fileName);

        String uploadDir = "user-photos/" + id;
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);


        ra.addFlashAttribute("message", "Created new event!");
//        }
        return "redirect:/events";
    }

    @GetMapping("/add-products/{id}")
    public String saveProductPage(@PathVariable Long id, Model model) {
        if (this.eventService.findById(id).isPresent()) {
            Event event = this.eventService.findById(id).get();
            List<Product> products = this.productService.findAll();
            model.addAttribute("products", products);
            model.addAttribute("event", event);
            model.addAttribute("bodyContent", "add-product-event");//
            return "master-template";
        }
        return "redirect:/events?error=EventNotFound";
    }

    @PostMapping("/add-product")
    public String saveProduct(
            @RequestParam(required = false) Long event_id,
            @RequestParam(required = false) List<Long> product_id)
    {
        this.eventService.saveProduct(event_id, product_id);
        return "redirect:/events";
    }



    @DeleteMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        this.eventService.deleteById(id);
        return "redirect:/events";
    }

    @GetMapping("/test")
    public String testMap(){
        return "test";
    }
}
