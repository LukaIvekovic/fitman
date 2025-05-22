package hr.fer.fitman.controller;

import hr.fer.fitman.model.Trener;
import hr.fer.fitman.model.Trening;
import hr.fer.fitman.service.TrenerService;
import hr.fer.fitman.service.ValidacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/treneri")
public class TrenerController {

    private final TrenerService trenerService;
    private final ValidacijaService validacijaService;

    @Autowired
    public TrenerController(TrenerService trenerService, ValidacijaService validacijaService) {
        this.trenerService = trenerService;
        this.validacijaService = validacijaService;
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Trener> createTrener(@RequestBody Trener trener) {
        Trener savedTrener = trenerService.save(trener);
        return ResponseEntity.ok(savedTrener);
    }

    @GetMapping("/dostupnost")
    @ResponseBody
    public ResponseEntity<?> provjeriDostupnostTrenera(
            @RequestParam Long trenerId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datum,
            @RequestParam Integer trajanje,
            @RequestParam(required = false) Long treningId) {

        // Get list of conflicts for this trainer
        List<Trening> conflicts = validacijaService.provjeriDostupnostTrenera(trenerId, datum, trajanje, treningId);

        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("dostupan", conflicts.isEmpty());

        // Include conflict details if any were found
        if (!conflicts.isEmpty()) {
            List<Map<String, Object>> conflictData = conflicts.stream()
                    .map(t -> {
                        Map<String, Object> data = new HashMap<>();
                        data.put("id", t.getId());
                        data.put("naziv", t.getNaziv());
                        data.put("datum", t.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
                        return data;
                    })
                    .toList();
            response.put("konflikti", conflictData);
        }

        return ResponseEntity.ok(response);
    }
}
