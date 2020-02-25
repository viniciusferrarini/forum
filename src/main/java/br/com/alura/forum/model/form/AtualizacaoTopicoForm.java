package br.com.alura.forum.model.form;

import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtualizacaoTopicoForm {

    @NotEmpty
    private String titulo;

    @NotEmpty
    private String mensagem;

    public Topico atualizar(Long id, TopicoRepository topicoRepository) {
        Topico topico = topicoRepository.getOne(id);
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);
        return topico;
    }
}
