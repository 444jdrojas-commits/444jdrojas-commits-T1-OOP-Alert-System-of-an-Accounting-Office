package ec.edu.espe.alertsystem.view;

import static com.mongodb.assertions.Assertions.assertNotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;
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
public class FrmAddBussinesTest {
    @Test
    public void testInstanceNotNull() {
        FrmAddBussines frm = new FrmAddBussines();
        assertNotNull(frm);
    }

    @Test
    public void testDateParsingYear() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        assertDoesNotThrow(() -> Integer.parseInt(sdf.format(now)));
    }

    @Test
    public void testDateParsingMonth() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        assertDoesNotThrow(() -> Integer.parseInt(sdf.format(now)));
    }

    @Test
    public void testDateParsingDay() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        assertDoesNotThrow(() -> Integer.parseInt(sdf.format(now)));
    }

    @Test
    public void testEmptyFieldsClearsName() throws Exception {
        FrmAddBussines frm = new FrmAddBussines();
        Field field = FrmAddBussines.class.getDeclaredField("txtNameBussines");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("Caramelos");

        Method method = FrmAddBussines.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }
    
    
    @Test
    public void testEmptyFieldsClearsLegalRepresentative() throws Exception {
        FrmAddBussines frm = new FrmAddBussines();
        Field field = FrmAddBussines.class.getDeclaredField("txtLegalRepresentative");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("Caramelos");

        Method method = FrmAddBussines.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }
    
    
    @Test
    public void testEmptyFieldTypeBussines() throws Exception {
        FrmAddBussines frm = new FrmAddBussines();
        Field field = FrmAddBussines.class.getDeclaredField("txtTypeBussines");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("Caramelos");

        Method method = FrmAddBussines.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }

    @Test
    public void testEmptyFieldsClearCity() throws Exception {
        FrmAddBussines frm = new FrmAddBussines();
        Field field = FrmAddBussines.class.getDeclaredField("txtCity");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("999");

        Method method = FrmAddBussines.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }

    @Test
    public void testEmptyFieldsClearStreet() throws Exception {
        FrmAddBussines frm = new FrmAddBussines();
        Field field = FrmAddBussines.class.getDeclaredField("txtStreet");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("999");

        Method method = FrmAddBussines.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }

    @Test
    public void testEmptyFieldsClearSector() throws Exception {
        FrmAddBussines frm = new FrmAddBussines();
        Field field = FrmAddBussines.class.getDeclaredField("txtSector");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("999");

        Method method = FrmAddBussines.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }

    @Test
    public void testEmptyFieldsClearRuc() throws Exception {
        FrmAddBussines frm = new FrmAddBussines();
        Field field = FrmAddBussines.class.getDeclaredField("txtRuc");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("1748597859785");

        Method method = FrmAddBussines.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }

    @Test
    public void testEmptyFieldsClearPhone() throws Exception {
        FrmAddBussines frm = new FrmAddBussines();
        Field field = FrmAddBussines.class.getDeclaredField("txtPhone");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("0995785176");

        Method method = FrmAddBussines.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }

    @Test
    public void testEmptyFieldsClearEmail() throws Exception {
        FrmAddBussines frm = new FrmAddBussines();
        Field field = FrmAddBussines.class.getDeclaredField("txtEmail");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("paulo@gmail.com");

        Method method = FrmAddBussines.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }

    @Test
    public void testEmptyFieldsClearHour() throws Exception {
        FrmAddBussines frm = new FrmAddBussines();
        Field field = FrmAddBussines.class.getDeclaredField("txtHour");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("14:45");

        Method method = FrmAddBussines.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }

    @Test
    public void testEmptyFieldsClearDescription() throws Exception {
        FrmAddBussines frm = new FrmAddBussines();
        Field field = FrmAddBussines.class.getDeclaredField("txtDescription");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("999");

        Method method = FrmAddBussines.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }
    
}
