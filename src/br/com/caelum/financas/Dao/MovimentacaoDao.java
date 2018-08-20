package br.com.caelum.financas.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class MovimentacaoDao {
	
	
	private EntityManager em;
	
	public MovimentacaoDao(EntityManager em) {
		this.em = em;
	}
	

	public List<Double> getMediasPorDiaETipo(TipoMovimentacao saida, Conta conta) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		// Com o jpql não se referencia a tabela e  sim a classe, nesse caso movimentacao é referente a classe movimentacao
		String jpql = "select AVG(m.valor) from Movimentacao m where m.conta = :pConta"
				+ " and m.tipo = :pTipo"
				+ " group by m.conta ";
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", saida);
		
		return query.getResultList();
	}

}
