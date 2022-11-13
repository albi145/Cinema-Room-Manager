package cinema;

import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        char[][] room = new char[rows][seats];

        emptyRoom(rows, seats, room);

        boolean shouldContinue = true;

        while (shouldContinue) {

            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1 -> showTheSeats(rows, seats, room);
                case 2 -> buyATicket(rows, seats, room);
                case 3 -> statistics(rows, seats, room);
                case 0 -> shouldContinue = false;
            }
            System.out.println();
        }
    }

    // Creates the empty room when none of the seats are taken. The "S" represents empty seats.
    public static void emptyRoom(int rows, int seats, char[][] room) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                room[i][j] = 'S';
            }
        }
    }

    // Showing how actual look the cinema room. B represent taken seats, S represents free seats.
    public static void showTheSeats(int rows, int seats, char[][] room) {
        System.out.print("Cinema:\n");

        System.out.print("  ");

        for (int i = 1; i <= seats; i++) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int x = 1; x <= rows; x++) {
            System.out.print(x + " ");
            for (int j = 1; j <= seats; j++) {
                System.out.print(room[x - 1][j - 1] + " ");
            }

            System.out.println();
        }
    }

    // Ask for row and seat and in this basis gives the price of that seat.
    // Changing the taken seat symbol from S to B.
    public static void buyATicket(int rows, int seats, char[][] room) {
        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;
        boolean isBigCinema = rows * seats > 60;
        int moreExpensiveRows = rows / 2;

        System.out.println("Enter a row number:");
        int userRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int userSeat = scanner.nextInt();


        do {
            if (userRow > rows || userRow < 1 || userSeat > seats || userSeat < 1) {
                System.out.println();
                System.out.println("Wrong input!");
                System.out.println();
                System.out.println("Enter a row number:");
                userRow = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                userSeat = scanner.nextInt();
            } else if (room[userRow - 1][userSeat - 1] == 'B') {
                System.out.println();
                System.out.println("That ticket has already been purchased!");
                System.out.println();
                System.out.println("Enter a row number:");
                userRow = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                userSeat = scanner.nextInt();
            } else {
                room[userRow - 1][userSeat - 1] = 'B';
                shouldContinue = false;
            }
        } while (shouldContinue);

        int ticketPrice = 10;
        if (isBigCinema && userRow > moreExpensiveRows) {
            ticketPrice = 8;
        }
        System.out.printf("Ticket price: $%d\n", ticketPrice);
    }

     /*
     during the operation of the program, when the user makes changes,
     this method changes the statistics to the current ones.
     */
    public static void statistics(int rows, int seats, char[][] room) {

        int purchasedTickets = 0;
        int currentIncome = 0;
        boolean isBigCinema = rows * seats > 60;
        int moreExpensiveRows = rows / 2;
        int totalIncome = 0;


        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seats; j++) {
                if (room[i - 1][j - 1] == 'B') {
                    purchasedTickets++;
                    if (isBigCinema) {
                        if (i <= moreExpensiveRows) {
                            currentIncome += 10;
                        } else {
                            currentIncome += 8;
                        }
                    } else {
                        currentIncome += 10;
                    }
                }
                if (isBigCinema) {
                    if (i <= moreExpensiveRows) {
                        totalIncome += 10;
                    } else {
                        totalIncome += 8;
                    }
                } else {
                    totalIncome += 10;
                }
            }
        }

        System.out.printf("Number of purchased tickets: %d%n", purchasedTickets);
        double percentage = (double) 100 / (rows * seats) * purchasedTickets;
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);

        System.out.println();
    }
}