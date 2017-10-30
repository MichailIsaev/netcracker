package sourcebean;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

public interface DataSourceHome extends EJBHome {
    DataSource create() throws CreateException, RemoteException;
}
