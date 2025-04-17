package editor;

import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.nio.file.*;

public class FileManager {

    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            textEditor.currentFile = file;

            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                textArea.setText(content);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor, "Error opening file: " + e.getMessage());
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                // Get the file selected by the user
                File file = fileChooser.getSelectedFile();
                textEditor.currentFile = file;

                try {
                    Files.write(file.toPath(), textArea.getText().getBytes());
                    JOptionPane.showMessageDialog(textEditor, "File saved successfully");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(textEditor,"Error saving file: " + e.getMessage());
                }
            }
        } else {
            try {
                Files.write(textEditor.currentFile.toPath(), textArea.getText().getBytes());
                JOptionPane.showMessageDialog(textEditor, "File saved successfully");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor,"Error saving file: " + e.getMessage());
            }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textArea.setText("");
        textEditor.currentFile = null;
    }
}
