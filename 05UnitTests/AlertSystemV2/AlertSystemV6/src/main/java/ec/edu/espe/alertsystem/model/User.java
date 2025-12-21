package ec.edu.espe.alertsystem.model;

/**
 *
 * @author JOSUE
 */
public class User {
    private String Name;
    private String Password;

    public User(String Name, String Password) {
        this.Name = Name;
        this.Password = Password;
    }

    
    
    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }
}
