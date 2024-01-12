package YGNY;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class KioskFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Stack<String> screenStack;

    public KioskFrame() {
        setTitle("다회용기 대여 키오스크");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        screenStack = new Stack<>();

        LanguageSelectionPanel languageSelectionPanel = new LanguageSelectionPanel(this);
        MainScreenPanel mainScreenPanel = new MainScreenPanel(this);
        ReturnPanel returnPanel = new ReturnPanel(this);

        mainScreenPanel.getContentPanel().add(new ContainerRental(), "Container");
        mainScreenPanel.getContentPanel().add(new TumblerRental(), "Tumbler");
        mainScreenPanel.getContentPanel().add(new ToolRental(), "Tool");

        cardPanel.add(languageSelectionPanel, "LanguageSelection");
        cardPanel.add(mainScreenPanel, "MainScreen");
        cardPanel.add(returnPanel, "ReturnScreen");

        setContentPane(cardPanel);
        navigateTo("LanguageSelection");
        
        // 투명
        cardPanel.setOpaque(false); // cardPanel 투명하게 설정
        languageSelectionPanel.setOpaque(false); // languageSelectionPanel 투명하게 설정
        mainScreenPanel.setOpaque(false); // mainScreenPanel 투명하게 설정
        returnPanel.setOpaque(false); // returnPanel 투명하게 설정
        
        // 프레임을 화면에 표시
        setVisible(true);
    }
    
    // 패널 이동 메서드
    public void navigateTo(String screenName) {
        cardLayout.show(cardPanel, screenName);
        screenStack.push(screenName);
    }
    
    // 뒤로가기 메서드
    public void goBack() {
        if (screenStack.size() > 1) {
            screenStack.pop();
            String previousScreen = screenStack.peek();
            cardLayout.show(cardPanel, previousScreen);
        }
    }
    
    
    public void switchToMainScreen() {
        navigateTo("MainScreen");
    }

    public void switchToLanguageSelection() {
        navigateTo("LanguageSelection");
    }

    public void switchToEmptyScreen() {
        // Switch to an empty screen. 
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.WHITE); // Set the background to white or any other color you prefer
        cardPanel.add(emptyPanel, "EmptyScreen");
        navigateTo("EmptyScreen");
    }
}
