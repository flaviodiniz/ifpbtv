package com.ads.ifpbtv.model.response;

import com.ads.ifpbtv.model.Usuario;

public class UsuarioResponse extends Response {
	
	
	public Object getUsuario(Object usuario) {
		
		return (Usuario) usuario;
	}
}
