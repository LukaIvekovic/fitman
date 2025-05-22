package hr.fer.fitman.controller;

import hr.fer.fitman.dto.TreningFormDTO;
import hr.fer.fitman.model.Trener;
import hr.fer.fitman.model.Trening;
import hr.fer.fitman.service.ProstorijaService;
import hr.fer.fitman.service.TrenerService;
import hr.fer.fitman.service.TreningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/search")
    public String pretragaTreninzi(
            @RequestParam(required = false) String naziv,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datumOd,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datumDo,
            Model model) {

        List<Trening> treninzi;

        if (naziv != null && !naziv.isEmpty() || datumOd != null || datumDo != null) {
            // Convert LocalDate to LocalDateTime for comparison
            LocalDateTime startDateTime = datumOd != null ?
                    datumOd.atStartOfDay() : LocalDateTime.of(1900, 1, 1, 0, 0);

            LocalDateTime endDateTime = datumDo != null ?
                    datumDo.plusDays(1).atStartOfDay().minusNanos(1) : LocalDateTime.of(2100, 12, 31, 23, 59, 59);

            // Get filtered treninzi
            treninzi = treningService.findByFilters(naziv, startDateTime, endDateTime);
        } else {
            treninzi = treningService.findAll();
        }

        model.addAttribute("treninzi", treninzi);
        return "trening/list";
    }
}
