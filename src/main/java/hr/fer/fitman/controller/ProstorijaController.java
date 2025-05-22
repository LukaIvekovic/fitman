package hr.fer.fitman.controller;

import hr.fer.fitman.model.Prostorija;
import hr.fer.fitman.service.ProstorijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/prostorije")
@RequiredArgsConstructor
public class ProstorijaController {
    private final ProstorijaService prostorijaService;

    @GetMapping
    public String listProstorije(Model model) {
        List<Prostorija> prostorije = prostorijaService.findAll();
        model.addAttribute("prostorije", prostorije);
        return "prostorija/list";
    }

    @GetMapping("/{id}")
    public String viewProstorija(@PathVariable Long id, Model model) {
        Prostorija prostorija = prostorijaService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nevažeći ID prostorije: " + id));
        model.addAttribute("prostorija", prostorija);
        return "prostorija/view";
    }

    @GetMapping("/new")
    public String createProstorijaForm(Model model) {
        model.addAttribute("prostorija", new Prostorija());
        model.addAttribute("mode", "create");
        return "prostorija/form";
    }

    @GetMapping("/{id}/edit")
    public String editProstorijaForm(@PathVariable Long id, Model model) {
        Prostorija prostorija = prostorijaService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nevažeći ID prostorije: " + id));
        model.addAttribute("prostorija", prostorija);
        model.addAttribute("mode", "edit");
        return "prostorija/form";
    }

    @PostMapping("/save")
    public String saveProstorija(@ModelAttribute Prostorija prostorija) {
        prostorijaService.save(prostorija);
        return "redirect:/prostorije";
    }

    @GetMapping("/{id}/delete")
    public String deleteProstorija(@PathVariable Long id) {
        prostorijaService.deleteProstorija(id);
        return "redirect:/prostorije";
    }
}