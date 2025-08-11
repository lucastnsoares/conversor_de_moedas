public class OpcoesMenu {
    private Moeda moeda1;
    private Moeda moeda2;

    public OpcoesMenu(Moeda moeda1, Moeda moeda2) {
        this.moeda1 = moeda1;
        this.moeda2 = moeda2;
    }

    public Moeda getMoeda1() {
        return moeda1;
    }

    public Moeda getMoeda2() {
        return moeda2;
    }
}
