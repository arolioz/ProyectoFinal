package Logico;

import java.io.Serializable;
import java.util.ArrayList;

import Excepcion.UsuarioYaExisteException;

public class Control implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<User> misUsers;
	private static Control control;
	private static User loginUser;


	private Control() {
		misUsers = new ArrayList<>(); 
	}

	public static Control getInstance(){
		if(control == null){
			control = new Control();
		}
		return control;
	}

	public ArrayList<User> getMisUsers() {
		return misUsers;
	}

	public void setMisUsers(ArrayList<User> misUsers) {
		this.misUsers = misUsers;
	}

	public static Control getControl() {
		return control;
	}

	public static void setControl(Control control) {
		Control.control = control;
	}

	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		Control.loginUser = loginUser;
	}

	public void regUser(User user) throws UsuarioYaExisteException {

		for(User u : misUsers)
		{
			if(u.getUserName().equalsIgnoreCase(user.getUserName())) 
			{
				throw new UsuarioYaExisteException("Ya existe un usuario con ese nombre.");
			}
		}

		misUsers.add(user);

	}

	public boolean confirmLogin(String text, String text2) {
		boolean login = false;
		for (User user : misUsers) {
			if(user.getUserName().equals(text) && user.getPass().equals(text2)){
				loginUser = user;
				login = true;
			}
		}
		return login;
	}

	public User buscarUsuarioDadoNombre(String id){
		User user = null;
		for( User aux : misUsers) {
			if(aux.getUserName().equalsIgnoreCase(id)) {
				user = aux;
			}
		}
		return user;
	}

	public void eliminarUsuario(User user) {
		misUsers.remove(user);
	}



}
