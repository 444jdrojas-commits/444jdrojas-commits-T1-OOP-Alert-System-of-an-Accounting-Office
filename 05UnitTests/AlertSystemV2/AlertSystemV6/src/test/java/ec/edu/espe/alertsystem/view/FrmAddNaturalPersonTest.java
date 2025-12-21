package ec.edu.espe.alertsystem.view;

import static com.mongodb.assertions.Assertions.assertNotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextArea;
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
public class FrmAddNaturalPersonTest {

    @Test
    public void testInstanceNotNull() {
        FrmAddNaturalPerson frm = new FrmAddNaturalPerson();
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
        FrmAddNaturalPerson frm = new FrmAddNaturalPerson();
        Field field = FrmAddNaturalPerson.class.getDeclaredField("txtName");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("999");

        Method method = FrmAddNaturalPerson.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }

    @Test
    public void testEmptyFieldsClearCity() throws Exception {
        FrmAddNaturalPerson frm = new FrmAddNaturalPerson();
        Field field = FrmAddNaturalPerson.class.getDeclaredField("txtCity");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("999");

        Method method = FrmAddNaturalPerson.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }

    @Test
    public void testEmptyFieldsClearStreet() throws Exception {
        FrmAddNaturalPerson frm = new FrmAddNaturalPerson();
        Field field = FrmAddNaturalPerson.class.getDeclaredField("txtStreet");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("999");

        Method method = FrmAddNaturalPerson.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }

    @Test
    public void testEmptyFieldsClearSector() throws Exception {
        FrmAddNaturalPerson frm = new FrmAddNaturalPerson();
        Field field = FrmAddNaturalPerson.class.getDeclaredField("txtSector");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("999");

        Method method = FrmAddNaturalPerson.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }

    @Test
    public void testEmptyFieldsClearNationality() throws Exception {
        FrmAddNaturalPerson frm = new FrmAddNaturalPerson();
        Field field = FrmAddNaturalPerson.class.getDeclaredField("txtNationality");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("ecuatoriana");

        Method method = FrmAddNaturalPerson.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }

    @Test
    public void testEmptyFieldsClearCi() throws Exception {
        FrmAddNaturalPerson frm = new FrmAddNaturalPerson();
        Field field = FrmAddNaturalPerson.class.getDeclaredField("txtIdentification");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("1722249388");

        Method method = FrmAddNaturalPerson.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }

    @Test
    public void testEmptyFieldsClearEmail() throws Exception {
        FrmAddNaturalPerson frm = new FrmAddNaturalPerson();
        Field field = FrmAddNaturalPerson.class.getDeclaredField("txtEmail");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("paulo@gmail.com");

        Method method = FrmAddNaturalPerson.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }

    @Test
    public void testEmptyFieldsClearPhone() throws Exception {
        FrmAddNaturalPerson frm = new FrmAddNaturalPerson();
        Field field = FrmAddNaturalPerson.class.getDeclaredField("txtPhone");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("0995785176");

        Method method = FrmAddNaturalPerson.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }

    @Test
    public void testEmptyFieldsClearOcuppation() throws Exception {
        FrmAddNaturalPerson frm = new FrmAddNaturalPerson();
        Field field = FrmAddNaturalPerson.class.getDeclaredField("txtOcuppation");
        field.setAccessible(true);
        ((JTextField) field.get(frm)).setText("1722249388");

        Method method = FrmAddNaturalPerson.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", ((JTextField) field.get(frm)).getText());
    }
}
