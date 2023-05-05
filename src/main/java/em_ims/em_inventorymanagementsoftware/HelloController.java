package em_ims.em_inventorymanagementsoftware;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Evelyn G Morrow.
 * @version 1.0.
 * Public class HelloController is used to retrieve and display the most up-to-date data on the parts and products tables after the user successfully signs in, as well as to manage some functionality such as delete, search, etc.
 */
public class HelloController implements Initializable {



    /**
     * Public void initialize() method called to initialize a controller after its root element has been completely processed.
     * @param url is used to resolve relative paths for the root object. It is null if the url is not known.
     * @param rb is used to localize the root object, and it is null if the root object is not located.
     * @exception SQLException if a database error or other errors occur.
     * @see SQLException
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        //SQL Query - executed in the backend database
        String partsViewQuery = "SELECT partID, part_name, stock, price_unit FROM parts";

        String productsViewQuery = "SELECT productID, product_name, stock, price_unit FROM products";

//        try {
//            Statement statement = connectDB.createStatement();
//            ResultSet queryPartsOutput = statement.executeQuery(partsViewQuery);
//
//            while (queryPartsOutput.next()) {
//
//                //populate the observableList
//                partList.add(new PartData(queryPartsOutput.getInt("partID"),
//                        queryPartsOutput.getString("part_name"),
//                        queryPartsOutput.getInt("stock"),
//                        queryPartsOutput.getBigDecimal("price_unit")));
//            }
//
//
//            //PropertyValueFactory corresponds to the new PartData fields
//            //the table column is the one we annotate above
//            parts_tableView_col_partID.setCellValueFactory(new PropertyValueFactory<>("partID"));
//            parts_tableView_col_partName.setCellValueFactory(new PropertyValueFactory<>("part_name"));
//            parts_tableView_col_inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
//            parts_tableView_col_priceUnit.setCellValueFactory(new PropertyValueFactory<>("price_unit"));
//
//            parts_tableView.setItems(partList);
//            //closing statement once I am done with the query to avoid crashing!!
//            statement.close();
//            queryPartsOutput.close();
//
//        } catch(SQLException e) {
//            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, e);
//            e.printStackTrace();
//            e.getCause();
//        }
//        try {
//            Statement statement = connectDB.createStatement();
//            ResultSet queryProductsOutput = statement.executeQuery(productsViewQuery);
//
//            while (queryProductsOutput.next()) {
//                productList.add(new ProductData(queryProductsOutput.getInt("productID"),
//                        queryProductsOutput.getString("product_name"),
//                        queryProductsOutput.getInt("stock"),
//                        queryProductsOutput.getBigDecimal("price_unit")));
//            }
//
//            products_tableView_col_productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
//            products_tableView_col_productName.setCellValueFactory(new PropertyValueFactory<>("product_name"));
//            products_tableView_col_inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
//            products_tableView_col_priceUnit.setCellValueFactory(new PropertyValueFactory<>("price_unit"));
//
//            products_tableView.setItems(productList);
//
//        } catch (SQLException e) {
//            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, e);
//            e.printStackTrace();
//            e.getCause();
//        }

    }
}