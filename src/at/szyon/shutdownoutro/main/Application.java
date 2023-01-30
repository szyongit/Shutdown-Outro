package at.szyon.shutdownoutro.main;

import java.io.File;

public class Application {
    private static int time = 30;
    private static boolean isError = false;
    public static void main(String[] args) throws Exception {
        System.out.println("Shutdown-Outro by Szyon");
        System.out.println("Music: TheFatRat - Xenogenesis");
        System.out.println();

        Thread musicThread = new Thread(() -> {
            try {
                Handler.playOutro();
            } catch (Exception e) {
                time = -1;
                isError = true;
                System.out.println("[Error]: " + e.getMessage());
            }
        });
        musicThread.start();

        while(time > 0) {
            long startTime = System.nanoTime();

            if(time == 30 || time == 15 || time == 10 || time == 5 || time == 4 || time == 3 || time == 2 || time == 1) {
                System.out.println("Shutting down in " + time + " seconds...");
            }

            time--;

            while ((long)(startTime + 1e+9) > System.nanoTime()) {
                Thread.sleep(0L, 10);
            }
        }

        if(time == -1 && isError) return;

        System.out.println("Good bye :D");
        Handler.shutdown();
    }
}
