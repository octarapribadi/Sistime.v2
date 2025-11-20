package repo;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class SkemaNilaiManager{
    @PersistenceContext
    EntityManager em;

}
