package org.example;

import org.example.WortSL;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author davidbednarik
 * @version 1.0
 */
public class GUIKomponenten extends JPanel
{
    private Controller con;
    private JTextField textField;
    private ImageIcon icon;
    private Image image;
    private JLabel lImage;

    private JPanel south;
    private JPanel southWest;
    private JPanel southCenter;
    private JPanel southEast;

    private JLabel labelRichtigeWoerter;
    private JLabel labelVersuche;
    private JLabel labelQuote;
    private JLabel counterRichtigeWoerter;
    private JLabel counterVersuche;
    private JLabel average;

    public JButton reset;
    public JButton addWort;
    public JButton zurueck;
    public JButton save;

    public GUIKomponenten(Controller con) throws MalformedURLException
    {
        this.con = con;
        BorderLayout basis = new BorderLayout();
        this.setLayout(basis);


        this.textField = new JTextField();
        this.textField.setOpaque(true);
        this.textField.setPreferredSize(new Dimension(450, 20));

        GridLayout grid  = new GridLayout(1,3);

        this.south = new JPanel();
        this.south.setLayout(grid);

        this.southWest = new JPanel(new GridLayout(3,1));
        this.labelRichtigeWoerter = new JLabel("Richtige Wörter:");
        this.labelRichtigeWoerter.setPreferredSize(new Dimension(200, 20));
        this.labelVersuche = new JLabel("Versuche:");
        this.labelVersuche.setPreferredSize(new Dimension(200, 20));
        this.labelQuote = new JLabel("Quote:");
        this.labelQuote.setPreferredSize(new Dimension(200, 20));
        this.southWest.add(labelRichtigeWoerter);
        this.southWest.add(labelVersuche);
        this.southWest.add(labelQuote);

        this.southCenter = new JPanel(new GridLayout(3, 1));
        this.counterRichtigeWoerter = new JLabel("0");
        this.counterVersuche = new JLabel("0");
        this.average = new JLabel("0%");
        this.counterRichtigeWoerter.setPreferredSize(new Dimension(200, 20));
        this.counterVersuche.setPreferredSize(new Dimension(200, 20));
        this.average.setPreferredSize(new Dimension(200, 20));
        this.southCenter.add(counterRichtigeWoerter);
        this.southCenter.add(counterVersuche);
        this.southCenter.add(average);

        this.southEast = new JPanel(new GridLayout(4, 1));
        this.reset = new JButton("Reset");
        this.reset.addActionListener(this.con);
        this.addWort = new JButton("Wort hinzufügen");
        this.addWort.addActionListener(this.con);
        this.zurueck = new JButton("Spiel beenden");
        this.zurueck.addActionListener(this.con);
        this.save = new JButton("Save");
        this.save.addActionListener(this.con);
        this.southEast.add(reset);
        this.southEast.add(addWort);
        this.southEast.add(zurueck);
        this.southEast.add(save);


        this.south.add(southWest);
        this.south.add(southCenter);
        this.south.add(southEast);


        try
        {
            this.icon = new ImageIcon(new URL(con.getTrainer().getAktuellerEintrag().getUrl()));
        } catch (MalformedURLException err)
        {
            err.printStackTrace();
            return;
        }
        this.image = icon.getImage(); // umwandeln in ein Image-Objekt
        this.image = image.getScaledInstance(200, 200,  Image.SCALE_SMOOTH); // skalieren auf gewünschte Größe
        this.lImage = new JLabel(new ImageIcon(image));


        this.add(lImage, basis.CENTER);
        this.add(textField, basis.NORTH);
        this.add(south, basis.SOUTH);
    }

    public String getText()
    {
        String r = this.textField.getText();
        this.textField.setText("");
        return r;
    }
    public void statistikAkt(int correct, int versuche)
            throws MalformedURLException
    {
        try
        {
            this.icon = new ImageIcon(new URL(con.getTrainer().getAktuellerEintrag().getUrl()));
        } catch (MalformedURLException err)
        {
            err.printStackTrace();
            return;
        }
        this.image = icon.getImage(); // umwandeln in ein Image-Objekt
        this.image = image.getScaledInstance(200, 200,  Image.SCALE_SMOOTH); // skalieren auf gewünschte Größe
        this.lImage.setIcon(new ImageIcon(image));


        this.counterVersuche.setText(String.valueOf(versuche));
        this.counterRichtigeWoerter.setText(String.valueOf(correct));
        this.average.setText(String.valueOf(con.getTrainer().getStatistik().quote()));
    }
    public void speichern()
    {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(null);
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            try
            {
                new WortSL().save(con.getTrainer(), selectedFile.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Fehler beim speichern");
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                new WortSL().save(con.getTrainer());
            } catch (IOException e)
            {
                JOptionPane.showMessageDialog(null, "Fehler beim speichern");
                e.printStackTrace();
            }
        }
    }



}
