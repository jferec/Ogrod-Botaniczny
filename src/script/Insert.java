package script;


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;

public class Insert {


    static char[] nieobecnosc;
    static Random random = new Random();

    static public void generateDate(int sekN, int polN, int ogrN, int rosN) throws Exception {
        PrintWriter writer = new PrintWriter("src/script/insert.sql");
        writer.write("");
        writer.close();
        insertSekcja(sekN);
        insertPoletko(sekN,polN);
        insertOgrodnik(ogrN);
        insertGatunki();
        insertRosliny(sekN, polN, rosN);
        insertSrodkiOchrony();
        insertGatunkiRosliny();
        insertOgrodnikPoletko(ogrN);
        insertTypUrlopu();
        insertNieobecnosci(ogrN, 1);
        insertZwolnienieUrlop();
        insertUzycia(polN, sekN);
    }

    static public void insertSekcja(int sekN) throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ALL\n");

        for (int i = 0; i < sekN; i++) {
            sb.append("INTO SEKCJA (nazwa) VALUES (" + "'Sekcja " + StaticData.getNum() + "')\n");
        }
        sb.append("SELECT * FROM DUAL;\n");
        saveScript(sb, "src/script/insert.sql");
    }

    static public void insertPoletko(int sekN, int polN) throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ALL\n");

        for (int i = 1; i <= sekN; i++) {
            for (int j = 1; j <= polN; j++) {
                sb.append("INTO POLETKO (nazwa, powierzchnia, Sekcja_id) VALUES ('Poletko #");
                sb.append((i-1)*polN + j);
                sb.append("', ");
                sb.append(StaticData.getRandomArea());
                sb.append(", ");
                sb.append(i);
                sb.append(")\n");
            }
        }
        sb.append("SELECT * FROM DUAL;\n");
        saveScript(sb, "src/script/insert.sql");
    }

    static public void insertOgrodnik(int ogrN) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ALL\n");
        for (int i = 0; i < ogrN; i++) {
            sb.append("INTO OGRODNIK (imie, nazwisko, pesel) VALUES ('");
            sb.append(StaticData.pickRandom(StaticData.imionaM));
            sb.append("', '");
            sb.append(StaticData.pickRandom(StaticData.nazwiskaM));
            sb.append("', '");
            sb.append(StaticData.getPESEL());
            sb.append("')\n");
        }
        sb.append("SELECT * FROM DUAL;\n");
        saveScript(sb, "src/script/insert.sql");
    }


    static public void insertGatunki() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ALL\n");
        for (String s : StaticData.gatunki) {
            sb.append("INTO GATUNEK (nazwa) VALUES ('");
            sb.append(s);
            sb.append("')\n");
        }
        sb.append("SELECT * FROM DUAL;\n");
        saveScript(sb, "src/script/insert.sql");;
    }

    static public void insertRosliny(int sekN, int polN, int n) throws Exception{
        int count = StaticData.gatunki.length;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ALL\n");
        for(int j = 1; j <= count; j++) {
            int k = random.nextInt(sekN * polN);
            for (int i = 0; i < n; i++) {
                sb.append("INTO ROSLINA (Gatunek_id, Poletko_id) VALUES (");
                sb.append(j);
                sb.append(", ");
                sb.append((k+i) % (sekN*polN) + 1);
                sb.append(")\n");
            }
        }
        sb.append("SELECT * FROM DUAL;\n");
        saveScript(sb, "src/script/insert.sql");
    }

    static public void insertSrodkiOchrony() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ALL\n");
        for (String s : StaticData.srodkiOchrony) {
            sb.append("INTO SRODEK_OCHRONY (nazwa) VALUES ('");
            sb.append(s);
            sb.append("')\n");
        }
        sb.append("SELECT * FROM DUAL;\n");
        saveScript(sb, "src/script/insert.sql");
    }

    static public void insertGatunkiRosliny() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ALL\n");
        for(int i = 1; i<=StaticData.gatunki.length; i++){
            sb.append("INTO GATUNEK_SRODEK_OCHRONY (Gatunek_id, Srodek_ochrony_id) VALUES (");
            sb.append(i);
            sb.append(", ");
            sb.append(i%StaticData.srodkiOchrony.length + 1);
            sb.append(")\n");
            sb.append("INTO GATUNEK_SRODEK_OCHRONY (Gatunek_id, Srodek_ochrony_id) VALUES (");
            sb.append(i);
            sb.append(", ");
            sb.append((i+5)%(StaticData.srodkiOchrony.length) + 1);
            sb.append(")\n");
        }
        sb.append("SELECT * FROM DUAL;\n");
        saveScript(sb, "src/script/insert.sql");
    }

    static public void insertOgrodnikPoletko(int ogrN) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ALL\n");
        for(int i = 1; i < ogrN; i++){
            int n = random.nextInt(ogrN);
            for(int j = 0; j < 5; j++){
                sb.append("INTO POLETKO_OGRODNIK (Poletko_id, Ogrodnik_id) VALUES (");
                sb.append(i);
                sb.append(", ");
                sb.append((n+j)%ogrN + 1);
                sb.append(")\n");
            }
        }
        sb.append("SELECT * FROM DUAL;\n");
        saveScript(sb, "src/script/insert.sql");
    }

    static public void insertTypUrlopu() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ALL\n");
        for(String s: StaticData.typyUrlopu){
            sb.append("INTO TYP_URLOPU (nazwa) VALUES ('");
            sb.append(s);
            sb.append("')\n");
        }
        sb.append("SELECT * FROM DUAL;\n");
        saveScript(sb, "src/script/insert.sql");
    }

    static public void insertNieobecnosci(int ogrN, int n) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ALL\n");
        nieobecnosc = new char[n * ogrN];
        for (int i = 1; i <= ogrN; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append("INTO NIEOBECNOSC (DATA_OD, DATA_DO, OGRODNIK_ID, TYP) VALUES (TO_DATE('");
                int idx = StaticData.getRandomDataIndex();
                sb.append(StaticData.daty[idx]);
                sb.append("', 'DD-MM-YYYY'), TO_DATE('");
                sb.append(StaticData.daty[idx + 1]);
                sb.append("', 'DD-MM-YYYY'), ");
                int id = (i - 1) * n + j;
                sb.append(id);
                sb.append(", ");
                int k = random.nextInt() % 2;
                if (k == 0) {
                    nieobecnosc[id - 1] = 'Z';
                    sb.append(" 'Z')\n");

                } else {
                    nieobecnosc[id - 1] = 'U';
                    sb.append(" 'U')\n");
                }
            }

        }
        sb.append("SELECT * FROM DUAL;\n");
        saveScript(sb, "src/script/insert.sql");

    }

    public static void insertZwolnienieUrlop() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ALL\n");
        int i = 0;
        for(char c: nieobecnosc){
            if(c == 'Z') {
                sb.append("INTO ZWOLNIENIE (id) VALUES (");
                sb.append(i+1);
            }
            else{
                sb.append("INTO URLOP (id, Typ_urlopu_id) VALUES (");
                sb.append(i+1);
                sb.append(", ");
                sb.append(2);
            }
            sb.append(")\n");
            i++;
        }

        sb.append("SELECT * FROM DUAL;\n");
        saveScript(sb, "src/script/insert.sql");
    }

    static public void insertUzycia(int polN, int sekN) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ALL\n");
        for(String s: StaticData.datyUzycia)
            for(int i = 1; i <= sekN*polN; i++){
                sb.append("INTO ZUZYCIE_SRODKA (waga, Srodek_ochrony_id, Poletko_id, data_uzycia) ");
                sb.append("VALUES (");
                sb.append(random.nextInt(9) + 1);
                sb.append(", ");
                sb.append(random.nextInt(9) + 1);
                sb.append(", ");
                sb.append(i);
                sb.append(", ");
                sb.append("TO_DATE('");
                sb.append(s);
                sb.append("', 'DD-MM-YYYY'))\n");
            }
        sb.append("SELECT * FROM DUAL;\n");
        saveScript(sb, "src/script/insert.sql");
    }

    static public void saveScript(StringBuilder sb, String path) throws Exception{
        PrintWriter writer = new PrintWriter(new FileOutputStream(new File(path), true));
        writer.write(sb.toString());
        writer.close();
    }
}
