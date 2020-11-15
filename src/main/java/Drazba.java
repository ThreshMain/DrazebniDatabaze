import java.util.Stack;

public class Drazba {
    private final Auto drazenaPolozka;
    private final Stack<Nabidka> prihozy;

    public Drazba(Auto drazenaPolozka) {
        if (drazenaPolozka == null) {
            throw new IllegalArgumentException("Drazena polozka nemuze byt NULL");
        }
        this.drazenaPolozka = drazenaPolozka;
        this.prihozy = new Stack<>();
    }

    @Override
    public String toString() {
        return "Drazba{" +
                "drazenaPolozka=" + drazenaPolozka +
                ", prihozy=" + prihozy +
                '}';
    }

    public Auto getDrazenaPolozka() {
        return drazenaPolozka;
    }

    public Stack<Nabidka> getPrihozy() {
        return prihozy;
    }

    public void Prihod(Nabidka nabidka) {
        if (nabidka.getCastka() >= drazenaPolozka.getCena()) {
            if (this.prihozy.size() == 0 || nabidka.getCastka() > this.prihozy.peek().getCastka()) {
                this.prihozy.add(nabidka);
            } else {
                throw new IllegalArgumentException("Nabidka musi byt vetsi nez posledni nabidka");
            }
        } else {
            throw new IllegalArgumentException("Nabidka musi byt vetsi cena Polozky");
        }
    }

    public Nabidka NejvysiNabidka() {
        return this.prihozy.peek();
    }
}

