package hr.fer.fitman.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreningFormDTO {
    private Long id;
    private String naziv;
    private String opis;
    private String tezina;
    private Integer trajanje;
    private Integer maxBrojPolaznika;
    private LocalDateTime datum;
    private List<Long> trenerId = new ArrayList<>();
    private String prostorijaOznaka;
}