import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InseratParser {
    private Auto entita;
    private Osoba prodavajici;

    public Auto getEntita() {
        return entita;
    }

    public Osoba getProdavajici() {
        return prodavajici;
    }

    public InseratParser(File textInseratSoubor) {
        if (textInseratSoubor == null) {
            throw new IllegalArgumentException("Soubor s inzeratem nemuze byt null");
        }
        if (!textInseratSoubor.exists() || !textInseratSoubor.canRead()) {
            throw new IllegalArgumentException("Soubor s inzeratem musi existovat a musi byt citelny");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(textInseratSoubor))) {
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            parseZTextu(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseZTextu(ArrayList<String> lines) {
        this.entita = new Auto();
        this.prodavajici = new Osoba();
        for (String line : lines) {
            if (line.contains(":")) {
                String[] splitedLine = line.split(":", 2);
                String field = splitedLine[0].trim().toLowerCase();
                String data = splitedLine[1].trim();
                try {

                    switch (field) {
                        case "nazev":
                            this.entita.setNazev(data);
                            break;
                        case "cena":
                            this.entita.setCena(Float.parseFloat(data));
                            break;
                        case "pohon":
                            this.entita.setPohonVozidla(Pohon.valueOf(data.toUpperCase()));
                            break;
                        case "pocet dveri":
                            this.entita.setPocetDveri(Integer.parseInt(data));
                            break;
                        case "datum vyroby":
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = formatter.parse(data);
                            this.entita.setDatumVyroby(date);
                            break;
                        case "popis":
                            this.entita.setPopis(data);
                            break;
                        case "jmeno":
                            this.prodavajici.setJmeno(data);
                            break;
                        case "adresa":
                            this.prodavajici.setAdresa(data);
                            break;
                        case "telefon":
                            this.prodavajici.setTelefon(data);
                            break;
                        case "mail":
                            this.prodavajici.setMail(data);
                            break;
                        default:
                            System.out.println("Kolonka: " + field + " neexistuje.");
                    }
                } catch (ParseException e) {
                    System.out.println("Datum musi byt ve formatu dd-MM-yyyy ("+e.getMessage()+") vase data:"+data);
                } catch (NumberFormatException e) {
                    System.out.println("Cislo neni ve spravnem formatu! vase data:"+data);
                } catch (IllegalArgumentException e) {
                    System.out.println("Kolonka: " + field + " neni ve spravnem formatu (" + e.getMessage() + ") vase data:"+data);
                }
            } else {
                System.out.println("Radek by mel obsahovat ':' (preskakuji: " + line + ")");
            }
        }
    }
}
