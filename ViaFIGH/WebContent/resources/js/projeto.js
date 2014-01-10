function addAnotherFornecedor() {
	PF('confirmarInicioNovoCadastro').hide();
	PF('cadProjeto').show();
}

function iniciarPesquisa() {
	PF('pesquisaProjeto').show();
}

function mascara(o,f){
    v_obj=o;
    v_fun=f;
    setTimeout("execmascara()",1);
}

function execmascara(){
    v_obj.value=v_fun(v_obj.value);
}

function data(v){
    v=v.replace(/\D/g,""); 
    v=v.replace(/(\d{2})(\d)/,"$1/$2"); 
    v=v.replace(/(\d{2})(\d)/,"$1/$2");
    return v;
}

/*function redefinirCombo() {
	$("input[id*='Date']").width(65);
}*/

function hideConfirmDelete() {
	PF('confirmarExclusao').hide();
}

function hideConfirmNovoCadastro() {
	PF('confirmarInicioNovoCadastro').hide();
}

function verifyValidate() {
	if(arguments.validationFailed==false) {
		PF('assForn').hide();
		PF('cadProjeto').show();
	}
}
