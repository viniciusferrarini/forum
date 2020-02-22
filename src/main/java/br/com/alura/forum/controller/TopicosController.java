package br.com.alura.forum.controller;

import br.com.alura.forum.model.Topico;
import br.com.alura.forum.model.dto.TopicoDto;
import br.com.alura.forum.model.form.TopicoForm;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista() {
        List<Topico> topicos = topicoRepository.findAll();
        return TopicoDto.converteParaListaDeTopicoDto(topicos);
    }

    @GetMapping("/{nomeCurso}")
    public List<TopicoDto> listaTopicosPorCurso(@Param("nomeCurso") String nomeCurso) {
        List<Topico> topicos = topicoRepository.findByCursoNomeLike(nomeCurso);
        return TopicoDto.converteParaListaDeTopicoDto(topicos);
    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody TopicoForm form, UriComponentsBuilder uriBuilder){
        Topico topico = form.converteFormParaTopico(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

}