package com.ifpb.ifpbtvapi.model.response;

import com.ifpb.ifpbtvapi.model.Usuario;

public class UsuarioResponse extends Response {	
	
	public Object getUsuario(Object usuario) {	
		return (Usuario) usuario;
	}
}
