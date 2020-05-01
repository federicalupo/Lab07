package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Nerc> boxScelta; //di Nerc

    @FXML
    private TextField txtAnni;

    @FXML
    private TextField txtOre;

    @FXML
    private Button btnWca;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void calcola(ActionEvent event) {
    this.txtRisultato.clear();
    
     Nerc nerc	= this.boxScelta.getValue();
     Integer ore = Integer.parseInt(this.txtOre.getText());
     Integer anni = Integer.parseInt(this.txtAnni.getText());
     
     //controlli
     
     List<PowerOutage> lista = this.model.cerca(anni, ore, nerc);
     
     for(PowerOutage l : lista)
     	{ 
    	 this.txtRisultato.appendText(l.toString());
     	}
     

    }

    @FXML
    void initialize() {
        assert boxScelta != null : "fx:id=\"boxScelta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnni != null : "fx:id=\"txtAnni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOre != null : "fx:id=\"txtOre\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnWca != null : "fx:id=\"btnWca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model= model; 
    	this.boxScelta.getItems().addAll(model.getNercList());
    	this.boxScelta.setValue(model.getNercList().get(0)); //userà il toString, stampando il valore
    	
    	
}
}



