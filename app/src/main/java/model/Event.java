package model;

/**
 * Created by Nipun on 18-Aug-16.
 */
public class Event {
    private int id;
    private int expense_id;
    private String event_name;
    private String event_location;
    private String event_start_date;
    private String event_end_date;
    private String event_budget;
    private String event_expenses;
    private String expense_scope;
    private String expense_amount;


    public Event(int id, String event_name, String event_location, String event_start_date, String event_end_date, String event_budget, String event_expenses) {
        this.id = id;
        this.event_name = event_name;
        this.event_location = event_location;
        this.event_start_date = event_start_date;
        this.event_end_date = event_end_date;
        this.event_budget = event_budget;
        this.event_expenses = event_expenses;
    }

    public Event(String event_name, String event_location, String event_start_date, String event_end_date, String event_budget, String event_expenses) {
        this.event_name = event_name;
        this.event_location = event_location;
        this.event_start_date = event_start_date;
        this.event_end_date = event_end_date;
        this.event_budget = event_budget;
        this.event_expenses = event_expenses;
    }

    public Event(String event_name, String event_location, String event_start_date, String event_end_date, String event_budget) {
        this.event_name = event_name;
        this.event_location = event_location;
        this.event_start_date = event_start_date;
        this.event_end_date = event_end_date;
        this.event_budget = event_budget;
    }



    public String getExpense_scope() {
        return expense_scope;
    }

    public void setExpense_scope(String expense_scope) {
        this.expense_scope = expense_scope;
    }

    public String getExpense_amount() {
        return expense_amount;
    }

    public void setExpense_amount(String expense_amount) {
        this.expense_amount = expense_amount;
    }

    public Event(int id, String expense_scope, String expense_amount) {
        this.id = id;
        this.expense_scope = expense_scope;
        this.expense_amount = expense_amount;
    }

    public Event(String expense_amount) {
        this.expense_amount = expense_amount;
    }

    public Event(int expense_id, String event_name, String expense_scope,String expense_amount) {
        this.expense_amount = expense_amount;
        this.expense_scope = expense_scope;
        this.expense_id = expense_id;
        this.event_name = event_name;
    }

    public Event() {
    }

    public Event(int expense_id) {
        this.expense_id = expense_id;
    }

    public int getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(int expense_id) {
        this.expense_id = expense_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    public String getEvent_start_date() {
        return event_start_date;
    }

    public void setEvent_start_date(String event_start_date) {
        this.event_start_date = event_start_date;
    }

    public String getEvent_end_date() {
        return event_end_date;
    }

    public void setEvent_end_date(String event_end_date) {
        this.event_end_date = event_end_date;
    }

    public String getEvent_budget() {
        return event_budget;
    }

    public void setEvent_budget(String event_budget) {
        this.event_budget = event_budget;
    }

    public String getEvent_expenses() {
        return event_expenses;
    }

    public void setEvent_expenses(String event_expenses) {
        this.event_expenses = event_expenses;
    }

    @Override
    public String toString() {
        return  ", event_name='" + event_name + '\'' +
                ", event_start_date='" + event_start_date + '\'' +
                "event_budget='" + event_budget + '\'' +
                ", event_expenses='" + event_expenses;
    }
}
