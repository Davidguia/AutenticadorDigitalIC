package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Professor;
import bd.ProfessorBD;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.ArrayList;
import javax.faces.bean.ManagedProperty;


@ManagedBean
@SessionScoped
public class ProfessorBean implements Serializable {

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    private Professor objProfessor;
    private ProfessorBD objProfessorBD;

    public ProfessorBean() {
        this.objProfessor = new Professor();
        this.objProfessorBD = new ProfessorBD();

    }

    public Professor getProfessor() {
        return objProfessor;
    }

    public void setObjProfessor(Professor objProfessor) {
        this.objProfessor = objProfessor;
    }

    public String salvaProfessor() throws SQLException {
        if (this.objProfessor.getId() != 0) {
            try {
                this.objProfessorBD.alterarProfessor(this.objProfessor);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM ALTERADOS COM SUCESSO!"));
                this.objProfessor = new Professor();
                this.objProfessorBD = new ProfessorBD();

            } catch (SQLException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO ALTERADOS!"));
                e.printStackTrace();
            }

            this.objProfessor = new Professor();
        } else {
            try {
                this.objProfessorBD.inserirProfessor(this.objProfessor);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM INSERIDOS COM SUCESSO!"));
                this.objProfessor = new Professor();
                this.objProfessorBD = new ProfessorBD();
            } catch (SQLException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO INSERIDOS!"));
                e.printStackTrace();
            }

        }

        return "mostra";
    }

    public String removerProfessor(Professor aluno) throws SQLException {
        try {
            this.objProfessorBD.removerProfessor(aluno);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM REMOVIDOS COM SUCESSO!"));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO INSERIDOS!"));
            e.printStackTrace();
        }
        this.objProfessor = new Professor();
        return "professor";
    }

    public List<Professor> getListarProfessor() {
        List<Professor> lstProfessor = new ArrayList<Professor>();
        try {
            lstProfessor = objProfessorBD.listarProfessor();
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM LISTADOS COM SUCESSO!"));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO LISTADOS!"));
            e.printStackTrace();

        }
        return lstProfessor;
    }

    public List<Professor> getListarUsuario() {
        List<Professor> lstProfessor = new ArrayList<Professor>();
        try {
            lstProfessor = objProfessorBD.listarUsuario(loginBean.login.getId());
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM LISTADOS COM SUCESSO!"));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO LISTADOS!"));
            e.printStackTrace();

        }
        return lstProfessor;
    }

    public String preparaEditar(Professor aluno) {
        this.objProfessor = aluno;
        return "mostra";
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

}
