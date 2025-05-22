package hr.fer.fitman.service;

import hr.fer.fitman.model.Prostorija;
import hr.fer.fitman.repository.ProstorijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProstorijaService {

    private final ProstorijaRepository prostorijaRepository;

    @Autowired
    public ProstorijaService(ProstorijaRepository prostorijaRepository) {
        this.prostorijaRepository = prostorijaRepository;
    }

    public List<Prostorija> findAll() {
        return prostorijaRepository.findAll();
    }

    public Prostorija findByOznaka(String oznaka) {
        return prostorijaRepository.findByOznaka(oznaka);
    }
}