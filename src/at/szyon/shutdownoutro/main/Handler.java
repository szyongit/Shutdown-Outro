package at.szyon.shutdownoutro.main;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Handler {
    public static void shutdown() throws IOException {
        String operatingSystem = System.getProperty("os.name");

        if(operatingSystem.equals("Linux") || operatingSystem.equals("Mac OS X")) {
            Runtime.getRuntime().exec("shutdown -h now");
            System.exit(0);
            return;
        }

        if(operatingSystem.contains("Windows")) {
            Runtime.getRuntime().exec("shutdown.exe -s -t 0");
            System.exit(0);
            return;
        }

        System.out.println("[Error]: Unsupported operating system!");
        System.exit(0);
    }

    public static void playOutro() throws Exception {
        InputStream inputStream = Handler.class.getResourceAsStream("/rsc/TheFatRat - Xenogenesis Outro.wav");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(bufferedInputStream));
        clip.start();

        do { Thread.sleep(20); } while(clip.isRunning());

        clip.drain();
    }
}
