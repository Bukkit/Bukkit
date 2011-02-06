package org.bukkit.fillr;

import org.bukkit.Server;
import org.bukkit.plugin.SimplePluginManager;

import java.io.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Downloader {
    private static final Logger logger = Logger.getLogger(Downloader.class.getName());
    private final File pluginFolder;
    private final File downloadFolder;
    private final File backupFolder;

    public Downloader(Server server) {
        SimplePluginManager pm = (SimplePluginManager) server.getPluginManager();
        pluginFolder = pm.getPluginFolder();
        downloadFolder = new File(pluginFolder, "downloads");
        backupFolder = new File(pluginFolder, "backups");
    }

    /**
     * Downloads the plugin from the given URL. If it is a compressed archive,
     * it tries to get the .jars out of it
     *
     * @param url The url to download from
     * @return True on success, false otherwise
     */
    public boolean download(URL url) {
        if (!pluginFolder.exists()) {
            pluginFolder.mkdir();
        }

        String path = url.getPath();
        int index = path.lastIndexOf('/');
        File file = new File(pluginFolder, path.substring(index + 1));

        return download(url, file);
    }

    /**
     * Downloads the file to a given location
     *
     * @param url The url of the file to download
     * @param file The location to store the file
     */
    private boolean download(URL url, File file) {
        if (!downloadFolder.exists()) {
            downloadFolder.mkdir();
        }

        File temp = new File(downloadFolder, file.getName());
        if (temp.exists()) {
            temp.delete();
        }
        try {
            temp.createNewFile();
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(temp));
            InputStream inputStream = url.openStream();
            copyInputStream(inputStream, outputStream);
            outputStream.close();
            inputStream.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Download failed", ex);
            return false;
        }

        if (file.exists()) {
            backupFile(file);
            file.delete();
        }
        temp.renameTo(file);
        return true;
    }

    /**
     * Copies to an output stream, from an input stream, until EOF
     *
     * @param in The input stream
     * @param out The output stream
     * @throws IOException Thrown when the read or write operation fails
     */
    private static final void copyInputStream(InputStream in, OutputStream out) throws IOException {
        int len;
        byte[] buffer = new byte[4096];

        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }
    }

    /**
     * Moves the file to the backup folder.
     *
     * @param file The file to backup
     */
    private void backupFile(File file) {
        System.out.println("Backing up old file: " + file.getName());
        if (!backupFolder.exists()) {
            backupFolder.mkdir();
        }
        file.renameTo(new File(backupFolder, file.getName() + ".bak"));
    }
}