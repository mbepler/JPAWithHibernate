package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPQL {

	
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		//String query = "select m from Movimentacao m where conta_id = 1";
		
		Conta conta = new Conta();
		conta.setId(2);
		
		// Com o jpql não se referencia a tabela e  sim a classe, nesse caso movimentacao é referente a classe movimentacao
		String jpql = "select m from Movimentacao m where m.conta = :pConta"
				+ " and m.tipo = :pTipo"
				+ " order by m.valor desc";
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.ENTRADA);
		
		List<Movimentacao> resultados = query.getResultList();
		
		for(Movimentacao movimentacao : resultados) {
			System.out.println("Descricao: " + movimentacao.getDescricao());
			System.out.println("Conta.id: " + movimentacao.getConta().getId());
		}
		
		em.getTransaction().commit();
		em.close();
	}
}
