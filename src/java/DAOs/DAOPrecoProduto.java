package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.PrecoProduto;
import Entidades.PrecoProdutoPK;
import Entidades.Produto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DAOPrecoProduto extends DAOGenerico<PrecoProduto> {

    private List<PrecoProduto> lista = new ArrayList<>();
    Produto produto = new Produto();
    DAOProduto daoProduto = new DAOProduto();

    public DAOPrecoProduto() {
        super(PrecoProduto.class);
    }
    

    public PrecoProduto obter(PrecoProdutoPK precoProdutoPK) {
        return em.find(PrecoProduto.class, precoProdutoPK);
    }

    public List<PrecoProduto> listByNome(String nome) {
        return em.createQuery("SELECT e FROM PrecoProduto e WHERE e.produto.nomeProduto LIKE nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<PrecoProduto> listById(int id) {
        return em.createQuery("SELECT e FROM PrecoProduto e WHERE e.produto.idProduto =id").setParameter("id", id).getResultList();
    }

    public List<PrecoProduto> listInOrderNome() {
        return em.createQuery("SELECT e FROM PrecoProduto e ORDER BY e.produto").getResultList();
    }

    public List<PrecoProduto> listInOrderId() {
        return em.createQuery("SELECT e FROM PrecoProduto e ORDER BY e.precoProdutoPK.produtoIdProduto").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<PrecoProduto> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getPrecoProdutoPK().getProdutoIdProduto() + "-"
                    + daoProduto.obter(lf.get(i).getPrecoProdutoPK().getProdutoIdProduto()).getNomeProduto()+ "-"
                    + sdf.format(lf.get(i).getPrecoProdutoPK().getDataPrecoProduto())
                    + "-" + lf.get(i).getPreco());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();
        List<PrecoProduto> listaPrecoProduto = daoPrecoProduto.list();
        for (PrecoProduto precoProduto : listaPrecoProduto) {
            System.out.println(precoProduto.getPreco() + "-" + precoProduto.getProduto());
        }
    }
}
