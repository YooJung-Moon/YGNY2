package YGNY;

import javax.swing.*;
import java.awt.*;

public class ReturnPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JLabel messageLabel;
    private JButton completeButton;
    private KioskFrame parent;

    public ReturnPanel(KioskFrame parent) {
        this.parent = parent;
        setLayout(new BorderLayout());

        messageLabel = new JLabel("사용한 다회용기를 기기에 반납해주세요", SwingConstants.CENTER);
        add(messageLabel, BorderLayout.CENTER);

        // 버튼을 위한 별도의 패널 생성 및 FlowLayout 설정
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        // 완료 버튼 설정
        completeButton = new JButton("완료");
        completeButton.setPreferredSize(new Dimension(150, 75)); // 버튼 크기 설정
        buttonPanel.add(completeButton); // 버튼을 별도의 패널에 추가

        // 버튼 패널을 남쪽에 추가
        add(buttonPanel, BorderLayout.SOUTH);

        // 완료 버튼 액션 리스너
        completeButton.addActionListener(e -> {
            messageLabel.setText("반납 중입니다");
            completeButton.setVisible(false); // 버튼 숨기기
            processReturn();
        });
    }

    private void processReturn() {
        Timer timer = new Timer(5000, e -> {
            messageLabel.setText("<html>반납이 완료되었습니다."
                    + "<br>용기 내 주셔서 감사합니다."
                    + "<br>*보증금은 영업일 기준 1~5일 내로 반환됩니다.</html>");
            ((Timer)e.getSource()).stop();
        });
        timer.setRepeats(false);
        timer.start();
    }
}
