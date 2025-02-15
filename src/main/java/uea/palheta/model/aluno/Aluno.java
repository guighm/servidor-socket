package uea.palheta.model.aluno;

public class Aluno {
    private String login;
    private String senha;
    private int anoDeIngresso;

    public Aluno(String login, String senha, int anoDeIngresso) {
        this.login = login;
        this.senha = senha;
        this.anoDeIngresso = anoDeIngresso;
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
    public int getAnoDeIngresso() {
        return anoDeIngresso;
    }
    public void setAnoDeIngresso(int anoDeIngresso) {
        this.anoDeIngresso = anoDeIngresso;
    }
}
