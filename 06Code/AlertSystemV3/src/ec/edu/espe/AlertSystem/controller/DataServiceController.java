/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.AlertSystem.controller;

/**
 *
 * @author Paulo Ramos
 */
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.AlertSystem.model.Assistant;
import ec.edu.espe.AlertSystem.model.Boss;
import ec.edu.espe.AlertSystem.model.Business;
import ec.edu.espe.AlertSystem.model.Invoice;
import ec.edu.espe.AlertSystem.model.NaturalPerson;
import ec.edu.espe.AlertSystem.model.Task;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataServiceController {

    private final Json<List<Business>> businessJson;
    private final Json<List<NaturalPerson>> naturalJson;
    private final Json<List<Task>> taskJson;
    private final Json<List<Invoice>> invoiceJson;
    private final Json<List<Assistant>> assistantJson;

    private final Json<Boss> bossJson;

    private List<Business> businesses;
    private List<NaturalPerson> naturalPersons;
    private List<Task> tasks;
    private List<Invoice> invoices;
    private List<Assistant> assistants;
    private Boss boss;

    public DataServiceController() {

        businessJson = new Json<>(
                "src/ec/edu/espe/AlertSystem/util/businesses.json",
                new TypeToken<List<Business>>() {
                }.getType()
        );

        naturalJson = new Json<>(
                "src/ec/edu/espe/AlertSystem/util/naturalPersons.json",
                new TypeToken<List<NaturalPerson>>() {
                }.getType()
        );

        taskJson = new Json<>(
                "src/ec/edu/espe/AlertSystem/util/tasks.json",
                new TypeToken<List<Task>>() {
                }.getType()
        );

        invoiceJson = new Json<>(
                "src/ec/edu/espe/AlertSystem/util/invoices.json",
                new TypeToken<List<Invoice>>() {
                }.getType()
        );

        assistantJson = new Json<>(
                "src/ec/edu/espe/AlertSystem/util/assistants.json",
                new TypeToken<List<Assistant>>() {
                }.getType()
        );

        bossJson = new Json<>(
                "src/ec/edu/espe/AlertSystem/util/boss.json",
                Boss.class
        );
        businesses = businessJson.loadData();
        naturalPersons = naturalJson.loadData();
        tasks = taskJson.loadData();
        invoices = invoiceJson.loadData();
        assistants = assistantJson.loadData();
        boss = bossJson.loadData();

        if (assistants == null || assistants.isEmpty()) {
            loadDefaultAssistants();
            assistantJson.saveData(assistants);
        }

        if (boss == null) {
            loadDefaultBoss();
            bossJson.saveData(boss);
        }

    }

    public void addBusiness(Business b) {
        businesses.add(b);
        businessJson.saveData(businesses);
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public Business findBusinessByRuc(String ruc) {
        return businesses.stream()
                .filter(b -> b.getRuc().equalsIgnoreCase(ruc))
                .findFirst()
                .orElse(null);
    }

    public void addNaturalPerson(NaturalPerson p) {
        naturalPersons.add(p);
        naturalJson.saveData(naturalPersons);
    }

    public List<NaturalPerson> getNaturalPersons() {
        return naturalPersons;
    }

    public NaturalPerson findNaturalById(String id) {
        return naturalPersons.stream()
                .filter(p -> p.getIdentification().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public Task findTaskById(int id) {
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean addTask(Task t) {
        int nextId = generateNextId();
        t.setId(nextId);

        tasks.add(t);
        taskJson.saveData(tasks);
        return true;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addInvoice(Invoice inv) {
        invoices.add(inv);
        invoiceJson.saveData(invoices);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void addAssistant(Assistant a) {
        assistants.add(a);
        assistantJson.saveData(assistants);
    }

    public List<Assistant> getAssistants() {
        return assistants;
    }

    public Assistant loginAssistant(String user, String pass) {
        return assistants.stream()
                .filter(a -> a.getUser().equalsIgnoreCase(user)
                && a.getPassword().equals(pass))
                .findFirst()
                .orElse(null);
    }

    private void loadDefaultAssistants() {
        assistants.add(new Assistant(1, "Paulo Ramos", new Date(), "0987654321", "paulo@correo.com", "paulo", "paulo123"));
        assistants.add(new Assistant(2, "Josue Rojas", new Date(), "0991112233", "josue@correo.com", "josue", "josue123"));
        assistants.add(new Assistant(3, "Thais Santorum", new Date(), "0975556677", "thais@correo.com", "thais", "thais123"));
    }

    public Boss loginBoss(String user, String pass) {
        if (boss != null
                && boss.getUser().equalsIgnoreCase(user)
                && boss.getPassword().equals(pass)) {
            return boss;
        }
        return null;
    }

    public Boss getBoss() {
        return boss;
    }

    public void updateBoss(Boss newBoss) {
        this.boss = newBoss;
        bossJson.saveData(boss);
    }

    public void resetBoss() {
        loadDefaultBoss();
        bossJson.saveData(boss);
    }

    private void loadDefaultBoss() {
        boss = new Boss(
                "Sandra Peña",
                new Date(),
                "0999999999",
                "sandra@correo.com",
                "sandrapena",
                "sandra1234"
        );
    }

    public List<Task> loadTasks() {
        return tasks;
    }

    public boolean removeBusinessByRuc(String ruc) {
        Business businessToRemove = findBusinessByRuc(ruc);
        if (businessToRemove != null) {
            businesses.remove(businessToRemove);
            businessJson.saveData(businesses);
            return true;
        }
        return false;
    }

    public boolean removeNaturalById(String id) {
        NaturalPerson personToRemove = findNaturalById(id);
        if (personToRemove != null) {
            naturalPersons.remove(personToRemove);
            naturalJson.saveData(naturalPersons);
            return true;
        }
        return false;
    }

    public boolean removeTaskById(int id) {
        Task taskToRemove = tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            taskJson.saveData(tasks);
            return true;
        }
        return false;
    }

    public boolean removeInvoiceByNumber(int number) {
        Invoice invoiceToRemove = invoices.stream()
                .filter(inv -> inv.getInvoiceNumber() == number)
                .findFirst()
                .orElse(null);

        if (invoiceToRemove != null) {
            invoices.remove(invoiceToRemove);
            invoiceJson.saveData(invoices);
            return true;
        }
        return false;
    }

    public boolean removeAssistantByUser(String user) {
        Assistant assistantToRemove = assistants.stream()
                .filter(a -> a.getUser().equalsIgnoreCase(user))
                .findFirst()
                .orElse(null);
        if (assistantToRemove != null) {
            assistants.remove(assistantToRemove);
            assistantJson.saveData(assistants);
            return true;
        }
        return false;
    }

    public boolean updateBusiness(Business updatedBusiness) {
        Business existing = findBusinessByRuc(updatedBusiness.getRuc());
        if (existing != null) {
            businesses.remove(existing);
            businesses.add(updatedBusiness);
            businessJson.saveData(businesses);
            return true;
        }
        return false;
    }

    public boolean updateNaturalPerson(NaturalPerson updatedPerson) {
        NaturalPerson existing = findNaturalById(updatedPerson.getIdentification());
        if (existing != null) {
            naturalPersons.remove(existing);
            naturalPersons.add(updatedPerson);
            naturalJson.saveData(naturalPersons);
            return true;
        }
        return false;
    }

    public boolean updateTask(Task updatedTask) {
        Task existing = tasks.stream()
                .filter(t -> t.getId() == updatedTask.getId())
                .findFirst()
                .orElse(null);
        if (existing != null) {
            tasks.remove(existing);
            tasks.add(updatedTask);
            taskJson.saveData(tasks);
            return true;
        }
        return false;
    }

    public boolean updateInvoice(Invoice updatedInvoice) {
        Invoice existing = invoices.stream()
                .filter(inv -> inv.getInvoiceNumber() == updatedInvoice.getInvoiceNumber())
                .findFirst()
                .orElse(null);
        if (existing != null) {
            invoices.remove(existing);
            invoices.add(updatedInvoice);
            invoiceJson.saveData(invoices);
            return true;
        }
        return false;
    }

    public boolean updateAssistant(Assistant updatedAssistant) {
        Assistant existing = assistants.stream()
                .filter(a -> a.getUser().equalsIgnoreCase(updatedAssistant.getUser()))
                .findFirst()
                .orElse(null);
        if (existing != null) {
            assistants.remove(existing);
            assistants.add(updatedAssistant);
            assistantJson.saveData(assistants);
            return true;
        }
        return false;
    }

    public int generateNextId() {
        List<Task> tasks = loadTasks();
        if (tasks.isEmpty()) {
            return 1;
        }

        return tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;
    }

}
