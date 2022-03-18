package interfaceCommon.cd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * G�re l'affichage des menus, permet de changer de menu
 * @author IMH
 *
 * @param <E>
 */
public class CtrlView<E> {
	
	private Stage stage;
	private Scene scene;
	private List<View> viewstack;
	private View actualview;

	// private App principale = null;
	// private String jeu;
	private Map<E, View> listeEcrans = new HashMap<>();

	public CtrlView(Stage primaryStage) {
		this.stage = primaryStage;
		this.scene = stage.getScene();
		viewstack=new ArrayList<>();
	}


	/**
	 * Change le menu pour le menu correspondant � la cl�
	 * @param s la cl�
	 */
	public void goTo(E s) {
		if (listeEcrans.containsKey(s)) {
			goToView(listeEcrans.get(s));
		}
	}
	
	private void goToView(View newview) {
		if(actualview!=null) {
			actualview.close(newview);
			if(actualview!=newview) {
				if(viewstack.size()>0 && viewstack.get(viewstack.size()-1)==newview) viewstack.remove(viewstack.size()-1);
				else viewstack.add(actualview);
			}
		}
		newview.open(actualview);
		
		actualview=newview;
		scene.setRoot(newview);
	}
	
	/**
	 * Change le menu pour le menu pr�c�dent
	 */
	public void goBack() {
		if(viewstack.size()>0)goToView(viewstack.get(viewstack.size()-1));
	}
	
	/**
	 * Vide la pile de menu
	 * Le controleur de vue ne connait plus les menus pr�c�dent.
	 * Le comportement de goBack() est r�initialis�.
	 */
	public void clearViewStack() {
		viewstack.clear();
	}

	/**
	 * Associe un menu � une cl�
	 * @param s La cl�
	 * @param n Le menu
	 */
	public void saveScreen(E s, View n) {
		listeEcrans.put(s, n);
	}

}
