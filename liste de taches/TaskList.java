import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TaskList extends Remote {
    void addTask(String task) throws RemoteException;
    void removeTask(String task) throws RemoteException;
    List<String> getAllTasks() throws RemoteException;
}
