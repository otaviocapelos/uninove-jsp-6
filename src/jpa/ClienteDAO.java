package jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

class ClienteDAO {

    private EntityManager em;
    private List<Cliente> clientes;
    private Cliente cliente;
    private Query q;

    ClienteDAO(EntityManager em) {
        this.em = em;
    }
    
    //    public void criaTabela() {
    //    public void apagaTabela() {
    
    void grava(Cliente cliente) {
        em.persist(cliente);
        
        System.out.println("Registro gravado com sucesso!");
    }
    
    void consultaTudo() {
        q = em.createQuery(
                "SELECT aula FROM Cliente cliente", Cliente.class);
        clientes = q.getResultList();
        
        clientes.forEach(a -> System.out.println("*** "
                        + a.getCpf()+ " | "
                        + a.getNome() + " | "
                        + a.getSalario() + " | "
                        + a.getStatus() + " ***"));
    }
    
    String consultarStatusPorCPF(String cpf) {
        
        q = em.createQuery("SELECT cliente FROM Cliente cliente WHERE Cpf = :CpfParam");
        q.setParameter("CpfParam", cpf);
        clientes = q.getResultList();
        
        clientes.forEach(a -> System.out.println("Status do Emprestimo:  " 
                            + a.getStatus()));
        return clientes.get(0).getStatus();
    }
    
    void alteraValor(Integer id, Double novoValor) {
        cliente = em.find(Cliente.class, id);
        
        if ( cliente != null) 
            //cliente.(novoValor);
        
        System.out.println("Registro alterado com sucesso!");
    }     
        
    void apaga(Integer id) {
        //cliente = em.find(VideoAula.class, id);
        
        if ( cliente != null) 
            em.remove(cliente);
        
        System.out.println("Registro apagado com sucesso!");
    }
    
}
