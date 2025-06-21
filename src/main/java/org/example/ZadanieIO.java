package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ZadanieIO {

    public static StringBuilder log = new StringBuilder();

    public static void createFolder() {
        File file = new File("D://Games//");
        if (file.mkdir()) {
            log.append("Directory created: ").append(file.getPath()).append("\n");
        } else {
            log.append("Failed to create the directory: ").append(file.getPath()).append("\n");

        }
    }

    public static void createFolder(String path, String[] newDirs) {
        for (String folder : newDirs) {
            File file = new File(path + folder);
            if (file.mkdir()) {
                log.append("Directory created: ").append(file.getPath()).append("\n");
            } else {
                log.append("Failed to create the directory: ").append(file.getPath()).append("\n");
            }
        }
    }

    public static void createFile(String path, String[] newFiles) {
        try {
            for (String files : newFiles) {
                File file1 = new File(path, files);

                if (file1.createNewFile()) {
                    log.append("File created: ").append(file1.getPath()).append("\n");
                } else {
                    log.append("Failed to create the file: ").append(file1.getPath()).append("\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createLog(String path, String file) {
        File logFile = new File(path, file);

        try (FileWriter writer = new FileWriter(logFile)) {
            writer.write(log.toString());
            System.out.println("The log has been successfully recorded in " + logFile.getPath());
        } catch (IOException e) {
            System.out.println("Error in logging: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        //0. Создание основной папки
        ZadanieIO.createFolder();

        //1.В папке Games создайте несколько директорий: src, res, savegames, temp.
        String[] folders = {"src", "res", "savegames", "temp"};
        ZadanieIO.createFolder("D://Games//", folders);

        //2.В каталоге src создайте две директории: main, test.
        String[] folders1 = {"main", "test"};
        ZadanieIO.createFolder("D://Games//src//", folders1);

        // 3.В подкаталоге main создайте два файла: Main.java, Utils.java.
        String[] files = {"Main.java", "Utils.java"};
        ZadanieIO.createFile("D://Games//src//main//", files);
        // 4. В каталог res создайте три директории: drawables, vectors, icons.
        String[] folders2 = {"drawables", "vectors", "icons"};
        ZadanieIO.createFolder("D://Games//res//", folders2);

        // 5. В директории temp создайте файл temp.txt.
        ZadanieIO.createLog("D://Games//temp//", "temp.txt");

    }
}
