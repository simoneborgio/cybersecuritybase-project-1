package sec.project.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sec.project.domain.Signup;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

public class SignupRepositoryImpl implements SignupRepositoryCustom {

    private static Logger log = LoggerFactory.getLogger(SignupRepositoryImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Integer insecureSave(Signup signup) {

        StringBuilder sql = new StringBuilder("INSERT INTO Signup (name, address) VALUES ('");
        sql.append(signup.getName());
        sql.append("', '");
        sql.append(signup.getAddress());
        sql.append("')");

        log.info("Executing native query: {}", sql.toString());

        Query nativeQuery = em.createNativeQuery(sql.toString());

        return nativeQuery.executeUpdate();
    }
}
