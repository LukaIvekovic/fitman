package hr.fer.fitman.service;

import hr.fer.fitman.model.Prostorija;
import hr.fer.fitman.repository.ProstorijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Optional<Prostorija> findById(Long id) {
        return prostorijaRepository.findById(id);
    }

    public Prostorija findByOznaka(String oznaka) {
        return prostorijaRepository.findByOznaka(oznaka);
    }

    @Transactional
    public Prostorija save(Prostorija prostorija) {
        return prostorijaRepository.save(prostorija);
    }

    @Transactional
    public void deleteProstorija(Long id) {
        prostorijaRepository.deleteById(id);
    }
}