package ec.edu.espe.alertsystem.view;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.swing.JButton;
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
public class FrmAlertSystemLoginTest {

    @Test
    public void testInstanceNotNull() {
        FrmAlertSystemLogin frm = new FrmAlertSystemLogin();
        assertNotNull(frm);
    }

    @Test
    public void testEmptyFieldsLogic() throws NoSuchFieldException, IllegalAccessException, Exception {
        FrmAlertSystemLogin frm = new FrmAlertSystemLogin();
        Field field = FrmAlertSystemLogin.class.getDeclaredField("txtUser");
        field.setAccessible(true);
        JTextField txt = (JTextField) field.get(frm);
        txt.setText("123");

        Method method = FrmAlertSystemLogin.class.getDeclaredMethod("emptyFields");
        method.setAccessible(true);
        method.invoke(frm);

        assertEquals("", txt.getText());
    }

    @Test
    public void testButtonText() throws NoSuchFieldException, IllegalAccessException {
        FrmAlertSystemLogin frm = new FrmAlertSystemLogin();
        Field field = FrmAlertSystemLogin.class.getDeclaredField("jButton1");
        field.setAccessible(true);
        JButton btn = (JButton) field.get(frm);
        assertEquals("Iniciar sesion", btn.getText());
    }

    
}
