package com.cmpt276.asn_2.controllers;

import com.cmpt276.asn_2.models.Rectangle;
import com.cmpt276.asn_2.models.RectangleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RectangleController {

    @Autowired
    private RectangleRepository rectangleRepository;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Rectangle> listRectangles = rectangleRepository.findAll();
        model.addAttribute("listRectangles", listRectangles);
        return "showAll";
    }

    @GetMapping("/rectangles/delete/{id}")
    public String deleteRectangle(@PathVariable("id") int id) {
        rectangleRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/rectangles/{id}")
    public String viewRectangleDetails(@PathVariable("id") int id, Model model) {
        Rectangle rectangle = rectangleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rectangle Id:" + id));
        model.addAttribute("rectangle", rectangle);
        return "detail";
    }

    @PostMapping("/rectangles/{id}")
    public String updateRectangle(@PathVariable("id") int id, @ModelAttribute("rectangle") Rectangle rectangle) {
        rectangle.setId(id);
        rectangleRepository.save(rectangle);
        return "redirect:/";
    }

    @GetMapping("/rectangles/new")
    public String showNewRectangleForm(Model model) {
        Rectangle rectangle = new Rectangle();
        model.addAttribute("rectangle", rectangle);
        return "add";
    }
    @PostMapping("/rectangles")
    public String saveNewRectangle(@ModelAttribute("rectangle") Rectangle rectangle, Model model) {
        rectangleRepository.save(rectangle);
        model.addAttribute("successMessage", "Rectangle added successfully!");
        return "redirect:/?success";
    }
    
}
 