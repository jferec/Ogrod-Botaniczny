package script;

import java.util.ArrayList;
import java.util.Random;

public class StaticData {

    static int num;
    public static String[] imionaM = {"Adam", "Adrian", "Albert", "Antoni", "Artur", "Bartosz", "Beniamin", "Damian", "Daniel", "Dariusz",
    "Dawid", "Dionizy", "Dominik", "Donald", "Edward", "Emanuel", "Eryk", "Filip", "Franciszek", "Gabriel", "Gerard",
    "Gustaw", "Fabian", "Herbert", "Henryk", "Jakub", "Kacper", "Kamil", "Krzysztof", "Lech", "Leon", "Lucjan",
    "Maciej", "Mateusz", "Piotr", "Przemyslaw", "Stefan", "Sylwester", "Tadeusz", "Tomasz", "Wiktor", "Zbigniew", "Pawel"};
    public static String[] imionaK = {"Anna", "Ada", "Agnieszka", "Aldona", "Aleksandra", "Agata", "Barbara", "Blanka", "Bozena", "Edyta", "Eliza",
    "Celina", "Daria", "Ewa", "Felicja", "Hanna", "Halina", "Kamila", "Karolina", "Malgorzata", "Beata", "Laura",
    "Lena", "Luiza", "Monika", "Natalia", "Nikola", "Zofia", "Zuzanna", "Weronika", "Wioletta", "Renata", "Regina",
    "Olga", "Oliwia", "Maria", "Magdalena", "Marzena", "Marlena", "Matylda", "Izabella"};
    public static String[] nazwiskaM = {"Nowak", "Kowalski", "Wisniewski", "Wojcik", "Kowalczyk", "Kaminski", "Lewandowski", "Zielinski",
    "Szymanski", "Wozniak", "Dabrowski", "Kozlowski", "Jankowski", "Mazur", "Wojciechowski", "Kwiatkowski", "Krawczyk", "Kaczmarek",
    "Kaczmarek", "Piotrowski", "Grabowski", "Zajac", "Krol", "Wieczorek", "Jablonski", "Wrobel", "Majewski", "Olszewski",
    "Stepien", "Malinowski", "Jaworski", "Adamczyk", "Dudek", "Gorski", "Witkowski", "Walczak", "Pawlak", "Sikora", "Baran", "Rutkowski"};
    public static String[] nazwiskaK = {"Nowak", "Kowalska", "Wisniewska", "Wojcik", "Kowalczyk", "Kaminska", "Lewandowska", "Zielinska",
    "Szymanska", "Kozlowska", "Wojciechowska", "Kaczmarek", "Piotrkowska", "Grabowska", "Zajac", "Michalska", "Krol", "Jablonska",
    "Wieczorka", "Nowakowska", "Wrobel", "Majewska", "Stepien", "Olszewska", "Jaworska", "Malinowska", "Adamczyk", "Gorska",
    "Pawlak", "Dudek", "Walczak", "Rutkowska", "Sikora", "Baran", "Tomaszewska", "Pietrzak", "Wrobelska"};
    public static String[] gatunki = {"dlugosz krolewski", "jezycznik zwyczajny", "pioropusznik strusi", " podrzeń zebrowiec", "poryblin jeziorny",
    "poryblin kolczasty", "salwinia plywajaca", "skrzyp olbrzymi", "widlakowate", "arnika gorska", "barwinek pospolity", "bluszcz pospolity", "ciemiezyca",
    "dziewiecsil bezlodygowy", "dziegiel litwor", "duptam jesionolistny", "gnidosz krolewski", "gnidosz rozeslany", "goryczka", "grazel drobrny", "grazel zolty",
    "groszek wschodniokarpacki", "jarzab brekinia", "jarzab szwedzki", "kosaciec", "klokoczka poludniowa", "len wlochaty", "len zlocisty", "lepnica litewska", "lilia zlotoglow",
    "luluczenica krainska", "lobelia jeziorna", "mieczyk blotny", "mikolajek nadmorski", "milek wiosenny", "mlecznik nadmorski", "naparstnica purpurowa", "niebielistka trwala",
    "ozota zwyczajna", "pajecznica liliowata", "parzydlo lesne", "pelnik europejski", "pierwiosnek omaczony", "pluskwica europejska", "rojnik", "rokitnik zwyczajny", "rosiczka",
    "rozanecznik zolty", "szafirek drobnkwiatowy", "szafran spiski", "szarotka alpejska", "szczodrzeniec zmienny", "sniadek cienkolistny", "sniezyca wiosenna",
    "sniezyczka przebisnieg", "wrzosiec bagienny", "zawilec naryzowaty", "zerwa kulista", "zimowit jesienny"};
    public static String[] srodkiOchrony = { "woda", "Agrosar", "Axial", "Chwastox", "Fusidale", "Clinik", "Huzak", "Miedzian", "Grodyl", "Chisel"};
    public static String[] typyUrlopu = {"macierzynski", "wypoczynkowy", "ojcowski", "okolicznosciowy", "bezplatny"};
    public static String[] daty = {"10-01-2010", "20-01-2010", "01-02-2010", "10-02-2010", "20-02-2010", "01-03-2010", "11-03-2010", "21-03-2010", "01-04-2010", "11-04-2010", "21-04-2010",
    "01-05-2010", "11-05-2010", "21-05-2010", "01-06-2010", "11-06-2010", "21-06-2010", "01-07-2010", "11-07-2010", "21-07-2010", "01-08-2010", "11-08-2010", "21-08-2010",
    "01-09-2010", "11-09-2010", "21-09-2010", "01-10-2010", "11-10-2010", "21-10-2010", "01-11-2010", "11-11-2010", "21-11-2010", "01-12-2010", "11-12-2010", "21-12-2010"};
    public static ArrayList<String> datyUzycia = new ArrayList<>();
    public static int getRandomDataIndex(){return random.nextInt(daty.length-2);};
    static
    {
        for(int k = 2018; k < 2019; k++){
            for(int n = 11; n < 12; n++) {
                for (int i = 1; i < 10; i++) {
                    StringBuilder sb = new StringBuilder(10);
                    if (i < 10)
                        sb.append(0);
                    sb.append(i);
                    sb.append("-");
                    if (n < 10)
                        sb.append(0);
                    sb.append(n);
                    sb.append("-");
                    sb.append(k);
                    datyUzycia.add(sb.toString());
                }
            }
        }

        num = 1;
    }

    public static int getNum(){
        return num++;
    }

    public static String pickRandom(String[] arr){
        return arr[new Random().nextInt(arr.length)];
    }
    static Random random = new Random();

    public static String getPESEL(){
        StringBuilder sb = new StringBuilder(11);
        sb.append(random.nextInt(69) + 30);
        sb.append(0);
        sb.append(random.nextInt(9) + 1);
        sb.append(random.nextInt(19) + 10);
        sb.append(random.nextInt(89999) + 10000);
        return sb.toString();
    }

    public static Integer getRandomArea(){
        return random.nextInt(899) + 100;
    }
}
