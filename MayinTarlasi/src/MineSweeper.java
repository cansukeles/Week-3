import java.util.Random;
import java.util.Scanner;

// MineSweeper sınıfı oluşturulur
public class MineSweeper {
    int rowNumber, colNumber, size, mineCount, emptySpaces, selectedSpaces;
    int[][] map;
    int[][] board;
    boolean game = true;

    Random rand = new Random();
    Scanner input = new Scanner(System.in);

    // MineSweeper sınıfının constructor metotu
    MineSweeper(int rowNumber, int colNumber) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.map = new int[rowNumber][colNumber];
        this.board = new int[rowNumber][colNumber];
        this.size = rowNumber * colNumber;
        this.mineCount = (size / 4);
        this.emptySpaces = size - mineCount;
        this.selectedSpaces = 0;
    }

    // Oyunu başlatma, start metodu
    public void start() {
        int row, col;
        prepareGame();
        print(map);
        System.out.println("Oyun başladı! ");

        // Mayına basılmadıkça oyun ilerlemeye devam eder
        while (game) {
            print(board);

            // Değer.Formu 9: Kullanıcıdan işaretlemek istediği satır ve sütun bilgisi alınır
            System.out.print("Satır sayısı: ");
            row = input.nextInt();
            System.out.print("Sütun sayısı: ");
            col = input.nextInt();

            // Değer.Formu 10: Kullanıcının seçtiği noktanın dizinin sınırları içerisinde olup olmadığı kontrol edilir
            if ((row < 0 || row >= rowNumber) || (col < 0 || col >= colNumber)) {
                System.out.println("Girdiğiniz koordinatlar yanlıştır. Lütfen tekrar deneyiniz!");
                // Girilen noktada mayın olup olmadığı kontrol edilir.
            } else if (map[row][col] != -1) {
                // Kullanıcının seçtiği nokta daha önce açılmış ise uyarı mesajı verilir.
                if (board[row][col] >= 0) {
                    System.out.println("Burası zaten açılmış! Başka bir yer seçiniz.");
                } else {
                    // Değ.Formu 12: Girilen noktada mayın yoksa etrafındaki mayın sayısı veya 0 değeri yazılır
                    int count = isCheck(row, col);
                    board[row][col] = count;
                    selectedSpaces++;
                }

                // Değer.Formu 15: Oyunu kazanma durumunda kullanıcıya bilgi verilir
                if (isWin()) {
                    System.out.println("Tebrikler! Oyunu kazandınız!");
                    break;
                }

            } else {
                // Değ.Formu 13-15: Kullanıcı mayına bastığında oyunu kaybeder. Kullanıcıya bilgi verilir
                System.out.println();
                System.out.println("--------------------------");
                System.out.println("Game over!");
                gameOver();
                break;
            }
        }
    }

    public void gameOver() {
        for (int i = 0; i < rowNumber; i++) {
            for (int k = 0; k < colNumber; k++) {
                if (map[i][k] == -1) {
                    board[i][k] = map[i][k];
                }
            }
        }
        print(board);
    }

    // Değ.Formu 14: Tüm noktalar mayınsız bir şekilde seçilirse oyunu kazanmanın kontrolü yapılır
    public boolean isWin() {
        return selectedSpaces == emptySpaces;
    }

    // Girilen değerlerin etrafındaki mayın sayısı kontrol edilir
    public int isCheck(int row, int col) {
        int count = 0;

        int lowerBoundRow = row - 1;
        int upperBoundRow = row + 1;
        int lowerBoundCol = col - 1;
        int upperBoundCol = col + 1;

        // Dizi sınırlarından çıkmamak için değer 0'dan küçük ise 0'a eşitlenir
        // ve dizi sınırlarının dışına çıkmaması için değer ataması yapılır
        if (lowerBoundRow < 0) {
            lowerBoundRow = 0;
        }
        if (upperBoundRow >= rowNumber) {
            upperBoundRow = rowNumber - 1;
        }
        if (lowerBoundCol < 0) {
            lowerBoundCol = 0;
        }
        if (upperBoundCol >= colNumber) {
            upperBoundCol = colNumber - 1;
        }
        // Girilen nokta etrafındaki mayın sayısı kontrol edilir
        for (int k = lowerBoundRow; k <= upperBoundRow; k++) {
            for (int m = lowerBoundCol; m <= upperBoundCol; m++) {
                if (map[k][m] == -1) {
                    count++;
                }
            }
        }

        return count;
    }

    // Oyunun başlangıcı için map ve board hazırlanır
    public void prepareGame() {
        // Mayın olmayan yerler -2 değerini alır
        for (int i = 0; i < rowNumber; i++) {
            for (int k = 0; k < colNumber; k++) {
                map[i][k] = -2;
                board[i][k] = -2;
            }
        }

        // Değer. Formu 8: Diziye eleman sayısının çeyreği kadar rastgele mayın yerleştirilir
        int randRow, randCol;
        int count = 0;
        while (count != mineCount) {
            randRow = rand.nextInt(rowNumber);
            randCol = rand.nextInt(colNumber);
            if (map[randRow][randCol] != -1) {
                map[randRow][randCol] = -1;
                count++;
            }
        }
    }

    // Print metodu içerisinde map'te mayın olan kısımlar (-1) "*";
    // olmayan kısımlar (-2) "-" ile ekrana yazdırılır
    public void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == -1) {
                    System.out.print("*");
                } else if (arr[i][j] == -2) {
                    System.out.print("-");
                } else {
                    System.out.print(arr[i][j]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}


