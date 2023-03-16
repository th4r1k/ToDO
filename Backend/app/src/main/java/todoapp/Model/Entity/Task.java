package todoapp.Model.Entity;

public class Task {
    private String name;
    private String description;
    private String endDate;
    private String endTime;
    private String priority;
    private String category;
    private String status;

    public Task() {
    }

    public Task(String name, String description, String endDate, String endTime, String priority, String category, String status) {
        this.name = name;
        this.description = description;
        this.endDate = endDate;
        this.endTime = endTime;
        this.priority = priority;
        this.category = category;
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return this.name + "," + this.description + "," + this.endDate + "," + this.endTime + "," + this.priority + "," + this.category + "," + this.status;
    }
}