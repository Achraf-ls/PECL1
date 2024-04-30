/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poo.pecl1;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.SwingUtilities;

/**
 *
 * @author Achraf El Idrissi y Gisela González
 *
 */
public class Ventana extends javax.swing.JFrame {

    Aeropuerto aeropuertoMadrid = new Aeropuerto();
    Aeropuerto aeropuertoBarcelona = new Aeropuerto();

    Aerovia aeroviaMadBar = new Aerovia(aeropuertoBarcelona);
    Aerovia aeroviaBarMad = new Aerovia(aeropuertoMadrid);

    /**
     * Creates new form Ventana
     */
    public Ventana() {
        initComponents();
        inicializar();
        obtener();

    }

    /**
     * Metodo para iniciar la ventana y los hilos del programa
     */
    public void inicializar() {
        botonRenaudar.setEnabled(false);
        this.setLocationRelativeTo(null);

        aeropuertoMadrid.setPasajeros(1000);
        aeropuertoBarcelona.setPasajeros(1000);
        aeropuertoMadrid.setAerovia(aeroviaMadBar);
        aeropuertoBarcelona.setAerovia(aeroviaBarMad);

        Thread avionesThread = new Thread(() -> {
            for (int i = 0; i < 8000; i++) {
                if (i % 2 == 0) {
                    new Avion(i, aeropuertoMadrid).start();
                } else {
                    new Avion(i, aeropuertoBarcelona).start();
                }
                try {
                    Thread.sleep(1000 + new Random().nextInt(2001)); //intervalo entre 1s y 3s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread autobusesThread = new Thread(() -> {
            for (int i = 0; i < 4000; i++) {
                if (i % 2 == 0) {
                    new Autobus(i, aeropuertoMadrid).start();
                } else {
                    new Autobus(i, aeropuertoBarcelona).start();
                }
                try {
                    Thread.sleep(500 + new Random().nextInt(501)); // intervalo entre 0,5s y 1s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        avionesThread.start();
        autobusesThread.start();
    }

    /**
     * Metodo para obtener todos los datos necesarios, mostrarlos en los
     * JTextFields y actualizarlos cada cierto tiempo
     */
    public void obtener() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            pasajerosM.setText(Integer.toString(aeropuertoMadrid.getPasajeros()));
                            pasajerosB.setText(Integer.toString(aeropuertoBarcelona.getPasajeros()));
                            obtenerHangarM();
                            obtenerHangarB();
                            obtenerTallerM();
                            obtenerTallerB();
                            obtenerAreaEstacionamientoM();
                            obtenerAreaEstacionamientoB();
                            obtenerAreaRodajeM();
                            obtenerAreaRodajeB();
                            obtenerAeroviaMB();
                            obtenerAeroviaBM();
                            obtenerPistasM();
                            obtenerPistasB();
                            obtenerPuertasM();
                            obtenerPuertasB();
                        }
                    });
                    try {
                        Thread.sleep(25);  // Espera medio segundo antes de la próxima actualización
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    /**
     * Metodo para obtener un String con los aviones que se encuentran en el
     * hangar del aeropuerto de Madrid
     */
    public void obtenerHangarM() {
        ConcurrentLinkedQueue<Avion> hangar = aeropuertoMadrid.getHangar();
        StringBuilder avionesHangar = new StringBuilder();

        for (Avion avion : hangar) {
            avionesHangar.append(avion.getNombreAvion());
            avionesHangar.append(", ");
        }

        if (avionesHangar.length() > 0) {
            avionesHangar.delete(avionesHangar.length() - 2, avionesHangar.length());
        }
        hangarM.setText(avionesHangar.toString());
    }

    /**
     * Metodo para obtener un String con los aviones que se encuentran en el
     * hangar del aeropuerto de Barcelona
     */
    public void obtenerHangarB() {
        ConcurrentLinkedQueue<Avion> hangar = aeropuertoBarcelona.getHangar();
        StringBuilder avionesHangar = new StringBuilder();

        for (Avion avion : hangar) {
            avionesHangar.append(avion.getNombreAvion());
            avionesHangar.append(", ");
        }

        if (avionesHangar.length() > 0) {
            avionesHangar.delete(avionesHangar.length() - 2, avionesHangar.length());
        }
        hangarB.setText(avionesHangar.toString());
    }

    /**
     * Metodo para obtener un String con los aviones que se encuentran en el
     * taller del aeropuerto de Madrid
     */
    public void obtenerTallerM() {
        ConcurrentLinkedQueue<Avion> taller = aeropuertoMadrid.getAvionesTaller();
        StringBuilder avionesTaller = new StringBuilder();

        for (Avion avion : taller) {
            avionesTaller.append(avion.getNombreAvion());
            avionesTaller.append(", ");
        }

        if (avionesTaller.length() > 0) {
            avionesTaller.delete(avionesTaller.length() - 2, avionesTaller.length());
        }
        tallerM.setText(avionesTaller.toString());
    }

    /**
     * Metodo para obtener un String con los aviones que se encuentran en el
     * taller del aeropuerto de Barcelona
     */
    public void obtenerTallerB() {
        ConcurrentLinkedQueue<Avion> taller = aeropuertoBarcelona.getAvionesTaller();
        StringBuilder avionesTaller = new StringBuilder();

        for (Avion avion : taller) {
            avionesTaller.append(avion.getNombreAvion());
            avionesTaller.append(", ");
        }

        if (avionesTaller.length() > 0) {
            avionesTaller.delete(avionesTaller.length() - 2, avionesTaller.length());
        }
        tallerB.setText(avionesTaller.toString());
    }

    public void obtenerAreaEstacionamientoM() {
        ConcurrentLinkedQueue<Avion> area = aeropuertoMadrid.getAreaDeEstacionamiento();
        StringBuilder avionesArea = new StringBuilder();

        for (Avion avion : area) {
            avionesArea.append(avion.getNombreAvion());
            avionesArea.append(", ");
        }

        if (avionesArea.length() > 0) {
            avionesArea.delete(avionesArea.length() - 2, avionesArea.length());
        }
        estacionamientoM.setText(avionesArea.toString());
    }

    public void obtenerAreaEstacionamientoB() {
        ConcurrentLinkedQueue<Avion> area = aeropuertoBarcelona.getAreaDeEstacionamiento();
        StringBuilder avionesArea = new StringBuilder();

        for (Avion avion : area) {
            avionesArea.append(avion.getNombreAvion());
            avionesArea.append(", ");
        }

        if (avionesArea.length() > 0) {
            avionesArea.delete(avionesArea.length() - 2, avionesArea.length());
        }
        estacionamientoB.setText(avionesArea.toString());
    }

    public void obtenerAreaRodajeM() {
        ConcurrentLinkedQueue<Avion> area = aeropuertoMadrid.getAreaDeRodaje();
        StringBuilder avionesArea = new StringBuilder();

        for (Avion avion : area) {
            avionesArea.append(avion.getNombreAvion());
            avionesArea.append(", ");
        }

        if (avionesArea.length() > 0) {
            avionesArea.delete(avionesArea.length() - 2, avionesArea.length());
        }
        areaRodajeM.setText(avionesArea.toString());
    }

    public void obtenerAreaRodajeB() {
        ConcurrentLinkedQueue<Avion> area = aeropuertoBarcelona.getAreaDeRodaje();
        StringBuilder avionesArea = new StringBuilder();

        for (Avion avion : area) {
            avionesArea.append(avion.getNombreAvion());
            avionesArea.append(", ");
        }

        if (avionesArea.length() > 0) {
            avionesArea.delete(avionesArea.length() - 2, avionesArea.length());
        }
        areaRodajeB.setText(avionesArea.toString());
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
        aeroviaMB.setText(avionesAerovia.toString());
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
        aeroviaBM.setText(avionesAerovia.toString());
    }

    public void obtenerPuertasM() {
        gate1M.setText("");
        gate2M.setText("");
        gate3M.setText("");
        gate4M.setText("");
        gate5M.setText("");
        gate6M.setText("");

        ArrayList<Avion> avionesPuertas = aeropuertoMadrid.getAvionesPuertas();
        String valor;

        for (int i = 0; i < avionesPuertas.size(); i++) {
            if (avionesPuertas.get(i) != null) {
                Avion avion = avionesPuertas.get(i);
                if (avion.getIdAvion() % 2 == 0) {
                    valor = "Embarque: ";
                } else {
                    valor = "Desembarque: ";
                }
                String texto = valor + avion.getNombreAvion();

                if ("".equals(gate1M.getText()) && i == 0) {
                    gate1M.setText(texto);
                } else if ("".equals(gate2M.getText()) && i == 1) {
                    gate2M.setText(texto);
                } else if ("".equals(gate3M.getText()) && i == 2) {
                    gate3M.setText(texto);
                } else if ("".equals(gate4M.getText()) && i == 3) {
                    gate4M.setText(texto);
                } else if ("".equals(gate5M.getText()) && i == 2) {
                    gate5M.setText(texto);
                } else if ("".equals(gate6M.getText()) && i == 3) {
                    gate6M.setText(texto);
                }
            }
        }
    }

    public void obtenerPuertasB() {
        gate1B.setText("");
        gate2B.setText("");
        gate3B.setText("");
        gate4B.setText("");
        gate5B.setText("");
        gate6B.setText("");

        ArrayList<Avion> avionesPuertas = aeropuertoBarcelona.getAvionesPuertas();
        String valor;

        for (int i = 0; i < avionesPuertas.size(); i++) {
            if (avionesPuertas.get(i) != null) {
                Avion avion = avionesPuertas.get(i);
                if (avion.getIdAvion() % 2 == 0) {
                    valor = "Desembarque: ";
                } else {
                    valor = "Embarque: ";
                }
                String texto = valor + avion.getNombreAvion();

                if ("".equals(gate1B.getText()) && i == 0) {
                    gate1B.setText(texto);
                } else if ("".equals(gate2B.getText()) && i == 1) {
                    gate2B.setText(texto);
                } else if ("".equals(gate3B.getText()) && i == 2) {
                    gate3B.setText(texto);
                } else if ("".equals(gate4B.getText()) && i == 3) {
                    gate4B.setText(texto);
                } else if ("".equals(gate5B.getText()) && i == 2) {
                    gate5B.setText(texto);
                } else if ("".equals(gate6B.getText()) && i == 3) {
                    gate6B.setText(texto);
                }
            }
        }
    }

    public void obtenerPistasM() {
        pista1M.setText("");
        pista2M.setText("");
        pista3M.setText("");
        pista4M.setText("");
        ArrayList<Avion> avionesPista = aeropuertoMadrid.getListaPista();
        String valor;

        for (int i = 0; i < avionesPista.size(); i++) {
            if (avionesPista.get(i) != null) {
                Avion avion = avionesPista.get(i);
                if (avion.getIdAvion() % 2 == 0) {
                    valor = "Despegue: ";
                } else {
                    valor = "Aterrizaje: ";
                }
                String texto = valor + avion.getNombreAvion();

                if ("".equals(pista1M.getText()) && i == 0) {
                    pista1M.setText(texto);
                } else if ("".equals(pista2M.getText()) && i == 1) {
                    pista2M.setText(texto);
                } else if ("".equals(pista3M.getText()) && i == 2) {
                    pista3M.setText(texto);
                } else if ("".equals(pista4M.getText()) && i == 3) {
                    pista4M.setText(texto);
                }
            }
        }
    }

    public void obtenerPistasB() {
        pista1B.setText("");
        pista2B.setText("");
        pista3B.setText("");
        pista4B.setText("");
        ArrayList<Avion> avionesPista = aeropuertoBarcelona.getListaPista();
        String valor;

        for (int i = 0; i < avionesPista.size(); i++) {
            if (avionesPista.get(i) != null) {
                Avion avion = avionesPista.get(i);
                if (avion.getIdAvion() % 2 == 0) {
                    valor = "Aterrizaje: ";
                } else {
                    valor = "Despegue: ";
                }
                String texto = valor + avion.getNombreAvion();

                if ("".equals(pista1B.getText()) && i == 0) {
                    pista1B.setText(texto);
                } else if ("".equals(pista2B.getText()) && i == 1) {
                    pista2B.setText(texto);
                } else if ("".equals(pista3B.getText()) && i == 2) {
                    pista3B.setText(texto);
                } else if ("".equals(pista4B.getText()) && i == 3) {
                    pista4B.setText(texto);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo = new javax.swing.JPanel();
        panelBarcelona = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        busesAB = new javax.swing.JTextField();
        busesCB = new javax.swing.JTextField();
        labelABarcelona = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        gate6B = new javax.swing.JTextField();
        tallerB = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        estacionamientoB = new javax.swing.JTextField();
        areaRodajeB = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        pista1B = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        pista2B = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        gate1B = new javax.swing.JTextField();
        gate2B = new javax.swing.JTextField();
        gate3B = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        pista3B = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        pista4B = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        pasajerosB = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        gate4B = new javax.swing.JTextField();
        hangarB = new javax.swing.JTextField();
        gate5B = new javax.swing.JTextField();
        panelMadrid = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        busesAM = new javax.swing.JTextField();
        busesCM = new javax.swing.JTextField();
        labelAMadrid = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pasajerosM = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        hangarM = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tallerM = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        estacionamientoM = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        gate1M = new javax.swing.JTextField();
        gate2M = new javax.swing.JTextField();
        gate3M = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        gate4M = new javax.swing.JTextField();
        gate5M = new javax.swing.JTextField();
        gate6M = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        areaRodajeM = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        pista1M = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        pista2M = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        pista3M = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        pista4M = new javax.swing.JTextField();
        panelAerovias = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        aeroviaBM = new javax.swing.JTextField();
        aeroviaMB = new javax.swing.JTextField();
        panelBotones = new javax.swing.JPanel();
        botonPausar = new javax.swing.JButton();
        botonRenaudar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBarcelona.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153)));

        jLabel5.setText("Transfers Aeropuerto:");

        jLabel6.setText("Transfers Ciudad:");

        labelABarcelona.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        labelABarcelona.setText("Aeropuerto de Barcelona");

        jLabel22.setText("Taller:");

        jLabel23.setText("Área de rodaje: ");

        jLabel24.setText("Área de estacionamiento:");

        jLabel25.setText("Pista 1:");

        jLabel26.setText("Gate 1:");

        jLabel27.setText("Pista 2:");

        jLabel28.setText("Gate 2:");

        jLabel29.setText("Gate 3:");

        jLabel30.setText("Pista 3:");

        jLabel31.setText("Pista 4:");

        jLabel32.setText("Gate 4:");

        jLabel33.setText("Nº de pasajeros:");

        jLabel34.setText("Gate 5:");

        jLabel35.setText("Gate 6:");

        jLabel36.setText("Hangar:");

        javax.swing.GroupLayout panelBarcelonaLayout = new javax.swing.GroupLayout(panelBarcelona);
        panelBarcelona.setLayout(panelBarcelonaLayout);
        panelBarcelonaLayout.setHorizontalGroup(
            panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarcelonaLayout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(labelABarcelona)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBarcelonaLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelBarcelonaLayout.createSequentialGroup()
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tallerB))
                        .addGroup(panelBarcelonaLayout.createSequentialGroup()
                            .addComponent(jLabel33)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pasajerosB))
                        .addGroup(panelBarcelonaLayout.createSequentialGroup()
                            .addComponent(jLabel36)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(hangarB, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBarcelonaLayout.createSequentialGroup()
                            .addComponent(jLabel24)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(estacionamientoB))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBarcelonaLayout.createSequentialGroup()
                            .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(panelBarcelonaLayout.createSequentialGroup()
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(gate3B))
                                .addGroup(panelBarcelonaLayout.createSequentialGroup()
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(gate2B))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBarcelonaLayout.createSequentialGroup()
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(gate1B, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(panelBarcelonaLayout.createSequentialGroup()
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(gate6B))
                                .addGroup(panelBarcelonaLayout.createSequentialGroup()
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(gate5B))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBarcelonaLayout.createSequentialGroup()
                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(gate4B, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(panelBarcelonaLayout.createSequentialGroup()
                            .addComponent(jLabel23)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(areaRodajeB))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBarcelonaLayout.createSequentialGroup()
                            .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBarcelonaLayout.createSequentialGroup()
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(pista2B))
                                .addGroup(panelBarcelonaLayout.createSequentialGroup()
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(pista1B, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(87, 87, 87)
                            .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBarcelonaLayout.createSequentialGroup()
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(pista4B))
                                .addGroup(panelBarcelonaLayout.createSequentialGroup()
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(pista3B, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelBarcelonaLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(busesAB, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(busesCB, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        panelBarcelonaLayout.setVerticalGroup(
            panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarcelonaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelABarcelona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(busesAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(busesCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(pasajerosB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hangarB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addGap(18, 18, 18)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(tallerB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(estacionamientoB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBarcelonaLayout.createSequentialGroup()
                        .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(gate1B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(gate2B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(gate3B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBarcelonaLayout.createSequentialGroup()
                        .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(gate4B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(gate5B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(gate6B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(areaRodajeB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBarcelonaLayout.createSequentialGroup()
                        .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(pista1B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(pista2B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBarcelonaLayout.createSequentialGroup()
                        .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(pista3B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBarcelonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(pista4B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelMadrid.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153)));

        jLabel7.setText("Transfers Aeropuerto:");

        jLabel8.setText("Transfers Ciudad:");

        labelAMadrid.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        labelAMadrid.setText("Aeropuerto de Madrid");

        jLabel3.setText("Nº de pasajeros:");

        jLabel4.setText("Hangar:");

        jLabel9.setText("Taller:");

        jLabel10.setText("Área de estacionamiento:");

        jLabel11.setText("Gate 1:");

        jLabel12.setText("Gate 2:");

        jLabel13.setText("Gate 3:");

        jLabel14.setText("Gate 4:");

        jLabel15.setText("Gate 5:");

        jLabel16.setText("Gate 6:");

        jLabel17.setText("Área de rodaje: ");

        jLabel18.setText("Pista 1:");

        jLabel19.setText("Pista 2:");

        jLabel20.setText("Pista 3:");

        jLabel21.setText("Pista 4:");

        javax.swing.GroupLayout panelMadridLayout = new javax.swing.GroupLayout(panelMadrid);
        panelMadrid.setLayout(panelMadridLayout);
        panelMadridLayout.setHorizontalGroup(
            panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMadridLayout.createSequentialGroup()
                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMadridLayout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(labelAMadrid))
                    .addGroup(panelMadridLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMadridLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tallerM))
                            .addGroup(panelMadridLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pasajerosM))
                            .addGroup(panelMadridLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(estacionamientoM, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
                            .addGroup(panelMadridLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(areaRodajeM))
                            .addGroup(panelMadridLayout.createSequentialGroup()
                                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelMadridLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(busesAM, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(busesCM, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelMadridLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(hangarM, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelMadridLayout.createSequentialGroup()
                                        .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(panelMadridLayout.createSequentialGroup()
                                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(gate2M))
                                                .addGroup(panelMadridLayout.createSequentialGroup()
                                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(gate1M, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(panelMadridLayout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(gate3M, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(74, 74, 74)
                                        .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(panelMadridLayout.createSequentialGroup()
                                                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(gate5M)
                                                    .addComponent(gate6M)))
                                            .addGroup(panelMadridLayout.createSequentialGroup()
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(gate4M, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(panelMadridLayout.createSequentialGroup()
                                        .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelMadridLayout.createSequentialGroup()
                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(pista2M))
                                            .addGroup(panelMadridLayout.createSequentialGroup()
                                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(pista1M, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(87, 87, 87)
                                        .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelMadridLayout.createSequentialGroup()
                                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(pista4M))
                                            .addGroup(panelMadridLayout.createSequentialGroup()
                                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(pista3M, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMadridLayout.setVerticalGroup(
            panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMadridLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelAMadrid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(busesAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(busesCM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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
                    .addComponent(jLabel11)
                    .addComponent(gate1M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(gate4M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(gate2M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(gate5M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(gate3M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(gate6M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(areaRodajeM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMadridLayout.createSequentialGroup()
                        .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(pista1M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(pista2M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelMadridLayout.createSequentialGroup()
                        .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(pista3M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMadridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(pista4M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel1.setText("Aerovía Madrid - Barcelona:");

        jLabel2.setText("Aerovía Barcelona - Madrid:");

        javax.swing.GroupLayout panelAeroviasLayout = new javax.swing.GroupLayout(panelAerovias);
        panelAerovias.setLayout(panelAeroviasLayout);
        panelAeroviasLayout.setHorizontalGroup(
            panelAeroviasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAeroviasLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelAeroviasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aeroviaMB, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aeroviaBM, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        panelAeroviasLayout.setVerticalGroup(
            panelAeroviasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAeroviasLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addComponent(aeroviaMB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aeroviaBM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        botonPausar.setText("Pausar");
        botonPausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPausarActionPerformed(evt);
            }
        });

        botonRenaudar.setText("Renaudar");
        botonRenaudar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRenaudarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addContainerGap(384, Short.MAX_VALUE)
                .addComponent(botonPausar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109)
                .addComponent(botonRenaudar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(399, 399, 399))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonPausar)
                    .addComponent(botonRenaudar))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMadrid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(550, 550, 550))
            .addGroup(fondoLayout.createSequentialGroup()
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAerovias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 68, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelBarcelona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(panelBarcelona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(panelMadrid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAerovias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonPausarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPausarActionPerformed
        botonPausar.setEnabled(false);
        botonRenaudar.setEnabled(true);
    }//GEN-LAST:event_botonPausarActionPerformed

    private void botonRenaudarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRenaudarActionPerformed
        botonRenaudar.setEnabled(false);
        botonPausar.setEnabled(true);
    }//GEN-LAST:event_botonRenaudarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws InterruptedException {
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
            java.util.logging.Logger.getLogger(Ventana.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField aeroviaBM;
    private javax.swing.JTextField aeroviaMB;
    private javax.swing.JTextField areaRodajeB;
    private javax.swing.JTextField areaRodajeM;
    private javax.swing.JButton botonPausar;
    private javax.swing.JButton botonRenaudar;
    private javax.swing.JTextField busesAB;
    private javax.swing.JTextField busesAM;
    private javax.swing.JTextField busesCB;
    private javax.swing.JTextField busesCM;
    private javax.swing.JTextField estacionamientoB;
    private javax.swing.JTextField estacionamientoM;
    private javax.swing.JPanel fondo;
    private javax.swing.JTextField gate1B;
    private javax.swing.JTextField gate1M;
    private javax.swing.JTextField gate2B;
    private javax.swing.JTextField gate2M;
    private javax.swing.JTextField gate3B;
    private javax.swing.JTextField gate3M;
    private javax.swing.JTextField gate4B;
    private javax.swing.JTextField gate4M;
    private javax.swing.JTextField gate5B;
    private javax.swing.JTextField gate5M;
    private javax.swing.JTextField gate6B;
    private javax.swing.JTextField gate6M;
    private javax.swing.JTextField hangarB;
    private javax.swing.JTextField hangarM;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labelABarcelona;
    private javax.swing.JLabel labelAMadrid;
    private javax.swing.JPanel panelAerovias;
    private javax.swing.JPanel panelBarcelona;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelMadrid;
    private javax.swing.JTextField pasajerosB;
    private javax.swing.JTextField pasajerosM;
    private javax.swing.JTextField pista1B;
    private javax.swing.JTextField pista1M;
    private javax.swing.JTextField pista2B;
    private javax.swing.JTextField pista2M;
    private javax.swing.JTextField pista3B;
    private javax.swing.JTextField pista3M;
    private javax.swing.JTextField pista4B;
    private javax.swing.JTextField pista4M;
    private javax.swing.JTextField tallerB;
    private javax.swing.JTextField tallerM;
    // End of variables declaration//GEN-END:variables
}
