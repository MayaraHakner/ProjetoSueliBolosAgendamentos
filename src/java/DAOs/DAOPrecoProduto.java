package DAOs;

import Entidades.PrecoProduto;
import java.util.ArrayList;
import java.util.List;

public class DAOPrecoProduto extends DAOGenerico<PrecoProduto> {

private List<PrecoProduto> lista = new ArrayList<>();    public DAOPrecoProduto(){
        super(PrecoProduto.class);
    }

    public int autoIdPrecoProduto() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idPrecoProduto) FROM PrecoProduto) e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<PrecoProduto> listByNome(String nome) {
        return em.createQuery("SELECT e FROM PrecoProduto e WHERE e.idPrecoProduto) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<PrecoProduto> listById(int id) {
        return em.createQuery("SELECT e FROM PrecoProduto + e WHERE e.nome= :id").setParameter("id", id).getResultList();
    }

    public List<PrecoProduto> listInOrderNome() {
        return em.createQuery("SELECT e FROM PrecoProduto e ORDER BY e.nomePrecoProduto").getResultList();
    }

    public List<PrecoProduto> listInOrderId() {
        return em.createQuery("SELECT e FROM PrecoProduto e ORDER BY e.idPrecoProduto").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<PrecoProduto> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getPrecoProdutoPK()+ "-" + lf.get(i).getProduto());
        }
        return ls;
    }


public static void main(String[] args) {
        DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();
        List<PrecoProduto> listaPrecoProduto = daoPrecoProduto.list();
        for (PrecoProduto produto : listaPrecoProduto) {
            System.out.println(produto.getPrecoProdutoPK()+"-"+produto.getProduto());
        }
    }}