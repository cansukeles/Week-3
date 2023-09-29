import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int row, column;
        Scanner scan = new Scanner(System.in);

        // Değerlendirme Formu 7: Kullanıcı tarafından satır ve sütun bilgileri aşağıdaki kod bloğunda alınır.
        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz!");
        System.out.println("Lütfen oynamak istediğiniz oyun tahtası boyutlarını belirtiniz.");
        System.out.print("Satır sayısı: ");
        row = scan.nextInt();
        System.out.print("Sütun sayısı: ");
        column = scan.nextInt();

        // Eğer kullanıcı min 3*3'lük bir matris boyutu girmezse hata mesajı verilir.
        while ((row < 3) || (column < 3)) {
            System.out.println("Oyun tahtası boyutları minimum 3*3 olmalı!");
            System.out.print("Satır sayısı: ");
            row = scan.nextInt();
            System.out.print("Sütun sayısı: ");
            column = scan.nextInt();
        }
        // Oyun başlatılır.
        MineSweeper mine = new MineSweeper(row, column);
        mine.start();
    }
}