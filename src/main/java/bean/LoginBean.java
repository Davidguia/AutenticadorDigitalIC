package bean;

import bd.ConnBD;
import java.io.Serializable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Login;

/**
 * Simple login bean.
 *
 * @author itcuties
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
    
    private static final long serialVersionUID = 7765876811740798583L;
    Login login = new Login();

    public Login getLogin() {
        return login;
    }
    
    public void setLogin(Login login) {
        this.login = login;
    }
    

   

    @ManagedProperty(value = "#{navigationBean}")
    private NavigationBean navigationBean;

    public String doLogin() throws SQLException {
        PreparedStatement ps = null;
        try {
            Connection con2 = ConnBD.getConnection();
            ps = con2.prepareStatement(
                    "select id, nome, senha from pessoa where nome= ?  ");
            ps.setString(1, this.login.getUname());
           // ps.setString(2, this.password);
            // System.out.println("hash nesse momento"+UseBCrypt.hashPassword(this.password));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                String temp = this.login.getPassword();
                this.login.setId(rs.getInt(1)); 
                this.login.setUname(rs.getString(2)); 
                this.login.setPassword(rs.getString(3)); 
                //System.out.println("passord que esta no banco: " + this.password);
                if (UseBCrypt.checkPassword(temp, this.login.getPassword())) {
                    this.login.setLoggedIn(true);
                    return navigationBean.redirectToWelcome();
                } else {
                    return navigationBean.toLogin();
                }

            } else {
                return navigationBean.toLogin();
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return navigationBean.toLogin();
        } finally {
            ConnBD.getConnection().close();
        }
    }

    /**
     * Logout operation.
     *
     * @return
     */
    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        this.login.setLoggedIn(false);

        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return navigationBean.redirectToLogin();
    }

    // ------------------------------
    // Getters & Setters 
    

    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }

    

    

}
