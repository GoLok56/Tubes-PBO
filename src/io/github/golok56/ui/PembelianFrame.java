/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.golok56.ui;

import io.github.golok56.helpers.Formatter;
import io.github.golok56.helpers.RowGenerator;
import io.github.golok56.models.Obat;
import io.github.golok56.models.Pembelian;
import io.github.golok56.repositories.ObatRepository;
import io.github.golok56.repositories.PembelianRepository;
import io.github.golok56.services.Navigation;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author satadii11
 */
public class PembelianFrame extends BaseFrame {

    private final PembelianRepository mRepository = new PembelianRepository();
    private final ObatRepository mObatRepository = new ObatRepository();
    
    private DefaultTableModel mModel;
    private List<Obat> mObats;
    private List<Pembelian> mPembelians;
    
    /**
     * Creates new form PembelianFrame
     */
    public PembelianFrame() {
        initComponents();
        setVisible(true);
         
        try {
            mObats = mObatRepository.fetch();
        } catch(SQLException ex){
            showDialog(ex);
        }
        mModel = (DefaultTableModel) table.getModel();
        
        inflateCombobox();
        inflateTable();
                
        btnTambah.addActionListener(e -> {
            Pembelian pembelian = getPembelianFromForm();
            if(pembelian == null){
                return;
            }
            
            try {
                if(mRepository.insert(pembelian)){
                    refresh();
                    showDialog("Berhasil menyimpan obat baru.");
                } else {
                    showDialog("Mohon maaf! Terjadi kesalahan!");
                }
                clearForm();
            } catch (SQLException ex) {
                showDialog(ex);
            }
        });
        
        btnHapus.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if(selectedRow == -1){
                showDialog("Pilih record yang ingin dihapus!");
                return;
            }
            
            int id = mPembelians.get(selectedRow).getId();
            try {
                if(mRepository.delete(id)){
                    mModel.removeRow(selectedRow);
                    showDialog("Berhasil menghapus data obat");
                } else {
                    showDialog("Mohon maaf! Terjadi kesalahan!");
                }
            } catch (SQLException ex) {
                showDialog(ex);
            }
        });
        
        btnKembali.addActionListener(ev -> Navigation.showMainMenu(this));
        
        table.getTableHeader().setBackground(new Color(238,255,107));
    }
    
    private void inflateCombobox(){
        String[] namaObat = new String[mObats.size()];
        for (int i = 0; i < namaObat.length; i++) {
            namaObat[i] = mObats.get(i).getNama();
        }
        
        DefaultComboBoxModel model = new DefaultComboBoxModel(namaObat);
        cbObat.setModel(model);
    }
    
    private void inflateTable(){
        try {
            mPembelians = mRepository.fetch();
        } catch (SQLException ex) { return; }
        mPembelians.forEach(pembelian -> mModel.addRow(RowGenerator.generate(pembelian)));
    }
    
    private Pembelian getPembelianFromForm(){
        Obat obat = mObats.get(cbObat.getSelectedIndex());
        
        String nama = obat.getNama();
        String date = Formatter.now();
        String jumlahString = tfJumlah.getText();
        
        if(jumlahString.isEmpty()){
            showDialog("Masukkan jumlah obat yang terjual");
            return null;
        }
        
        int jumlah = Integer.parseInt(jumlahString);
        int total = jumlah * obat.getHarga();
        
        return new Pembelian(nama, date, jumlah, total);
    }
    
    private void refresh(){
        int rows = table.getRowCount();
        for (int i = 0; i < rows; i++) {
            mModel.removeRow(0);
        }
        inflateTable();
    }
    
    private void clearForm(){
        cbObat.setSelectedIndex(0);
        tfJumlah.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        form = new javax.swing.JPanel();
        javax.swing.JLabel labelNamaObat = new javax.swing.JLabel();
        javax.swing.JLabel labelJumlah = new javax.swing.JLabel();
        tfJumlah = new javax.swing.JTextField();
        cbObat = new javax.swing.JComboBox<>();
        javax.swing.JLabel labelDataPemasok = new javax.swing.JLabel();
        tableContainer = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnKembali = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        container.setBackground(new java.awt.Color(28, 34, 54));

        form.setBackground(new java.awt.Color(28, 34, 54));
        form.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Form Pembelian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        labelNamaObat.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        labelNamaObat.setForeground(new java.awt.Color(254, 254, 254));
        labelNamaObat.setText("Nama Obat");
        labelNamaObat.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));

        labelJumlah.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        labelJumlah.setForeground(new java.awt.Color(254, 254, 254));
        labelJumlah.setText("Jumlah");
        labelJumlah.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));

        cbObat.setBackground(new java.awt.Color(255, 255, 255));
        cbObat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout formLayout = new javax.swing.GroupLayout(form);
        form.setLayout(formLayout);
        formLayout.setHorizontalGroup(
            formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNamaObat)
                    .addComponent(labelJumlah))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(cbObat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        formLayout.setVerticalGroup(
            formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNamaObat, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbObat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelJumlah))
                .addContainerGap())
        );

        labelDataPemasok.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        labelDataPemasok.setForeground(new java.awt.Color(254, 254, 254));
        labelDataPemasok.setText("Data Pembelian");

        tableContainer.setBackground(new java.awt.Color(28, 34, 54));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Obat", "Tanggal", "Jumlah", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableContainer.setViewportView(table);

        btnKembali.setBackground(new java.awt.Color(28, 34, 54));
        btnKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/back.png"))); // NOI18N
        btnKembali.setBorderPainted(false);
        btnKembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnTambah.setBackground(new java.awt.Color(8, 170, 199));
        btnTambah.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(254, 254, 254));
        btnTambah.setText("Tambah");
        btnTambah.setBorderPainted(false);
        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.setOpaque(true);

        btnHapus.setBackground(new java.awt.Color(8, 170, 199));
        btnHapus.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(254, 254, 254));
        btnHapus.setText("Hapus");
        btnHapus.setBorderPainted(false);
        btnHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus.setOpaque(true);

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addComponent(labelDataPemasok)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerLayout.createSequentialGroup()
                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(containerLayout.createSequentialGroup()
                                .addComponent(btnKembali)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tableContainer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                            .addComponent(form, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30))))
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDataPemasok)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnHapus)
                        .addComponent(btnTambah)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cbObat;
    private javax.swing.JPanel container;
    private javax.swing.JPanel form;
    private javax.swing.JTable table;
    private javax.swing.JScrollPane tableContainer;
    private javax.swing.JTextField tfJumlah;
    // End of variables declaration//GEN-END:variables
}
