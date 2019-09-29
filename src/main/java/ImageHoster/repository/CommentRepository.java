package ImageHoster.repository;
import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
//Comment repository Class
@Repository//The annotation is a special type of @Component annotation which describes that the class defines a data repository
public class CommentRepository {

    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf; //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    //The method receives the Image object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public Comment insertComment(Comment comment)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.persist(comment);
            transaction.commit();
        }
        catch (Exception e)
        {
            transaction.rollback();
        }
        return comment;
    }
}
