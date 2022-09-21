package br.dev.magliano.productjpa.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvaliacaoForm {

    @JsonProperty("review")
    private String avaliacao;

    public String getAvaliacao() {
        return avaliacao;
    }

    public AvaliacaoForm() {
    }

    public AvaliacaoForm(String avaliacao) {
        this.avaliacao = avaliacao;
    }

}
