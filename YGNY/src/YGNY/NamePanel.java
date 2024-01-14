package YGNY;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

// 상단 가게명을 name으로 지정
public class NamePanel extends JPanel {
    JLabel title; // 가게명
    public NamePanel(){
        // 배치관리자
        setLayout(new BorderLayout());
        setBackground(new Color(0x7EBCBE));

        // 라벨 및 폰트
        title = new JLabel("용기를 내용");
        Font font = new Font("맑은 고딕", Font.BOLD, 40);
        title.setFont(font);

        // 가운데 정렬
        title.setHorizontalAlignment(JLabel.CENTER);
        add("Center", title);

    }
} // class KioskName extends JPanel 끝 ================
