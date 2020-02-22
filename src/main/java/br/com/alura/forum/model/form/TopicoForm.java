package br.com.alura.forum.model.form;

import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode
public class TopicoForm {

    private String titulo;

    private String mensagem;

    private String nomeCurso;

    public Topico converteFormParaTopico(CursoRepository cursoRepository) {
        return new Topico(this.titulo, this.mensagem, cursoRepository.findByNome(this.nomeCurso));
    }
}
