package interfaceIDJR.regles;

import java.util.ArrayList;
import java.util.List;

import interfaceCommon.lang.LanguageManager;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


/** ListStyle class made by LLona Andree-Augustine **/

public class ListStyle extends VBox {
    
    private ArrayList<PuceList> pucelists;

    public ListStyle(List<String> list) {
        
        this.pucelists = new ArrayList<>(); 

        for (String s : list) {

            PuceList p =  new PuceList(s);
            this.pucelists.add(p); 
        }
        
        this.getChildren().addAll(pucelists);
    }
    
    public ListStyle(String puceSymbole, List<String> list) {

        this.pucelists = new ArrayList<>(); 
        
        for (String s : list) {
            
            PuceList p =  new PuceList(puceSymbole, s);
            this.pucelists.add(p); 
        }
        
        this.getChildren().addAll(pucelists);
    }
    
    
    public void changePuceSymbol(String puceSymbole) {
        for(PuceList pl : pucelists) {
            pl.setPuceSymbole(puceSymbole);
        }
    }
    
    //-----------
    

    public class PuceList extends HBox {
        
        private Label puceLabel;
        private Label s;

        public PuceList(String puceSymbole, String text) {
            
            puceLabel = new Label(puceSymbole);
            puceLabel.setPadding(new Insets(0, 20, 0, 20));
            puceLabel.setFont(Font.font(20));
            
            s = new Label(); 
            s.setFont(Font.font(20));
			s.setPrefWidth(900);
			s.setPadding(new Insets(0, 0, 20, 0));
			s.setWrapText(true);
            s.textProperty().bind(LanguageManager.createBinding(text));
            s.setWrapText(true);            

            this.getChildren().addAll(puceLabel, s);
        }
        

        public PuceList(String text) {
            this("-", text);
        }
        

        public void setPuceSymbole(String puceSymbole) {
            this.puceLabel.setText(puceSymbole);
        }
        
    }

}