/**
 * 往form里面添加参数
 * @param form 指定的form
 * @param id 对象id，如果id已经存在那么直接修改id对应的值，否则往form里面添加一个hidden
 * @param name input 名字
 * @param value input vaue
 */
function addParameterToForm(form,id,name,value){
	if(id==null || $("#"+id).length == 0){
		form.append("<input type='hidden' id='"+id+"' name='"+name+"' value='"+value+"'>");
	}else{
		$("#"+id).val(value);
	}
}

