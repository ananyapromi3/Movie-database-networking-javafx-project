package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TotalProfitDialogController {
    @FXML
    private Label productionCompanyName;
    @FXML
    private Label profit;
    @FXML
    private Button closeButton;

    public void init(String productionCompanyName, long totalProfit) {
        this.productionCompanyName.setText(productionCompanyName);
        profit.setText("Total Profit : " + totalProfit + "$");
    }

    @FXML
    void close(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }
}
