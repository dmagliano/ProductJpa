package br.dev.magliano.productjpa.controller.dto;

public class AvaliacaoOutputDTO {

    private String avaliacao;

    private String usuario;

    public String getAvaliacao() {
        return avaliacao;
    }

    public String getUsuario() {
        return usuario;
    }

    public AvaliacaoOutputDTO(String avaliacao, String usuario) {
        this.avaliacao = avaliacao;
        this.usuario = usuario;
    }


}
