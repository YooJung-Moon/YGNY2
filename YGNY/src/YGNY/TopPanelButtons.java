package YGNY;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

public class TopPanelButtons extends JPanel {
    private static final long serialVersionUID = 1L;

    public TopPanelButtons(Runnable goBackAction, Runnable switchToMainScreenAction) {
        setLayout(new BorderLayout());

        // 이미지 아이콘 로드 및 버튼 생성
        JButton btnBack = createButton("back.png", 50, 50);
        JButton btnHome = createButton("home.png", 50, 50);

        // 이벤트 리스너 설정
        btnBack.addActionListener(e -> goBackAction.run());
        btnHome.addActionListener(e -> switchToMainScreenAction.run());

        add(btnBack, BorderLayout.WEST);
        add(btnHome, BorderLayout.EAST);
    }

    private JButton createButton(String imagePath, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File(imagePath)));
            Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            JButton button = new JButton(new ImageIcon(image));
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            return button;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
