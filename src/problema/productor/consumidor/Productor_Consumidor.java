//Autores:
//Jesús Ricardo Delgado Sánchez
//Isaías Juarez Esparza

package problema.productor.consumidor;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;


public class Productor_Consumidor extends javax.swing.JFrame {
    ArrayList<String> productos = new ArrayList();   //Buffer
    HiloPrograma productor_consumidor = new HiloPrograma();
    String[] lista_productos = { "Papas", "Chicle", "Cacahuate", "Refresco", "Café", "Jabón", "Desodorante", "Leche", "Queso", "Mantequilla",
                                "Agua", "Paleta", "Tostadas", "Tortillas", "Cerveza", "Birote", "Hielo", "Azúcar", "Mayonesa", "Mermelada",
                                "Huevo", "Aceite", "Encendedor", "Frijoles", "Chiles", "Aluminio", "Yogur", "Pan", "Jamón", "Salchicha",
                                "Suero", "Rastrillo", "Shampoo", "Servilletas", "Pilas", "Té", "Chocolate", "Detergente", "Cloro", "Vasos",
                                "Platos", "Cerillos", "Cereal", "Palomitas", "Galletas", "Atún", "Medicamento", "Carbón", "Fibras", "Cinta"
                                };   //50 productos
    int indice_productor = 0;
    int indice_consumidor = 0;
    static final int max = 20;
    int total_productos = 0;
    Color color_defecto = new Color(240,240,240);      //#F0F0F0
    Color color_productor = new Color(255,217,102);    //#FFD966
    Color color_consumidor = new Color(255,181,144);   //#FFB590
    int descolorear_productor = 0;
    int descolorear_consumidor = 0;
    
    ImageIcon producir = new ImageIcon("src/img/producir.png");
    ImageIcon consumir = new ImageIcon("src/img/consumir.png");
    ImageIcon esperar = new ImageIcon("src/img/esperar.png");
    ImageIcon dormir = new ImageIcon("src/img/dormir.png");
    
    
    public Productor_Consumidor() {
        initComponents();
        
        //Configuración de la ventana
        this.setSize(600, 550);
        this.setResizable(false);
        this.setTitle("Productor-Consumidor");
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(192, 220, 240));
        
        SetArrayList();                 //Asigna por defecto el valor "—", lo que quiere decir que no hay productos agregados
        productor_consumidor.start();   //Comienza a ejecutarse el hilo
        
        Icon icono_productor = new ImageIcon(producir.getImage().getScaledInstance(iconProductor.getWidth(), iconProductor.getHeight(), Image.SCALE_DEFAULT));
        iconProductor.setIcon(icono_productor);
        Icon icono_consumidor = new ImageIcon(consumir.getImage().getScaledInstance(iconConsumidor.getWidth(), iconConsumidor.getHeight(), Image.SCALE_DEFAULT));
        iconConsumidor.setIcon(icono_consumidor);
        
        iconos(0, 0);
    }
    
    
    private void EvaluarProductor() {
        if (indice_productor >= max) {
            indice_productor = 0;
        }
    }
    
    private void EvaluarConsumidor() {
        if (indice_consumidor >= max) {
            indice_consumidor = 0;
        }
    }
    
    private void SetArrayList() {
        for(int i = 0; i < max; i++)
            productos.add(i, "—");
    }
    
    private int LanzamientoMoneda() {   //Se decide quien trabaja en ese momento, si el productor o consumidor (Semáforo)
        int numero_aleatorio = (int) (Math.floor(Math.random() * 2));   // 0 = Productor, 1 = Consumidor
        System.out.println("Lanzamiento Moneda: " + numero_aleatorio);
        return numero_aleatorio;
    }
    
    private int NumeroProductos() {
        int numero_aleatorio = (int) (Math.floor(Math.random() * 4) + 1);   //Números aleatorios del 1 - 4
        System.out.println("Número de Productos: " + numero_aleatorio);
        return numero_aleatorio;
    }
    
    private void iconos(int opc_producir, int opc_consumir) {   //0 = Sin Icono, 1 = Trabajar, 2 = Dormir, 3 = Esperar
        switch(opc_producir) {
            case 0:
                iconProductor1.setVisible(false);
                iconProductor2.setVisible(false);
            case 1:
                Icon productor_trabajar_1 = new ImageIcon(producir.getImage().getScaledInstance(iconProductor1.getWidth(), iconProductor1.getHeight(), Image.SCALE_DEFAULT));
                iconProductor1.setIcon(productor_trabajar_1);
                Icon productor_trabajar_2 = new ImageIcon(producir.getImage().getScaledInstance(iconProductor2.getWidth(), iconProductor2.getHeight(), Image.SCALE_DEFAULT));
                iconProductor2.setIcon(productor_trabajar_2);
                break;
            case 2:
                Icon productor_dormir_1 = new ImageIcon(dormir.getImage().getScaledInstance(iconProductor1.getWidth(), iconProductor1.getHeight(), Image.SCALE_DEFAULT));
                iconProductor1.setIcon(productor_dormir_1);
                Icon productor_dormir_2 = new ImageIcon(dormir.getImage().getScaledInstance(iconProductor2.getWidth(), iconProductor2.getHeight(), Image.SCALE_DEFAULT));
                iconProductor2.setIcon(productor_dormir_2);
                break;
            case 3:
                Icon productor_esperar_1 = new ImageIcon(esperar.getImage().getScaledInstance(iconProductor1.getWidth(), iconProductor1.getHeight(), Image.SCALE_DEFAULT));
                iconProductor1.setIcon(productor_esperar_1);
                Icon productor_esperar_2 = new ImageIcon(esperar.getImage().getScaledInstance(iconProductor2.getWidth(), iconProductor2.getHeight(), Image.SCALE_DEFAULT));
                iconProductor2.setIcon(productor_esperar_2);
                break;
        }
        
        if(opc_producir != 0) {
            iconProductor1.setVisible(true);
            iconProductor2.setVisible(true);
        }
        
        switch(opc_consumir) {
            case 0:
                iconConsumidor1.setVisible(false);
                iconConsumidor2.setVisible(false);
            case 1:
                Icon consumidor_trabajar_1 = new ImageIcon(consumir.getImage().getScaledInstance(iconConsumidor1.getWidth(), iconConsumidor1.getHeight(), Image.SCALE_DEFAULT));
                iconConsumidor1.setIcon(consumidor_trabajar_1);
                Icon consumidor_trabajar_2 = new ImageIcon(consumir.getImage().getScaledInstance(iconConsumidor2.getWidth(), iconConsumidor2.getHeight(), Image.SCALE_DEFAULT));
                iconConsumidor2.setIcon(consumidor_trabajar_2);
                break;
            case 2:
                Icon consumidor_dormir_1 = new ImageIcon(dormir.getImage().getScaledInstance(iconConsumidor1.getWidth(), iconConsumidor1.getHeight(), Image.SCALE_DEFAULT));
                iconConsumidor1.setIcon(consumidor_dormir_1);
                Icon consumidor_dormir_2 = new ImageIcon(dormir.getImage().getScaledInstance(iconConsumidor2.getWidth(), iconConsumidor2.getHeight(), Image.SCALE_DEFAULT));
                iconConsumidor2.setIcon(consumidor_dormir_2);
                break;
            case 3:
                Icon consumidor_esperar_1 = new ImageIcon(esperar.getImage().getScaledInstance(iconConsumidor1.getWidth(), iconConsumidor1.getHeight(), Image.SCALE_DEFAULT));
                iconConsumidor1.setIcon(consumidor_esperar_1);
                Icon consumidor_esperar_2 = new ImageIcon(esperar.getImage().getScaledInstance(iconConsumidor2.getWidth(), iconConsumidor2.getHeight(), Image.SCALE_DEFAULT));
                iconConsumidor2.setIcon(consumidor_esperar_2);
                break;
        }
        
        if(opc_consumir != 0) {
            iconConsumidor1.setVisible(true);
            iconConsumidor2.setVisible(true);
        }
    }
    
    private void Producir(int veces) {
        if(total_productos + veces >= max) {
            iconos(3, 1);
            lblProdujo.setText("Intentó producir " + veces + " producto(s).");
            JOptionPane.showMessageDialog(null, "No es posible producir más productos de los que se pueden almacenar.", "Error del productor", JOptionPane.ERROR_MESSAGE);
        }
        else {
            for(int i = 0; i < veces; i++) {
                EvaluarProductor();
                if (productos.get(indice_productor) != "—") {
                    iconos(3, 1);
                    JOptionPane.showMessageDialog(null, "Intentando producir... Esperando al consumidor", "Error del productor", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    String producto_generado = lista_productos[(int) (Math.floor(Math.random() * lista_productos.length))];
                    productos.set(indice_productor, producto_generado);
                    SetTextField(descolorear_productor, productos.get(descolorear_productor), false);
                    SetTextField(indice_productor, productos.get(indice_productor), false);

                    indice_productor++;
                    total_productos++;
                }
            }
            if(indice_consumidor > 0)
                SetTextField(indice_consumidor-1, productos.get(indice_consumidor-1), true);
            SetTextField(indice_productor-1, productos.get(indice_productor-1), true);
            descolorear_productor = indice_productor-1;
            iconos(1, 2);
            lblConsumio.setText("");
            lblProdujo.setText("Produjo " + veces + " producto(s).");
        }
    }
    
    private void Consumir(int veces) {
        if(total_productos < veces) {
            iconos(1, 3);
            lblConsumio.setText("Intentó consumir " + veces + " producto(s).");
            JOptionPane.showMessageDialog(null, "No es posible consumir más productos de los que hay en la lista.", "Error del consumidor", JOptionPane.ERROR_MESSAGE);
        }
        else {
            for(int i = 0; i < veces; i++) {
                EvaluarConsumidor();
                if (productos.get(indice_consumidor) == "—") {
                    iconos(1, 3);
                    JOptionPane.showMessageDialog(null, "Intentando consumir... Esperando al productor", "Error del consumidor", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    productos.set(indice_consumidor, "—");
                    SetTextField(descolorear_consumidor, productos.get(descolorear_consumidor), false);
                    SetTextField(indice_consumidor, productos.get(indice_consumidor), false);

                    indice_consumidor++;
                    total_productos--;
                }
            }
            if(indice_productor > 0)
                SetTextField(indice_productor-1, productos.get(indice_productor-1), true);
            SetTextField(indice_consumidor-1, productos.get(indice_consumidor-1), true);
            descolorear_consumidor = indice_consumidor-1;
            iconos(2, 1);
            lblProdujo.setText("");
            lblConsumio.setText("Consumió " + veces + " producto(s).");
        }
    }

    private void SetTextField(int num, String txt, boolean colorear) {
        Color color;
        
        if(colorear && num == indice_productor-1) {
            color = color_productor;
        }
        else if(colorear && num == indice_consumidor-1) {
            color = color_consumidor;
        }
        else {
            color = color_defecto;
        }
        
        switch (num+1) {
            case 1: txt1.setText(txt); txt1.setBackground(color); break;
            case 2: txt2.setText(txt); txt2.setBackground(color); break;
            case 3: txt3.setText(txt); txt3.setBackground(color); break;
            case 4: txt4.setText(txt); txt4.setBackground(color); break;
            case 5: txt5.setText(txt); txt5.setBackground(color); break;
            case 6: txt6.setText(txt); txt6.setBackground(color); break;
            case 7: txt7.setText(txt); txt7.setBackground(color); break;
            case 8: txt8.setText(txt); txt8.setBackground(color); break;
            case 9: txt9.setText(txt); txt9.setBackground(color); break;
            case 10: txt10.setText(txt); txt10.setBackground(color); break;
            case 11: txt11.setText(txt); txt11.setBackground(color); break;
            case 12: txt12.setText(txt); txt12.setBackground(color); break;
            case 13: txt13.setText(txt); txt13.setBackground(color); break;
            case 14: txt14.setText(txt); txt14.setBackground(color); break;
            case 15: txt15.setText(txt); txt15.setBackground(color); break;
            case 16: txt16.setText(txt); txt16.setBackground(color); break;
            case 17: txt17.setText(txt); txt17.setBackground(color); break;
            case 18: txt18.setText(txt); txt18.setBackground(color); break;
            case 19: txt19.setText(txt); txt19.setBackground(color); break;
            case 20: txt20.setText(txt); txt20.setBackground(color); break;
        }
    }
    
    public int GenerarTiempoHilo() {
        int tiempo_aleatorio = (int)(Math.floor(Math.random() * (5000 - 1000 + 1) + 1000));   //Generar números entre 1000 - 5000 (1 a 5 segundos)
        System.out.println("Tiempo: " + tiempo_aleatorio);
        
        return tiempo_aleatorio;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        iconProductor = new javax.swing.JLabel();
        iconConsumidor = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        txt3 = new javax.swing.JTextField();
        txt4 = new javax.swing.JTextField();
        txt5 = new javax.swing.JTextField();
        txt6 = new javax.swing.JTextField();
        txt7 = new javax.swing.JTextField();
        txt8 = new javax.swing.JTextField();
        txt9 = new javax.swing.JTextField();
        txt10 = new javax.swing.JTextField();
        txt11 = new javax.swing.JTextField();
        txt12 = new javax.swing.JTextField();
        txt13 = new javax.swing.JTextField();
        txt14 = new javax.swing.JTextField();
        txt15 = new javax.swing.JTextField();
        txt16 = new javax.swing.JTextField();
        txt17 = new javax.swing.JTextField();
        txt18 = new javax.swing.JTextField();
        txt19 = new javax.swing.JTextField();
        txt20 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblProductor = new javax.swing.JLabel();
        lblProdujo = new javax.swing.JLabel();
        iconProductor1 = new javax.swing.JLabel();
        iconProductor2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblConsumidor = new javax.swing.JLabel();
        lblConsumio = new javax.swing.JLabel();
        iconConsumidor1 = new javax.swing.JLabel();
        iconConsumidor2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Productor-Consumidor");

        jPanel1.setBackground(new java.awt.Color(153, 176, 192));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setName(""); // NOI18N

        txt1.setEditable(false);
        txt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt1.setText("—");
        txt1.setPreferredSize(new java.awt.Dimension(100, 30));

        txt2.setEditable(false);
        txt2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt2.setText("—");
        txt2.setPreferredSize(new java.awt.Dimension(100, 30));

        txt3.setEditable(false);
        txt3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt3.setText("—");
        txt3.setPreferredSize(new java.awt.Dimension(100, 30));

        txt4.setEditable(false);
        txt4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt4.setText("—");
        txt4.setPreferredSize(new java.awt.Dimension(100, 30));

        txt5.setEditable(false);
        txt5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt5.setText("—");
        txt5.setPreferredSize(new java.awt.Dimension(100, 30));

        txt6.setEditable(false);
        txt6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt6.setText("—");
        txt6.setPreferredSize(new java.awt.Dimension(100, 30));

        txt7.setEditable(false);
        txt7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt7.setText("—");
        txt7.setPreferredSize(new java.awt.Dimension(100, 30));

        txt8.setEditable(false);
        txt8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt8.setText("—");
        txt8.setPreferredSize(new java.awt.Dimension(100, 30));

        txt9.setEditable(false);
        txt9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt9.setText("—");
        txt9.setPreferredSize(new java.awt.Dimension(100, 30));

        txt10.setEditable(false);
        txt10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt10.setText("—");
        txt10.setPreferredSize(new java.awt.Dimension(100, 30));

        txt11.setEditable(false);
        txt11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt11.setText("—");
        txt11.setToolTipText("");
        txt11.setPreferredSize(new java.awt.Dimension(100, 30));

        txt12.setEditable(false);
        txt12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt12.setText("—");
        txt12.setPreferredSize(new java.awt.Dimension(100, 30));

        txt13.setEditable(false);
        txt13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt13.setText("—");
        txt13.setPreferredSize(new java.awt.Dimension(100, 30));

        txt14.setEditable(false);
        txt14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt14.setText("—");
        txt14.setPreferredSize(new java.awt.Dimension(100, 30));

        txt15.setEditable(false);
        txt15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt15.setText("—");
        txt15.setPreferredSize(new java.awt.Dimension(100, 30));

        txt16.setEditable(false);
        txt16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt16.setText("—");
        txt16.setPreferredSize(new java.awt.Dimension(100, 30));

        txt17.setEditable(false);
        txt17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt17.setText("—");
        txt17.setPreferredSize(new java.awt.Dimension(100, 30));

        txt18.setEditable(false);
        txt18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt18.setText("—");
        txt18.setPreferredSize(new java.awt.Dimension(100, 30));

        txt19.setEditable(false);
        txt19.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt19.setText("—");
        txt19.setPreferredSize(new java.awt.Dimension(100, 30));

        txt20.setEditable(false);
        txt20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt20.setText("—");
        txt20.setPreferredSize(new java.awt.Dimension(100, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 217, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setMaximumSize(new java.awt.Dimension(180, 100));
        jPanel2.setMinimumSize(new java.awt.Dimension(180, 100));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblProductor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblProductor.setText("PRODUCTOR");
        jPanel2.add(lblProductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 15, -1, -1));

        lblProdujo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProdujo.setText("Produjo X productos.");
        jPanel2.add(lblProdujo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 65, 200, 20));
        jPanel2.add(iconProductor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 32, 32));
        jPanel2.add(iconProductor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 20, 32, 32));

        jPanel3.setBackground(new java.awt.Color(255, 181, 144));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setMaximumSize(new java.awt.Dimension(180, 100));
        jPanel3.setMinimumSize(new java.awt.Dimension(180, 100));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblConsumidor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblConsumidor.setText("CONSUMIDOR");
        jPanel3.add(lblConsumidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 15, -1, -1));

        lblConsumio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConsumio.setText("Consumió X productos.");
        jPanel3.add(lblConsumio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 65, 200, 15));
        jPanel3.add(iconConsumidor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 32, 32));
        jPanel3.add(iconConsumidor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 20, 32, 32));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(iconProductor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(iconConsumidor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(iconProductor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(iconConsumidor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private class HiloPrograma extends Thread {
        public void run() {
            int num_veces_comienzo = NumeroProductos();
            Producir(num_veces_comienzo);
            try {
                Thread.sleep(GenerarTiempoHilo());
            } catch (InterruptedException ex) {}
            
            while(true) {
                
                int quien_trabaja = LanzamientoMoneda();
                int num_veces = NumeroProductos();
                
                if(quien_trabaja == 0)
                    Producir(num_veces);
                else
                    Consumir(num_veces);
                
                try {
                    Thread.sleep(GenerarTiempoHilo());
                } catch (InterruptedException ex) {}
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Productor_Consumidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Productor_Consumidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Productor_Consumidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Productor_Consumidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
            
        /* Create and display the form */
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JTextField component = new JTextField();
                component.addKeyListener(new MyKeyListener());
                Productor_Consumidor p = new Productor_Consumidor();
                p.add(component);
                p.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconConsumidor;
    private javax.swing.JLabel iconConsumidor1;
    private javax.swing.JLabel iconConsumidor2;
    private javax.swing.JLabel iconProductor;
    private javax.swing.JLabel iconProductor1;
    private javax.swing.JLabel iconProductor2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblConsumidor;
    private javax.swing.JLabel lblConsumio;
    private javax.swing.JLabel lblProductor;
    private javax.swing.JLabel lblProdujo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt10;
    private javax.swing.JTextField txt11;
    private javax.swing.JTextField txt12;
    private javax.swing.JTextField txt13;
    private javax.swing.JTextField txt14;
    private javax.swing.JTextField txt15;
    private javax.swing.JTextField txt16;
    private javax.swing.JTextField txt17;
    private javax.swing.JTextField txt18;
    private javax.swing.JTextField txt19;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt20;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JTextField txt5;
    private javax.swing.JTextField txt6;
    private javax.swing.JTextField txt7;
    private javax.swing.JTextField txt8;
    private javax.swing.JTextField txt9;
    // End of variables declaration//GEN-END:variables
}

class MyKeyListener extends KeyAdapter {   //Clase que detecta el evento del teclado
  public void keyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {   //Si se presiona escape salimos del programa
        System.exit(0);
    }
  }
}

//Autores:
//Jesús Ricardo Delgado Sánchez
//Isaías Juarez Esparza