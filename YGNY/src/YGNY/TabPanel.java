package YGNY;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

//10개 이상이면 보증금 바뀌는 코드 완료

// 메뉴를 고르는 키오스크 탭 화면
public class TabPanel extends JPanel {
    // 탭 화면으로 구성되는 탭 팬
    private JTabbedPane tp;
    // 3개의 탭 메뉴들의 이름
    //참고 코드
    private static String[] tabTitle = {"텀블러", "다회용기", "식기"};
    // 각각의 탭 화면을 구성하는 JPanel
    private JPanel[] menuPanel = new JPanel[tabTitle.length];
    // 메뉴 버튼 title
    public static String[][] menuTitle = new String[tabTitle.length][];

    // 메뉴 버튼
    private JButton[][] menuButton = new JButton[tabTitle.length][];
    // 버튼 이미지 경로

    private String imgPath = "/resources/icecream/";
    // 이미지 파일명 // (첨부할시)
    private String[][] imgName = new String[tabTitle.length][];



    public TabPanel(){
        // 배치관리자
        setLayout(new BorderLayout());
        // 폰트
        Font font = new Font("맑은 고딕", Font.PLAIN, 20);
        


        // 탭 팬 생성
        UIManager.put("TabbedPane.selected", new Color(0xEED0CE));
        tp = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        tp.setFont(font);

        // 메뉴판 패널 init
        GridBagLayout gridbag = new GridBagLayout(); // 배치관리자
        GridBagConstraints constraint = new GridBagConstraints();
//        constraint.fill = GridBagConstraints.BOTH;
        constraint.weightx = 1;
        constraint.weighty = 1;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;

        for(int i=0; i < tabTitle.length; i++){
            menuPanel[i] = new JPanel(); // 패널 생성
            menuPanel[i].setLayout (gridbag);;
            menuPanel[i].setBackground(new Color(0xE9E9E2));

        
            
        }

     

        // 메뉴 버튼 타이틀
        menuTitle[0] = new String [] {"스테인리스텀블러","플라스틱 텀블러 ", "빨대형 텀블러", "뚜껑형 텀블러"};
        menuTitle[1] = new String [] {"스테인리스식기", "유리 식기", "뚜껑 식기","뚜껑X식기" };
        menuTitle[2] = new String [] {"나무젓가락", "나무수저","나무포크"};
    
        

        // 메뉴 이미지 파일명
        for(int i=0; i<tabTitle.length;i++){
            imgName[i] = new String [menuTitle[i].length];
            for(int j=0; j < menuTitle[i].length; j++) {
                // imgName[i][j] = "testImage.jpg";
                imgName[i][j] = Integer.toString(i) + Integer.toString(j) + ".jpg";
            }
        }


        // 메뉴버튼 생성
    for(int i = 0; i < menuTitle.length; i++) {
        menuButton[i] = new JButton[menuTitle[i].length];
        for(int j = 0; j < menuTitle[i].length; j++) {
            // 버튼 이름
            String str = "<html>" + menuTitle[i][j] + "<br>";
            menuButton[i][j] = new JButton(str);
            menuButton[i][j].setVerticalTextPosition(JButton.BOTTOM);  // 텍스트 아래로
            menuButton[i][j].setHorizontalTextPosition(JButton.CENTER); // 텍스트 가운데
            menuButton[i][j].setFont(font);

            // 버튼 이미지
           menuButton[i][j].setIcon(new ImageIcon(imgPath + imgName[i][j])); // 버튼 이미지

            // 버튼 배경색, 테두리
            menuButton[i][j].setBackground(new Color(0xFFFFFF));
            menuButton[i][j].setFocusPainted(false);
            menuButton[i][j].setBorderPainted(false);
            

            menuButton[i][j].addActionListener(new MenuBtnActionListener());
        }
    }

        



        // 각 메뉴판 패널에 메뉴 버튼 붙이기
        for(int i=0; i < menuPanel.length; i++){
            for(int j=0; j < menuButton[i].length; j++){
                gridbag.setConstraints(menuButton[i][j], constraint);
                menuPanel[i].add(menuButton[i][j]);

            }
            //"용량선택" 라벨 추가
            /*
             JLabel capacityLabel = new JLabel("용량선택");
            capacityLabel.setFont(font);
            gridbag.setConstraints(capacityLabel, constraint);
            menuPanel[i].add(capacityLabel);
            */
            
        
            
        }



        // 탭 팬에 메뉴판 패널 붙이기
        for(int i=0; i < tabTitle.length; i++){
//            tp.addTab(tabTitle[i], menuPanel[i]);
            tp.addTab("<html><body><table width='94'>"+tabTitle[i]+"</table></body></html>", menuPanel[i]);
        }


        // 탭 패널에 탭 팬 붙이기
        add("Center", tp);
    } // public TabPanel() 끝 ================

    // 메뉴 버튼 리스너
    class MenuBtnActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            String b = btn.getText();

            // 버튼으로 눌려진 메뉴명
            b = b.split(">")[1];
            b = b.split("<")[0];

            // 장바구니에 담긴 서로 다른 메뉴의 개수
            int tableSize = OrderPanel.model.getRowCount();
            // 장바구니에 담긴 해당 메뉴의 수량
            int quantity = 0;

            //장바구니에 담긴 모든 수량
            int allquantity = 0; 
            
         
            // 이미 장바구니에 담겨져 있는 검사
            boolean inBasket = false;
            int selectedRow = -1; // 존재하면 몇 번째 행인지 저장할 변수

            // 해당 메뉴 가격


            // 장바구니에 이미 담겨있는지 검사
            for(int i=0; i < tableSize; i++){
                // 같은 값이 존재할 때
                if(b.equals(OrderPanel.model.getValueAt(i,0))) {
                    String[] curMenu = new String[2];
                    curMenu[0] = (String) OrderPanel.model.getValueAt(i,1); // 수량
                    curMenu[1] = (String) OrderPanel.model.getValueAt(i,2); // 가격
                    
                    selectedRow = i; // 해당 행 번호 저장
                    quantity = Integer.parseInt(curMenu[0]) + 1; // 수량 + 1
                   
               

                    // table 업데이트
                    OrderPanel.model.setValueAt(Integer.toString(quantity),i,1);
                  

                    allquantity = tableSize + quantity;
                    inBasket = true;
                    break;
                    
                }

            }
            // 새로운 상품이라면
            if(!inBasket){
                tableSize++; // 테이블 사이즈 + 1

                String[] newMenu = new String[3];
                newMenu[0] = b;
                newMenu[1] = "1";
                newMenu[2] = "0";


                // table 업데이트. 새로운 상품이 추가될 때마다  quantity는 초기화됨 
                OrderPanel.model.addRow(newMenu);
                quantity = 1;

            } 
            
            // 
            allquantity = 0;
            for (int i = 0; i < tableSize; i++) {
                allquantity += Integer.parseInt(OrderPanel.model.getValueAt(i, 1).toString());
            }

           
            
            if (allquantity == 0) {
                // 장바구니가 비어있을 경우 합계를 0으로 설정
                OrderPanel.sumPrice.setText(" 0원");
            } else if (allquantity >=10) {
                OrderPanel.sumPrice.setText(" 10,000원");
            } else  {
                // 그 외의 경우에는 일반적인 합계 설정 (여기에서는 5,000원으로 설정)
                OrderPanel.sumPrice.setText(" 5,000원");
            }
            
        
            
          


        }


        
    } // class BtnActionListener implements ActionListener 끝

}// class TabPanel extends JPanel 끝 ================

