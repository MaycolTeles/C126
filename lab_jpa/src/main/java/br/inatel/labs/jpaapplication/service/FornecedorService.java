package br.inatel.labs.jpaapplication.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.labs.jpaapplication.entity.Fornecedor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class FornecedorService {

    @PersistenceContext
    private EntityManager em;

    public Fornecedor salvar(Fornecedor fornecedor){
        return em.merge(fornecedor);
    }

    public Fornecedor buscarFornecedorPeloId(Long id){
        return em.find(Fornecedor.class,id);
    }

    public List<Fornecedor> listar(){
        return em.createQuery("select f from Fornecedor f", Fornecedor.class).getResultList();
    }

    public void remover(Fornecedor fornecedor){
        fornecedor = em.merge(fornecedor);
        em.remove(fornecedor);
    }
}
