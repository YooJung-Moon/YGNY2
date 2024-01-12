package YGNY;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolRental extends JPanel {
    private JRadioButton radioPlastic, radioMetal;
    private JCheckBox checkBoxSpoon, checkBoxFork, checkBoxChopsticks;
    private JSpinner spinnerSpoon, spinnerFork, spinnerChopsticks;

    public ToolRental() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel labelMaterial = new JLabel("소재 선택:");
        ButtonGroup groupMaterial = new ButtonGroup();
        radioPlastic = new JRadioButton("스테인리스");
        radioMetal = new JRadioButton("나무");

        groupMaterial.add(radioPlastic);
        groupMaterial.add(radioMetal);

        JLabel labelUtensils = new JLabel("식기 선택:");

        checkBoxSpoon = new JCheckBox("숟가락");
        spinnerSpoon = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        JPanel spoonPanel = new JPanel();
        spoonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        spoonPanel.add(checkBoxSpoon);
        spoonPanel.add(new JLabel("수량:"));
        spoonPanel.add(spinnerSpoon);

        checkBoxFork = new JCheckBox("포크");
        spinnerFork = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        JPanel forkPanel = new JPanel();
        forkPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        forkPanel.add(checkBoxFork);
        forkPanel.add(new JLabel("수량:"));
        forkPanel.add(spinnerFork);

        checkBoxChopsticks = new JCheckBox("젓가락");
        spinnerChopsticks = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        JPanel chopsticksPanel = new JPanel();
        chopsticksPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        chopsticksPanel.add(checkBoxChopsticks);
        chopsticksPanel.add(new JLabel("수량:"));
        chopsticksPanel.add(spinnerChopsticks);

        JButton btnRent = new JButton("담기");

        btnRent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder rentDetails = new StringBuilder("대여 내역:\n");

                if (radioPlastic.isSelected()) {
                    rentDetails.append("소재: 스테인리스\n");
                } else if (radioMetal.isSelected()) {
                    rentDetails.append("소재: 나무\n");
                }

                rentDetails.append("식기 선택: ");
                if (checkBoxSpoon.isSelected()) {
                    rentDetails.append("숟가락(").append(spinnerSpoon.getValue()).append("), ");
                }
                if (checkBoxFork.isSelected()) {
                    rentDetails.append("포크(").append(spinnerFork.getValue()).append("), ");
                }
                if (checkBoxChopsticks.isSelected()) {
                    rentDetails.append("젓가락(").append(spinnerChopsticks.getValue()).append("), ");
                }

                // 결과를 다이얼로그 박스가 아닌 다른 방식으로 표시해야 할 수 있음
                JOptionPane.showMessageDialog(null, rentDetails.toString().replaceAll(", $", ""));
            }
        });

        this.add(labelMaterial);
        this.add(radioPlastic);
        this.add(radioMetal);
        this.add(labelUtensils);
        this.add(spoonPanel);
        this.add(forkPanel);
        this.add(chopsticksPanel);
        this.add(btnRent);
    }
}
