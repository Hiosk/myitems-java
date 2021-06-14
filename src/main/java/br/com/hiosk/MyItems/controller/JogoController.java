package br.com.hiosk.MyItems.controller;

import br.com.hiosk.MyItems.Model.Jogo;
import br.com.hiosk.MyItems.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class JogoController {

    @Autowired
    private JogoRepository jogoRepository;

    @GetMapping("/catalogo")
    public String listarJogos(Model model) {
        model.addAttribute("listaJogos", jogoRepository.findAll());
        return "catalogo";
    }
    @GetMapping("/biblioteca")
    public String listarJogosComprados(Model model) {
        return "biblioteca";
    }

    @GetMapping({"/criarJogo", "admin"})
    public String criarJogo(Model model) {
        model.addAttribute("jogo", new Jogo());
        return "alterar_jogo";
    }

    @GetMapping("/editarJogo/{id}")
    public String editarProduto(@PathVariable("id") long id, Model model) {
        Optional<Jogo> jogo = jogoRepository.findById(id);
        model.addAttribute("jogo", jogo.get());
        return "alterar_jogo";
    }

    @PostMapping("/salvarJogo")
    public String salvarProduto(Jogo jogo, BindingResult result) {
        if (result.hasErrors()) {
            return "alterar_jogo";
        }
        jogoRepository.save(jogo);
        return "redirect:/catalogo";
    }

    @GetMapping("/excluirJogo/{id}")
    public String excluirJogo(@PathVariable("id") long id) {
        jogoRepository.deleteById(id);
        return "redirect:/catalogo";
    }
}