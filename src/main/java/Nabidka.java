
public class Nabidka {
	private Uzivatel prihazujici;
	private float castka;
	public Nabidka(Uzivatel prihazujici,float castka){
		setCastka(castka);
		setPrihazujici(prihazujici);
	}

	public Uzivatel getPrihazujici() {
		return prihazujici;
	}

	public void setPrihazujici(Uzivatel prihazujici) {
		if (prihazujici == null) {
			throw new IllegalArgumentException("Prihazuji nemuze byt null");
		}
		this.prihazujici = prihazujici;
	}

	public float getCastka() {
		return castka;
	}

	public void setCastka(float castka) {if (castka < 0) {
		throw new IllegalArgumentException("Caska nemuze byt zaporna");
	}
		this.castka = castka;
	}
}
