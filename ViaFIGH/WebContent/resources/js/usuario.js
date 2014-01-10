function addAnotherUser() {
	PF('confirmarInicioNovoCadastro').hide();
	PF('cadUsuario').show();
}

function iniciarPesquisa() {
	PF('pesquisaUsuario').show();
}

function showHideComboForn() {
	var x=document.getElementById("FormCadastroUsuarios:idPerfil_input").selectedIndex;
	
	if(x==2)
		$('#FormCadastroUsuarios:menuFornecedores_input').attr('disabled', false);
	else
		$('#FormCadastroUsuarios:menuFornecedores_input').attr('disabled', true);
	
}