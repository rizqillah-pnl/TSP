/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program2;

import java.util.Scanner;

/**
 *
 * @author Mayor Kucing
 */
public class TSP {

    static int tspdp(int jarak[][], int tour[], int mulai, int jlhKota) {
        int mintour[] = new int[10], temp[] = new int[10], mincost = 999, ccost, i, j, k;

        if (mulai == jlhKota - 1) {
            return (jarak[tour[jlhKota - 1]][tour[jlhKota]] + jarak[tour[jlhKota]][1]);
        }

        for (i = mulai + 1; i <= jlhKota; i++) {

            for (j = 1; j <= jlhKota; j++) {
                temp[j] = tour[j];
            }

            temp[mulai + 1] = tour[i];
            temp[i] = tour[mulai + 1];

            if ((jarak[tour[mulai]][tour[i]] + (ccost = tspdp(jarak, temp, mulai + 1, jlhKota))) < mincost) {
                mincost = jarak[tour[mulai]][tour[i]] + ccost;

                for (k = 1; k <= jlhKota; k++) {
                    mintour[k] = temp[k];
                }
            }
        }

        // Menambahkan kota-kota yang dikunjungi
        for (i = 1; i <= jlhKota; i++) {
            tour[i] = mintour[i];
        }

        // Mengembalikan nilai jarak terdekat
        return mincost;
    }

    public static void main(String[] args) {
        int kota, i, j, tempuh;
        Scanner in = new Scanner(System.in);
        System.out.print("Input Jumlah Kota : ");
        kota = in.nextInt();

        if (kota == 1) {
            System.out.println("Perjalanan tidak memungkinkan!");
            System.exit(0);
        }

        //get distance of cities from the user
        int jarak[][] = new int[10][10], tour[] = new int[10];

        System.out.println("Masukkan Jarak Tempuh(bentuk Matrix) : ");
        for (i = 1; i <= kota; i++) {
            for (j = 1; j <= kota; j++) {
                jarak[i][j] = in.nextInt();
            }
        }

        for (i = 1; i <= kota; i++) {
            tour[i] = i;
        }

        // Memanggil method tspdp
        // Method ini akan menghitung setiap jarak tempuh dari tiap kota
        tempuh = tspdp(jarak, tour, 1, kota);

        System.out.println("Kota yang dikunjungi : ");
        for (i = 1; i <= kota; i++) {
            // mencetak perjalanan yang dilakukan
            System.out.print(tour[i] + "->");

        }

        // akhir perjalanan akan yaitu 1
        System.out.println("1");
        System.out.println("Jarak tempuh : " + tempuh);
    }
}
