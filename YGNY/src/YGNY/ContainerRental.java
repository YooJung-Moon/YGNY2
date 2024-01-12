package YGNY;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContainerRental extends JPanel {
    private JRadioButton radioPlastic, radioMetal;
    private JComboBox<String> comboBoxCapacity;
    private JCheckBox checkBoxLid;

    public ContainerRental() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel labelMaterial = new JLabel("소재 선택:");
        ButtonGroup groupMaterial = new ButtonGroup();
        radioPlastic = new JRadioButton("스테인리스");
        radioMetal = new JRadioButton("유리");

        groupMaterial.add(radioPlastic);
        groupMaterial.add(radioMetal);

        JLabel labelCapacity = new JLabel("용량 선택:");
        comboBoxCapacity = new JComboBox<>(new String[]{"500ml", "870ml", "1000ml", "3000ml"});

        JLabel labelLid = new JLabel("뚜껑 여부:");
        checkBoxLid = new JCheckBox("뚜껑 포함");

        JButton btnRent = new JButton("담기");
        btnRent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder rentDetails = new StringBuilder("대여 내역:\n");

                if (radioPlastic.isSelected()) {
                    rentDetails.append("소재: 스테인리스\n");
                } else if (radioMetal.isSelected()) {
                    rentDetails.append("소재: 유리\n");
                }

                rentDetails.append("용량: ").append(comboBoxCapacity.getSelectedItem()).append("\n");

                if (checkBoxLid.isSelected()) {
                    rentDetails.append("뚜껑 포함: 예\n");
                } else {
                    rentDetails.append("뚜껑 포함: 아니오\n");
                }

                // 결과를 다이얼로그 박스가 아닌 다른 방식으로 표시해야 할 수 있음
                JOptionPane.showMessageDialog(null, rentDetails.toString());
            }
        });

        this.add(labelMaterial);
        this.add(radioPlastic);
        this.add(radioMetal);
        this.add(labelCapacity);
        this.add(comboBoxCapacity);
        this.add(labelLid);
        this.add(checkBoxLid);
        this.add(btnRent);
    }
}
