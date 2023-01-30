package src.Entity;

public class Todo {
    String name;
    String description;
    String endDate;
    String endTime;
    String priority;
    String category;
    String status;

    public Todo() {
    }

    public Todo(String name, String description, String endDate, String endTime, String priority, String category, String status) {
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
        this.endDate = endTime;
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
        // return "Todo{name='" + this.name + "', description='" + this.description + "', endDate='" + this.endDate + "', priority=" + this.priority + ", category='" + this.category + "', status=" + this.status + "}";
        return this.name + "," + this.description + ", " + this.endDate + "," + this.priority + ",'" + this.category + "'," + this.status;
    }
}