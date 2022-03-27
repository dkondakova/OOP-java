package ru.nsu.ccfit.kondakova.minesweeper.gui;

import ru.nsu.ccfit.kondakova.minesweeper.Box;
import ru.nsu.ccfit.kondakova.minesweeper.Coord;
import ru.nsu.ccfit.kondakova.minesweeper.Game;
import ru.nsu.ccfit.kondakova.minesweeper.Ranges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIpart extends JFrame {
    private Game game;

    private JPanel  panel;
    private JLabel label;
    private JButton newGameButton, aboutButton, highScores;

    private final int COLS = 17;
    private final int ROWS = 17;
    private final int BOMBS = 35;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args) {
        new GUIpart().setVisible(true);
    }

    private GUIpart() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initLabel();
        initButtons();
        initPanel();
        initFrame();
    }

    private void initButtons() {
        newGameButton = new JButton("New Game");
        aboutButton = new JButton("About");
        highScores = new JButton("High Scores");
        newGameButton.setBounds(0, ROWS * IMAGE_SIZE + 15, COLS * IMAGE_SIZE / 3, 25);
        aboutButton.setBounds(COLS * IMAGE_SIZE / 3, ROWS * IMAGE_SIZE + 15, COLS * IMAGE_SIZE / 3, 25);
        highScores.setBounds(COLS * IMAGE_SIZE * 2 / 3, ROWS * IMAGE_SIZE + 15, COLS * IMAGE_SIZE / 3, 25);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == newGameButton) {
                    game.start();
                } else if (e.getSource() == aboutButton) {
                    return;
                } else {
                    return;
                }
            }
        };
        newGameButton.addActionListener(listener);
        aboutButton.addActionListener(listener);
        highScores.addActionListener(listener);
        add(newGameButton);
        add(aboutButton);
        add(highScores);
    }

    private void initLabel() {
        label = new JLabel("Welcome!");
        add(label, BorderLayout.NORTH);
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()) {
                    g.drawImage((Image)game.getBox(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeftButton(coord);
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    game.pressRightButton(coord);
                }
                label.setText(getMessage());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE + 25));
        add(panel);
        /*panel.add(newGameButton);
        panel.add(aboutButton);
        panel.add(highScores);*/
    }

    private String getMessage() {
        switch (game.getState()) {
            case PLAYED: return "Think twice!";
            case BOMBED: return "YOU LOSE!";
            case WINNER: return "YOU WON!";
            default: return "";
        }
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minesweeper");
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setIconImage(getImage("icon"));
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
    }

    private Image getImage (String name) {
        String filename = "resources/img/" + name + ".png";
        ImageIcon icon = new ImageIcon(filename);
        return icon.getImage();
    }
}