package uea.palheta;

public class Teste {
    public static void main(String[] args) {
        int tamanho = Application.apelidos.size();
        String texto = "";
        for (int i = 1; i < tamanho; i++) {
            String elemento = Application.apelidos.get(i);
            texto += "%s:".formatted(elemento);
        }
        String ultimo =  Application.apelidos.get(tamanho);
        texto += "%s".formatted(ultimo);
        System.out.println(texto);
    }
}