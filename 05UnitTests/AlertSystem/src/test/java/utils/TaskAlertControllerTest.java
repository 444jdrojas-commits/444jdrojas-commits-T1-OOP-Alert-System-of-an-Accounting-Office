package utils;

import ec.edu.espe.AlertSystem.controller.TaskAlertController;
import ec.edu.espe.AlertSystem.controller.Validation;
import ec.edu.espe.AlertSystem.model.Task;
import java.util.Date;
import java.util.Calendar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskAlertControllerTest {

    public TaskAlertControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testDaysRemaining1() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays2() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining3() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 5);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea 1", creationDate, deliveryDate, "Pendiente", "C001", null, null, "user");
        Date today = new Date();

        TaskAlertController instance = new TaskAlertController();
        long expResult = 5L;
        long result = instance.daysRemaining(task, today);

        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays4() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining5() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays6() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining7() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays8() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining9() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays10() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDay11() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining12() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date today = Validation.truncateToDate(new Date());

        cal.setTime(today);
        cal.add(Calendar.DAY_OF_YEAR, -2);
        Date deliveryDate = cal.getTime();

        Task task = new Task(2, "Tarea", today, deliveryDate,
                "Pendiente", "C001", null, null, "usuario1");

        TaskAlertController instance = new TaskAlertController();
        long expResult = -2L;
        long result = instance.daysRemaining(task, today);

        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays13() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining14() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date today = Validation.truncateToDate(new Date());

        cal.setTime(today);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date deliveryDate = cal.getTime();

        Task task = new Task(4, "Tarea", today, deliveryDate,
                "Pendiente", "C001", null, null, "usuario1");

        TaskAlertController instance = new TaskAlertController();
        long expResult = 1L;
        long result = instance.daysRemaining(task, today);

        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays15() {
        System.out.println("statusAccordingDays");

        long diffDays = -2L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "VENCIDA";
        String result = instance.statusAccordingDays(diffDays);

        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining16() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays17() {
        TaskAlertController instance = new TaskAlertController();
        long diffDays = -5L;

        String expResult = "   VENCIDA hace 5 dia(s)";
        String result = instance.statusAccordingDays(diffDays);

        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining18() {
        System.out.println("daysRemaining");

        Date today = Validation.truncateToDate(new Date());

        Task task = new Task(3, "Tarea", today, today,
                "Pendiente", "C001", null, null, "usuario1");

        TaskAlertController instance = new TaskAlertController();
        long expResult = 0L;
        long result = instance.daysRemaining(task, today);

        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays19() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining20() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays21() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining22() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays23() {
        TaskAlertController instance = new TaskAlertController();
        long diffDays = 0L;

        String expResult = "   Es para HOY";
        String result = instance.statusAccordingDays(diffDays);

        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining24() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays25() {
        TaskAlertController instance = new TaskAlertController();
        long diffDays = 2L;

        String expResult = "   Proxima a vencer en 2 dia(s)";
        String result = instance.statusAccordingDays(diffDays);

        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining26() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays27() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining28() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays29() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining30() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays31() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test

    public void testDaysRemaining32() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 5);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea 1", creationDate, deliveryDate, "Pendiente", "C001", null, null, "user");
        Date today = new Date();

        TaskAlertController instance = new TaskAlertController();
        long expResult = 5L;
        long result = instance.daysRemaining(task, today);

        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays33() {
        System.out.println("statusAccordingDays");

        long diffDays = 8L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);

        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining34() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays35() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining37() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 10);
        Date deliveryDate = cal.getTime();

        Task task = new Task(2, "Tarea 2", creationDate, deliveryDate, "Pendiente", "C002", null, null, "user");
        Date today = new Date();

        TaskAlertController instance = new TaskAlertController();
        long expResult = 10L;
        long result = instance.daysRemaining(task, today);

        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays38() {
        System.out.println("statusAccordingDays");

        long diffDays = 1L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "URGENTE";
        String result = instance.statusAccordingDays(diffDays);

        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining39() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays40() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining41() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays42() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining42() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays43() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining44() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays45() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining46() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays47() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testDaysRemaining48() {
        System.out.println("daysRemaining");

        Calendar cal = Calendar.getInstance();
        Date creationDate = new Date();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date deliveryDate = cal.getTime();

        Task task = new Task(1, "Tarea de prueba", creationDate, deliveryDate, "Pendiente", "C001", null, null, "usuario1");
        Date today = new Date();
        TaskAlertController instance = new TaskAlertController();
        long expResult = 7L;
        long result = instance.daysRemaining(task, today);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays49() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

    @Test
    public void testStatusAccordingDays50() {
        System.out.println("statusAccordingDays");

        long diffDays = 3L;
        TaskAlertController instance = new TaskAlertController();
        String expResult = "A TIEMPO";
        String result = instance.statusAccordingDays(diffDays);
        assertEquals(expResult, result);
    }

}
