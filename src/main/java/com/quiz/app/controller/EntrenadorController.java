package com.quiz.app.controller;
import com.quiz.app.entity.Entrenador;
import com.quiz.app.repository.EntrenadorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/entrenadores")
public class EntrenadorController {
    private final EntrenadorRepository repo;
    public EntrenadorController(EntrenadorRepository repo) { this.repo = repo; }
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("entrenadores", repo.findAll());
        return "entrenadores/listar";
    }
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        return "entrenadores/insertar";
    }
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Entrenador entrenador) {
        repo.save(entrenador);
        return "redirect:/entrenadores";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/entrenadores";
    }
}
