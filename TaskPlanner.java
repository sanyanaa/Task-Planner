import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
//all of the imports below are for de-/serealization
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;

public class TaskPlanner {
    static ArrayList<Task> tasks = new ArrayList<Task>();
    static Scanner scn = new Scanner(System.in);
    static int nextID = 1;
    static final String FILE_NAME = "Tasks.dat"; // final потому что константа

    public static void main(String[] args) {
        loadTasksFromFile();
        // Scanner scn = new Scanner(System.in);
        boolean running = true;
        int choice = -1;

        while (running) {
            printMenu();
            System.out.println("Choose an option from 1 to 12 or 0...");
            choice = readMenuChoice();
            
            if (choice == 0) {
                running = false;
                System.out.println("See you next time!");
                saveTasksToFile();
            } else if (choice == 1) {
                addTask();

            } else if (choice == 2) {
                showAllTasks();

            } else if (choice == 3) {
                showTaskDetails();

            } else if (choice == 4) {
                updateTask();

            } else if (choice == 5) {
                removeTask();

            } else if (choice == 6) {
                markAsCompl();

            } else if (choice == 7) {
                searchByTitle();

            } else if (choice == 8) {
                showByStatus();
            } else if (choice == 9) {
                showOverdue();
            } else if (choice == 10) {
                sortByDueDate();

            } else if (choice == 11) {
                sortByPriority();

            } else if (choice == 12) {
                showStatistics();
            }
            printMenu();

        }
    }

    public static void printMenu() {
        System.out.println("==============================\r\n" + //
                "        TASK PLANNER\r\n" + //
                "==============================\r\n" + //
                "1. Add task\r\n" + //
                "2. Show all tasks\r\n" + //
                "3. Show task details\r\n" + //
                "4. Update task\r\n" + //
                "5. Remove task\r\n" + //
                "6. Mark task as completed\r\n" + //
                "7. Search tasks by title\r\n" + //
                "8. Show tasks by status\r\n" + //
                "9. Show overdue tasks\r\n" + //
                "10. Sort tasks by due date\r\n" + //
                "11. Sort tasks by priority\r\n" + //
                "12. Show task statistics\r\n" + //
                "0. Exit\r\n" + //
                "==============================\r\n" + //
                "Choose an option:");

    }

    public static void addTask() {
        scn.nextLine();// костыль
        System.out.println("Enter your task:\nTitle of your task:");// не читать строки после \n
        String title = scn.nextLine();
        System.out.println("Description:");
        String desc = scn.nextLine();
        System.out.println("Priority:");
        String prio = scn.nextLine();
        System.out.println("Status:");
        String stat = scn.nextLine();
        System.out.println("Enter due date(yyyy-MM-dd):");
        LocalDate dueDate = readDate();
        Task task = new Task(nextID, title, desc, dueDate, prio, stat);
        nextID++;
        tasks.add(task);
        System.out.println("The task was added successfully");
    }

    public static LocalDate readDate() {
        String input = scn.nextLine().trim();
        return LocalDate.parse(input);
    }

    public static void showAllTasks() {
        System.out.println("Here are all of your tasks:");
        if (tasks.size() != 0) {
            for (int i = 0; i < tasks.size(); i++) {

                tasks.get(i).details();

            }
        }
    }

    public static void showTaskDetails() {
        boolean x = false;
        System.err.println("Enter task ID:");
        int id = scn.nextInt();
        for (int i = 0; i < tasks.size(); i++) {
            if (id == tasks.get(i).getId()) {
                System.out.println("Task with ID was found:");
                tasks.get(i).details();
                x = true;
            }

        }
        if (x == false) {
            System.out.println("Task was not found");
        }

    }

    public static void sortByDueDate() {
        if (tasks.size() != 0) {
            for (int i = 0; i < tasks.size(); i++) {
                for (int j = 0; j < tasks.size(); j++) {// два цикла для сравнения
                    Task leftTask = tasks.get(i);
                    Task rightTask = tasks.get(j);
                    if (leftTask.getDueDate().isAfter(rightTask.getDueDate())) {
                        Task temp = tasks.get(i);
                        tasks.set(i, tasks.get(j));
                        tasks.set(j, temp);
                    }
                }
            }
        }

        else {
            System.out.println("No tasks yet");
        }
    }

    public static void searchByTitle() {
        if (tasks.size() != 0) {
            System.out.println("Task title...");
            String taskTitle = scn.nextLine();
            for (int i = 0; i < tasks.size(); i++) {
                if (taskTitle.equals(tasks.get(i).getTitle())) {
                    tasks.get(i).details();
                }
            }
        } else {
            System.out.println("No tasks yet");
        }
    }

    public static void updateTask() {
        if (tasks.size() != 0) {
            System.out.println("Task ID...");
            int id = scn.nextInt();
            for (int i = 0; i < tasks.size(); i++) {
                if (id == tasks.get(i).getId()) {
                    System.out.println(
                            "What do you want to update?\n1.Title\n2.Description\n3.Priority\n4.Status\n5.Due date\n0.Cancel");
                    int option = scn.nextInt();
                    if (option == 0) {
                        return;
                    }
                    if (option == 1) {
                        System.out.println("Enter new title...");
                        String newTitle = scn.nextLine();
                        tasks.get(i).setTitle(newTitle);
                    }
                    if (option == 2) {
                        System.out.println("Enter new despcription...");
                        String newDesc = scn.nextLine();
                        tasks.get(i).setDescription(newDesc);
                    }
                    if (option == 3) {
                        System.out.println("What is your new priority?");
                        String newPrio = scn.nextLine();
                        tasks.get(i).setPriority(newPrio);
                    }
                    if (option == 4) {
                        System.out.println("What is the new status?");
                        String newStat = scn.nextLine();
                        tasks.get(i).setStatus(newStat);
                    }
                    if (option == 5) {
                        System.out.println("What is the new deadline?(yyyy-MM-dd)");
                        LocalDate newDueDate = readDate();
                        tasks.get(i).setDueDate(newDueDate);
                    }
                }
            }
        }

    }

    public static void removeTask() {
        if (tasks.size() != 0) {
            System.out.println("Enter the ID of the task you want to remove");
            int id = scn.nextInt();
            for (int i = 0; i < tasks.size(); i++) {
                if (id == tasks.get(i).getId()) {
                    tasks.remove(i);
                    System.out.println("The task was successfully removed");
                }
            }
        }

    }

    public static void markAsCompl() {
        if (tasks.size() != 0) {
            System.out.println("the ID of the task...");
            int id = scn.nextInt();
            for (int i = 0; i < tasks.size(); i++) {
                if (id == tasks.get(i).getId()) {
                    tasks.get(i).setStatus("completed");
                    System.out.println("The status was updated");
                }
            }
        }
    }

    public static void showStatistics() {
        if (tasks.size() != 0) {
            System.out.println("Your total amount of tasks: " + tasks.size());// total amount of tasks

            int counter1 = 0;// tasks amount by status counter1 for TODOs, counter 2 for IN_PROGRESS and c3
                             // for DONE
            int counter2 = 0;
            int counter3 = 0;
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getStatus().equals("TODO")) {
                    counter1++;// should i also show the task here?
                } else if (tasks.get(i).getStatus().equals("IN_PROGRESS")) {
                    counter2++;
                } else if (tasks.get(i).getStatus().equals("DONE")) {
                    counter3++;
                }
            }
            System.out.printf(
                    "Amount of TODO tasks: %d\nAmount of IN_PROGRESS tasks: %d\nAmount of DONE tasks %d\n",
                    counter1, counter2, counter3);

            LocalDate today = LocalDate.now();// number of overdue tasks
            int counter4 = 0;
            for (int i = 0; i < tasks.size(); i++) {

                if (isOverdue(today, tasks.get(i))) {
                    counter4++;
                }

            }
            System.out.printf("The number of overdue tasks: %d", counter4);

            int counter5 = 0;// number of high priority tasks
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getPriority().equals("HIGH")) {
                    counter5++;
                }

            }
            System.out.printf("The number of high priority tasks: %d", counter5);
        }
    }

    public static boolean isOverdue(LocalDate today, Task taskk) {
        /*
         * return taskk.getDueDate().isBefore(today)&&
         * taskk.getStatus().equals("TODO")||
         * taskk.getStatus().equals("IN_PROGRESS");
         */
        if (taskk.getDueDate().isBefore(today) &&
                (taskk.getStatus().equals("TODO") ||
                        taskk.getStatus().equals("IN_PROGRESS"))) {
            return true;
        } else {
            return false;
        }

    }

    public static void showByStatus() {

        if (tasks.size() != 0) {
            boolean y = false;
            System.out.println("Enter status...");
            String stat = scn.nextLine();
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getStatus().equals(stat)) {
                    tasks.get(i).details();
                    y = true;
                }

            }
            if (y == false) {
                System.out.println("You don`t have any tasks with this status");
            }
        }
    }

    public static void showOverdue() {
        LocalDate today = LocalDate.now();
        if (tasks.size() != 0) {
            for (int i = 0; i < tasks.size(); i++) {
                if (isOverdue(today, tasks.get(i))) {
                    tasks.get(i).details();
                }
            }
        }
    }

    public static void sortByPriority() {

        if (tasks.size() != 0) {
            System.out.println("Would you like to sort your tasks by priority descending(1) or ascending(2)");
            int answer = scn.nextInt();
            if (answer == 1) {
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i).getPriority().equals("HIGH")) {
                        tasks.get(i).details();
                    } else if (tasks.get(i).getPriority().equals("MEDIUM")) {
                        tasks.get(i).details();
                    } else if (tasks.get(i).getPriority().equals("LOW")) {
                        tasks.get(i).details();
                    }
                }
            } else if (answer == 2) {
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i).getPriority().equals("LOW")) {
                        tasks.get(i).details();
                    } else if (tasks.get(i).getPriority().equals("MEDIUM")) {
                        tasks.get(i).details();
                    } else if (tasks.get(i).getPriority().equals("HIGH")) {
                        tasks.get(i).details();
                    }
                }

            } else {
                System.out.println("Wrong input!");
            }
        }
    }

    public static void saveTasksToFile() { // сереализация
        try {

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));

            objectOutputStream.writeObject(tasks);
            System.out.println("Save file success! " + FILE_NAME);
            objectOutputStream.close();

        } catch (Exception e) {
            System.out.println("Couldn`t save tasks to file");

        }

    }

    public static void loadTasksFromFile() { // десереализация
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        } else {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME));
                tasks = (ArrayList<Task>) inputStream.readObject();
                inputStream.close();
                System.out.println("Tasks were loaded from file succesfully");
            } catch (Exception e) {
                System.out.println("Couldn`t load tasks from file");

            }
        }
    }

    public static int readMenuChoice() {
        while (true/* or js 1 */) {
            String input = scn.nextLine();
            try {

                int choice = Integer.parseInt(input);
                if (choice >= 0 && choice <= 12) {
                    return choice;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a whole number:");
            }
        }
    }
}
