package YGNY;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.imageio.ImageIO;

public class LanguageSelectionPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private KioskFrame parent;
    private Image backgroundImage; // 배경 이미지를 저장할 변수
    private String currentBackgroundPath; // 현재 배경 이미지의 경로를 저장할 변수

    public LanguageSelectionPanel(KioskFrame parent) {
        this.parent = parent;

        // 초기 배경 이미지 설정
        setBackgroundImage("background.png");

        setLayout(null); // 배치 관리자를 사용하지 않음

        // "usa.png" 이미지를 클릭 가능한 버튼으로 추가하고 크기 조정
        ImageIcon usaIcon = new ImageIcon("usa.png");
        JButton usaButton = new JButton(usaIcon);
        usaButton.setBounds(5, 10, usaIcon.getIconWidth(), usaIcon.getIconHeight()); // 버튼 위치와 크기 조정
        usaButton.setBorderPainted(false); // 버튼의 테두리를 숨김
        usaButton.setContentAreaFilled(false); // 버튼의 내용 영역을 투명하게 설정
        usaButton.addActionListener(e -> handleUSAButtonClick()); // 버튼 클릭 시 이벤트 추가
        add(usaButton);

        // "korea.png" 이미지를 클릭 가능한 버튼으로 추가하고 크기 조정
        ImageIcon koreaIcon = new ImageIcon("korea.png");
        JButton koreaButton = new JButton(koreaIcon);
        koreaButton.setBounds(usaIcon.getIconWidth() + 10, 10, koreaIcon.getIconWidth(), koreaIcon.getIconHeight()); // 버튼 위치와 크기 조정
        koreaButton.setBorderPainted(false); // 버튼의 테두리를 숨김
        koreaButton.setContentAreaFilled(false); // 버튼의 내용 영역을 투명하게 설정
        koreaButton.addActionListener(e -> handleKoreaButtonClick()); // 버튼 클릭 시 이벤트 추가
        add(koreaButton);

        // 마우스 클릭 이벤트 처리
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick();
            }
        });
    }

    // USA 버튼 클릭 시 처리
    private void handleUSAButtonClick() {
        setBackgroundImage("background_eng.png"); // USA 버튼을 누르면 배경을 background_eng.png로 변경
    }

    // Korea 버튼 클릭 시 처리
    private void handleKoreaButtonClick() {
        setBackgroundImage("background.png"); // Korea 버튼을 누르면 배경을 background.png로 변경
    }

    // 마우스 클릭 시 처리
    private void handleClick() {
        // 배경이 background.png 일 때만 메인 화면으로 이동
        if ("background.png".equals(currentBackgroundPath)) {
            parent.switchToMainScreen();
        }
    }

    // 배경 이미지 설정 메서드
    private void setBackgroundImage(String imagePath) {
        currentBackgroundPath = imagePath; // 현재 배경 이미지 경로 업데이트
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
            repaint(); // 배경 이미지 변경 후 화면 다시 그리기
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
