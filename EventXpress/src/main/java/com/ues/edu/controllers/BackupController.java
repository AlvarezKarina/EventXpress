package com.ues.edu.controllers;

import dorkbox.notify.DesktopNotify;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author A5155456HP
 */
@WebServlet(name = "BackupController", urlPatterns = {"/BackupController"})
public class BackupController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String host = "localhost";
        String port = "5432";
        String user = "postgres";
        String password = "root";
        String dbName = "EventXpress";
        String backupFileName = "EventXpress.backup";
        String backupFolderPath = "C:\\Backup";
        String path = backupFolderPath + "\\" + backupFileName;
        File directory = new File(backupFolderPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File existingBackup = new File(path);
        if (existingBackup.exists()) {
            int counter = 1;
            while (existingBackup.exists()) {
                backupFileName = "EventXpress" + counter + ".backup";
                path = backupFolderPath + "\\" + backupFileName;
                existingBackup = new File(path);
                counter++;
            }
        }
        ProcessBuilder pb = new ProcessBuilder(
                "C:\\Program Files\\PostgreSQL\\13\\bin\\pg_dump.exe",
                "--host", host,
                "--port", port,
                "--username", user,
                "--no-password",
                "--format", "custom",
                "--blobs",
                "--verbose", "--file", path, dbName);
        Map<String, String> env = pb.environment();
        env.put("PGPASSWORD", password);
        try {
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.err.println(line);
            }
            reader.close();
            int exitCode = p.waitFor();
            if (exitCode == 0) {
//                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
//                DesktopNotify.showDesktopMessage("Mensaje", "Backup generado correctamente.", DesktopNotify.SUCCESS, 4000);
                JOptionPane.showMessageDialog(null, "Backup generado correctamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            } else {
//                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
//                DesktopNotify.showDesktopMessage("Mensaje", "Error al generar el backup.", DesktopNotify.ERROR, 4000);
                JOptionPane.showMessageDialog(null, "Error al generar el backup.", "Mensaje", JOptionPane.ERROR_MESSAGE);

            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
//            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
//            DesktopNotify.showDesktopMessage("Mensaje", "Error al generar el backup.Consulte los registros para obtener más detalles.", DesktopNotify.ERROR, 4000);
            JOptionPane.showMessageDialog(null, "Error al generar el backup.Consulte los registros para obtener más detalles.", "Mensaje", JOptionPane.ERROR_MESSAGE);

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
