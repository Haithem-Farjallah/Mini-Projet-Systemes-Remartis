
public class Server  {
    
    public static void main(String[] args) {
        try {
            // Crée un registre RMI sur le port 1099
            java.rmi.registry.LocateRegistry.createRegistry(1099); 
            TaskList taskList = new TaskListImpl();
            // Lie l'objet distant au registre
           java.rmi.Naming.rebind("//localhost/TaskListService", taskList); 
            System.out.println("RMI server is listening.");
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}
