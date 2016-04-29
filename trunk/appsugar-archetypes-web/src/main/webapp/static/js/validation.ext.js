
/**
 * form验证
 */
$(document).ready(function(){
	$(".validator").validate({onfocusout: function(element) {$(element).valid();},focusCleanup: true});
});

jQuery.extend(jQuery.validator.defaults, {
    errorElement: "span",
	errorClass:"help-inline"
});

jQuery.validator.addMethod("mobile", function(value, element) {       
   	var length = value.length;   
   	var mobile =  /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;   
   	return this.optional(element) || (length == 11 && mobile.test(value));       
}, jQuery.validator.messages.mobile);  

jQuery.validator.addMethod("idcard", function(value, element) {   
    value = value.toUpperCase();
	if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(value))){
		return this.optional(element) || false;
	}
	var len, re;
	len = value.length;
   if (len == 15){
		re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
		var arrSplit = value.match(re);
		var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay){
			return this.optional(element) || false;
		}else{
			  var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
			  var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
			  var nTemp = 0, i;  
			  value = value.substr(0, 6) + '19' + value.substr(6, value.length - 6);
			  for(i = 0; i < 17; i ++){
					nTemp += value.substr(i, 1) * arrInt[i];
			  }
			   value += arrCh[nTemp % 11];  
			   return this.optional(element) || true;
		}  

   }
   if (len == 18){
		re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
		var arrSplit = value.match(re);
		var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay){
			return this.optional(element) || false;
	   }else{
			var valnum;
			var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
			var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
			var nTemp = 0, i;
			for(i = 0; i < 17; i ++){
				nTemp += value.substr(i, 1) * arrInt[i];
			}
			valnum = arrCh[nTemp % 11];
			if (valnum != value.substr(17, 1)){
			return this.optional(element) || false;
			}
			return this.optional(element) || true;
		}
	}		
	},jQuery.validator.messages.idcard); 