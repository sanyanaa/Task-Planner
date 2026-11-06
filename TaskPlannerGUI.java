import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Component;

public class TaskPlannerGUI {
    public static void main(String[] args) {
        
    JFrame window = new JFrame("Task planner");
    window.setSize(500,500);
    window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);

    JLabel welcome = new JLabel("Welcome to task planner!");

    JButton addTask = new JButton("Add task");
    JButton showAll = new JButton("Show all tasks");
    JButton taskDetails = new JButton("Show task details");
    JButton updateTask = new JButton("Update task");
    JButton removeTask = new JButton("Remove task");
    JButton markCompl = new JButton("Mark as completed");
    JButton searchTitle= new JButton("Search task by title");
    JButton showStatus = new JButton("Show tasks by status");
    JButton showOverdue = new JButton("Show overdue tasks");
    JButton sortDueDate = new JButton("Sort tasks by due date");
    JButton sortPriority = new JButton("Sort tasks by priority");
    JButton taskStatistic= new JButton("Show statistics");
    JPanel buttons = new JPanel();
    buttons.add(addTask);
    buttons.add(showAll);
    buttons.add(updateTask);
    buttons.add(removeTask);
    buttons.add(markCompl);
    buttons.add(searchTitle);
    buttons.add(showStatus);
    buttons.add(showOverdue);
    buttons.add(sortDueDate);
    buttons.add(sortPriority);
    buttons.add(taskStatistic);
    buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));    
   

    window.add(buttons);


    window.setVisible(true);
    }
}