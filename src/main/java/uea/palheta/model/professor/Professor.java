package uea.palheta.model.professor;

public class Professor {
    private String login;
    private String senha;
    private String titulacao;

    public Professor(String login, String senha, String titulacao) {
        this.login = login;
        this.senha = senha;
        this.titulacao = titulacao;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getTitulacao() {
        return titulacao;
    }
    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }
}
