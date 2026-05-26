package com.quiz.app.controller;
import com.quiz.app.entity.Club;
import com.quiz.app.entity.Entrenador;
import com.quiz.app.entity.Asociacion;
import com.quiz.app.entity.Jugador;
import com.quiz.app.entity.Competicion;
import com.quiz.app.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/clubes")
public class ClubController {
    private final ClubRepository clubRepo;
    private final EntrenadorRepository entrenadorRepo;
    private final AsociacionRepository asociacionRepo;
    private final JugadorRepository jugadorRepo;
    private final CompeticionRepository competicionRepo;
    public ClubController(ClubRepository clubRepo, EntrenadorRepository entrenadorRepo,
                          AsociacionRepository asociacionRepo, JugadorRepository jugadorRepo,
                          CompeticionRepository competicionRepo) {
        this.clubRepo = clubRepo;
        this.entrenadorRepo = entrenadorRepo;
        this.asociacionRepo = asociacionRepo;
        this.jugadorRepo = jugadorRepo;
        this.competicionRepo = competicionRepo;
    }
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clubes", clubRepo.findAll());
        return "clubes/listar";
    }
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        model.addAttribute("jugadores", jugadorRepo.findAll());
        model.addAttribute("competiciones", competicionRepo.findAll());
        return "clubes/insertar";
    }
    @PostMapping("/guardar")
    public String guardar(@RequestParam("nombre") String nombre,
                          @RequestParam("entrenadorId") Long entrenadorId,
                          @RequestParam("asociacionId") Long asociacionId,
                          @RequestParam(value = "jugadorIds", required = false) List<Long> jugadorIds,
                          @RequestParam(value = "competicionIds", required = false) List<Long> competicionIds) {
        Club club = new Club();
        club.setNombre(nombre);
        Entrenador entrenador = entrenadorRepo.findById(entrenadorId).orElse(null);
        Asociacion asociacion = asociacionRepo.findById(asociacionId).orElse(null);
        club.setEntrenador(entrenador);
        club.setAsociacion(asociacion);
        if (jugadorIds != null && !jugadorIds.isEmpty()) {
            club.setJugadores(jugadorRepo.findAllById(jugadorIds));
        }
        if (competicionIds != null && !competicionIds.isEmpty()) {
            club.setCompeticiones(competicionRepo.findAllById(competicionIds));
        }
        clubRepo.save(club);
        return "redirect:/clubes";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        clubRepo.deleteById(id);
        return "redirect:/clubes";
    }
}