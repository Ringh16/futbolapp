package com.quiz.app.controller;
import com.quiz.app.entity.Asociacion;
import com.quiz.app.repository.AsociacionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/asociaciones")
public class AsociacionController {
    private final AsociacionRepository repo;
    public AsociacionController(AsociacionRepository repo) { this.repo = repo; }
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("asociaciones", repo.findAll());
        return "asociaciones/listar";
    }
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        return "asociaciones/insertar";
    }
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Asociacion asociacion) {
        repo.save(asociacion);
        return "redirect:/asociaciones";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/asociaciones";
    }
}
