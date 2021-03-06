package magic.ui.viewer;

import magic.data.FileIO;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class DeckDescriptionPreview extends JComponent implements PropertyChangeListener {

    private static final long serialVersionUID = 1L;

    File file;
    String description;
    private final JTextArea textArea = new JTextArea();
    private final JScrollPane scrollPane = new JScrollPane(textArea);

    public DeckDescriptionPreview(final JFileChooser fc) {
        setPreferredSize(new Dimension(200, 50));
        setLayout(new BorderLayout());
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        add(scrollPane, BorderLayout.CENTER);
        fc.addPropertyChangeListener(this);
    }

    public void loadDescription() {
        String content = "";
        description = "";
        try { //load deck description
            content = FileIO.toStr(file);
        } catch (final IOException ex) {
            System.err.println("ERROR! Unable to load " + file.getName());
            ex.printStackTrace();
            return;
        }

        final Scanner sc = new Scanner(content);

        while (sc.hasNextLine()) {
            final String line = sc.nextLine().trim();
            if (line.startsWith(">")) {
                description = line.substring(1);
                break;
            }
        }

        showDescription();
    }
    public void showDescription() {
        textArea.setText(description);
        textArea.setCaretPosition(0);
    }

    public void propertyChange(final PropertyChangeEvent e) {
        final String prop = e.getPropertyName();
        if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
            file = (File) e.getNewValue();
            if (isShowing()) {
                loadDescription();
            }
        }
    }
}
