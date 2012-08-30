/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Enumeration;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;

/**
 *
 * @author oleg
 */
public class FormCalcPlate extends javax.swing.JInternalFrame implements PropertyChangeListener {
    private static Logger log = Logger.getLogger(AppLauncher.class.getName());
    //Values for the fields
    private int widthPlate = 1000;
    private int heightPlate = 800;
    private int sheetWidth = 3050;
    private int sheetHeight = 2030;
    private int countPlates = 1;
    private int workTimeDesigner = 15;
    private int countWorkers = 2;
    private int planWorkTimeWorkers = 24;
    public int yearWorkHours = 1986;
    private BigDecimal priceBasis = new BigDecimal("2163.65");
    private BigDecimal priceCoat = new BigDecimal("140.00");
    private BigDecimal delivBasis = new BigDecimal("300");
    private BigDecimal delivCoat = new BigDecimal("0.00");
    private BigDecimal salaryDesigner = new BigDecimal("30000.00");
    private BigDecimal salaryWorkers = new BigDecimal("20000.00");
    private BigDecimal amountMaket = new BigDecimal("40.76");
    private BigDecimal costComputer = new BigDecimal("10000.00");
    private BigDecimal tax = new BigDecimal("0.07");
    private BigDecimal amortizationPeriodComputer = new BigDecimal("3.00");
    private BigDecimal rent = new BigDecimal("20000.00");
    private BigDecimal profit = new BigDecimal("30.00");
    private BigDecimal amountBasis = new BigDecimal("0.00");
    private BigDecimal amountCoat = new BigDecimal("0.00");
    private BigDecimal amountWorkers = new BigDecimal("0.00");
    private BigDecimal resMaterial = new BigDecimal("0.00"); 
    private BigDecimal resSum = new BigDecimal("0.00");
    private BigDecimal amountAmortization = new BigDecimal("0.00"); 
    private BigDecimal amountAmortizationComputer = new BigDecimal("0.00");
    private BigDecimal amountTax = new BigDecimal("0.00");
    private BigDecimal amountRent = new BigDecimal("0.00"); 
    private BigDecimal timeRent = new BigDecimal("0.00");
    private BigDecimal cost = new BigDecimal("0.00"); 
    private BigDecimal amountProfit = new BigDecimal("0.00");
    private int month;
    public int[] workHours = new int[] { 128, 159, 167, 167, 167, 159, 176, 184, 160, 184, 168, 167 };
    /**
     * Creates new form FormCalcBook
     */
    public FormCalcPlate() {
        initComponents();
        calcWorkHours();
    }
    
 
    //The property change listener is registered on each
    //field using code like this:
    //    someField.addPropertyChangeListener("value", this);

    /** Called when a field's "value" property changes. */
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if (source == widthField) {
            log.info("Изменилась ширина");
            widthPlate = ((Number)widthField.getValue()).intValue();
            int heightPlateI = ((Number)heightField.getValue()).intValue();
            countWorkers(widthPlate, heightPlateI);
            amountBasis((new BigDecimal (priceBasisField.getValue().toString())), (new BigDecimal (delivBasisField.getValue().toString())), ((Number)sheetWidthField.getValue()).intValue(), ((Number)sheetHeightField.getValue()).intValue(), ((Number)widthField.getValue()).intValue(), ((Number)heightField.getValue()).intValue(), ((Number)countPlatesField.getValue()).intValue());
            amountCoat((new BigDecimal (priceCoatField.getValue().toString())), (new BigDecimal (delivCoatField.getValue().toString())), ((Number)widthField.getValue()).intValue(), ((Number)heightField.getValue()).intValue(), ((Number)countPlatesField.getValue()).intValue());
        } else if (source == heightField) {
            log.info("Изменилась длина");
            heightPlate = ((Number)heightField.getValue()).intValue();
            int widthPlateI = ((Number)widthField.getValue()).intValue();
            countWorkers(widthPlateI, heightPlate);
            amountBasis((new BigDecimal (priceBasisField.getValue().toString())), (new BigDecimal (delivBasisField.getValue().toString())), ((Number)sheetWidthField.getValue()).intValue(), ((Number)sheetHeightField.getValue()).intValue(), ((Number)widthField.getValue()).intValue(), ((Number)heightField.getValue()).intValue(), ((Number)countPlatesField.getValue()).intValue());
            amountCoat((new BigDecimal (priceCoatField.getValue().toString())), (new BigDecimal (delivCoatField.getValue().toString())), ((Number)widthField.getValue()).intValue(), ((Number)heightField.getValue()).intValue(), ((Number)countPlatesField.getValue()).intValue());
        } else if (source == delivBasisField) {
            delivBasis = (new BigDecimal (delivBasisField.getValue().toString()));
        } else if (source == delivCoatField) {
            delivCoat = (new BigDecimal (delivCoatField.getValue().toString()));
        } else if (source == sheetWidth) {
            sheetWidth = ((Number)sheetWidthField.getValue()).intValue();
        } else if (source == sheetHeight) {
            sheetHeight = ((Number)sheetHeightField.getValue()).intValue();
        } else if (source == priceBasisField) {
            priceBasis = (new BigDecimal (priceBasisField.getValue().toString()));
        } else if (source == priceCoatField) {
            priceCoat = (new BigDecimal (priceCoatField.getValue().toString()));
        } else if (source == countPlatesField) {
            countPlates = ((Number)countPlatesField.getValue()).intValue();
            amountBasis((new BigDecimal (priceBasisField.getValue().toString())), (new BigDecimal (delivBasisField.getValue().toString())), ((Number)sheetWidthField.getValue()).intValue(), ((Number)sheetHeightField.getValue()).intValue(), ((Number)widthField.getValue()).intValue(), ((Number)heightField.getValue()).intValue(), countPlates);
            amountCoat((new BigDecimal (priceCoatField.getValue().toString())), (new BigDecimal (delivCoatField.getValue().toString())), ((Number)widthField.getValue()).intValue(), ((Number)heightField.getValue()).intValue(), countPlates);
            planWorkTimeWorkers(((Number)widthField.getValue()).intValue(), ((Number)heightField.getValue()).intValue(), ((Number)countPlatesField.getValue()).intValue());
            doPerform();
        } else if (source == countWorkersField) {
            BigDecimal countWorkers = new BigDecimal (countWorkersField.getValue().toString());
            amountWorkers(new BigDecimal (planWorkTimeWorkersField.getValue().toString()), new BigDecimal (salaryWorkersField.getValue().toString()), countWorkers);
        } else if (source == planWorkTimeWorkersField) {
            BigDecimal planWorkTimeWorkersBD = new BigDecimal (planWorkTimeWorkersField.getValue().toString());
            amountWorkers(planWorkTimeWorkersBD, new BigDecimal (salaryWorkersField.getValue().toString()), new BigDecimal (countWorkersField.getValue().toString()));
        } else if (source == salaryWorkersField) {
            BigDecimal salaryeWorkersBD = new BigDecimal (salaryWorkersField.getValue().toString());
            amountWorkers(new BigDecimal (planWorkTimeWorkersField.getValue().toString()), salaryeWorkersBD, new BigDecimal (countWorkersField.getValue().toString()));
        }
        cost((new BigDecimal (amountBasisField.getValue().toString())), (new BigDecimal (amountCoatField.getValue().toString())), (new BigDecimal (amountMaketField.getValue().toString())), (new BigDecimal (amountWorkersField.getValue().toString())), (new BigDecimal (amountAmortizationField.getValue().toString())), (new BigDecimal (amountRentField.getValue().toString())), ((Number)countPlatesField.getValue()).intValue());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jOptionPane1 = new javax.swing.JOptionPane();
        heightField = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        widthField = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        delivCoatField = new javax.swing.JFormattedTextField();
        delivBasisField = new javax.swing.JFormattedTextField();
        delivBasisField.setValue(delivBasis);
        delivBasisField.setColumns(10);
        delivBasisField.addPropertyChangeListener("value", this)
        ;
        jLabel8 = new javax.swing.JLabel();
        amountBasisField = new javax.swing.JFormattedTextField();
        amountCoatField = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        countPlatesField = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        resSumField = new javax.swing.JFormattedTextField();
        sheetWidthField = new javax.swing.JFormattedTextField();
        sheetHeightField = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        priceBasisField = new javax.swing.JFormattedTextField();
        priceCoatField = new javax.swing.JFormattedTextField();
        workDesignerPanel = new javax.swing.JPanel();
        descriptionDesignerLabel = new javax.swing.JLabel();
        workTimeDesignerLabel = new javax.swing.JLabel();
        workTimeDesignerField = new javax.swing.JFormattedTextField();
        salaryDesignerLabel = new javax.swing.JLabel();
        salaryDesignerField = new javax.swing.JFormattedTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        amountMaketField = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        workPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        planWorkTimeWorkersField = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        salaryWorkersField = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        countWorkersField = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        amountWorkersField = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel18 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        amountAmortizationComputerField = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        amountAmortizationField = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        timeRentField = new javax.swing.JFormattedTextField();
        rentField = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        amountRentField = new javax.swing.JFormattedTextField();
        costField = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        profitField = new javax.swing.JFormattedTextField();
        taxField = new javax.swing.JFormattedTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        amountTaxField = new javax.swing.JFormattedTextField();
        jLabel28 = new javax.swing.JLabel();
        amountProfitField = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Расчёт стоимости табличек");
        setAutoscrolls(true);
        setMaximumSize(new java.awt.Dimension(900, 900));
        setMinimumSize(new java.awt.Dimension(800, 900));
        setPreferredSize(new java.awt.Dimension(800, 900));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                SubWindowListener(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        heightField.setValue(new Integer(heightPlate));
        heightField.setColumns(10);
        heightField.addPropertyChangeListener("value", this);

        jLabel4.setText("Высота, мм");

        widthField.setValue(new Integer(widthPlate));
        widthField.setColumns(10);
        widthField.addPropertyChangeListener("value", this);

        jLabel3.setText("Ширина, мм");

        jLabel6.setText("Материал основы");

        jLabel7.setText("Материал покрытия");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ПВХ 5 мм 3,05х2,03 м", "ПВХ 6 мм 3,05х2,03 м" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Без покрытия", "Самоклейка Avery", "Цветная печать" }));

        jLabel10.setText("Доставка");

        delivCoatField.setValue(delivCoat);
        delivCoatField.setColumns(10);
        delivCoatField.addPropertyChangeListener("value", this);
        delivCoatField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delivCoatFieldActionPerformed(evt);
            }
        });

        jLabel8.setText("Стоимость");

        amountBasisField.setValue(amountBasis);
        amountBasisField.setColumns(10);
        amountBasisField.addPropertyChangeListener("value", this);
        amountBasisField.setEditable(false);

        amountCoatField.setValue(amountCoat);
        amountCoatField.setColumns(10);
        amountCoatField.addPropertyChangeListener("value", this);
        amountCoatField.setEditable(false);

        jLabel1.setText("Количество");

        countPlatesField.setValue(new Integer(countPlates));
        countPlatesField.setColumns(10);
        countPlatesField.addPropertyChangeListener("value", this);

        jLabel5.setText("Итоговая сумма:");

        resSumField.setEditable(false);

        sheetWidthField.setValue(new Integer(sheetWidth));
        sheetWidthField.setColumns(10);
        sheetWidthField.addPropertyChangeListener("value", this);

        sheetHeightField.setValue(new Integer(sheetHeight));
        sheetHeightField.setColumns(10);
        sheetHeightField.addPropertyChangeListener("value", this);

        jLabel2.setText("x");

        jLabel9.setText("Размер листа");

        jLabel11.setText("Цена материала");

        priceBasisField.setValue(priceBasis);
        priceBasisField.setColumns(10);
        priceBasisField.addPropertyChangeListener("value", this);

        priceCoatField.setValue(priceCoat);
        priceCoatField.setColumns(10);
        priceCoatField.addPropertyChangeListener("value", this);

        workDesignerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Дизайн макета"));

        descriptionDesignerLabel.setText("Нашему дизайнеру нужно лишь проверить макет");

        workTimeDesignerLabel.setText("Плановое время работы дизайнера, мин.");

        workTimeDesignerField.setValue(new Integer(workTimeDesigner));
        workTimeDesignerField.setColumns(10);
        //workTimeDesignerField.addPropertyChangeListener("value", this);
        workTimeDesignerField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workTimeDesignerFieldActionPerformed(evt);
            }
        });

        salaryDesignerLabel.setText("Среднемесячная зарплата дизайнера");

        salaryDesignerField.setValue(salaryDesigner);
        salaryDesignerField.setColumns(10);
        //salaryDesignerField.addPropertyChangeListener("value", this);
        salaryDesignerField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salaryDesignerFieldActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Макет заказчика");
        jRadioButton1.setSelected(true);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Заказ макета");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Разработка макета");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        amountMaketField.setValue(amountMaket);
        amountMaketField.setColumns(10);
        amountMaketField.addPropertyChangeListener("value", this);
        amountMaketField.setEditable(false);
        amountMaketField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountMaketFieldActionPerformed(evt);
            }
        });

        jLabel14.setText("Итого стоимость макета:");

        javax.swing.GroupLayout workDesignerPanelLayout = new javax.swing.GroupLayout(workDesignerPanel);
        workDesignerPanel.setLayout(workDesignerPanelLayout);
        workDesignerPanelLayout.setHorizontalGroup(
            workDesignerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workDesignerPanelLayout.createSequentialGroup()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(workDesignerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(workDesignerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(workDesignerPanelLayout.createSequentialGroup()
                        .addGroup(workDesignerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(workTimeDesignerLabel)
                            .addComponent(salaryDesignerLabel))
                        .addGap(18, 18, 18)
                        .addGroup(workDesignerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(workTimeDesignerField, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(salaryDesignerField))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amountMaketField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(descriptionDesignerLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        workDesignerPanelLayout.setVerticalGroup(
            workDesignerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workDesignerPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(workDesignerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(descriptionDesignerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(workDesignerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(workTimeDesignerLabel)
                    .addComponent(workTimeDesignerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(workDesignerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salaryDesignerLabel)
                    .addComponent(salaryDesignerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amountMaketField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setText("Дата расчёта");

        workPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Изготовление"));

        jLabel13.setText("Плановое время работы работника, мин.");

        planWorkTimeWorkersField.setValue(planWorkTimeWorkers);
        planWorkTimeWorkersField.setColumns(10);
        planWorkTimeWorkersField.addPropertyChangeListener("value", this);
        planWorkTimeWorkersField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planWorkTimeWorkersFieldActionPerformed(evt);
            }
        });

        jLabel15.setText("Среднемесячная зарплата работника");

        salaryWorkersField.setValue(salaryWorkers);
        salaryWorkersField.setColumns(10);
        salaryWorkersField.addPropertyChangeListener("value", this);
        salaryWorkersField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salaryWorkersFieldActionPerformed(evt);
            }
        });

        jLabel16.setText("Требуемое количество работников");

        countWorkersField.setValue(countWorkers);
        countWorkersField.setColumns(10);
        countWorkersField.addPropertyChangeListener("value", this);
        countWorkersField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countWorkersFieldActionPerformed(evt);
            }
        });

        jLabel17.setText("Итого стоимость работы:");

        amountWorkersField.setValue(amountWorkers);
        amountWorkersField.setColumns(10);
        amountWorkersField.addPropertyChangeListener("value", this);
        amountWorkersField.setEditable(false);

        javax.swing.GroupLayout workPanelLayout = new javax.swing.GroupLayout(workPanel);
        workPanel.setLayout(workPanelLayout);
        workPanelLayout.setHorizontalGroup(
            workPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(workPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(workPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(planWorkTimeWorkersField)
                    .addComponent(salaryWorkersField, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(countWorkersField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amountWorkersField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        workPanelLayout.setVerticalGroup(
            workPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(workPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workPanelLayout.createSequentialGroup()
                        .addGroup(workPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(countWorkersField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(workPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(planWorkTimeWorkersField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(workPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(salaryWorkersField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(workPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(amountWorkersField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Подрядные работы"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Наименование", "Количество", "Цена", "Сумма"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        dateChooserCombo1.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dateChooserCombo1OnCommit(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Амортизация"));

        jLabel19.setText("Амортизация компьютера");

        amountAmortizationComputerField.setValue(amountAmortizationComputer);
        amountAmortizationComputerField.setColumns(10);

        jLabel20.setText("Итого амортизация:");

        amountAmortizationField.setValue(amountAmortization);
        amountAmortizationField.setColumns(10);
        amountAmortizationField.addPropertyChangeListener("value", this);
        amountAmortizationField.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amountAmortizationComputerField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amountAmortizationField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(amountAmortizationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(amountAmortizationComputerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Рента"));

        jLabel21.setText("Длительность аренды, мин.");

        timeRentField.setValue(timeRent);
        timeRentField.setColumns(10);

        rentField.setValue(rent);
        rentField.setColumns(10);

        jLabel22.setText("Стоимость аренды в мес.");

        jLabel23.setText("Итого рента:");

        amountRentField.setValue(amountRent);
        amountRentField.setColumns(10);
        amountRentField.addPropertyChangeListener("value", this);
        amountRentField.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rentField, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(timeRentField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amountRentField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeRentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(amountRentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        costField.setValue(cost);
        costField.setColumns(10);
        costField.setEditable(false);

        jLabel24.setText("Итого себестоимость:");

        jLabel25.setText("Прибыль, %:");

        profitField.setValue(profit);
        profitField.setColumns(10);

        taxField.setValue(tax);
        taxField.setColumns(10);

        jLabel26.setText("Налоговый коэффициент:");

        jLabel27.setText("Сумма налога:");

        amountTaxField.setValue(amountTax);
        amountTaxField.setColumns(10);
        amountTaxField.setEditable(false);

        jLabel28.setText("Итого прибыль:");

        amountProfitField.setValue(amountProfit);
        amountProfitField.setColumns(10);
        amountProfitField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(workDesignerPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(workPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(widthField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(heightField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(countPlatesField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(313, 313, 313)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(26, 26, 26)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(sheetWidthField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(sheetHeightField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(delivCoatField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(delivBasisField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(priceCoatField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(priceBasisField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(amountBasisField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(amountCoatField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addGap(18, 18, 18)
                                        .addComponent(taxField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(resSumField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(costField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel27))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(amountTaxField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(profitField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(59, 59, 59)
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(amountProfitField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(widthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(heightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(countPlatesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(amountBasisField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(amountCoatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(sheetWidthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sheetHeightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel9))
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(delivBasisField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(delivCoatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(priceBasisField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(38, 38, 38)
                            .addComponent(priceCoatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(workDesignerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(workPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(costField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(profitField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(amountProfitField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(taxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(amountTaxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resSumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void countWorkers (int widthPlate, int heightPlate) {
        log.info("Зашли в расчёт количества работников");
        int minLength = Math.min(widthPlate, heightPlate);
        int maxLength = Math.max(widthPlate, heightPlate);
        if (maxLength <= 500) {
            log.info("Макс. длина менее 500 мм");
            if (minLength <= 300) {
                countWorkersField.setValue(1);
            } else {
                countWorkersField.setValue(2);
            }
        } else if (maxLength > 500 & minLength <= 1500) {
            log.info("Макс. длина от 500 до 1500 мм");
            if (minLength <= 1200) {
                countWorkersField.setValue(2);
            } else {
                countWorkersField.setValue(3);
            }
        } else if (maxLength > 1500 & minLength <= 2500) {
            log.info("Макс. длина от 1500 до 2500 мм");
            if (minLength <= 1200) {
                countWorkersField.setValue(2);
            } else {
                countWorkersField.setValue(3);
            }
        } else {
            log.info("Макс. длина более 2500 мм");
            countWorkersField.setValue(3);
            jOptionPane1.showMessageDialog(null, "Такого размера стенды с самоклейкой мы не делаем!", "Недопустимая продукция", JOptionPane.ERROR_MESSAGE);
        }
        planWorkTimeWorkers(((Number)widthField.getValue()).intValue(), ((Number)heightField.getValue()).intValue(), ((Number)countPlatesField.getValue()).intValue());
        amountWorkers(new BigDecimal (planWorkTimeWorkersField.getValue().toString()), new BigDecimal (salaryWorkersField.getValue().toString()), new BigDecimal (countWorkersField.getValue().toString()));
    }
    
    private void planWorkTimeWorkers(int widthPlate, int heightPlate, int countPlates) {
        log.info("Вошли в расчёт времени работы одного работника");
        BigDecimal planWorkTimeWorkersBD = (new BigDecimal (widthPlate)).multiply((new BigDecimal (heightPlate))).divide(new BigDecimal ("1000000.00"), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal ("30.00")).multiply(new BigDecimal (countPlates)).setScale(0, BigDecimal.ROUND_HALF_UP);
        planWorkTimeWorkersField.setValue(planWorkTimeWorkersBD);
        amountWorkers(new BigDecimal (planWorkTimeWorkersField.getValue().toString()), new BigDecimal (salaryWorkersField.getValue().toString()), new BigDecimal (countWorkersField.getValue().toString()));
    }
    
    private void planWorkTimeDesigner(String typeWork, int countPlates) {
        log.info("Вошли в расчёт времени работы дизайнера");
        log.info("typeWork=" + typeWork);
        int planWorkTimeDesignerBD = 0;
        if (typeWork == "Макет заказчика") {
                planWorkTimeDesignerBD = countPlates * 5;
            } else if (typeWork == "Разработка макета") { 
                planWorkTimeDesignerBD = countPlates * 40;
            }
        workTimeDesignerField.setValue(planWorkTimeDesignerBD);
        amountMaket(new BigDecimal (workTimeDesignerField.getValue().toString()), new BigDecimal (salaryDesignerField.getValue().toString()));
    }
    
    private void amountBasis(BigDecimal priceBasis, BigDecimal delivBasis, int sheetWidth, int sheetHeight, int widthPlate, int heightPlate, int countPlates) {
        log.info("Вошли в расчёт стоимости материала основы");
        int countPlatesFromSheet = Math.max((int)(sheetHeight/widthPlate*sheetWidth/heightPlate), (int)(sheetWidth/widthPlate*sheetHeight/heightPlate));
        amountBasis = (priceBasis.add(delivBasis)).divide(new BigDecimal (countPlatesFromSheet), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal (countPlates));
        amountBasisField.setValue(amountBasis);
    }
    
    private void amountCoat(BigDecimal priceCoat, BigDecimal delivCoat, int widthPlate, int heightPlate, int countPlates) {
        log.info("Вошли в расчёт стоимости материала покрытия");
        BigDecimal squarePlates = ((new BigDecimal (widthPlate)).multiply(new BigDecimal (heightPlate)).divide((new BigDecimal ("1000000.00")), 2, BigDecimal.ROUND_HALF_UP));
        amountCoat = (priceCoat.add(delivCoat)).multiply(squarePlates).multiply(new BigDecimal (countPlates));
        amountCoatField.setValue(amountCoat);
    }
    
    private void amountMaket(BigDecimal workTimeDesigner, BigDecimal salaryDesigner) {
        log.info("Вошли в расчёт стоимости макета");
        if (jRadioButton2.isSelected()) {
            amountMaketField.setValue(salaryDesigner.setScale(2, BigDecimal.ROUND_HALF_UP));
        } else {
            BigDecimal workHoursBD = new BigDecimal (workHours[month-1]);
            amountMaket = ((workTimeDesigner.divide(new BigDecimal("60.00"), 2, BigDecimal.ROUND_HALF_UP)).multiply(salaryDesigner.divide(workHoursBD, 2, BigDecimal.ROUND_HALF_UP))).setScale(2, BigDecimal.ROUND_HALF_UP);
            amountMaketField.setValue(amountMaket);
        }
        amountAmortization(new BigDecimal (workTimeDesignerField.getValue().toString()));
        timeRent(new BigDecimal (workTimeDesignerField.getValue().toString()), new BigDecimal (planWorkTimeWorkersField.getValue().toString()));
        amountRent(new BigDecimal (timeRentField.getValue().toString()), new BigDecimal (rentField.getValue().toString()));
    }
    
    private void amountWorkers(BigDecimal planWorkTimeWorkers, BigDecimal salaryWorkers, BigDecimal countWorkers) {
        log.info("Вошли в расчёт стоимости работы работников");
        BigDecimal workHoursBD = new BigDecimal (workHours[month-1]);
        amountWorkers = ((planWorkTimeWorkers.divide(new BigDecimal("60.00"), 2, BigDecimal.ROUND_HALF_UP)).multiply(salaryWorkers.divide(workHoursBD, 2, BigDecimal.ROUND_HALF_UP))).multiply(countWorkers).setScale(2, BigDecimal.ROUND_HALF_UP);
        amountWorkersField.setValue(amountWorkers);
        timeRent(new BigDecimal (workTimeDesignerField.getValue().toString()), new BigDecimal (planWorkTimeWorkersField.getValue().toString()));
        amountRent(new BigDecimal (timeRentField.getValue().toString()), new BigDecimal (rentField.getValue().toString()));
    }
    
    private void amountAmortization(BigDecimal planWorkTimeDesigner) {
        log.info("Вошли в расчёт стоимости амортизации оборудования");
        BigDecimal yearWorkHoursBD = new BigDecimal (yearWorkHours);
        amountAmortizationComputer = costComputer.divide((amortizationPeriodComputer.multiply(yearWorkHoursBD)), 2, BigDecimal.ROUND_HALF_UP).multiply(planWorkTimeDesigner).setScale(2, BigDecimal.ROUND_HALF_UP);
        amountAmortizationComputerField.setValue(amountAmortizationComputer);
        amountAmortizationField.setValue(amountAmortizationComputer);
    }
    
    private void timeRent(BigDecimal timeWork, BigDecimal timeDesigner) {
        log.info("Вошли в расчёт стоимости времени аренды");
        timeRent = timeWork.add(timeDesigner);
        timeRentField.setValue(timeRent);
    }
    
    private void amountRent(BigDecimal timeRent, BigDecimal rent) {
        log.info("Вошли в расчёт стоимости аренды");
        BigDecimal workHoursBD = new BigDecimal (workHours[month-1]);
        amountRent = (rent.divide(workHoursBD, 2, BigDecimal.ROUND_HALF_UP)).multiply(timeRent.divide(new BigDecimal ("60.00"), 2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);
        amountRentField.setValue(amountRent);
    }
    
    public void cost(BigDecimal amountBasis, BigDecimal amountCoat, BigDecimal amountMaket, BigDecimal amountWorkers, BigDecimal amountAmortization, BigDecimal amountRent, int countPlates) {
        log.info("Вошли в расчёт себестоимости");
        cost = (amountBasis.add(amountCoat).add(amountMaket).add(amountWorkers).add(amountAmortization).add(amountRent)).multiply(new BigDecimal (countPlates));
        costField.setValue(cost);
        amountProfit(cost, new BigDecimal (profitField.getValue().toString()));
        amountTax(cost, new BigDecimal (profitField.getValue().toString()), new BigDecimal (taxField.getValue().toString()));
        resSum(cost, new BigDecimal (amountProfitField.getValue().toString()), new BigDecimal (amountTaxField.getValue().toString()));
    }
    
    private void amountProfit(BigDecimal cost, BigDecimal profit) {
        log.info("Вошли в расчёт прибыли");
        amountProfit = cost.multiply(profit).divide(new BigDecimal ("100.00"), 2, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
        amountProfitField.setValue(amountProfit);
    }
    
    private void amountTax(BigDecimal cost, BigDecimal profit, BigDecimal tax) {
        log.info("Вошли в предварительный расчёт налога");
        amountTax = cost.add(profit).multiply(tax).setScale(2, BigDecimal.ROUND_HALF_UP);
        amountTaxField.setValue(amountTax);
    }
    
    private void resSum(BigDecimal cost, BigDecimal amountProfit, BigDecimal amountTax) {
        log.info("Вошли в расчёт общей стоимости");
        resSum = cost.add(amountProfit).add(amountTax);
        resSumField.setValue(resSum);
    }
    
    private void delivCoatFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delivCoatFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delivCoatFieldActionPerformed

    private void workTimeDesignerFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workTimeDesignerFieldActionPerformed
        amountMaket(new BigDecimal (workTimeDesignerField.getValue().toString()), new BigDecimal (salaryDesignerField.getValue().toString()));
        
    }//GEN-LAST:event_workTimeDesignerFieldActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        doPerform();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        doPerform();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        doPerform();
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void dateChooserCombo1OnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dateChooserCombo1OnCommit
        calcWorkHours(); 
        amountMaket(new BigDecimal (workTimeDesignerField.getValue().toString()), new BigDecimal (salaryDesignerField.getValue().toString()));
    }//GEN-LAST:event_dateChooserCombo1OnCommit

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        //amountMaket();
    }//GEN-LAST:event_formComponentShown

    private void salaryDesignerFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salaryDesignerFieldActionPerformed
        amountMaket(new BigDecimal (workTimeDesignerField.getValue().toString()), new BigDecimal (salaryDesignerField.getValue().toString()));
    }//GEN-LAST:event_salaryDesignerFieldActionPerformed

    private void amountMaketFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountMaketFieldActionPerformed
        amountMaket(new BigDecimal (workTimeDesignerField.getValue().toString()), new BigDecimal (salaryDesignerField.getValue().toString()));
    }//GEN-LAST:event_amountMaketFieldActionPerformed

    private void planWorkTimeWorkersFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planWorkTimeWorkersFieldActionPerformed
        amountWorkers(new BigDecimal (planWorkTimeWorkersField.getValue().toString()), new BigDecimal (salaryWorkersField.getValue().toString()), new BigDecimal (countWorkersField.getValue().toString()));
    }//GEN-LAST:event_planWorkTimeWorkersFieldActionPerformed

    private void salaryWorkersFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salaryWorkersFieldActionPerformed
        amountWorkers(new BigDecimal (planWorkTimeWorkersField.getValue().toString()), new BigDecimal (salaryWorkersField.getValue().toString()), new BigDecimal (countWorkersField.getValue().toString()));
    }//GEN-LAST:event_salaryWorkersFieldActionPerformed

    private void countWorkersFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countWorkersFieldActionPerformed
        log.info("Изменилось вручную количество работников, надо пересчитать стоимость их работы");
        amountWorkers(new BigDecimal (planWorkTimeWorkersField.getValue().toString()), new BigDecimal (salaryWorkersField.getValue().toString()), new BigDecimal (countWorkersField.getValue().toString()));
    }//GEN-LAST:event_countWorkersFieldActionPerformed

    private void SubWindowListener(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_SubWindowListener
        amountBasis((new BigDecimal (priceBasisField.getValue().toString())), (new BigDecimal (delivBasisField.getValue().toString())), ((Number)sheetWidthField.getValue()).intValue(), ((Number)sheetHeightField.getValue()).intValue(), ((Number)widthField.getValue()).intValue(), ((Number)heightField.getValue()).intValue(), ((Number)countPlatesField.getValue()).intValue());
        amountCoat((new BigDecimal (priceCoatField.getValue().toString())), (new BigDecimal (delivCoatField.getValue().toString())), ((Number)widthField.getValue()).intValue(), ((Number)heightField.getValue()).intValue(), ((Number)countPlatesField.getValue()).intValue());
        planWorkTimeWorkers(((Number)widthField.getValue()).intValue(), ((Number)heightField.getValue()).intValue(), ((Number)countPlatesField.getValue()).intValue());
        doPerform();
        amountMaket(new BigDecimal (workTimeDesignerField.getValue().toString()), new BigDecimal (salaryDesignerField.getValue().toString()));
        amountWorkers(new BigDecimal (planWorkTimeWorkersField.getValue().toString()), new BigDecimal (salaryWorkersField.getValue().toString()), new BigDecimal (countWorkersField.getValue().toString()));
        amountAmortization(new BigDecimal (workTimeDesignerField.getValue().toString()));
        amountRent(new BigDecimal (timeRentField.getValue().toString()), new BigDecimal (rentField.getValue().toString()));
        cost((new BigDecimal (amountBasisField.getValue().toString())), (new BigDecimal (amountCoatField.getValue().toString())), (new BigDecimal (amountMaketField.getValue().toString())), (new BigDecimal (amountWorkersField.getValue().toString())), (new BigDecimal (amountAmortizationField.getValue().toString())), (new BigDecimal (amountRentField.getValue().toString())), ((Number)countPlatesField.getValue()).intValue());
    }//GEN-LAST:event_SubWindowListener
    private void calcWorkHours() {
        month = dateChooserCombo1.getSelectedDate().get(2) + 1;
        switch (month) {
            case 1:  jLabel18.setText("В январе " + workHours[month-1] + " рабочих часов");
                     break;
            case 2:  jLabel18.setText("В феврале " + workHours[month-1] + " рабочих часов");
                     break;
            case 3:  jLabel18.setText("В марте " + workHours[month-1] + " рабочих часов");
                     break;
            case 4:  jLabel18.setText("В апреле " + workHours[month-1] + " рабочих часов");
                     break;
            case 5:  jLabel18.setText("В мае " + workHours[month-1] + " рабочих часов");
                     break;
            case 6:  jLabel18.setText("В июне " + workHours[month-1] + " рабочих часов");
                     break;
            case 7:  jLabel18.setText("В июле " + workHours[month-1] + " рабочих часов");
                     break;
            case 8:  jLabel18.setText("В августе " + workHours[month-1] + " рабочих часов");
                     break;
            case 9:  jLabel18.setText("В сентябре " + workHours[month-1] + " рабочих часов");
                     break;
            case 10: jLabel18.setText("В октябре " + workHours[month-1] + " рабочих часов");
                     break;
            case 11: jLabel18.setText("В ноябре " + workHours[month-1] + " рабочих часов");
                     break;
            case 12: jLabel18.setText("В декабре " + workHours[month-1] + " рабочих часов");
                     break;
            default: jLabel18.setText("Не верно указана дата");
                     break;
        }
    }
    /*
    * Каждая радио-кнопка выполняет этот метод.
    * Здесь будет определено, какая кнопка выбрана
    * и будут выполнены соответствующие действия.
    */
    private void doPerform() {
        Enumeration elements = buttonGroup1.getElements();
        while (elements.hasMoreElements()) {
          AbstractButton button = (AbstractButton)elements.nextElement();
          if (button.isSelected()) {
            log.info("button.getText().toString() " + button.getText().toString());
            if (button.getText().toString() == "Макет заказчика") {
                log.info("1 Режим ");
                workTimeDesignerLabel.setEnabled(true);
                descriptionDesignerLabel.setEnabled(true);
                descriptionDesignerLabel.setText("Нашему дизайнеру нужно лишь проверить макет");
                workTimeDesignerField.setEnabled(true);
                //workTimeDesigner = ((Number)countPlatesField.getValue()).intValue() * 5;
                //workTimeDesignerField.setValue(workTimeDesigner);
                planWorkTimeDesigner(button.getText().toString(), ((Number)countPlatesField.getValue()).intValue());
                salaryDesigner = new BigDecimal("30000.00");
                salaryDesignerField.setValue(salaryDesigner);
                log.info("Новое значение зарплаты " + salaryDesigner);
                salaryDesignerLabel.setText("Среднемесячная зарплата дизайнера");
            } else if (button.getText().toString() == "Заказ макета") { 
                log.info("2 Режим ");
                workTimeDesignerLabel.setEnabled(false);
                descriptionDesignerLabel.setEnabled(true);
                descriptionDesignerLabel.setText("Макет перезаказываем на стороне");
                workTimeDesignerField.setEnabled(false);
                workTimeDesigner = 0;
                workTimeDesignerField.setValue(workTimeDesigner);
                salaryDesigner = new BigDecimal("500.00");
                salaryDesignerField.setValue(salaryDesigner);
                log.info("Новое значение зарплаты " + salaryDesigner);
                salaryDesignerLabel.setText("Сдельная оплата стороннему дизайнеру");
            } else if (button.getText().toString() == "Разработка макета") { 
                log.info("3 Режим ");
                workTimeDesignerLabel.setEnabled(true);
                descriptionDesignerLabel.setEnabled(true);
                descriptionDesignerLabel.setText("Трудоёмкость макета должен оценить дизайнер");
                workTimeDesignerField.setEnabled(true);
                //workTimeDesigner = ((Number)countPlatesField.getValue()).intValue() * 40;
                //workTimeDesignerField.setValue(workTimeDesigner);
                planWorkTimeDesigner(button.getText().toString(), ((Number)countPlatesField.getValue()).intValue());
                salaryDesigner = new BigDecimal("30000.00");
                salaryDesignerField.setValue(salaryDesigner);
                log.info("Новое значение зарплаты " + salaryDesigner);
                salaryDesignerLabel.setText("Среднемесячная зарплата дизайнера");
            }
            amountMaket(new BigDecimal (workTimeDesigner), salaryDesigner);
          }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField amountAmortizationComputerField;
    private javax.swing.JFormattedTextField amountAmortizationField;
    private javax.swing.JFormattedTextField amountBasisField;
    private javax.swing.JFormattedTextField amountCoatField;
    private javax.swing.JFormattedTextField amountMaketField;
    private javax.swing.JFormattedTextField amountProfitField;
    private javax.swing.JFormattedTextField amountRentField;
    private javax.swing.JFormattedTextField amountTaxField;
    private javax.swing.JFormattedTextField amountWorkersField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField costField;
    private javax.swing.JFormattedTextField countPlatesField;
    private javax.swing.JFormattedTextField countWorkersField;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JFormattedTextField delivBasisField;
    private javax.swing.JFormattedTextField delivCoatField;
    private javax.swing.JLabel descriptionDesignerLabel;
    private javax.swing.JFormattedTextField heightField;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JFormattedTextField planWorkTimeWorkersField;
    private javax.swing.JFormattedTextField priceBasisField;
    private javax.swing.JFormattedTextField priceCoatField;
    private javax.swing.JFormattedTextField profitField;
    private javax.swing.JFormattedTextField rentField;
    private javax.swing.JFormattedTextField resSumField;
    private javax.swing.JFormattedTextField salaryDesignerField;
    private javax.swing.JLabel salaryDesignerLabel;
    private javax.swing.JFormattedTextField salaryWorkersField;
    private javax.swing.JFormattedTextField sheetHeightField;
    private javax.swing.JFormattedTextField sheetWidthField;
    private javax.swing.JFormattedTextField taxField;
    private javax.swing.JFormattedTextField timeRentField;
    private javax.swing.JFormattedTextField widthField;
    private javax.swing.JPanel workDesignerPanel;
    private javax.swing.JPanel workPanel;
    private javax.swing.JFormattedTextField workTimeDesignerField;
    private javax.swing.JLabel workTimeDesignerLabel;
    // End of variables declaration//GEN-END:variables
}
