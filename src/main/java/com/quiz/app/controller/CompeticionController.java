package com.quiz.app.controller;
import com.quiz.app.entity.Competicion;
import com.quiz.app.repository.CompeticionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/competiciones")
public class CompeticionController {
    private final CompeticionRepository repo;
    public CompeticionController(CompeticionRepository repo) { this.repo = repo; }
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("competiciones", repo.findAll());
        return "competiciones/listar";
    }
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("competicion", new Competicion());
        return "competiciones/insertar";
    }
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Competicion competicion) {
        repo.save(competicion);
        return "redirect:/competiciones";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/competiciones";
    }
}
