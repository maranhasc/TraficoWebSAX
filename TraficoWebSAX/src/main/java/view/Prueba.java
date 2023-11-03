package view;

import java.util.ArrayList;

import manejador.ManejadorWeb;
import model.Item;

public class Prueba {

	public static void main(String[] args) {	
		ManejadorWeb manejador = new ManejadorWeb("https://infocar.dgt.es/etraffic/rss_ca_13.xml");
		ArrayList<Item> items = manejador.parsear();
		for (Item item:items) {
			System.out.println(item);
		}

	}

}
