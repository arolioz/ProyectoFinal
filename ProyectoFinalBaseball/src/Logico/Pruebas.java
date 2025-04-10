package Logico;

import java.util.ArrayList;

public class Pruebas {

	public static void main(String[] args) {
		Equipo equipo = new Equipo("1", "Lakers", "Santiago", "Contreras");
		Equipo equipo1 = new Equipo("2", "Chicago bull", "Santiago", "Contreras");
		Equipo equipo2 = new Equipo("3", "Golden state", "Santiago", "Contreras");
		Equipo equipo3 = new Equipo("4", "Santiago", "Santiago", "Contreras");

		ArrayList<Equipo> misEquipos = new ArrayList<>();
		misEquipos.add(equipo);
		misEquipos.add(equipo1);
		misEquipos.add(equipo2);
		misEquipos.add(equipo3);

		SerieNacional.getInstance().crearTorneo(misEquipos);
		for(int i = 0; i < SerieNacional.getInstance().getMisJuegos().size();i++) {
			System.out.println("Equipo Local:"+SerieNacional.getInstance().getMisJuegos().get(i).getEquipoLocal().getNombre() +" Equipo visitante:"+SerieNacional.getInstance().getMisJuegos().get(i).getEquipoVisitante().getNombre()+"\n");
		}

	}

}
