package components.gui;

import concepts.FachkonzeptMock;
import concepts.IFachkonzept;
import models.Besitzer;
import models.BesitzerMeta;
import models.Fahrzeug;
import models.FahrzeugMeta;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Proxy;
import java.util.function.Function;

public class GraphicalUserInterface {
    protected IFachkonzept fachkonzept;
    private final DefaultListModel<BesitzerMeta> besitzerListModel;
    private final DefaultListModel<FahrzeugMeta> fahrzeugListModel;
    private final JList<BesitzerMeta> besitzerList;
    private final JList<FahrzeugMeta> fahrzeugList;
    private JPanel mainPanel;
    private JPanel besitzerPanel;
    private JPanel fahrzeugPanel;
    private JTextField addBesitzerTextField;
    private JTextField addFahrzeugTextField;
    private JButton addBesitzerBtn;
    private JButton addFahrzeugBtn;
    private JButton deleteBesitzerBtn;
    private JButton deleteFahrzeugBtn;
    private JScrollPane besitzerScrollPane;
    private JScrollPane fahrzeugScrollPane;
    private JButton verbindenButton;

    public GraphicalUserInterface(IFachkonzept fachkonzept) {
        this.fachkonzept = fachkonzept;
        this.verbindenButton.setForeground(Color.LIGHT_GRAY);
        this.verbindenButton.setEnabled(false);

        this.besitzerListModel = new DefaultListModel<>();
        this.fachkonzept.getAllBesitzer().map(BesitzerMeta::new).forEach(besitzerMeta -> {
            this.besitzerListModel.addElement(new BesitzerMeta(besitzerMeta.getBesitzerId(), besitzerMeta.getName()));
        });
        this.besitzerList = new JList<>(this.besitzerListModel);
        SetValueCallback(BesitzerMeta.class, besitzerList, BesitzerMeta::getName);
        this.besitzerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.besitzerScrollPane.setViewportView(besitzerList);

        this.fahrzeugListModel = new DefaultListModel<>();
        this.fachkonzept.getAllFahrzeuge().map(FahrzeugMeta::new).forEach(fahrzeugMeta -> {
            this.fahrzeugListModel.addElement(new FahrzeugMeta(fahrzeugMeta.getFahrzeugId(), fahrzeugMeta.getBezeichnung()));
        });
        this.fahrzeugList = new JList<>(this.fahrzeugListModel);
        SetValueCallback(FahrzeugMeta.class, fahrzeugList, FahrzeugMeta::getBezeichnung);
        this.fahrzeugScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.fahrzeugScrollPane.setViewportView(fahrzeugList);

        besitzerList.addListSelectionListener(e -> {
            checkOwnership();
            fahrzeugList.updateUI();
        });
        fahrzeugList.addListSelectionListener(e -> {
            checkOwnership();
            besitzerList.updateUI();
        });
        addColoring(fahrzeugList, (component, value) -> {
            BesitzerMeta besitzer = besitzerList.getSelectedValue();
            if(besitzer == null) {
                component.setForeground(Color.LIGHT_GRAY);
                return;
            }
            if(this.fachkonzept.getFahrzeugeByBesitzer(besitzer.getBesitzerId()).allMatch(fahrzeugMeta -> fahrzeugMeta.getFahrzeugId() != value.getFahrzeugId())){
                component.setForeground(Color.LIGHT_GRAY);
            }
        });
        addBesitzerBtn.addActionListener(e -> {
            String besitzer = addBesitzerTextField.getText();
            System.out.println(besitzer);
            Besitzer b = new Besitzer(besitzer);
            int id = this.fachkonzept.saveBesitzer(b);
            b.setBesitzerId(id);
            if(id != -1){
                this.besitzerListModel.addElement(b);
                addBesitzerTextField.setText("");
            }
        });
        addFahrzeugBtn.addActionListener(e -> {
            String fahrzeug = addFahrzeugTextField.getText();
            System.out.println(fahrzeug);
            Fahrzeug f = new Fahrzeug(fahrzeug);
            int id = this.fachkonzept.saveFahrzeug(f);
            f.setFahrzeugId(id);
            if(id != -1){
                this.fahrzeugListModel.addElement(f);
                addFahrzeugTextField.setText("");
            }
        });
        deleteBesitzerBtn.addActionListener(e -> {
            int index = besitzerList.getSelectedIndex();
            BesitzerMeta value = besitzerList.getSelectedValue();
            if(value != null){
                System.out.println(value);
                if(this.fachkonzept.deleteBesitzer(value.getBesitzerId())){
                    deleteElement(this.besitzerListModel, index);
                }
            }
        });
        deleteFahrzeugBtn.addActionListener(e -> {
            int index = fahrzeugList.getSelectedIndex();
            FahrzeugMeta value = fahrzeugList.getSelectedValue();
            if(value != null){
                System.out.println(value);
                if(this.fachkonzept.deleteFahrzeug(value.getFahrzeugId())){
                    deleteElement(this.fahrzeugListModel, index);
                }
            }
        });
        verbindenButton.addActionListener(e -> {
            BesitzerMeta besitzer = besitzerList.getSelectedValue();
            FahrzeugMeta fahrzeug = fahrzeugList.getSelectedValue();
            if( besitzer == null || fahrzeug == null ) {
                System.out.println("Es müssen beide ausgewählt werden.");
            } else if (this.fachkonzept.getFahrzeugeByBesitzer(besitzer.getBesitzerId()).anyMatch(fahrzeugMeta -> fahrzeugMeta.getFahrzeugId() == fahrzeug.getFahrzeugId())){
                this.fachkonzept.setNewBesitzer(fahrzeug.getFahrzeugId(), -1);
                this.verbindenButton.setText("Verbinden");
                System.out.println("Lösche Verbindung von " + besitzer.getName() + " und Fahrzeug " + fahrzeug.getBezeichnung());
            } else if (this.fachkonzept.setNewBesitzer(fahrzeug.getFahrzeugId(), besitzer.getBesitzerId())){
                this.verbindenButton.setText("Verbindung löschen");
                System.out.println("Verbinde Besitzer " + besitzer.getBesitzerId() + " mit Fahrzeug " + fahrzeug.getFahrzeugId());
            }
            fahrzeugList.updateUI();
        });
    }


    public static void main(String[] args) {
        JFrame f = new JFrame();
        IFachkonzept fachKonzept = new FachkonzeptMock();
        GraphicalUserInterface gui = new GraphicalUserInterface(fachKonzept);

        f.setContentPane(gui.mainPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setTitle("Fahrzeugverwaltung");
        f.setSize(400,600);
        f.setVisible(true);
    }

    private <T> void deleteElement(DefaultListModel<T> list, int index) {
        list.remove(index);
    }

    private void checkOwnership() {
        BesitzerMeta besitzer = besitzerList.getSelectedValue();
        FahrzeugMeta fahrzeug = fahrzeugList.getSelectedValue();
        if( besitzer == null || fahrzeug == null ) {
            this.verbindenButton.setForeground(Color.LIGHT_GRAY);
            this.verbindenButton.setEnabled(false);
        } else if (this.fachkonzept.getFahrzeugeByBesitzer(besitzer.getBesitzerId()).anyMatch(fahrzeugMeta -> fahrzeugMeta.getFahrzeugId() == fahrzeug.getFahrzeugId())){
            this.verbindenButton.setForeground(Color.BLACK);
            this.verbindenButton.setEnabled(true);
            this.verbindenButton.setText("Verbindung löschen");
        } else {
            this.verbindenButton.setEnabled(true);
            this.verbindenButton.setForeground(Color.BLACK);
            this.verbindenButton.setText("Verbinden");
        }
    }

    private static <T> void SetValueCallback(Class<T> tClass, @NotNull JList<T> besitzerList, Function<T, String> customToString) {
        besitzerList.setCellRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Object proxyInstance = Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{}, (proxy, method, args) -> {
                    if (method.getName().equals("toString")) {
                        return customToString.apply((T)value);
                    }

                    return method.invoke(value, args);
                });
                Component component = super.getListCellRendererComponent(list, (T) proxyInstance, index, isSelected, cellHasFocus);

                return component;
            }

        });
    }

    @FunctionalInterface
    private interface ColorComponentMethod<T>{
        void apply(Component component, T value);
    }

    private static <T> void addColoring(@NotNull JList<T> listToColor, ColorComponentMethod<T> colorComponent) {
        ListCellRenderer<? super T> originalCellRenderer = listToColor.getCellRenderer();
        listToColor.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            Component component = originalCellRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            colorComponent.apply(component, value);

            return component;
        });
    }
}
