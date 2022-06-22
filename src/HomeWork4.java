//Экспертный уровень
// Дано: папка, внутри которой находятся файлы, следующего именования - report_01_2012.txt, где 01 - месяц, 2012 - год
// Внутри файла следующий формат:
// pyterochka;122300.20;100312.10;20/01/2012
// pyterochka;299922.00;323333.02;21/01/2012
// perekrestok;9920.20;28200.01;21/01/2012
// Где pyterochka - название магазина; 122300.20 - доход; 100312.10 - расход, 20/01/2012 - дата операции

// Задача №1
// Необходимо составить отчет о итоговой прибыли за каждый месяц по магазину pyterochka
// Формат ожидаемого результат:
// Прибыль по магазину pyterochka по месяцам
// 01.2012: 20000.00
// 02.2012: -100332.00
// и тд


// Задача №2
// Необходим составить отчет о расходах всех магазинов за весь период по магазинам (т.е. прочитать все файлы внутри папки)
// Формат ожидаемого результат:
// Расходы pyterochka за весь период: 20032220.00
// Расходы perekrestok за весь период: 1734220.00
// .. и тд


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HomeWork4 {
    public static void main(String[] args) throws IOException {
        Exercise1();
        Exercise2();
    }

    public static void Exercise1() throws IOException {
        System.out.println("Прибыль магазина пятерочка по месяцам:");
        File[] FilesList = getFilesPath();
        ArrayList<String> Shop5 = new ArrayList<>();
        for (int i = 0; i < FilesList.length; i++) {
            Shop5.clear();
            FileReader fileReader = new FileReader(FilesList[i]);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                String readLine = bufferedReader.readLine();
                if (readLine.contains("pyterochka")) {
                    Shop5.add(readLine);
                }
            }
            IncomePyterochka(Shop5, FilesList[i].getName().substring(7, 14));
        }
        System.out.println("\n");
    }

    public static void IncomePyterochka(ArrayList<String> Shop, String name) {
        Double Income = 0.0;
        Double Outcom = 0.0;
        for (int i = 0; i < Shop.size(); i++) {
            String[] x = Shop.get(i).split(";");
            Income += Double.parseDouble(x[1]);
            Outcom += Double.parseDouble(x[2]);
        }
        Double result = Income - Outcom;
        System.out.printf(name.replace("_", ".") + ":" + "%.2f", result);
        System.out.println();
    }

    public static File[] getFilesPath() throws IOException {
        File folder = new File("D:\\Projekt JAVA\\HomeWork4_Expert\\resource");
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }

    public static void Exercise2() throws IOException {
        File file[] = getFilesPath();
        ArrayList<String> ShopsName = new ArrayList<>();
        for (int i = 0; i < file.length; i++) {
            FileReader fileReader = new FileReader(file[i]);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                String readLine = bufferedReader.readLine();
                Pattern pattern = Pattern.compile("\\D+;\\d");
                Matcher matcher = pattern.matcher(readLine);
                while (matcher.find()) {
                    String s = matcher.group(0).substring(0,matcher.group(0).length()-2);
                    if (!ShopsName.contains(s)) {
                        ShopsName.add(s);
                    }
                }
            }
        }
        CreateObjectShop(ShopsName);
    }

    public static double CalculetShop(String shop) {
        String arrShop[] = shop.split(";");
        double Outcom = Double.parseDouble(arrShop[2]);
        return Outcom;
    }

    public static void CreateObjectShop(ArrayList<String> name) throws IOException {
        ArrayList<Shop> shopsName = new ArrayList<>();
        for (int i = 0; i < name.size(); i++) {
            shopsName.add(new Shop(name.get(i)));
        }
        ResultForAllStores(shopsName);
    }

    public static void ResultForAllStores(ArrayList<Shop> shops) throws IOException {
        File file[] = getFilesPath();
        for (int i = 0; i < file.length; i++) {
            FileReader fileReader = new FileReader(file[i]);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                String readLine = bufferedReader.readLine();
                for (int j = 0; j < shops.size(); j++) {
                    if (readLine.contains(shops.get(j).name)) {
                        shops.get(j).Outcom += CalculetShop(readLine);
                    }
                }
            }
        }
        for (Shop s : shops) {
            System.out.printf("Расходы " + s.name + " за весь период : " + "%.2f", s.Outcom);
            System.out.println();
        }
    }
}











