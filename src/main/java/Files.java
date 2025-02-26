package com.github.williwadelmawisky.utils;

import java.io.*;

/**
 * Very generic static helper functions for many different use cases (File operations).
 * This class is not meant to be constructed.
 *
 * @version 26.2.2025
 */
public abstract class Files {

    /**
     * Read all contents of the file
     * @param file A file to read
     * @return Contents of the file (Null if file doesn't exist)
     */
    public static String readAllText(final File file) {
        if (file == null || !file.exists())
            return null;

        final StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }

        return stringBuilder.toString();
    }

    /**
     * Read all contents of the file
     * @param path A path of the file to read
     * @return Contents of the file (Null if file doesn't exist)
     */
    public static String readAllText(final String filePath) {
        final File file = new File(filePath);
        return readAllText(file);
    }


    /**
     * Write a string to a file (Overrides any previous contents)
     * @param file A file to write to
     * @param data A string to write
     * @return True or False depending on the success of the operation
     */
    public static boolean writeAllText(final File file, final String data) {
        if (file == null || data == null)
            return false;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch(IOException ioException) {
            throw new RuntimeException(ioException);
        }

        return true;
    }

    /**
     * Write a string to a file (Overrides any previous contents)
     * @param path A path of the file to write to
     * @param data A string to write
     * @return True or False depending on the success of the operation
     */
    public static boolean writeAllText(final String filePath, final String data) {
        final File file = new File(filePath);
        return write(file, data);
    }


    /**
     * Lists all the files inside the given directory.
     * @param directory A directory to list contents of.
     * @param extensions A list of extensions to include in the listing. If null or empty or contains *, all files are included.
     * @param recursive A flag to include the contents of subdirectories as well. If true, contents of the subdirectories are included.
     * @param proc A function to call for a listed file.
     */
    public static void listFiles(final File directory, final String[] extensions, final boolean recursive, final EventHandler<File> proc) {
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                if (!recursive)
                    continue;

                listFiles(file, extensions, true, proc);
                continue;
            }

            final boolean includeAll = extensions == null || extensions.length == 0 || Arrays.contains(extensions, "*");
            if (includeAll || Arrays.containsFunc(extensions, extension -> file.getName().endsWith(extension)))
                proc.invoke(file);
        }
    }


    /**
     * Get an extension of a file (name.text -> txt)
     * @param file A file to get the extension of
     * @return The extension of the file
     */
    public static String getExtension(final File file) {
        final int index = file.getName().lastIndexOf('.');
        return (index != -1) ? file.getName().substring(index + 1) : null;
    }

    /**
     * Get name of the file without extension (name.txt -> name)
     * @param file A file to get the name of
     * @return The name of the file
     */
    public static String getNameWithoutExtension(final File file) {
        final int index = file.getName().lastIndexOf('.');
        return (dotIndex != -1) ? file.getName().substring(0, dotIndex) : null;
    }
}
