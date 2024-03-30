import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TaskListImpl extends UnicastRemoteObject implements TaskList {
    private List<String> taskList;

    public TaskListImpl() throws RemoteException {
        super();
        taskList = new ArrayList<>();
    }

    @Override
    public void addTask(String task) throws RemoteException {
        taskList.add(task);
        System.out.println("Task added: " + task);
    }

    @Override
    public void removeTask(String task) throws RemoteException {
        if (taskList.contains(task)) {
            taskList.remove(task);
            System.out.println("Task removed: " + task);
        } else {
            System.out.println("Task not found: " + task);
        }
    }

    @Override
    public List<String> getAllTasks() throws RemoteException {
        return new ArrayList<>(taskList);
    }
}
