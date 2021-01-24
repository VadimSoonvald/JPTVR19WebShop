package session;

import entity.Buyer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BuyerFacade extends AbstractFacade<Buyer> {

    @PersistenceContext(unitName = "JPTVR19WebShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BuyerFacade() {
        super(Buyer.class);
    }

}
