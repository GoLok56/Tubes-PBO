package io.github.golok56.ui;

import io.github.golok56.helpers.Formatter;
import io.github.golok56.helpers.RowGenerator;
import io.github.golok56.models.Obat;
import io.github.golok56.repositories.ObatRepository;
import io.github.golok56.services.Navigation;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * @author satadii11
 */
public class DataObatFrame extends BaseFrame {

    private final ObatRepository mRepository = new ObatRepository();
    private DefaultTableModel mModel;
    
    /**
     * Creates new form DataObatFrame
     */
    public DataObatFrame() {
        initComponents();
        setVisible(true);
        
        mModel = (DefaultTableModel) table.getModel();
        
        inflateTable();
        
        tfHarga.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if(!Character.isDigit(ke.getKeyChar())){
                    ke.consume();
                }
            }
        });
        
        btnSimpan.addActionListener(e -> {
            Obat obat = getObatFromForm();
            if(obat == null){
                return;
            }
            
            try {
                if(mRepository.update(obat)){
//                    updateTable(obat);
                    showDialog("Berhasil melakukan perubahan terhadap suatu obat");
                } else {
                    showDialog("Mohon maaf! Terjadi kesalahan!");
                }
                clearForm();
            } catch (SQLException ex){
                showDialog(ex);
            }
        });
        
        btnTambah.addActionListener(e -> {
            Obat obat = getObatFromForm();
            if(obat == null){
                return;
            }
            
            try {
                if(mRepository.insert(obat)){
                    showDialog("Berhasil menyimpan obat baru.");
                    addToTable(obat);
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
            
            String kode = table.getValueAt(selectedRow, 0).toString();
            try {
                if(mRepository.delete(kode)){
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

    private void inflateTable(){
        List<Obat> obats;
        try {
            obats = mRepository.fetch();
        } catch (SQLException ex) { return; }
        obats.forEach(obat -> mModel.addRow(RowGenerator.generate(obat)));
    }
     
    private void addToTable(Obat obat){
        try {
            obat.setKadaluarsa(Formatter.format(obat.getKadaluarsa()));
            mModel.addRow(RowGenerator.generate(obat));
        } catch (ParseException ex) {
            showDialog(ex);
        }
    }
    
    private void clearForm(){
        tfKode.setText("");
        tfNama.setText("");
        tfSatuan.setText("");
        tfHarga.setText("");
        cbTahun.setSelectedIndex(0);
        cbTanggal.setSelectedIndex(0);
        cbBulan.setSelectedIndex(0);
    }
    
    private Obat getObatFromForm(){
        String kode = tfKode.getText();
        String nama = tfNama.getText();
        String satuan = tfSatuan.getText();

        if(kode.isEmpty() || nama.isEmpty() || satuan.isEmpty()){
            showDialog("Tidak boleh ada field yang kosong!");
            return null;
        }

        int harga;
        try {
            harga = Integer.parseInt(tfHarga.getText());
        } catch(NumberFormatException ex){
            showDialog("Tidak boleh ada field yang kosong!");
            return null;
        }

        String date = cbTanggal.getSelectedItem().toString();
        String month = cbBulan.getSelectedItem().toString();
        String year = cbTahun.getSelectedItem().toString();

        if(date.equals("Tanggal") || month.equals("Bulan") || year.equals("Tahun")){
            showDialog("Silahkan pilih tanggal kadaluarsa terlebih dahulu");
            return null;
        }

        String kadaluarsa = year + "-" + month + "-" + date;
        return new Obat(kode, nama, satuan, kadaluarsa, harga);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel container = new javax.swing.JPanel();
        javax.swing.JPanel form = new javax.swing.JPanel();
        tfNama = new javax.swing.JTextField();
        cbBulan = new javax.swing.JComboBox<>();
        cbTanggal = new javax.swing.JComboBox<>();
        tfKode = new javax.swing.JTextField();
        cbTahun = new javax.swing.JComboBox<>();
        javax.swing.JLabel labelNama = new javax.swing.JLabel();
        javax.swing.JLabel labelExpired = new javax.swing.JLabel();
        javax.swing.JLabel labelKode = new javax.swing.JLabel();
        javax.swing.JLabel labelSatuan = new javax.swing.JLabel();
        javax.swing.JLabel labelHarga = new javax.swing.JLabel();
        tfSatuan = new javax.swing.JTextField();
        tfHarga = new javax.swing.JTextField();
        tableContainer = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        javax.swing.JLabel labelDataObat = new javax.swing.JLabel();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Apotik - Data Obat");
        setResizable(false);

        container.setBackground(new java.awt.Color(28, 34, 54));

        form.setBackground(new java.awt.Color(28, 34, 54));
        form.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Form Obat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(254, 254, 254))); // NOI18N
        form.setForeground(new java.awt.Color(254, 254, 254));

        tfNama.setToolTipText("Enter the name of the medicine");

        cbBulan.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbBulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bulan", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cbBulan.setToolTipText("Choose the expired month");

        cbTanggal.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cbTanggal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tanggal", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cbTanggal.setToolTipText("Choose the expired date");

        tfKode.setToolTipText("Enter the code of the medicine");

        cbTahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tahun", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));
        cbTahun.setToolTipText("Choose the expired year");

        labelNama.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        labelNama.setForeground(new java.awt.Color(254, 254, 254));
        labelNama.setLabelFor(tfNama);
        labelNama.setText("Nama Obat");
        labelNama.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));

        labelExpired.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        labelExpired.setForeground(new java.awt.Color(254, 254, 254));
        labelExpired.setText("Kadaluarsa");
        labelExpired.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));

        labelKode.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        labelKode.setForeground(new java.awt.Color(254, 254, 254));
        labelKode.setLabelFor(tfKode);
        labelKode.setText("Kode Obat");
        labelKode.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));

        labelSatuan.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        labelSatuan.setForeground(new java.awt.Color(254, 254, 254));
        labelSatuan.setLabelFor(tfSatuan);
        labelSatuan.setText("Satuan");
        labelSatuan.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));

        labelHarga.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        labelHarga.setForeground(new java.awt.Color(254, 254, 254));
        labelHarga.setLabelFor(tfHarga);
        labelHarga.setText("Harga");
        labelHarga.setToolTipText("");
        labelHarga.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));

        tfHarga.setToolTipText("Enter medicine's price here");

        javax.swing.GroupLayout formLayout = new javax.swing.GroupLayout(form);
        form.setLayout(formLayout);
        formLayout.setHorizontalGroup(
            formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelKode, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNama, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelExpired, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfSatuan)
                    .addGroup(formLayout.createSequentialGroup()
                        .addComponent(cbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfNama)
                    .addComponent(tfKode)
                    .addComponent(tfHarga))
                .addGap(50, 50, 50))
        );
        formLayout.setVerticalGroup(
            formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelKode)
                    .addComponent(tfKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNama))
                .addGap(6, 6, 6)
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelExpired)
                    .addComponent(cbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSatuan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHarga))
                .addContainerGap())
        );

        tableContainer.setBackground(new java.awt.Color(28, 34, 54));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Obat", "Nama Obat", "Satuan", "Harga", "Kadaluarsa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableContainer.setViewportView(table);

        labelDataObat.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        labelDataObat.setForeground(new java.awt.Color(254, 254, 254));
        labelDataObat.setText("Data Obat");

        btnHapus.setBackground(new java.awt.Color(8, 170, 199));
        btnHapus.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(254, 254, 254));
        btnHapus.setText("Hapus");
        btnHapus.setBorderPainted(false);
        btnHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus.setOpaque(true);

        btnSimpan.setBackground(new java.awt.Color(8, 170, 199));
        btnSimpan.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(254, 254, 254));
        btnSimpan.setText("Simpan");
        btnSimpan.setBorderPainted(false);
        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.setOpaque(true);

        btnTambah.setBackground(new java.awt.Color(8, 170, 199));
        btnTambah.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(254, 254, 254));
        btnTambah.setText("Tambah");
        btnTambah.setBorderPainted(false);
        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.setOpaque(true);

        btnKembali.setBackground(new java.awt.Color(28, 34, 54));
        btnKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/back.png"))); // NOI18N
        btnKembali.setBorderPainted(false);
        btnKembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addComponent(labelDataObat)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerLayout.createSequentialGroup()
                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tableContainer)
                            .addComponent(form, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(containerLayout.createSequentialGroup()
                                .addComponent(btnKembali)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))))
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDataObat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnHapus)
                        .addComponent(btnSimpan)
                        .addComponent(btnTambah)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cbBulan;
    private javax.swing.JComboBox<String> cbTahun;
    private javax.swing.JComboBox<String> cbTanggal;
    private javax.swing.JTable table;
    private javax.swing.JScrollPane tableContainer;
    private javax.swing.JTextField tfHarga;
    private javax.swing.JTextField tfKode;
    private javax.swing.JTextField tfNama;
    private javax.swing.JTextField tfSatuan;
    // End of variables declaration//GEN-END:variables
}
