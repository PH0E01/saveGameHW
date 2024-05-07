import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GameProgress progress1 = new GameProgress(88, 3, 5, 200.12);
        GameProgress progress2 = new GameProgress(89, 2, 3, 34.78);
        GameProgress progress3 = new GameProgress(90, 5, 7, 210.54);

        progress1.saveGame("C://GameHW/saveGame1.txt", progress1);
        progress2.saveGame("C://GameHW/saveGame2.txt", progress2);
        progress3.saveGame("C://GameHW/saveGame3.txt", progress3);     //C://GameHW/saveGame

        ArrayList<String> filesToZip = new ArrayList<>();
        filesToZip.add("C://GameHW/saveGame1.txt");
        filesToZip.add("C://GameHW/saveGame2.txt");
        filesToZip.add("C://GameHW/saveGame3.txt");

        GameProgress.zipFiles("C://GameHW/saveGame/zip.zip", filesToZip);


        File[] files = new File("saveGames").listFiles();
        for (File file : files) {
            if (!filesToZip.contains(file.getPath())) {
                file.delete();
                System.out.println(file.getName() + " deleted!");
            }
        }
    }
}
