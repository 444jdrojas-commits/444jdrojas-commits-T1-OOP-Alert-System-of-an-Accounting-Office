package ec.edu.espe.alertsystem.view;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paulo Ramos
 */
public class FrmManageTaskTest {
    @Test
    public void testInstanceNotNull() {
        FrmManageTask frm = new FrmManageTask();
        assertNotNull(frm);
    }

    @Test
    public void testTableModelColumnCount() throws NoSuchFieldException, IllegalAccessException {
        FrmManageTask frm = new FrmManageTask();
        Field field = FrmManageTask.class.getDeclaredField("tblTask");
        field.setAccessible(true);
        JTable table = (JTable) field.get(frm);
        assertEquals(4, table.getModel().getColumnCount());
    }

    @Test
    public void testColumnNameId() throws NoSuchFieldException, IllegalAccessException {
        FrmManageTask frm = new FrmManageTask();
        Field field = FrmManageTask.class.getDeclaredField("tblTask");
        field.setAccessible(true);
        JTable table = (JTable) field.get(frm);
        assertEquals("ID", table.getModel().getColumnName(0));
    }

    @Test
    public void testColumnDescription() throws NoSuchFieldException, IllegalAccessException {
        FrmManageTask frm = new FrmManageTask();
        Field field = FrmManageTask.class.getDeclaredField("tblTask");
        field.setAccessible(true);
        JTable table = (JTable) field.get(frm);
        assertEquals("Descripcion", table.getModel().getColumnName(1));
    }

    @Test
    public void testColumnNameClient() throws NoSuchFieldException, IllegalAccessException {
        FrmManageTask frm = new FrmManageTask();
        Field field = FrmManageTask.class.getDeclaredField("tblTask");
        field.setAccessible(true);
        JTable table = (JTable) field.get(frm);
        assertEquals("Cliente", table.getModel().getColumnName(2));
    }

    @Test
    public void testColumnNameState() throws NoSuchFieldException, IllegalAccessException {
        FrmManageTask frm = new FrmManageTask();
        Field field = FrmManageTask.class.getDeclaredField("tblTask");
        field.setAccessible(true);
        JTable table = (JTable) field.get(frm);
        assertEquals("Estado", table.getModel().getColumnName(3));
    }

    
}
