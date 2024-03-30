import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            TaskList taskList = (TaskList) Naming.lookup("//localhost/TaskListService");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Add a task");
                System.out.println("2. Remove a task");
                System.out.println("3. Get all tasks");
                System.out.println("4. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Enter task to add:");
                        String taskToAdd = scanner.nextLine();
                        taskList.addTask(taskToAdd);
                        break;
                    case 2:
                        System.out.println("Enter task to remove:");
                        String taskToRemove = scanner.nextLine();
                        taskList.removeTask(taskToRemove);
                        break;
                    case 3:
                        List<String> tasks = taskList.getAllTasks();
                        System.out.println("All tasks: " + tasks);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        } catch (Exception e) {
            System.err.println("taskList client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
