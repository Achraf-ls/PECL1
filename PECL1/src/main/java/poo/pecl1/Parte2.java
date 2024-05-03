/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poo.pecl1;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gisela González y Achraf El Idrissi
 */
public class Parte2 extends javax.swing.JFrame implements Serializable {

    /**
     * Creates new form Parte2
     *
     */
    private boolean pista1M = true;
    private boolean pista2M = true;
    private boolean pista3M = true;
    private boolean pista4M = true;
    private boolean pista1B = true;
    private boolean pista2B = true;
    private boolean pista3B = true;
    private boolean pista4B = true;
    private int contadorM = 4;
    private int contadorB = 4;
    private Conexor2 conexor2;

    private Aeropuerto aeropuertoMadrid;
    private Aeropuerto aeropuertoBarcelona;

    public Parte2() {
        initComponents();
        botonAbrir1M.setEnabled(false);
        botonAbrir2M.setEnabled(false);
        botonAbrir3M.setEnabled(false);
        botonAbrir4M.setEnabled(false);

        botonAbrir1B.setEnabled(false);
        botonAbrir2B.setEnabled(false);
        botonAbrir3B.setEnabled(false);
        botonAbrir4B.setEnabled(false);
        this.setLocationRelativeTo(null);
        try {
            inicializarConexor2();
        } catch (Exception e) {
            System.out.println(e);
        }

        inicializar();

    }

    public void inicializar() {

        Thread updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    try {
                        actualizarInformación();

                        InterfazConexion conexor = (InterfazConexion) Naming.lookup("//127.0.0.1/ObjetoConecta");

                        // Solicitar los objetos actualizados de los aeropuertos
                        aeropuertoMadrid = conexor.getAeropuertoMadrid();
                        aeropuertoBarcelona = conexor.getAeropuertoBarcelona();
                        pasajerosM.setText(Integer.toString(aeropuertoMadrid.getPasajeros()));
                        pasajerosB.setText(Integer.toString(aeropuertoBarcelona.getPasajeros()));
                        hangarM.setText(Integer.toString(aeropuertoMadrid.getHangar().size()));
                        hangarB.setText(Integer.toString(aeropuertoBarcelona.getHangar().size()));
                        tallerM.setText(Integer.toString(aeropuertoMadrid.getAvionesTaller().size()));
                        tallerB.setText(Integer.toString(aeropuertoBarcelona.getAvionesTaller().size()));
                        estacionamientoM.setText(Integer.toString(aeropuertoMadrid.getAreaDeEstacionamiento().size()));
                        estacionamientoB.setText(Integer.toString(aeropuertoBarcelona.getAreaDeEstacionamiento().size()));
                        areaRodajeM.setText(Integer.toString(aeropuertoMadrid.getAreaDeRodaje().size()));
                        areaRodajeB.setText(Integer.toString(aeropuertoBarcelona.getAreaDeRodaje().size()));
                        obtenerAeroviaMB();
                        obtenerAeroviaBM();

                        // Esperar medio segundo antes de la próxima actualización
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        updateThread.start();

    }

    public void actualizarInformación() {
        try {

            boolean[] pistasMadrid = new boolean[]{pista1M, pista2M, pista3M, pista4M};
            boolean[] pistasBarcelona = new boolean[]{pista1B, pista2B, pista3B, pista4B};

            conexor2.enviarDatos(pistasMadrid, pistasBarcelona, contadorM, contadorB);

        } catch (Exception ex) {
            Logger.getLogger(Parte1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void inicializarConexor2() throws RemoteException {
        try {
            conexor2 = new Conexor2();

            boolean[] pistasMadrid = new boolean[]{pista1M, pista2M, pista3M, pista4M};
            boolean[] pistasBarcelona = new boolean[]{pista1B, pista2B, pista3B, pista4B};

            conexor2.enviarDatos(pistasMadrid, pistasBarcelona, contadorM, contadorB);

            // Registrar el conector en el registro RMI
            Registry registro = LocateRegistry.createRegistry(1050);
            Naming.rebind("//127.0.0.1/ObjetoConecta2", conexor2);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Parte1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void obtenerAeroviaMB() {
        ConcurrentLinkedQueue<Avion> aerovia = aeropuertoMadrid.getAerovia().getAvionesAerovia();
        StringBuilder avionesAerovia = new StringBuilder();

        for (Avion avion : aerovia) {
            avionesAerovia.append(avion.getNombreAvion());
            avionesAerovia.append(", ");
        }

        if (avionesAerovia.length() > 0) {
            avionesAerovia.delete(avionesAerovia.length() - 2, avionesAerovia.length());
        }
        aeroviaMB1.setText(avionesAerovia.toString());
    }

    public void obtenerAeroviaBM() {
        ConcurrentLinkedQueue<Avion> aerovia = aeropuertoBarcelona.getAerovia().getAvionesAerovia();
        StringBuilder avionesAerovia = new StringBuilder();

        for (Avion avion : aerovia) {
            avionesAerovia.append(avion.getNombreAvion());
            avionesAerovia.append(", ");
        }

        if (avionesAerovia.length() > 0) {
            avionesAerovia.delete(avionesAerovia.length() - 2, avionesAerovia.length());
        }
        aeroviaBM1.setText(avionesAerovia.toString());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelMadrid = new javax.swing.JPanel();
        labelAMadrid = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pasajerosM = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        hangarM = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tallerM = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        estacionamientoM = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        areaRodajeM = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        botonCerrar1M = new javax.swing.JButton();
        botonAbrir1M = new javax.swing.JButton();
        botonCerrar2M = new javax.swing.JButton();
        botonCerrar3M = new javax.swing.JButton();
        botonCerrar4M = new javax.swing.JButton();
        botonAbrir2M = new javax.swing.JButton();
        botonAbrir3M = new javax.swing.JButton();
        botonAbrir4M = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        aeroviaBM1 = new javax.swing.JTextField();
        aeroviaMB1 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        panelBarcelona = new javax.swing.JPanel();
        labelABarcelona = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pasajerosB = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        hangarB = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tallerB = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        estacionamientoB = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        areaRodajeB = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        botonCerrar1B = new javax.swing.JButton();
        botonAbrir1B = new javax.swing.JButton();
        botonCerrar2B = new javax.swing.JButton();
        botonCerrar3B = new javax.swing.JButton();
        botonCerrar4B = new javax.swing.JButton();
        botonAbrir2B = new javax.swing.JButton();
        botonAbrir3B = new javax.swing.JButton();
        botonAbrir4B = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMadrid.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153)));

        labelAMadrid.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        labelAMadrid.setText("Aeropuerto de Madrid");

        jLabel3.setText("Nº de pasajeros en Aeropuerto:");

        jLabel4.setText("Nº de aviones en Hangar:");

        jLabel9.setText("Nº de aviones en Taller:");

        jLabel10.setText("Nº de aviones en Área de estacionamiento:");

        jLabel17.setText("Nº de aviones en Área de rodaje: ");

        jLabel18.setText("Pista 1:");

        jLabel19.setText("Pista 2:");

        jLabel20.setText("Pista 3:");

        jLabel21.setText("Pista 4:");

        botonCerrar1M.setText("Cerrar");
        botonCerrar1M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrar1MActionPerformed(evt);
            }
        });

        botonAbrir1M.setText("Abrir");
        botonAbrir1M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrir1MActionPerformed(evt);
            }
        });

        botonCerrar2M.setText("Cerrar");
        botonCerrar2M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrar2MActionPerformed(evt);
            }
        });

        botonCerrar3M.setText("Cerrar");
        botonCerrar3M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrar3MActionPerformed(evt);
            }
        });

        botonCerrar4M.setText("Cerrar");
        botonCerrar4M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrar4MActionPerformed(evt);
            }
        });

        botonAbrir2M.setText("Abrir");
        botonAbrir2M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrir2MActionPerformed(evt);
            }
        });

        botonAbrir3M.setText("Abrir");
        botonAbrir3M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrir3MActionPerformed(evt);
            }
        });

        botonAbrir4M.setText("Abrir");
        botonAbrir4M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrir4MActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMadridLayout = new javax.swing.GroupLayout(panelMadrid);
        panelMadrid.setLayout(panelMadridLayout);
        panelMadridLayout.setHorizontalGroup(
            panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMadridLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelMadridLayout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(estacionamientoM))
                        .addGroup(panelMadridLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pasajerosM))
                        .addGroup(panelMadridLayout.createSequentialGroup()
                            .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4)
                                .addGroup(panelMadridLayout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(hangarM)
                                .addComponent(tallerM, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelMadridLayout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(areaRodajeM, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelMadridLayout.createSequentialGroup()
                        .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMadridLayout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonCerrar1M, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonAbrir1M, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelMadridLayout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonCerrar2M, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonAbrir2M, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37)
                        .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMadridLayout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonCerrar4M, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonAbrir4M, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelMadridLayout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonCerrar3M, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonAbrir3M, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(34, 34, 34))
            .addGroup(panelMadridLayout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(labelAMadrid)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMadridLayout.setVerticalGroup(
            panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMadridLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelAMadrid)
                .addGap(28, 28, 28)
                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(pasajerosM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hangarM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tallerM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(estacionamientoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(areaRodajeM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20)
                    .addComponent(botonCerrar1M)
                    .addComponent(botonAbrir1M)
                    .addComponent(botonCerrar3M)
                    .addComponent(botonAbrir3M))
                .addGap(25, 25, 25)
                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel19)
                    .addComponent(botonCerrar2M)
                    .addComponent(botonCerrar4M)
                    .addComponent(botonAbrir2M)
                    .addComponent(botonAbrir4M))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jLabel37.setText("Aerovía Barcelona - Madrid:");

        jLabel38.setText("Aerovía Madrid - Barcelona:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aeroviaMB1, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aeroviaBM1, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel38)
                .addGap(8, 8, 8)
                .addComponent(aeroviaMB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aeroviaBM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        panelBarcelona.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153)));

        labelABarcelona.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        labelABarcelona.setText("Aeropuerto de Barcelona");

        jLabel5.setText("Nº de pasajeros en Aeropuerto:");

        jLabel6.setText("Nº de aviones en Hangar:");

        jLabel11.setText("Nº de aviones en Taller:");

        jLabel12.setText("Nº de aviones en Área de estacionamiento:");

        jLabel22.setText("Nº de aviones en Área de rodaje: ");

        jLabel23.setText("Pista 1:");

        jLabel24.setText("Pista 2:");

        jLabel25.setText("Pista 3:");

        jLabel26.setText("Pista 4:");

        botonCerrar1B.setText("Cerrar");
        botonCerrar1B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrar1BActionPerformed(evt);
            }
        });

        botonAbrir1B.setText("Abrir");
        botonAbrir1B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrir1BActionPerformed(evt);
            }
        });

        botonCerrar2B.setText("Cerrar");
        botonCerrar2B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrar2BActionPerformed(evt);
            }
        });

        botonCerrar3B.setText("Cerrar");
        botonCerrar3B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrar3BActionPerformed(evt);
            }
        });

        botonCerrar4B.setText("Cerrar");
        botonCerrar4B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrar4BActionPerformed(evt);
            }
        });

        botonAbrir2B.setText("Abrir");
        botonAbrir2B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrir2BActionPerformed(evt);
            }
        });

        botonAbrir3B.setText("Abrir");
        botonAbrir3B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrir3BActionPerformed(evt);
            }
        });

        botonAbrir4B.setText("Abrir");
        botonAbrir4B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrir4BActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBarcelonaLayout = new javax.swing.GroupLayout(panelBarcelona);
        panelBarcelona.setLayout(panelBarcelonaLayout);
        panelBarcelonaLayout.setHorizontalGroup(
            panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBarcelonaLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelBarcelonaLayout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(estacionamientoB))
                        .addGroup(panelBarcelonaLayout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pasajerosB))
                        .addGroup(panelBarcelonaLayout.createSequentialGroup()
                            .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel6)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(hangarB)
                                .addComponent(tallerB, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelBarcelonaLayout.createSequentialGroup()
                            .addComponent(jLabel22)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(areaRodajeB, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBarcelonaLayout.createSequentialGroup()
                        .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBarcelonaLayout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonCerrar1B, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonAbrir1B, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBarcelonaLayout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonCerrar2B, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonAbrir2B, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37)
                        .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBarcelonaLayout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonCerrar4B, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonAbrir4B, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBarcelonaLayout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonCerrar3B, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonAbrir3B, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(34, 34, 34))
            .addGroup(panelBarcelonaLayout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(labelABarcelona)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBarcelonaLayout.setVerticalGroup(
            panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarcelonaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelABarcelona)
                .addGap(30, 30, 30)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(pasajerosB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hangarB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tallerB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(estacionamientoB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(areaRodajeB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel25)
                    .addComponent(botonCerrar1B)
                    .addComponent(botonAbrir1B)
                    .addComponent(botonCerrar3B)
                    .addComponent(botonAbrir3B))
                .addGap(25, 25, 25)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel24)
                    .addComponent(botonCerrar2B)
                    .addComponent(botonCerrar4B)
                    .addComponent(botonAbrir2B)
                    .addComponent(botonAbrir4B))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelMadrid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelBarcelona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMadrid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBarcelona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCerrar1MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrar1MActionPerformed
        botonAbrir1M.setEnabled(true);
        botonCerrar1M.setEnabled(false);
        pista1M = false;
        contadorM--;
    }//GEN-LAST:event_botonCerrar1MActionPerformed

    private void botonAbrir1MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrir1MActionPerformed
        botonAbrir1M.setEnabled(false);
        botonCerrar1M.setEnabled(true);
        pista1M = true;
        contadorM++;
    }//GEN-LAST:event_botonAbrir1MActionPerformed

    private void botonCerrar2MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrar2MActionPerformed
        botonAbrir2M.setEnabled(true);
        botonCerrar2M.setEnabled(false);
        pista2M = false;
        contadorM--;
    }//GEN-LAST:event_botonCerrar2MActionPerformed

    private void botonAbrir2MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrir2MActionPerformed
        botonAbrir2M.setEnabled(false);
        botonCerrar2M.setEnabled(true);
        pista2M = true;
        contadorM++;
    }//GEN-LAST:event_botonAbrir2MActionPerformed

    private void botonCerrar3MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrar3MActionPerformed
        botonAbrir3M.setEnabled(true);
        botonCerrar3M.setEnabled(false);
        pista3M = false;
        contadorM--;
    }//GEN-LAST:event_botonCerrar3MActionPerformed

    private void botonAbrir3MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrir3MActionPerformed
        botonAbrir3M.setEnabled(false);
        botonCerrar3M.setEnabled(true);
        pista3M = true;
        contadorM++;
    }//GEN-LAST:event_botonAbrir3MActionPerformed

    private void botonCerrar4MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrar4MActionPerformed
        botonAbrir4M.setEnabled(true);
        botonCerrar4M.setEnabled(false);
        pista4M = false;
        contadorM--;
    }//GEN-LAST:event_botonCerrar4MActionPerformed

    private void botonAbrir4MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrir4MActionPerformed
        botonAbrir4M.setEnabled(false);
        botonCerrar4M.setEnabled(true);
        pista4M = true;
        contadorM++;
    }//GEN-LAST:event_botonAbrir4MActionPerformed

    private void botonCerrar1BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrar1BActionPerformed
        botonAbrir1B.setEnabled(true);
        botonCerrar1B.setEnabled(false);
        pista1B = false;
        contadorB--;
    }//GEN-LAST:event_botonCerrar1BActionPerformed

    private void botonAbrir1BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrir1BActionPerformed
        botonAbrir1B.setEnabled(false);
        botonCerrar1B.setEnabled(true);
        pista1B = true;
        contadorB++;
    }//GEN-LAST:event_botonAbrir1BActionPerformed

    private void botonCerrar2BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrar2BActionPerformed
        botonAbrir2B.setEnabled(true);
        botonCerrar2B.setEnabled(false);
        pista2B = false;
        contadorB--;
    }//GEN-LAST:event_botonCerrar2BActionPerformed

    private void botonAbrir2BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrir2BActionPerformed
        botonAbrir2B.setEnabled(false);
        botonCerrar2B.setEnabled(true);
        pista2B = true;
        contadorB++;
    }//GEN-LAST:event_botonAbrir2BActionPerformed

    private void botonCerrar3BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrar3BActionPerformed
        botonAbrir3B.setEnabled(true);
        botonCerrar3B.setEnabled(false);
        pista3B = false;
        contadorB--;
    }//GEN-LAST:event_botonCerrar3BActionPerformed

    private void botonAbrir3BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrir3BActionPerformed
        botonAbrir3B.setEnabled(false);
        botonCerrar3B.setEnabled(true);
        pista3B = true;
        contadorB++;
    }//GEN-LAST:event_botonAbrir3BActionPerformed

    private void botonCerrar4BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrar4BActionPerformed
        botonAbrir4B.setEnabled(true);
        botonCerrar4B.setEnabled(false);
        pista4B = false;
        contadorB--;
    }//GEN-LAST:event_botonCerrar4BActionPerformed

    private void botonAbrir4BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrir4BActionPerformed
        botonAbrir4B.setEnabled(false);
        botonCerrar4B.setEnabled(true);
        pista4B = true;
        contadorB++;
    }//GEN-LAST:event_botonAbrir4BActionPerformed

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
            java.util.logging.Logger.getLogger(Parte2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Parte2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Parte2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Parte2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Parte2().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField aeroviaBM;
    private javax.swing.JTextField aeroviaBM1;
    private javax.swing.JTextField aeroviaMB;
    private javax.swing.JTextField aeroviaMB1;
    private javax.swing.JTextField areaRodajeB;
    private javax.swing.JTextField areaRodajeM;
    private javax.swing.JButton botonAbrir1B;
    private javax.swing.JButton botonAbrir1M;
    private javax.swing.JButton botonAbrir2B;
    private javax.swing.JButton botonAbrir2M;
    private javax.swing.JButton botonAbrir3B;
    private javax.swing.JButton botonAbrir3M;
    private javax.swing.JButton botonAbrir4B;
    private javax.swing.JButton botonAbrir4M;
    private javax.swing.JButton botonCerrar1B;
    private javax.swing.JButton botonCerrar1M;
    private javax.swing.JButton botonCerrar2B;
    private javax.swing.JButton botonCerrar2M;
    private javax.swing.JButton botonCerrar3B;
    private javax.swing.JButton botonCerrar3M;
    private javax.swing.JButton botonCerrar4B;
    private javax.swing.JButton botonCerrar4M;
    private javax.swing.JTextField estacionamientoB;
    private javax.swing.JTextField estacionamientoM;
    private javax.swing.JTextField hangarB;
    private javax.swing.JTextField hangarM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelABarcelona;
    private javax.swing.JLabel labelAMadrid;
    private javax.swing.JPanel panelAerovias;
    private javax.swing.JPanel panelBarcelona;
    private javax.swing.JPanel panelMadrid;
    private javax.swing.JTextField pasajerosB;
    private javax.swing.JTextField pasajerosM;
    private javax.swing.JTextField tallerB;
    private javax.swing.JTextField tallerM;
    // End of variables declaration//GEN-END:variables
}
