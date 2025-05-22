package hr.fer.fitman.controller;

import hr.fer.fitman.model.Trener;
import hr.fer.fitman.service.TrenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/treneri")
public class TrenerController {

    private final TrenerService trenerService;

    @Autowired
    public TrenerController(TrenerService trenerService) {
        this.trenerService = trenerService;
    }

    @PostMapping("/api/create")
    @ResponseBody
    public ResponseEntity<Trener> createTrener(@RequestBody Trener trener) {
        Trener savedTrener = trenerService.save(trener);
        return ResponseEntity.ok(savedTrener);
    }
}
