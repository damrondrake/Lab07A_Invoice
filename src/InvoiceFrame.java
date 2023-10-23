import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InvoiceFrame extends JFrame implements ActionListener {

    private JTextField nameField, priceField, quantityField;
    private JTextArea invoiceArea;
    private ArrayList<LineItem> lineItems;

    public InvoiceFrame() {
        super("Invoice");

        // create input fields
        JLabel nameLabel = new JLabel("Product Name:");
        nameField = new JTextField(20);
        JLabel priceLabel = new JLabel("Unit Price:");
        priceField = new JTextField(10);
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(10);
        JButton addButton = new JButton("Add Line Item");
        addButton.addActionListener(this);

        // create output area
        invoiceArea = new JTextArea(20, 40);

        // add components to panel
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);
        inputPanel.add(addButton);

        // add components to frame
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(invoiceArea), BorderLayout.CENTER);

        // initialize line items
        lineItems = new ArrayList<LineItem>();

        // set up frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Product product = new Product(name, price);
        LineItem lineItem = new LineItem(product, quantity);
        lineItems.add(lineItem);

        displayInvoice();
    }

    private void displayInvoice() {
        double total = 0;

        invoiceArea.setText("");
        invoiceArea.append("Invoice:\n\n");

        for (LineItem lineItem : lineItems) {
            double cost = lineItem.getCost();
            total += cost;
        }
    }
}