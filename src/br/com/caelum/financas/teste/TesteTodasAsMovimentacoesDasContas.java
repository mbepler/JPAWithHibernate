package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteTodasAsMovimentacoesDasContas {

	
	public static void main(String[] args) {
		
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
			
		// join fetch é utilizado quando ha um relacionamento many to something
		// sem o join fetch é realizado uma busca para cada conta, de acordo com que a conta é usada (LazyList)
		// com o join fetch a consulta da movimentação é realizada no mesmo momento q é feita a consulta as contas (EagerList)
		String jpql = "select distinct c from Conta c left join fetch c.movimentacoes";
		Query query = em.createQuery(jpql);
		
		
		List<Conta> todasAsContas = query.getResultList();
		
		for (Conta conta : todasAsContas) {
			System.out.println("Titular : " + conta.getTitular());
			System.out.println("Movimentacoes : ");
			System.out.println(conta.getMovimentacoes());
		}
		
		em.getTransaction().commit();
		em.close();
		
	}
}
