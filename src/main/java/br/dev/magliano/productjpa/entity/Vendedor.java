package br.dev.magliano.productjpa.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Vendedor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private Usuario usuarioVendedor;

  @OneToMany
  private List<Produto> produtos = new ArrayList<>();

  public Vendedor(Usuario usuarioVendedor) {
    this.usuarioVendedor = usuarioVendedor;
  }

  public void adicionaItens(Produto produto){
    this.produtos.add(produto);
  }

  public void adicionaMultiplosItens(List<Produto> produtos){
    this.produtos.addAll(produtos);
  }

  public List<Produto> getProdutos() {
    return produtos;
  }

  public Usuario getUsuarioVendedor() {
    return usuarioVendedor;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
