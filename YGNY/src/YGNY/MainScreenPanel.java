package YGNY;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainScreenPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel contentPanel; // 내용을 표시할 패널
    private Image backgroundImage;

    public MainScreenPanel(KioskFrame parentFrame) {
        // 배경 이미지 로드
        try {
            backgroundImage = ImageIO.read(new File("basic.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());
        
        // TopPanelButtons 인스턴스 생성 및 추가
        TopPanelButtons topPanelButtons = new TopPanelButtons(parentFrame::goBack, parentFrame::switchToLanguageSelection);
        topPanelButtons.setOpaque(false); // 투명하게 설정
        add(topPanelButtons, BorderLayout.NORTH);

        // 버튼 패널 생성
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); //투명
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        Dimension buttonSize = new Dimension(120, 120);
        
        JButton btnContainer = new JButton("용기");
        btnContainer.setOpaque(false); // 투명
        btnContainer.setPreferredSize(buttonSize);
        buttonPanel.add(btnContainer, gbc);

        JButton btnTumbler = new JButton("텀블러");
        btnTumbler.setOpaque(false); // 투명
        btnTumbler.setPreferredSize(buttonSize);
        buttonPanel.add(btnTumbler, gbc);

        JButton btnTool = new JButton("식기류");
        btnTool.setOpaque(false); // 투명
        btnTool.setPreferredSize(buttonSize);
        buttonPanel.add(btnTool, gbc);

        // 내용 패널 생성 및 카드 레이아웃 설정
        contentPanel = new JPanel(new CardLayout());
        contentPanel.setOpaque(false); // 투명
        JPanel emptyPanel = new JPanel(); // 초기에 표시할 빈 패널
        emptyPanel.setOpaque(false); // 투명
        contentPanel.add(emptyPanel, "Empty");

        // 버튼 액션 리스너 설정
        btnContainer.addActionListener(e -> switchToPanel("Container"));
        btnTumbler.addActionListener(e -> switchToPanel("Tumbler"));
        btnTool.addActionListener(e -> switchToPanel("Tool"));

        // JSplitPane에 버튼 패널과 내용 패널 추가
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonPanel, contentPanel);
        splitPane.setDividerLocation(150);

        add(splitPane, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 배경 이미지 그리기
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    private void switchToPanel(String cardName) {
        CardLayout cl = (CardLayout) (contentPanel.getLayout());
        cl.show(contentPanel, cardName);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
