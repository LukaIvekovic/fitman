package hr.fer.fitman.controller;

import hr.fer.fitman.dto.TreningFormDTO;
import hr.fer.fitman.model.Trener;
import hr.fer.fitman.model.Trening;
import hr.fer.fitman.service.ProstorijaService;
import hr.fer.fitman.service.TrenerService;
import hr.fer.fitman.service.TreningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/treninzi")
public class TreningController {

    private final TreningService treningService;
    private final TrenerService trenerService;
    private final ProstorijaService prostorijaService;

    @Autowired
    public TreningController(TreningService treningService,
                             TrenerService trenerService,
                             ProstorijaService prostorijaService) {
        this.treningService = treningService;
        this.trenerService = trenerService;
        this.prostorijaService = prostorijaService;
    }

    @GetMapping
    public String listTreninzi(Model model) {
        List<Trening> treninzi = treningService.findAll();
        model.addAttribute("treninzi", treninzi);
        return "trening/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        TreningFormDTO treningForm = new TreningFormDTO();
        treningForm.setDatum(LocalDateTime.now());

        model.addAttribute("treningForm", treningForm);
        model.addAttribute("treneri", trenerService.findAll());
        model.addAttribute("prostorije", prostorijaService.findAll());
        model.addAttribute("mode", "create");

        return "trening/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Trening trening = treningService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid trening ID: " + id));

        TreningFormDTO treningForm = new TreningFormDTO();
        treningForm.setId(trening.getId());
        treningForm.setNaziv(trening.getNaziv());
        treningForm.setOpis(trening.getOpis());
        treningForm.setTezina(trening.getTezina());
        treningForm.setTrajanje(trening.getTrajanje());
        treningForm.setMaxBrojPolaznika(trening.getMaxBrojPolaznika());
        treningForm.setDatum(trening.getDatum());

        // Set selected trainer IDs
        treningForm.setTrenerId(trening.getTreneri().stream()
                .map(Trener::getId)
                .collect(Collectors.toList()));

        model.addAttribute("treningForm", treningForm);
        model.addAttribute("treneri", trenerService.findAll());
        model.addAttribute("prostorije", prostorijaService.findAll());
        model.addAttribute("mode", "edit");

        return "trening/form";
    }

    @PostMapping("/save")
    public String saveTrening(@ModelAttribute("treningForm") TreningFormDTO treningForm) {
        treningService.saveTrening(treningForm);
        return "redirect:/treninzi";
    }

    @GetMapping("/{id}/delete")
    public String deleteTrening(@PathVariable Long id) {
        treningService.deleteTrening(id);
        return "redirect:/treninzi";
    }

    @GetMapping("/{id}")
    public String viewTrening(@PathVariable Long id, Model model) {
        Trening trening = treningService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid trening ID: " + id));

        model.addAttribute("trening", trening);
        return "trening/view";
    }
}
