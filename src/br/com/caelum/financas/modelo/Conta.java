package br.com.caelum.financas.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//@Entity para passar para o Hibernate que ele esta classe representa uma tabela 
//e o Hibernate deve gerenciar estes dados
@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String titular;
	private String numero;
	private String banco;
	private String agencia;
	
//	É possível ter N + 1 mesmo com FetchType.EAGER, a diferença é que ele executará todas as queries antecipadamente. 
	//	@OneToMany(mappedBy="conta", fetch=FetchType.EAGER)
	
	
	// para que não seja criado uma tabela separada para indicar esta lista, necessário adicionar o mappedBy
	// assim definindo quem sera o lado forte, o responsável por criar a ligação
	@OneToMany(mappedBy="conta")
	private List<Movimentacao> movimentacoes;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public List<Movimentacao> getMovimentacoes() {
		return this.movimentacoes;
		
	}
	
	
}
