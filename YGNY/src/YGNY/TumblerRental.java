package YGNY;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TumblerRental extends JPanel {
    public TumblerRental() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel labelMaterial = new JLabel("소재 선택:");
        ButtonGroup groupMaterial = new ButtonGroup();
        JRadioButton radioPlastic = new JRadioButton("플라스틱");
        JRadioButton radioMetal = new JRadioButton("스테인리스");

        groupMaterial.add(radioPlastic);
        groupMaterial.add(radioMetal);

        JLabel labelCapacity = new JLabel("용량 선택:");
        JComboBox<String> comboBoxCapacity = new JComboBox<>(new String[]{"350ml", "500ml", "700ml", "1000ml"});

        JLabel labelStraw = new JLabel("빨대 유무:");
        JCheckBox checkBoxStraw = new JCheckBox("빨대 포함");

        JButton btnOrder = new JButton("담기");

        btnOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder orderDetails = new StringBuilder("주문 내역:\n");

                if (radioPlastic.isSelected()) {
                    orderDetails.append("소재: 플라스틱\n");
                } else if (radioMetal.isSelected()) {
                    orderDetails.append("소재: 스테인리스\n");
                }

                orderDetails.append("용량: ").append(comboBoxCapacity.getSelectedItem()).append("\n");

                if (checkBoxStraw.isSelected()) {
                    orderDetails.append("빨대 포함: 예\n");
                } else {
                    orderDetails.append("빨대 포함: 아니오\n");
                }

                // 결과를 다이얼로그 박스가 아닌 다른 방식으로 표시해야 할 수 있음
                JOptionPane.showMessageDialog(null, orderDetails.toString());
            }
        });

        this.add(labelMaterial);
        this.add(radioPlastic);
        this.add(radioMetal);
        this.add(labelCapacity);
        this.add(comboBoxCapacity);
        this.add(labelStraw);
        this.add(checkBoxStraw);
        this.add(btnOrder);
    }
}
