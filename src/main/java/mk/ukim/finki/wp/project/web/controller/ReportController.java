package mk.ukim.finki.wp.project.web.controller;

import mk.ukim.finki.wp.project.model.Event;
import mk.ukim.finki.wp.project.service.EventService;
import mk.ukim.finki.wp.project.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class ReportController{
    private final EventService eventService;
    private final ProductService productService;

    public ReportController(EventService eventService, ProductService productService) {
        this.eventService = eventService;
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getEventReport(@PathVariable Long id,
                                 Model model){
        Event event = this.eventService.findById(id).orElseThrow();
        model.addAttribute("event", event);
        model.addAttribute("bodyContent", "get-event-report");
        return "master-template";
    }
}
