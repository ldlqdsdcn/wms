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
 * @author admin
 */
public class InputFrame extends javax.swing.JFrame {
    private static ResourceBundle rb;
    private static ApplicationContext applicationContext;
    private List<GenModelDto> genModelDtoList=new ArrayList<>();
    private Map<String,GenModelDto> childrenMap=new HashMap<>();
    private Map<String,File> outModuleMap=new HashMap<>();
    private String[] tableHeaders;
    private String[] values=null;
    /**
     * Creates new form InputFrame
     */
    public InputFrame() {
        initComponents();
        initLabel();
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
        includeModuleList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = values;
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }
    private void initLabel()
    {
        tableHeaders=new String [] {
                rb.getString("dev.generation.tool.tablename"),  rb.getString("dev.generation.tool.classname"), rb.getString("dev.generation.tool.modulename"),
                rb.getString("dev.generation.tool.name"), rb.getString("dev.generation.tool.gen_service"), rb.getString("dev.generation.tool.generate_action"),
                rb.getString("dev.generation.tool.include_module"), rb.getString("dev.generation.tool.output_location"), rb.getString("dev.generation.tool.is_translation"), rb.getString("dev.generation.tool.is_physical_paging")
        };
        tableLabel.setText(rb.getString("dev.generation.tool.tablename"));
        nameLabel.setText(rb.getString("dev.generation.tool.name"));
        moduleLabel.setText(rb.getString("dev.generation.tool.modulename"));
        classNameLabel.setText(rb.getString("dev.generation.tool.classname"));
        genServiceLabel.setText(rb.getString("dev.generation.tool.gen_service"));
        genActionLabel.setText(rb.getString("dev.generation.tool.generate_action"));
        outputLocationLabel.setText(rb.getString("dev.generation.tool.output_location"));
        includeModule.setText(rb.getString("dev.generation.tool.include_module"));
        transLabel.setText(rb.getString("dev.generation.tool.is_translation"));
        pagingLabel.setText(rb.getString("dev.generation.tool.is_physical_paging"));
        addButton.setText(rb.getString("dev.generation.tool.add"));
        deleteButton.setText(rb.getString("dev.generation.tool.remove_selected_row"));
        generateButton.setText(rb.getString("dev.generation.tool.generate_code"));
        settingMenu.setText(rb.getString("dev.generation.tool.setting"));

        zhCnCheckBoxMenuItem.setText(rb.getString("dev.generation.tool.zh_cn"));
        enUsCheckBoxMenuItem.setText(rb.getString("dev.generation.tool.en_us"));
    }
    private void init()
    {
        this.setTitle(rb.getString("dev.generation.tool.title"));
        // Listen for changes in the text
        this.tableText.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateChildModuleList(tableText.getText());
            }
            public void removeUpdate(DocumentEvent e) {
                updateChildModuleList(tableText.getText());
            }
            public void insertUpdate(DocumentEvent e) {
                updateChildModuleList(tableText.getText());
            }

        });
        genServiceCheck.setSelected(true);
        genActionCheck.setSelected(true);

        includeModuleList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        outputLocationComboBox.removeAllItems();

        File directory = new File("");
        this.msgTextArea.setText(directory.getAbsolutePath());
        File currentFolder=new File(directory.getAbsolutePath());
        File[] files= currentFolder.listFiles();
        if(files!=null)
            for(File f:files)
            {
                if(f.isDirectory())
                {
                    String name=f.getName().substring( directory.getName().length());
                    if(!name.contains(".")&&!name.contains("web")&&!name.contains("dev")&&!name.contains("doc")&&!name.contains("util"))
                    {outputLocationComboBox.addItem(name);
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
        tableText.setText(genModel.getTableName());
        classNameText.setText(genModel.getModelName());
        moduleText.setText(genModel.getModule());
        nameText.setText(genModel.getRemark());
        genServiceCheck.setSelected(genModel.isGenerateService());
        genActionCheck.setSelected(genModel.isGenerateWeb());
        outputLocationComboBox.setSelectedItem(genModel.getOutputModule());
        transCheck.setSelected(genModel.isTrl());
        pagingCheck.setSelected(genModel.isPagingByDb());
        List<GenModelDto> includeModelList= genModel.getIncludeModelList();
        if(includeModelList!=null)
        {
            for(GenModelDto genModelDto:includeModelList)
            {
                this.includeModuleList.setSelectedValue(genModelDto.getTableName(), rootPaneCheckingEnabled);
            }
        }



    }
    private void updateTable()
    {
        modelTable.removeAll();
        int i=0;
        Object[][] content=  new Object [genModelDtoList.size()][10];

        for(GenModelDto genModelDto:genModelDtoList)
        {
            content[i][0]=genModelDto.getTableName();
            content[i][1]=genModelDto.getModelName();
            content[i][2]=genModelDto.getModule();
            content[i][3]=genModelDto.getRemark();
            content[i][4]=genModelDto.isGenerateService()?rb.getString("dev.generation.tool.yes"):rb.getString("dev.generation.tool.no");
            content[i][5]=genModelDto.isGenerateWeb()?rb.getString("dev.generation.tool.yes"):rb.getString("dev.generation.tool.no");
            String lineName="";
            if(genModelDto.getIncludeModelList()!=null)
                for(GenModelDto line:genModelDto.getIncludeModelList())
                {
                    lineName+=" "+line.getTableName();
                }

            content[i][6]=lineName;
            content[i][7]=genModelDto.getOutputModule();
            content[i][8]=genModelDto.isTrl()?rb.getString("dev.generation.tool.yes"):rb.getString("dev.generation.tool.no");
            content[i][9]=genModelDto.isPagingByDb()?rb.getString("dev.generation.tool.yes"):rb.getString("dev.generation.tool.no");
            i++;
        }
        DefaultTableModel defaultTableModel=  new javax.swing.table.DefaultTableModel(
                content,tableHeaders
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false, false,false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

        this.modelTable.setModel(defaultTableModel);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        inputPanel = new javax.swing.JPanel();
        tableLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        tableText = new javax.swing.JTextField();
        nameText = new javax.swing.JTextField();
        moduleLabel = new javax.swing.JLabel();
        classNameText = new javax.swing.JTextField();
        classNameLabel = new javax.swing.JLabel();
        moduleText = new javax.swing.JTextField();
        genServiceLabel = new javax.swing.JLabel();
        genServiceCheck = new javax.swing.JCheckBox();
        genActionLabel = new javax.swing.JLabel();
        genActionCheck = new javax.swing.JCheckBox();
        outputLocationLabel = new javax.swing.JLabel();
        outputLocationComboBox = new javax.swing.JComboBox<>();
        includeModule = new javax.swing.JLabel();
        pagingCheck = new javax.swing.JCheckBox();
        transLabel = new javax.swing.JLabel();
        transCheck = new javax.swing.JCheckBox();
        pagingLabel = new javax.swing.JLabel();
        includeModuleListScrollPane = new javax.swing.JScrollPane();
        includeModuleList = new javax.swing.JList<>();
        buttonPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        generateButton = new javax.swing.JButton();
        modelTableScrollPane = new javax.swing.JScrollPane();
        modelTable = new javax.swing.JTable();
        msgTextAreaScrollPane = new javax.swing.JScrollPane();
        msgTextArea = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        settingMenu = new javax.swing.JMenu();
        zhCnCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        enUsCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableLabel.setBackground(new java.awt.Color(255, 255, 204));
        tableLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);


        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);


        moduleLabel.setBackground(new java.awt.Color(255, 255, 204));
        moduleLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);


        classNameLabel.setBackground(new java.awt.Color(255, 255, 204));
        classNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);


        genServiceLabel.setBackground(new java.awt.Color(255, 255, 204));
        genServiceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);


        genActionLabel.setBackground(new java.awt.Color(255, 255, 204));
        genActionLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);


        outputLocationLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        includeModule.setBackground(new java.awt.Color(255, 255, 204));
        includeModule.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        transLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        pagingLabel.setBackground(new java.awt.Color(255, 255, 204));
        pagingLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        includeModuleListScrollPane.setViewportView(includeModuleList);

        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
                buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(generateButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap())
        );
        buttonPanelLayout.setVerticalGroup(
                buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(buttonPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(addButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(generateButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
                inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(inputPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addComponent(tableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tableText, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(classNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(classNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                                                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                                                .addComponent(outputLocationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(outputLocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(genServiceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(pagingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                                .addComponent(transLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(transCheck)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(includeModule, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(pagingCheck)
                                                        .addComponent(genServiceCheck)
                                                        .addComponent(includeModuleListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                                .addComponent(moduleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(moduleText, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                                .addComponent(genActionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(genActionCheck)))
                                                .addContainerGap(116, Short.MAX_VALUE))
                                        .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        inputPanelLayout.setVerticalGroup(
                inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(inputPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tableLabel)
                                        .addComponent(tableText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(moduleLabel)
                                        .addComponent(classNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(classNameLabel)
                                        .addComponent(moduleText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(nameLabel)
                                                .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(genServiceLabel)
                                                .addComponent(genServiceCheck))
                                        .addComponent(genActionLabel)
                                        .addComponent(genActionCheck))
                                .addGap(18, 18, 18)
                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(pagingLabel)
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(outputLocationLabel)
                                                                .addComponent(outputLocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(pagingCheck))
                                                .addGap(18, 18, 18)
                                                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(includeModule)
                                                        .addComponent(transCheck)
                                                        .addComponent(transLabel)
                                                        .addComponent(includeModuleListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        modelTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},tableHeaders)
        {   boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        modelTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modelTableMouseClicked(evt);
            }
        });
        modelTableScrollPane.setViewportView(modelTable);

        msgTextArea.setColumns(20);
        msgTextArea.setRows(5);
        msgTextAreaScrollPane.setViewportView(msgTextArea);

        zhCnCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zhCnCheckBoxMenuItemActionPerformed(evt);
            }
        });
        settingMenu.add(zhCnCheckBoxMenuItem);

        enUsCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enUsCheckBoxMenuItemActionPerformed(evt);
            }
        });
        settingMenu.add(enUsCheckBoxMenuItem);

        menuBar.add(settingMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(modelTableScrollPane)
                        .addComponent(msgTextAreaScrollPane)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(msgTextAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(modelTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        GenModelDto genModelDto=null;
        boolean include=false;
        for(GenModelDto gen:genModelDtoList)
        {
            if(gen.getTableName().equals(tableText.getText()))
            {
                include=true;
                genModelDto=gen;
                break;
            }
        }
        if(genModelDto==null)
            genModelDto=new GenModelDto();
        genModelDto.setTableName(tableText.getText().trim());
        genModelDto.setModule(moduleText.getText().trim());
        genModelDto.setModelName(classNameText.getText().trim());

        genModelDto.setRemark(nameText.getText().trim());
        genModelDto.setGenerateService(genServiceCheck.isSelected());
        genModelDto.setGenerateWeb(genActionCheck.isSelected());
        genModelDto.setOutputModule(outputLocationComboBox.getSelectedItem().toString());
        genModelDto.setTrl(transCheck.isSelected());
        if(!include)
        {
            genModelDtoList.add(genModelDto);
        }

        List<String> selectedList= includeModuleList.getSelectedValuesList();
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
        genModelDto.setPagingByDb(pagingCheck.isSelected());
        updateTable();
    }
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow=modelTable.getSelectedRow();
        DefaultTableModel  model= (DefaultTableModel) modelTable.getModel();
        model.removeRow(selectedRow);
    }
    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        msgTextArea.setText(DateTimeDecorator.dateToLongString(new Date())+":正在生成代码\n");
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

                msgTextArea.append(DateTimeDecorator.dateToLongString(new Date())+":生成代码成功\n");
            }
            catch (Exception e)
            {
                msgTextArea.append(DateTimeDecorator.dateToLongString(new Date())+":生成代码失败\n"+e.getMessage());
            }

        }
    }
    private void modelTableMouseClicked(java.awt.event.MouseEvent evt) {
        DefaultTableModel  model= (DefaultTableModel) modelTable.getModel();
        int selectedRow= modelTable.getSelectedRow();
        String value= model.getValueAt(selectedRow, 0).toString();
        updateForm(value);
    }

    private void enUsCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        updateLanguage("en_US");
        enUsCheckBoxMenuItem.setSelected(true);
        zhCnCheckBoxMenuItem.setSelected(false);
    }
    private void zhCnCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        updateLanguage("zh_CN");
        enUsCheckBoxMenuItem.setSelected(false);
        zhCnCheckBoxMenuItem.setSelected(true);
    }
    private void updateLanguage(String language)
    {
        Locale locale=new Locale(language);
        rb=ResourceBundle.getBundle("msg",locale);
        initLabel();
        updateTable();
    }
    public static void main(ResourceBundle resourceBundle) {
        InputFrame.rb=resourceBundle;
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
                InputFrame inputFrame=  new InputFrame();
                int windowWidth = inputFrame.getWidth(); // 获得窗口宽
                int windowHeight = inputFrame.getHeight(); // 获得窗口高
                Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
                Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
                int screenWidth = screenSize.width; // 获取屏幕的宽
                int screenHeight = screenSize.height; // 获取屏幕的高
                inputFrame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// 设置窗口居中显示
                inputFrame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton addButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JLabel classNameLabel;
    private javax.swing.JTextField classNameText;
    private javax.swing.JButton deleteButton;
    private javax.swing.JCheckBoxMenuItem enUsCheckBoxMenuItem;
    private javax.swing.JCheckBox genActionCheck;
    private javax.swing.JLabel genActionLabel;
    private javax.swing.JCheckBox genServiceCheck;
    private javax.swing.JLabel genServiceLabel;
    private javax.swing.JButton generateButton;
    private javax.swing.JLabel includeModule;
    private javax.swing.JList<String> includeModuleList;
    private javax.swing.JScrollPane includeModuleListScrollPane;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTable modelTable;
    private javax.swing.JScrollPane modelTableScrollPane;
    private javax.swing.JLabel moduleLabel;
    private javax.swing.JTextField moduleText;
    private javax.swing.JTextArea msgTextArea;
    private javax.swing.JScrollPane msgTextAreaScrollPane;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameText;
    private javax.swing.JComboBox<String> outputLocationComboBox;
    private javax.swing.JLabel outputLocationLabel;
    private javax.swing.JCheckBox pagingCheck;
    private javax.swing.JLabel pagingLabel;
    private javax.swing.JMenu settingMenu;
    private javax.swing.JLabel tableLabel;
    private javax.swing.JTextField tableText;
    private javax.swing.JCheckBox transCheck;
    private javax.swing.JLabel transLabel;
    private javax.swing.JCheckBoxMenuItem zhCnCheckBoxMenuItem;
    // End of variables declaration
}
