package com.quiz.app.controller;
import com.quiz.app.entity.Jugador;
import com.quiz.app.repository.JugadorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/jugadores")
public class JugadorController {
    private final JugadorRepository repo;
    public JugadorController(JugadorRepository repo) { this.repo = repo; }
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("jugadores", repo.findAll());
        return "jugadores/listar";
    }
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        return "jugadores/insertar";
    }
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Jugador jugador) {
        repo.save(jugador);
        return "redirect:/jugadores";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/jugadores";
    }
}
