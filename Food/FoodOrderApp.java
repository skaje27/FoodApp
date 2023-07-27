import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FoodOrderApp extends JFrame implements ActionListener {
    
    // GUI components
    private JLabel titleLabel = new JLabel("Food Order App");
    private JLabel nameLabel = new JLabel("Name:");
    private JTextField nameField = new JTextField(10);
    private JLabel foodLabel = new JLabel("Food Item:");
    private JComboBox<String> foodComboBox = new JComboBox<String>();
    private JLabel drinkLabel = new JLabel("Drink:");
    private JComboBox<String> drinkComboBox = new JComboBox<String>();
    private JLabel foodquantityLabel = new JLabel("Quantity:");
    private JTextField foodquantityField = new JTextField(5);
    private JLabel drinkquantityLabel = new JLabel("Quantity:");
    private JTextField drinkquantityField = new JTextField(5);
    private JButton addButton = new JButton("Add to Cart");
    private JButton checkoutButton = new JButton("Checkout");
    private JTextArea cartArea = new JTextArea(7, 30);
    private JScrollPane cartScrollPane = new JScrollPane(cartArea);
    
    // Food items
    private String[] foodItems = {"Burger", "French Fries", "Pizza", "Rice Bowl", "Pasta", "Salad"};
    //drinks
    private String[] drinks = {"Coke", "Fanta", "Sprite", "Mango Juice", "Apple Juice"};
    
    private String foodItem;
    private String drink;
    private int foodquantity;
    private int drinkquantity;
    private double totalCost;
    
    public FoodOrderApp() {
        // Set up the GUI
        setTitle("Food Order Management System");
        setSize(580, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create the top panel with the grid layout
        JPanel topPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        topPanel.setPreferredSize(new Dimension(480, 250));
    
        // Load the image file and add it to a label
        ImageIcon imageIcon = new ImageIcon("image.jpg");
        Image image = imageIcon.getImage().getScaledInstance(200, 140, Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(resizedImageIcon);
    
        // Load the image file and add it to a label
        ImageIcon image1Icon = new ImageIcon("image1.jpg");
        Image image1 = image1Icon.getImage().getScaledInstance(200, 140, Image.SCALE_SMOOTH);
        ImageIcon resizedImage1Icon = new ImageIcon(image1);
        JLabel image1Label = new JLabel(resizedImage1Icon);
    
        // Add components to the top panel
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.setBackground(Color.CYAN);
        topPanel.add(titleLabel);
        topPanel.add(new JLabel());
        topPanel.add(nameLabel);
        topPanel.add(nameField);
        topPanel.add(foodLabel);
        for (String food : foodItems) {
            foodComboBox.addItem(food);
        }
        topPanel.add(foodComboBox);
        topPanel.add(foodquantityLabel);
        topPanel.add(foodquantityField);
        topPanel.add(drinkLabel);
        for (String drink : drinks) {
            drinkComboBox.addItem(drink);
        }
        topPanel.add(drinkComboBox);
        topPanel.add(drinkquantityLabel);
        topPanel.add(drinkquantityField);
        topPanel.add(addButton);
        topPanel.add(checkoutButton);
        
        // Create a panel to hold the image labels
        JPanel imagePanel = new JPanel(new GridLayout(0, 2));
        imagePanel.add(imageLabel);
        imagePanel.add(image1Label);
        imagePanel.setBackground(Color.CYAN);
    
        // Add the components to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(cartScrollPane, BorderLayout.SOUTH);
        mainPanel.add(imagePanel, BorderLayout.CENTER);
    
        addButton.setBackground(Color.YELLOW);
        checkoutButton.setBackground(Color.GREEN);
    
        add(mainPanel);
        
        // Register event listeners
        addButton.addActionListener(this);
        checkoutButton.addActionListener(this);
    }
    
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            nameField.getText();
            foodItem = (String) foodComboBox.getSelectedItem();
            foodquantity = Integer.parseInt(foodquantityField.getText());
            drink = (String) drinkComboBox.getSelectedItem();
            drinkquantity = Integer.parseInt(drinkquantityField.getText());
            totalCost += calculateFoodCost(foodItem, foodquantity);
            totalCost += calculateDrinkCost(drink, drinkquantity);
            cartArea.append(foodquantity + " " + foodItem + " - Rs " + calculateFoodCost(foodItem, foodquantity)+ "\n" + drinkquantity + " " + drink + " - Rs " + calculateDrinkCost(drink, drinkquantity) + "\n");
        } else if (e.getSource() == checkoutButton) {
            // Display the total cost and reset the cart
            JOptionPane.showMessageDialog(null, "Total cost: Rs " + totalCost);
            cartArea.setText("");
            nameField.setText("");
            foodComboBox.setSelectedIndex(0);
            foodquantityField.setText("");
            drinkComboBox.setSelectedIndex(0);
            drinkquantityField.setText("");
            totalCost = 0;
        }
    }
    
    private double calculateFoodCost(String foodItem, int quantity) {
        double cost = 0;
        switch (foodItem) {
            case "Burger":
                cost = 110.00;
                break;
            case "French Fries":
                cost = 80.00;
                break;
            case "Pizza":
                cost = 199.00;
                break;
            case "Pasta":
                cost = 179.00;
                break;
            case "Rice Bowl":
                cost = 150.00;
                break;
            case "Salad":
                cost = 89.00;
                break;
        }
        
        return cost * quantity;

    }
    private double calculateDrinkCost(String drink, int quantity) {
        double cost = 0;
        switch (drink) {
            case "Coke":
                cost = 60.00;
                break;
            case "Fanta":
                cost = 60.00;
                break;
            case "Sprite":
                cost = 60.00;
                break;
            case "Mango Juice":
                cost = 70.00;
                break;
            case "Mixed Fruit Juice":
                cost = 70.00;
                break;
        }
        return cost * quantity;

    }
    public static void main(String[] args) {
        FoodOrderApp app = new FoodOrderApp();
        app.setVisible(true);
    }
}