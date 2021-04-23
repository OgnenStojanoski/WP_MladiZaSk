package mk.ukim.finki.wp.project.web.controller;

import mk.ukim.finki.wp.project.model.Product;
import mk.ukim.finki.wp.project.model.Project;
import mk.ukim.finki.wp.project.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController{
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProductsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("bodyContent", "products");
        return "master-template";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProductPage(Model model) {
        model.addAttribute("bodyContent", "add-product");
        return "master-template";
    }

    @PostMapping("/add-product-type")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProductTypePage(@RequestParam String type,
                                     Model model){
        if(type.equals("CD"))
            model.addAttribute("bodyContent", "add-cd-product");
        else if(type.equals("T_SHIRT"))
            model.addAttribute("bodyContent", "add-shirt-product");
        else if(type.equals("TICKET"))
            model.addAttribute("bodyContent", "add-ticket-product");
        return "master-template";
    }
}
