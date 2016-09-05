/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delmar.devs.swing;

import com.delmar.devs.model.GenModelDto;
import com.delmar.devs.service.CodeGenerationService;
import com.delmar.utils.DateTimeDecorator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class GenCodeJFrame extends javax.swing.JFrame {
    private static ApplicationContext applicationContext;
    private static final String EMPTY="无";
    private List<GenModelDto> genModelDtoList=new ArrayList<>();
    private Map<String,GenModelDto> childrenMap=new HashMap<>();
    private Map<String,File> outModuleMap=new HashMap<>();
    private String[] values=null;
    /**
     * Creates new form GenCodeJFrame
     */
    private GenCodeJFrame() {
        initComponents();
        init();
    }
    private void updateChildModuleList(String text)
    {
        List<String> childItems=new ArrayList<>();
        for(GenModelDto model:genModelDtoList)
        {
            if(!model.getTableName().equals(text))
            {
                childrenMap.put(model.getTableName(), model);
                childItems.add(model.getTableName());
            }
        }
         values=new String[childItems.size()];
        childItems.toArray(values);
        childrenModel.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = values;
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }

    private void init()
    {
        this.setTitle("SWMS 代码生成工具");
        // Listen for changes in the text
        tableName.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateChildModuleList(tableName.getText());
            }
            public void removeUpdate(DocumentEvent e) {
                updateChildModuleList(tableName.getText());
            }
            public void insertUpdate(DocumentEvent e) {
                updateChildModuleList(tableName.getText());
            }

        });
        genServiceCheck.setSelected(true);
        genActionCheck.setSelected(true);

        childrenModel.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        outPutLocation.removeAllItems();

        File directory = new File("");
        msgText.setText(directory.getAbsolutePath());
        File currentFolder=new File(directory.getAbsolutePath());
        File[] files= currentFolder.listFiles();
        if(files!=null)
            for(File f:files)
            {
                if(f.isDirectory())
                {
                    String name=f.getName().substring( directory.getName().length());
                    if(!name.contains(".")&&!name.contains("web")&&!name.contains("dev")&&!name.contains("doc")&&!name.contains("util"))
                    {outPutLocation.addItem(name);
                        outModuleMap.put(name, f);
                    }
                }

            }
    }
    private void updateForm(String table_name)
    {
        GenModelDto genModel=null;
        for(GenModelDto model:genModelDtoList)
        {
            if(model.getTableName().equals(table_name))
            {
                genModel=model;
                break;
            }
        }
        assert genModel != null;
        tableName.setText(genModel.getTableName());
        objectName.setText(genModel.getModelName());
        moduleName.setText(genModel.getModule());
        remarkName.setText(genModel.getRemark());
        genServiceCheck.setSelected(genModel.isGenerateService());
        genActionCheck.setSelected(genModel.isGenerateWeb());
        outPutLocation.setSelectedItem(genModel.getOutputModule());
        trlCheck.setSelected(genModel.isTrl());
        this.paging.setSelected(genModel.isPagingByDb());
        List<GenModelDto> includeModelList= genModel.getIncludeModelList();
        if(includeModelList!=null)
        {
            for(GenModelDto genModelDto:includeModelList)
            {
                this.childrenModel.setSelectedValue(genModelDto.getTableName(), rootPaneCheckingEnabled);
            }
        }



    }
    private void updateTable()
    {
        modelList.removeAll();
        int i=0;
        Object[][] content=  new Object [genModelDtoList.size()][10];

        for(GenModelDto genModelDto:genModelDtoList)
        {
            content[i][0]=genModelDto.getTableName();
            content[i][1]=genModelDto.getModelName();
            content[i][2]=genModelDto.getModule();
            content[i][3]=genModelDto.getRemark();
            content[i][4]=genModelDto.isGenerateService()?"是":"否";
            content[i][5]=genModelDto.isGenerateWeb()?"是":"否";
            String lineName="";
            if(genModelDto.getIncludeModelList()!=null)
                for(GenModelDto line:genModelDto.getIncludeModelList())
                {
                    lineName+=" "+line.getTableName();
                }

            content[i][6]=lineName;
            content[i][7]=genModelDto.getOutputModule();
            content[i][8]=genModelDto.isTrl()?"是":"否";
            content[i][9]=genModelDto.isPagingByDb()?"是":"否";
            i++;
        }
        DefaultTableModel defaultTableModel=  new javax.swing.table.DefaultTableModel(
                content,
                new String [] {
                        "表名", "对象名", "模块名", "名称", "生成Service", "生成Action", "子模块", "代码输出位置", "是否翻译","物理分页"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false, false,false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

        modelList.setModel(defaultTableModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        modelPanel = new javax.swing.JPanel();
        table_label = new javax.swing.JLabel();
        tableName = new javax.swing.JTextField();
        objectLabel = new javax.swing.JLabel();
        objectName = new javax.swing.JTextField();
        moduleLabel = new javax.swing.JLabel();
        moduleName = new javax.swing.JTextField();
        remarkLabel = new javax.swing.JLabel();
        remarkName = new javax.swing.JTextField();
        childrenModuleLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        genServiceCheck = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        genActionCheck = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        outPutLocation = new javax.swing.JComboBox<>();
        addBtn = new javax.swing.JButton();
        genCode = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        msgText = new javax.swing.JTextArea();
        deleteRow = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        trlCheck = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        childrenModel = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        paging = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        modelList = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table_label.setText("表名");

        objectLabel.setText("对象名");

        moduleLabel.setText("模块");

        remarkLabel.setText("名称");

        childrenModuleLabel.setText("子模块");

        jLabel1.setText("生成Service");

        genServiceCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genServiceCheckActionPerformed(evt);
            }
        });

        jLabel2.setText("生成action");

        jLabel3.setText("业务代码输出位置");

        outPutLocation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        addBtn.setText("添加");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        genCode.setText("生成代码");
        genCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genCodeActionPerformed(evt);
            }
        });

        msgText.setEditable(false);
        msgText.setColumns(20);
        msgText.setRows(5);
        jScrollPane2.setViewportView(msgText);

        deleteRow.setText("删除选择行");
        deleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRowActionPerformed(evt);
            }
        });

        jLabel4.setText("是否翻译表");

        trlCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trlCheckActionPerformed(evt);
            }
        });

        childrenModel.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        childrenModel.setFixedCellHeight(20);
        childrenModel.setFixedCellWidth(100);
        jScrollPane3.setViewportView(childrenModel);

        jLabel5.setText("物理分页");

        paging.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modelPanelLayout = new javax.swing.GroupLayout(modelPanel);
        modelPanel.setLayout(modelPanelLayout);
        modelPanelLayout.setHorizontalGroup(
                modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(modelPanelLayout.createSequentialGroup()
                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(modelPanelLayout.createSequentialGroup()
                                                .addGap(54, 54, 54)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(66, 66, 66)
                                                .addComponent(jScrollPane2))
                                        .addGroup(modelPanelLayout.createSequentialGroup()
                                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(modelPanelLayout.createSequentialGroup()
                                                                .addComponent(childrenModuleLabel)
                                                                .addGap(127, 127, 127)
                                                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(modelPanelLayout.createSequentialGroup()
                                                                                .addGap(289, 289, 289)
                                                                                .addComponent(addBtn)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(genCode)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(deleteRow))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modelPanelLayout.createSequentialGroup()
                                                                                .addComponent(outPutLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(253, 253, 253))))
                                                        .addGroup(modelPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modelPanelLayout.createSequentialGroup()
                                                                                .addComponent(table_label)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(tableName, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(objectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(27, 27, 27))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modelPanelLayout.createSequentialGroup()
                                                                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(jLabel3)
                                                                                        .addGroup(modelPanelLayout.createSequentialGroup()
                                                                                                .addComponent(remarkLabel)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(remarkName)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jLabel1)))
                                                                                .addGap(18, 18, 18)))
                                                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(modelPanelLayout.createSequentialGroup()
                                                                                .addComponent(genServiceCheck)
                                                                                .addGap(40, 40, 40)
                                                                                .addComponent(jLabel2))
                                                                        .addComponent(objectName, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(modelPanelLayout.createSequentialGroup()
                                                                                .addComponent(genActionCheck)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jLabel5))
                                                                        .addComponent(jLabel4)
                                                                        .addComponent(moduleLabel))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(trlCheck)
                                                                        .addComponent(paging)
                                                                        .addComponent(moduleName, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(0, 67, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        modelPanelLayout.setVerticalGroup(
                modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(modelPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(modelPanelLayout.createSequentialGroup()
                                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(table_label)
                                                        .addComponent(tableName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(objectLabel)
                                                        .addComponent(objectName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(moduleLabel)
                                                        .addComponent(moduleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(modelPanelLayout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(remarkLabel)
                                                                        .addComponent(remarkName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel1)
                                                                        .addComponent(genServiceCheck)))
                                                        .addGroup(modelPanelLayout.createSequentialGroup()
                                                                .addGap(10, 10, 10)
                                                                .addComponent(jLabel2))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modelPanelLayout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel5)
                                                                        .addComponent(paging)))))
                                        .addComponent(genActionCheck))
                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(modelPanelLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(modelPanelLayout.createSequentialGroup()
                                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(modelPanelLayout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(childrenModuleLabel)
                                                                        .addComponent(jLabel3)
                                                                        .addComponent(outPutLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel4)))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modelPanelLayout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(trlCheck)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(addBtn)
                                                        .addComponent(genCode)
                                                        .addComponent(deleteRow))
                                                .addGap(8, 8, 8)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(40, 40, 40))
        );

        modelList.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "表名", "对象名", "模块名", "名称", "生成Service", "生成Action", "子模块", "代码输出位置", "是否翻译", "物理分页"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        modelList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modelListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(modelList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(modelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(modelPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(152, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void genServiceCheckActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {
        GenModelDto genModelDto=null;
        boolean include=false;
        for(GenModelDto gen:genModelDtoList)
        {
            if(gen.getTableName().equals(tableName.getText()))
            {
                include=true;
                genModelDto=gen;
                break;
            }
        }
        if(genModelDto==null)
            genModelDto=new GenModelDto();
        genModelDto.setTableName(tableName.getText().trim());
        genModelDto.setModule(moduleName.getText().trim());
        genModelDto.setModelName(objectName.getText().trim());

        genModelDto.setRemark(remarkName.getText().trim());
        genModelDto.setGenerateService(genServiceCheck.isSelected());
        genModelDto.setGenerateWeb(genActionCheck.isSelected());
        genModelDto.setOutputModule(outPutLocation.getSelectedItem().toString());
        genModelDto.setTrl(trlCheck.isSelected());
        if(!include)
        {
            genModelDtoList.add(genModelDto);
        }

        List<String> selectedList= childrenModel.getSelectedValuesList();
        if(selectedList!=null)
        {
            List<GenModelDto> includeModelList= new ArrayList<>();
            for(String childModel:selectedList)
            {
                GenModelDto child=  this.childrenMap.get(childModel);
                includeModelList.add(child);
            }
            genModelDto.setIncludeModelList(includeModelList);
        }
        genModelDto.setPagingByDb(paging.isSelected());
        updateTable();
    }

    private void deleteRowActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow=modelList.getSelectedRow();
        DefaultTableModel  model= (DefaultTableModel) modelList.getModel();
        model.removeRow(selectedRow);
    }

    private void trlCheckActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void genCodeActionPerformed(java.awt.event.ActionEvent evt) {
        msgText.setText(DateTimeDecorator.dateToLongString(new Date())+":正在生成代码\n");
        if(genModelDtoList.size()>0)
        {
            for(GenModelDto genModelDto:genModelDtoList)
            {
                genModelDto.setOutputPath(outModuleMap.get(genModelDto.getOutputModule()));
            }

            CodeGenerationService codeGenerationService= applicationContext.getBean(CodeGenerationService.class);
            try
            {
                codeGenerationService.generateMapperAndModel(genModelDtoList);

                msgText.append(DateTimeDecorator.dateToLongString(new Date())+":生成代码成功\n");
            }
            catch (Exception e)
            {
                msgText.append(DateTimeDecorator.dateToLongString(new Date())+":生成代码失败\n"+e.getMessage());
            }

        }


    }

    private void modelListMouseClicked(java.awt.event.MouseEvent evt) {
        DefaultTableModel  model= (DefaultTableModel) modelList.getModel();
        int selectedRow= modelList.getSelectedRow();
        String value= model.getValueAt(selectedRow, 0).toString();
        updateForm(value);
    }

    private void pagingActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenCodeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if(UIManager.getLookAndFeel().isSupportedLookAndFeel()){
                    final String platform = UIManager.getSystemLookAndFeelClassName();
                    // If the current Look & Feel does not match the platform Look & Feel,
                    // change it so it does.
                    if (!UIManager.getLookAndFeel().getName().equals(platform)) {
                        try {
                            UIManager.setLookAndFeel(platform);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
                GenCodeJFrame genCodeFrame=  new GenCodeJFrame();
                int windowWidth = genCodeFrame.getWidth(); // 获得窗口宽
                int windowHeight = genCodeFrame.getHeight(); // 获得窗口高
                Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
                Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
                int screenWidth = screenSize.width; // 获取屏幕的宽
                int screenHeight = screenSize.height; // 获取屏幕的高
                genCodeFrame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// 设置窗口居中显示
                genCodeFrame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton addBtn;
    private javax.swing.JList<String> childrenModel;
    private javax.swing.JLabel childrenModuleLabel;
    private javax.swing.JButton deleteRow;
    private javax.swing.JCheckBox genActionCheck;
    private javax.swing.JButton genCode;
    private javax.swing.JCheckBox genServiceCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable modelList;
    private javax.swing.JPanel modelPanel;
    private javax.swing.JLabel moduleLabel;
    private javax.swing.JTextField moduleName;
    private javax.swing.JTextArea msgText;
    private javax.swing.JLabel objectLabel;
    private javax.swing.JTextField objectName;
    private javax.swing.JComboBox<String> outPutLocation;
    private javax.swing.JCheckBox paging;
    private javax.swing.JLabel remarkLabel;
    private javax.swing.JTextField remarkName;
    private javax.swing.JTextField tableName;
    private javax.swing.JLabel table_label;
    private javax.swing.JCheckBox trlCheck;
    // End of variables declaration
}
