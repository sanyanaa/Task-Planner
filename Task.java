import java.time.LocalDate;
import java.io.Serializable;

class Task implements Serializable{

    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private String status;

    public Task(int id, String title, String description, LocalDate dueDate, String priority, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    void details(){
        System.out.println("\n\n\tTaskdetails\n");
        System.out.println("****************");
        System.out.println("ID: "+id);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Due date: " + dueDate);
        System.out.println("Priority: " + priority);
        System.out.println("Status: " + status);
    }
}
