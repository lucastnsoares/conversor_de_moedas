public class Moeda {
    private String nomeMoeda;
    private String siglaMoeda;

    public Moeda(String nomeMoeda, String siglaMoeda) {
        this.nomeMoeda = nomeMoeda;
        this.siglaMoeda = siglaMoeda;
    }

    public String getSiglaMoeda() {
        return siglaMoeda;
    }

    @Override
    public String toString() {
        return nomeMoeda + "(" + siglaMoeda + ")";
    }
}
