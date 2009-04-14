import Authentification.Session; 
import Authentification.Utilisateur; 

public interface SessionDAO {

    public void insert (Session s);

    public void update (Session s);

    public void delete (Session s);

    public Session findByUser (Utilisateur u);

}

