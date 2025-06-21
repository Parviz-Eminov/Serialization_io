package org.example;


import java.io.Serializable;
import java.io.*;
import java.util.*;
import java.util.zip.*;

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
}

class Main {

    public static void saveGame(String filePath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
            System.out.println("Game saved to: " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to save game: " + e.getMessage());
        }
    }

    public static void zipFiles(String zipFilePath, List<String> filePaths) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            for (String filePath : filePaths) {
                try (FileInputStream fis = new FileInputStream(filePath)) {
                    ZipEntry entry = new ZipEntry(new File(filePath).getName());
                    zos.putNextEntry(entry);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }

                    zos.closeEntry();
                } catch (IOException e) {
                    System.out.println("Failed to add file to zip: " + filePath);
                }
            }
            System.out.println("Files zipped to: " + zipFilePath);
        } catch (IOException e) {
            System.out.println("Failed to create zip file: " + e.getMessage());
        }
    }

    public static void deleteFiles(List<String> filePaths) {
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (file.delete()) {
                System.out.println("Deleted file: " + filePath);
            } else {
                System.out.println("Failed to delete file: " + filePath);
            }
        }
    }

    public static void main(String[] args) {
        String saveDir = "D:\\Games\\savegames";

        GameProgress gp1 = new GameProgress(100, 3, 5, 100.5);
        GameProgress gp2 = new GameProgress(85, 2, 10, 225.7);
        GameProgress gp3 = new GameProgress(45, 5, 15, 450.3);

        String save1 = saveDir + "/save1.dat";
        String save2 = saveDir + "/save2.dat";
        String save3 = saveDir + "/save3.dat";

        saveGame(save1, gp1);
        saveGame(save2, gp2);
        saveGame(save3, gp3);

        List<String> saves = Arrays.asList(save1, save2, save3);
        String zipFile = saveDir + "/saves.zip";

        zipFiles(zipFile, saves);
        deleteFiles(saves);
    }
}

