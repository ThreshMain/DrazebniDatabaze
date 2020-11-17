import javax.management.InstanceAlreadyExistsException;
import java.util.*;

public class DrazebniDatabaze {
    private static DrazebniDatabaze instance = null;

    private final TreeSet<Auto> auta;

    private Drazba aktualniDrazba;
    private final Queue<Drazba> frontaDrazeb;
    private final LinkedList<Drazba> ukonceneDrazby;

    private DrazebniDatabaze() {
        super();
        this.auta = new TreeSet<>();
        frontaDrazeb = new ArrayDeque<>();
        ukonceneDrazby = new LinkedList<>();
    }

    public static DrazebniDatabaze getInstance() {
        if (instance == null) {
            instance = new DrazebniDatabaze();
        }
        return instance;
    }

    public void ukonciDazbu() {
        ukonceneDrazby.add(aktualniDrazba);
        aktualniDrazba = frontaDrazeb.poll();
    }

    public boolean zacniDrazbu(String nazev) {
        Auto drazebniPolozka = this.hledej(nazev);
        if (aktualniDrazba == null) {
            aktualniDrazba = new Drazba(drazebniPolozka);
            return true;
        }
        frontaDrazeb.add(new Drazba(drazebniPolozka));
        return false;
    }

    public boolean zacniDrazbu(Auto a) throws Exception {
        if (!this.auta.contains(a)) {
            this.pridej(a);
        }
        if (aktualniDrazba == null) {
            aktualniDrazba = new Drazba(a);
            return true;
        }
        frontaDrazeb.add(new Drazba(a));
        return false;
    }

    public Auto hledej(String nazev) {
        for (Auto auto : auta) {
            if (auto.getNazev().equals(nazev)) {
                return auto;
            }
        }
        throw new NoSuchElementException("Auto nenalezeno v databazi");
    }
    public void pridej(Auto auto) throws InstanceAlreadyExistsException {
        if(!auto.isValid()){
            throw new IllegalArgumentException("Drazena polozka nema vsechny potrebne hodnoty");
        }
        if (!this.auta.add(auto)) {
            throw new InstanceAlreadyExistsException("Auto je uz zapsane v databazi");
        }
    }

    public String GetSerazene() {
        StringBuilder output = new StringBuilder();
        for (Auto a : auta) {
            output.append(a.toString()).append("\n");
        }
        return output.toString();
    }

    public TreeSet<Auto> getAuta() {
        return new TreeSet<>(auta);
    }

    public Drazba getAktualniDrazba() {
        return aktualniDrazba;
    }

    public Queue<Drazba> getFrontaDrazeb() {
        return new ArrayDeque<>(frontaDrazeb);
    }

    public LinkedList<Drazba> getUkonceneDrazby() {
        return new LinkedList<>(ukonceneDrazby);
    }

}
