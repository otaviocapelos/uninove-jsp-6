package jpa;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AplicacaoJPA {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("MySQL");
        EntityManager em = emf.createEntityManager();

        ClienteDAO dao = new ClienteDAO(em);
        Cliente cliente = new Cliente();

        System.out.println("Informe o seu Nome:");
        cliente.setNome(sc.next());
        System.out.println("Informe o seu CPF:");
        cliente.setCpf(sc.next());
        System.out.println("Informe o seu Salario Bruto:");
        cliente.setSalario(sc.nextDouble());
        System.out.println("Informe o Valor do Emprestimo Solicitado:");
        Double valorEmprestimo = sc.nextDouble();
        System.out.println("Informe a Quantidade de Parcelas:");
        Integer parcela = sc.nextInt();
        cliente.setStatus(realizarEmprestimo(cliente.salario, valorEmprestimo, parcela));
        em.getTransaction().begin();
        dao.grava(cliente);
        em.getTransaction().commit();
        System.out.println("Informe o CPF para buscar o Status do Emprestimo:");
        System.out.println(dao.consultarStatusPorCPF(sc.next()));
        em.close();
        emf.close();
    }

    public static String realizarEmprestimo(Double SalarioBruto, Double valorEmprestimo, int parcela) {
        valorEmprestimo = valorEmprestimo * 1.45;
        Double valorMensalidade = valorEmprestimo / parcela;

        if (valorMensalidade < (SalarioBruto * 0.30)) {
            return "Credito Aprovado!";
        } else {
            return "Credito Reprovado!";
        }

    }
}
