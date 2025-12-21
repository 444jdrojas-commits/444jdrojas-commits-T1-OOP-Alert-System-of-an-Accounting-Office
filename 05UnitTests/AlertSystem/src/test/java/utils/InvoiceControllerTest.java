package utils;

import ec.edu.espe.AlertSystem.controller.InvoiceController;
import ec.edu.espe.AlertSystem.model.Invoice;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class InvoiceControllerTest {

    public InvoiceControllerTest() {
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
    void testGroupTaskCustomer() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente"),
                new Invoice(4, new Date(), 300.0F, "Cliente: Pedro", "Pagada")
        );
        InvoiceController controller = new InvoiceController();
        Map<String, List<Invoice>> result = controller.groupTaskCustomer(invoices);
        assertEquals(1, result.get("Juan").size());
        assertEquals(2, result.get("Maria").size());
        assertFalse(result.containsKey("Pedro"));
    }

    @Test
    void testTotalOutstanding2() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(24.50F, total, 0);
    }

    @Test
    void testDebtCustomer3() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(348.30F, deuda, 0);
    }

    @Test
    void testTotalOutstanding4() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Marco", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Pedro", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(249.7F, total, 0);
    }

    @Test
    void testDebtCustomer5() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 200.0F, "Cliente: Manuel", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Manuel", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(42.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding6() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(245.0F, total, 0);
    }

    @Test
    void testDebtCustomer7() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(303.36F, deuda, 0);
    }

    @Test
    void testTotalOutstanding8() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(20.0F, total, 0);
    }

    @Test
    void testDebtCustomer9() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(38.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding10() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(295.0F, total, 0);
    }

    @Test
    void testDebtCustomer11() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(351.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding12() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(27.0F, total, 0);
    }

    @Test
    void testDebtCustomer13() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(30.95F, deuda, 0);
    }

    @Test
    void testTotalOutstanding14() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(248.60F, total, 0);
    }

    @Test
    void testDebtCustomer15() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(95.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding16() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(22.75F, total, 0);
    }

    @Test
    void testDebtCustomer17() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(35.64F, deuda, 0);
    }

    @Test
    void testTotalOutstanding18() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(250.0F, total, 0);
    }

    @Test
    void testDebtCustomer19() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(341.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding20() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(244.0F, total, 0);
    }

    @Test
    void testDebtCustomer21() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(365.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding22() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(24.0F, total, 0);
    }

    @Test
    void testDebtCustomer23() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(78.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding24() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(44.0F, total, 0);
    }

    @Test
    void testDebtCustomer25() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(31.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding26() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(240.0F, total, 0);
    }

    @Test
    void testDebtCustomer27() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(300.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding28() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(250.0F, total, 0);
    }

    @Test
    void testDebtCustomer29() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 105.5F, "Cliente: Daniela", "Pendiente"),
                new Invoice(2, new Date(), 210.0F, "Cliente: Daniela", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(315.5F, deuda, 0);
    }

    @Test
    void testTotalOutstanding30() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(240.0F, total, 0);
    }

    @Test
    void testDebtCustomer31() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(301.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding32() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(240.0F, total, 0);
    }

    @Test
    void testDebtCustomer33() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(301.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding34() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(240.0F, total, 0);
    }

    @Test
    void testDebtCustomer35() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(301.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding36() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(240.0F, total, 0);
    }

    @Test
    void testDebtCustomer37() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(301.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding38() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(240.0F, total, 0);
    }

    @Test
    void testDebtCustomer39() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(301.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding40() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(240.0F, total, 0);
    }

    @Test
    void testDebtCustomer41() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(301.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding42() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(240.0F, total, 0);
    }

    @Test
    void testDebtCustomer43() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(301.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding44() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(240.0F, total, 0);
    }

    @Test
    void testDebtCustomer145() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(301.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding46() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(240.0F, total, 0);
    }

    @Test
    void testDebtCustomer47() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(301.0F, deuda, 0);
    }

    @Test
    void testTotalOutstanding48() {
        List<Invoice> invoices = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Juan", "Pendiente"),
                new Invoice(2, new Date(), 200.0F, "Cliente: Juan", "Pagada"),
                new Invoice(3, new Date(), 150.0F, "Cliente: Maria", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double total = controller.totalOutstanding(invoices);
        assertEquals(240.11F, total, 0);
    }

    @Test
    void testDebtCustomer49() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.45F, "Cliente: Pedro", "Pendiente"),
                new Invoice(2, new Date(), 210.0F, "Cliente: Pedro", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(33.45F, deuda, 0);
    }

    @Test
    void testDebtCustomer50() {
        List<Invoice> facturasPendientes = List.of(
                new Invoice(1, new Date(), 100.0F, "Cliente: Mario", "Pendiente"),
                new Invoice(2, new Date(), 205.36F, "Cliente: Mario", "Pendiente")
        );
        InvoiceController controller = new InvoiceController();
        double deuda = controller.debtCustomer(facturasPendientes);
        assertEquals(301.0F, deuda, 0);
    }
}
