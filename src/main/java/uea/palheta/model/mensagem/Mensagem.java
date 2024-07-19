package uea.palheta.model.mensagem;

import java.io.Serializable;

public class Mensagem implements Serializable {
    String texto;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
