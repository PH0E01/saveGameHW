import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

    public static void saveGame(String pathToFile, GameProgress gameProgress) {
        try (FileOutputStream fileOut = new FileOutputStream(pathToFile);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOut)) {
            objectOutputStream.writeObject(gameProgress);
            System.out.println("Игровой процесс сохранен успешно (★ω★)! ");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void zipFiles(String pathToZipFile, ArrayList<String> filesToZip) {

        //try {
        //    File file = new File(pathToFile);
        //    if (file.createNewFile()) {
        //        System.out.println("Файл создан");
        //    } else {
        //        System.out.println("Файл уже существует");
        //    }
        //} catch (IOException e) {
        //    System.out.println("Ошибка при создании файла");
        //    e.printStackTrace();
        //}


        try (FileOutputStream fileOutputStream = new FileOutputStream(pathToZipFile);
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {
            for (String fileToZip : filesToZip) {
                File file = new File(String.valueOf(fileToZip));
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zipOutputStream.putNextEntry(zipEntry);

                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fileInputStream.read(bytes)) >= 0) {
                        zipOutputStream.write(bytes, 0, length);
                    }
                    zipOutputStream.closeEntry();
                    System.out.println(fileToZip + " zipped Удачно (★ω★)");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //FileOutputStream fileOutputStream = new FileOutputStream(pathToFile);
        //fileOutputStream.write(this.toString().getBytes());
        //fileOutputStream.close();


    }
}





