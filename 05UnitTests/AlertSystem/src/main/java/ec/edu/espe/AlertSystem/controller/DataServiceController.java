package ec.edu.espe.AlertSystem.controller;

/**
 *
 * @author Paulo Ramos
 */
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.AlertSystem.model.Assistant;
import ec.edu.espe.AlertSystem.model.Boss;
import ec.edu.espe.AlertSystem.model.Business;
import ec.edu.espe.AlertSystem.model.Customer;
import ec.edu.espe.AlertSystem.model.Invoice;
import ec.edu.espe.AlertSystem.model.Task;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import ec.edu.espe.AlertSystem.model.NaturalPerson;
import java.util.function.Predicate;

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
                "businesses.json",
                new TypeToken<List<Business>>() {
                }.getType()
        );

        naturalJson = new Json<>(
                "naturalPersons.json",
                new TypeToken<List<NaturalPerson>>() {
                }.getType()
        );

        taskJson = new Json<>(
                "tasks.json",
                new TypeToken<List<Task>>() {
                }.getType()
        );

        invoiceJson = new Json<>(
                "invoices.json",
                new TypeToken<List<Invoice>>() {
                }.getType()
        );

        assistantJson = new Json<>(
                "assistants.json",
                new TypeToken<List<Assistant>>() {
                }.getType()
        );

        bossJson = new Json<>(
                "boss.json",
                Boss.class
        );
        businesses = Optional.ofNullable(businessJson.loadData()).orElse(new ArrayList<>());
        naturalPersons = Optional.ofNullable(naturalJson.loadData()).orElse(new ArrayList<>());
        tasks = Optional.ofNullable(taskJson.loadData()).orElse(new ArrayList<>());
        for (Task task : tasks) {
            if (task.getCustomerId() != null) {
                Customer c = findCustomerByName(task.getCustomerId());
                task.setCustomer(c);
            }
        }

        invoices = Optional.ofNullable(invoiceJson.loadData()).orElse(new ArrayList<>());
        assistants = Optional.ofNullable(assistantJson.loadData()).orElse(new ArrayList<>());
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

    public <T> T findByPredicate(List<T> list, Predicate<T> condition) {
        return list.stream()
                .filter(condition)
                .findFirst()
                .orElse(null);
    }

    public Business findBusinessByRuc(String ruc) {
        return findByPredicate(businesses, b -> b.getRuc().equalsIgnoreCase(ruc));
    }

    public NaturalPerson findNaturalById(String id) {
        return findByPredicate(naturalPersons, p -> p.getIdentification().equalsIgnoreCase(id));
    }

    public Task findTaskById(int id) {
        return findByPredicate(tasks, t -> t.getId() == id);
    }

    public Customer findCustomerByName(String name) {
        String nn = name == null ? "" : name.trim().toLowerCase();

        for (Business b : businesses) {
            if (b.getName() != null && b.getName().trim().toLowerCase().equals(nn)) {
                return b;
            }
        }

        for (NaturalPerson p : naturalPersons) {
            if (p.getName() != null && p.getName().trim().toLowerCase().equals(nn)) {
                return p;
            }
        }

        return null;
    }

    public void addBusiness(Business b) {
        businesses.add(b);
        businessJson.saveData(businesses);
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void addNaturalPerson(NaturalPerson p) {
        naturalPersons.add(p);
        naturalJson.saveData(naturalPersons);
    }

    public List<NaturalPerson> getNaturalPersons() {
        return naturalPersons;
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

    public List<Task> loadTasks() {
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
        return findByPredicate(assistants,
                a -> a.getUser().equalsIgnoreCase(user) && a.getPassword().equals(pass));
    }

    private void loadDefaultAssistants() {
        assistants.add(new Assistant(1, "Paulo Ramos", new Date(), "0987654321", "paulo@correo.com", "paulo", "paulo123"));
        assistants.add(new Assistant(2, "Josue Rojas", new Date(), "0991112233", "josue@correo.com", "josue", "josue123"));
        assistants.add(new Assistant(3, "Thais Santorum", new Date(), "0975556677", "thais@correo.com", "thais", "thais123"));
    }

    public Boss loginBoss(String user, String pass) {
        if (boss != null && boss.getUser().equalsIgnoreCase(user) && boss.getPassword().equals(pass)) {
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
        boss = new Boss("Sandra Peña", new Date(), "0999999999", "sandra@correo.com", "sandrapena", "sandra1234");
    }

    public int generateNextId() {
        if (tasks.isEmpty()) {
            return 1;
        }
        return tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;
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
        Task taskToRemove = findTaskById(id);
        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            taskJson.saveData(tasks);
            return true;
        }
        return false;
    }

    public boolean removeInvoiceByNumber(int number) {
        Invoice invoiceToRemove = findByPredicate(invoices, inv -> inv.getInvoiceNumber() == number);
        if (invoiceToRemove != null) {
            invoices.remove(invoiceToRemove);
            invoiceJson.saveData(invoices);
            return true;
        }
        return false;
    }

    public boolean removeAssistantByUser(String user) {
        Assistant assistantToRemove = findByPredicate(assistants, a -> a.getUser().equalsIgnoreCase(user));
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
        Task existing = findTaskById(updatedTask.getId());
        if (existing != null) {
            tasks.remove(existing);
            tasks.add(updatedTask);
            taskJson.saveData(tasks);
            return true;
        }
        return false;
    }

    public boolean updateInvoice(Invoice updatedInvoice) {
        Invoice existing = findByPredicate(invoices, inv -> inv.getInvoiceNumber() == updatedInvoice.getInvoiceNumber());
        if (existing != null) {
            invoices.remove(existing);
            invoices.add(updatedInvoice);
            invoiceJson.saveData(invoices);
            return true;
        }
        return false;
    }

    public boolean updateAssistant(Assistant updatedAssistant) {
        Assistant existing = findByPredicate(assistants, a -> a.getUser().equalsIgnoreCase(updatedAssistant.getUser()));
        if (existing != null) {
            assistants.remove(existing);
            assistants.add(updatedAssistant);
            assistantJson.saveData(assistants);
            return true;
        }
        return false;
    }

    public String findAssistantNameById(String id) {
        Assistant assistant = assistants.stream()
                .filter(a -> String.valueOf(a.getId()).equals(id))
                .findFirst()
                .orElse(null);

        return (assistant != null) ? assistant.getName() : "No asignado";
    }

}
